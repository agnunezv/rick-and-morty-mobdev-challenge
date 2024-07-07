package org.example.rickandmortymobdevchallenge.infrastructure.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class HttpClientImpl implements HttpClient {

    private final WebClient webClient;

    public HttpClientImpl(WebClient.Builder webClientBuilder, @Value("${character.api.base-url}") String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    @Override
    public <T> Mono<T> get(String uri, Class<T> responseType) {
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(responseType);
    }
}
