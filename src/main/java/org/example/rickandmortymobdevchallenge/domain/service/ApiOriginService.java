package org.example.rickandmortymobdevchallenge.domain.service;

import org.apache.logging.log4j.util.Strings;
import org.example.rickandmortymobdevchallenge.application.dto.OriginResponseDTO;
import org.example.rickandmortymobdevchallenge.application.usecase.OriginReadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ApiOriginService implements OriginReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OriginReadService.class);

    private final WebClient webClient;

    public ApiOriginService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public Mono<OriginResponseDTO> getOrigin(String originUrl) {

        if (originUrl == null || originUrl.isEmpty()) {
            OriginResponseDTO emptyOrigin = new OriginResponseDTO(Strings.EMPTY, List.of());
            LOGGER.warn("Origin URL is empty, returning default OriginResponseDTO with empty values.");
            return Mono.just(emptyOrigin);
        }

        return webClient.get()
                .uri(originUrl)
                .retrieve()
                .bodyToMono(OriginResponseDTO.class);
    }

}
