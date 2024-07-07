package org.example.rickandmortymobdevchallenge.infrastructure.service;

import org.example.rickandmortymobdevchallenge.application.dto.CharacterResponseDTO;
import org.example.rickandmortymobdevchallenge.application.dto.OriginResponseDTO;
import org.example.rickandmortymobdevchallenge.infrastructure.http.HttpClient;
import org.example.rickandmortymobdevchallenge.utils.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApiOriginServiceTest {

    @Mock
    private HttpClient httpClient;

    @InjectMocks
    private ApiOriginService apiOriginService;

    @Test
    @DisplayName("Given a character with empty origin URL, when getOrigin is called, then an empty origin is returned")
    public void givenCharacterWithEmptyOriginUrl_whenGetOriginIsCalled_thenEmptyOriginIsReturned() {

        CharacterResponseDTO characterResponseDTO = TestUtils.createCharacterResponseDTOWithUnknownOrigin();

        StepVerifier.create(apiOriginService.getOrigin(characterResponseDTO))
                .expectNextMatches(origin -> origin.dimension().isEmpty() && origin.residents().isEmpty())
                .verifyComplete();
    }

    @Test
    @DisplayName("Given a valid character with origin URL, when getOrigin is called, then the origin is returned")
    public void givenValidCharacterWithOriginUrl_whenGetOriginIsCalled_thenOriginIsReturned() {

        CharacterResponseDTO characterResponseDTO = TestUtils.createCharacterResponseDTO();
        OriginResponseDTO originResponseDTO = TestUtils.createOriginResponseDTO();

        when(httpClient.get(anyString(), any())).thenReturn(Mono.just(originResponseDTO));

        StepVerifier.create(apiOriginService.getOrigin(characterResponseDTO))
                .expectNext(originResponseDTO)
                .verifyComplete();
    }

    @Test
    @DisplayName("Given a valid character with origin URL but origin service returns 404, then an error is returned")
    public void givenValidCharacterWithOriginUrlButOriginServiceReturns404_thenErrorIsReturned() {

        CharacterResponseDTO characterResponseDTO = TestUtils.createCharacterResponseDTO();

        when(httpClient.get(anyString(), any()))
                .thenReturn(Mono.error(WebClientResponseException.create(404, "Not Found", null, null, null)));

        StepVerifier.create(apiOriginService.getOrigin(characterResponseDTO))
                .expectError(WebClientResponseException.NotFound.class)
                .verify();
    }

    @Test
    @DisplayName("Given a valid character with origin URL but origin service returns 500, then an error is returned")
    public void givenValidCharacterWithOriginUrlButOriginServiceReturns500_thenErrorIsReturned() {

        CharacterResponseDTO characterResponseDTO = TestUtils.createCharacterResponseDTO();

        when(httpClient.get(anyString(), any()))
                .thenReturn(Mono.error(WebClientResponseException.create(500, "Internal Server Error", null, null, null)));

        StepVerifier.create(apiOriginService.getOrigin(characterResponseDTO))
                .expectError(WebClientResponseException.InternalServerError.class)
                .verify();
    }
}