package org.example.rickandmortymobdevchallenge.infrastructure.service;

import org.example.rickandmortymobdevchallenge.application.dto.CharacterResponseDTO;
import org.example.rickandmortymobdevchallenge.application.dto.OriginResponseDTO;
import org.example.rickandmortymobdevchallenge.application.service.OriginReadService;
import org.example.rickandmortymobdevchallenge.domain.model.Character;
import org.example.rickandmortymobdevchallenge.infrastructure.http.HttpClient;
import org.example.rickandmortymobdevchallenge.infrastructure.mapper.CharacterDTOToModelMapper;
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

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApiCharacterServiceTest {

    @Mock
    private OriginReadService originReadService;

    @Mock
    private CharacterDTOToModelMapper characterDTOToModelMapper;

    @Mock
    private HttpClient httpClient;

    @InjectMocks
    private ApiCharacterService apiCharacterService;

    @Test
    @DisplayName("Given a valid character ID, when getCharacter is called, then the character is returned")
    public void givenValidCharacterId_whenGetCharacterIsCalled_thenCharacterIsReturned() {

        CharacterResponseDTO characterResponseDTO = TestUtils.createCharacterResponseDTO();
        OriginResponseDTO originResponseDTO = TestUtils.createOriginResponseDTO();
        Character character = TestUtils.createCharacter();

        when(httpClient.get("/1", CharacterResponseDTO.class)).thenReturn(Mono.just(characterResponseDTO));
        when(originReadService.getOrigin(characterResponseDTO)).thenReturn(Mono.just(originResponseDTO));
        when(characterDTOToModelMapper.apply(characterResponseDTO, originResponseDTO)).thenReturn(character);

        StepVerifier.create(apiCharacterService.getCharacter(1))
                .expectNext(character)
                .verifyComplete();
    }

    @Test
    @DisplayName("Given a non-existing character ID, when getCharacter is called, then a 404 error is returned")
    public void givenNonExistingCharacterId_whenGetCharacterIsCalled_then404ErrorIsReturned() {

        when(httpClient.get("/0", CharacterResponseDTO.class))
                .thenReturn(Mono.error(WebClientResponseException.create(404, "Not Found", null, null, null)));

        StepVerifier.create(apiCharacterService.getCharacter(0))
                .expectError(WebClientResponseException.NotFound.class)
                .verify();
    }

    @Test
    @DisplayName("Given a server error, when getCharacter is called, then a 500 error is returned")
    public void givenServerError_whenGetCharacterIsCalled_then500ErrorIsReturned() {

        when(httpClient.get("/1", CharacterResponseDTO.class))
                .thenReturn(Mono.error(WebClientResponseException.create(500, "Internal Server Error", null, null, null)));

        StepVerifier.create(apiCharacterService.getCharacter(1))
                .expectError(WebClientResponseException.InternalServerError.class)
                .verify();
    }

    @Test
    @DisplayName("Given a valid character ID but origin service returns 404, then origin is mapped to null")
    public void givenValidCharacterIdButOriginServiceReturns404_thenOriginIsMappedToNull() {

        CharacterResponseDTO characterResponseDTO = TestUtils.createCharacterResponseDTO();
        Character characterWithoutOrigin = TestUtils.createCharacterWithoutDimensionAndResidents();

        when(httpClient.get("/1", CharacterResponseDTO.class)).thenReturn(Mono.just(characterResponseDTO));
        when(originReadService.getOrigin(characterResponseDTO))
                .thenReturn(Mono.error(WebClientResponseException.create(404, "Not Found", null, null, null)));
        when(characterDTOToModelMapper.apply(characterResponseDTO, null)).thenReturn(characterWithoutOrigin);

        StepVerifier.create(apiCharacterService.getCharacter(1))
                .expectNext(characterWithoutOrigin)
                .verifyComplete();
    }

    @Test
    @DisplayName("Given a valid character ID but origin service returns 500, then origin is mapped to null")
    public void givenValidCharacterIdButOriginServiceReturns500_thenOriginIsMappedToNull() {

        CharacterResponseDTO characterResponseDTO = TestUtils.createCharacterResponseDTO();
        Character characterWithoutOrigin = TestUtils.createCharacterWithoutDimensionAndResidents();

        when(httpClient.get("/1", CharacterResponseDTO.class)).thenReturn(Mono.just(characterResponseDTO));
        when(originReadService.getOrigin(characterResponseDTO))
                .thenReturn(Mono.error(WebClientResponseException.create(500, "Internal Server Error", null, null, null)));
        when(characterDTOToModelMapper.apply(characterResponseDTO, null)).thenReturn(characterWithoutOrigin);

        StepVerifier.create(apiCharacterService.getCharacter(1))
                .expectNext(characterWithoutOrigin)
                .verifyComplete();
    }
}