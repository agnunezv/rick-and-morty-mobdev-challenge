package org.example.rickandmortymobdevchallenge.domain.service;

import org.example.rickandmortymobdevchallenge.application.dto.OriginResponseDTO;
import org.example.rickandmortymobdevchallenge.application.usecase.OriginReadService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiOriginService implements OriginReadService {

    private final WebClient webClient;

    public ApiOriginService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public Mono<OriginResponseDTO> getOrigin(String originUrl) {
        return webClient.get()
                .uri(originUrl)
                .retrieve()
                .bodyToMono(OriginResponseDTO.class);
    }

}
