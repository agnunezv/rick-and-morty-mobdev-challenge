package org.example.rickandmortymobdevchallenge.domain.service;

import org.example.rickandmortymobdevchallenge.application.dto.CharacterDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CharacterService {

    private final WebClient webClient;

    public CharacterService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://rickandmortyapi.com/api/character/").build();
    }

    public Mono<CharacterDTO> getCharacter(int characterId) {
        return webClient.get()
                .uri("/" + characterId)
                .retrieve()
                .bodyToMono(CharacterDTO.class);
    }
}
