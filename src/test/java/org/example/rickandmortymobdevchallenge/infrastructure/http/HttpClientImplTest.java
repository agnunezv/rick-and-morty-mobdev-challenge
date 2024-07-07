package org.example.rickandmortymobdevchallenge.infrastructure.http;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.example.rickandmortymobdevchallenge.application.dto.OriginResponseDTO;
import org.example.rickandmortymobdevchallenge.utils.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

public class HttpClientImplTest {

    private MockWebServer mockWebServer;

    private HttpClientImpl httpClient;

    @BeforeEach
    public void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        String baseUrl = mockWebServer.url("/").toString();
        WebClient.Builder webClientBuilder = WebClient.builder();
        httpClient = new HttpClientImpl(webClientBuilder, baseUrl);
    }

    @AfterEach
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    @DisplayName("Given a valid request, when get is called, then a valid response is returned")
    public void givenValidRequest_whenGetIsCalled_thenAValidResponseIsReturned() {

        String responseBody = TestUtils.createOriginResponse();
        mockWebServer.enqueue(new MockResponse().setBody(responseBody).addHeader("Content-Type", "application/json"));

        Mono<OriginResponseDTO> responseMono = httpClient.get("/api/location/1", OriginResponseDTO.class);

        StepVerifier.create(responseMono)
                .expectNextMatches(response -> response.dimension().equals("Dimension C-137"))
                .verifyComplete();
    }

    @Test
    @DisplayName("Given a 404 response, when get is called, then a WebClientResponseException is thrown")
    public void given404Response_whenGetIsCalled_thenExceptionIsThrown() {

        mockWebServer.enqueue(new MockResponse().setResponseCode(404));

        Mono<OriginResponseDTO> responseMono = httpClient.get("/api/location/1", OriginResponseDTO.class);

        StepVerifier.create(responseMono)
                .expectErrorMatches(throwable -> throwable instanceof WebClientResponseException.NotFound)
                .verify();
    }

    @Test
    @DisplayName("Given a 500 response, when get is called, then a WebClientResponseException is thrown")
    public void given500Response_whenGetIsCalled_thenExceptionIsThrown() {

        mockWebServer.enqueue(new MockResponse().setResponseCode(500));

        Mono<OriginResponseDTO> responseMono = httpClient.get("/api/location/1", OriginResponseDTO.class);

        StepVerifier.create(responseMono)
                .expectErrorMatches(throwable -> throwable instanceof WebClientResponseException.InternalServerError)
                .verify();
    }
}