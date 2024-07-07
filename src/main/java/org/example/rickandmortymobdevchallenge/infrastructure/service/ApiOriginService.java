package org.example.rickandmortymobdevchallenge.infrastructure.service;

import org.apache.logging.log4j.util.Strings;
import org.example.rickandmortymobdevchallenge.application.dto.CharacterResponseDTO;
import org.example.rickandmortymobdevchallenge.application.dto.OriginResponseDTO;
import org.example.rickandmortymobdevchallenge.application.service.OriginReadService;
import org.example.rickandmortymobdevchallenge.infrastructure.http.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ApiOriginService implements OriginReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiOriginService.class);

    private final HttpClient httpClient;

    public ApiOriginService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public Mono<OriginResponseDTO> getOrigin(CharacterResponseDTO character) {

        if (character.origin().url() == null || character.origin().url().isEmpty()) {
            LOGGER.warn("Origin URL is empty for character id: {}", character.id());
            return Mono.just(new OriginResponseDTO(Strings.EMPTY, List.of()));
        }

        return httpClient.get(character.origin().url(), OriginResponseDTO.class);
    }
}
