package org.example.rickandmortymobdevchallenge.infrastructure.controller;

import org.example.rickandmortymobdevchallenge.application.service.CharacterReadService;
import org.example.rickandmortymobdevchallenge.domain.exception.GlobalExceptionHandler;
import org.example.rickandmortymobdevchallenge.domain.model.Character;
import org.example.rickandmortymobdevchallenge.domain.model.ErrorMessage;
import org.example.rickandmortymobdevchallenge.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CharacterControllerTest {

    @Mock
    private CharacterReadService characterReadService;

    @InjectMocks
    private CharacterController characterController;

    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp() {
        webTestClient = WebTestClient.bindToController(characterController)
                .controllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @DisplayName("Given an existing Character, when calling Character read endpoint, then return requested Character")
    @Test
    public void givenAnExistingCharacter_whenCallingCharacterReadEndpoint_thenReturnRequestedCharacter() {
        Character character = TestUtils.createCharacter();

        when(characterReadService.getCharacter(1)).thenReturn(Mono.just(character));

        webTestClient.get().uri("/api/characters/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Character.class)
                .value(
                        responseCharacter -> assertThat(responseCharacter)
                                .usingRecursiveComparison()
                                .isEqualTo(character)
                );
    }

    @DisplayName("Given a non-existing Character, when calling Character read endpoint, then 404 status code is returned")
    @Test
    public void givenNonExistingCharacter_whenCallingCharacterReadEndpoint_then404StatusCodeIsReturned() {

        WebClientResponseException notFoundException = TestUtils.createNotFoundException();

        when(characterReadService.getCharacter(0)).thenReturn(Mono.error(notFoundException));

        webTestClient.get().uri("/api/characters/0")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorMessage.class)
                .value(
                        errorMessage -> assertThat(errorMessage)
                                .extracting(ErrorMessage::getStatus)
                                .isEqualTo(HttpStatus.NOT_FOUND.toString())
                );
    }

    @DisplayName("Given an internal server error, when calling Character read endpoint, then 500 status code is returned")
    @Test
    public void givenAnInternalServerError_whenCallingCharacterReadEndpoint_then500StatusCodeIsReturned() {

        WebClientResponseException internalServerErrorException = TestUtils.createInternalServerErrorException();

        when(characterReadService.getCharacter(1)).thenReturn(Mono.error(internalServerErrorException));

        webTestClient.get().uri("/api/characters/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody(ErrorMessage.class)
                .value(
                        errorMessage -> assertThat(errorMessage)
                                .extracting(ErrorMessage::getStatus)
                                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                );
    }
}