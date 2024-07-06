package org.example.rickandmortymobdevchallenge.domain.service;

import org.example.rickandmortymobdevchallenge.application.dto.CharacterResponseDTO;
import org.example.rickandmortymobdevchallenge.application.dto.OriginResponseDTO;
import org.example.rickandmortymobdevchallenge.application.usecase.CharacterReadService;
import org.example.rickandmortymobdevchallenge.domain.model.Character;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiCharacterService implements CharacterReadService {

    private final WebClient webClient;

    public ApiCharacterService(WebClient.Builder webClientBuilder, @Value("${character.api.base-url}") String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    @Override
    public Mono<Character> getCharacter(int characterId) {
        return webClient.get()
                .uri("/" + characterId)
                .retrieve()
                .bodyToMono(CharacterResponseDTO.class)
                .flatMap(this::mapToCharacter)
                .onErrorResume(e -> {
                    System.err.println("Error fetching character from API: " + e.getMessage());
                    return Mono.error(new RuntimeException("Failed to fetch character from API", e));
                });
    }

    private Mono<Character> mapToCharacter(CharacterResponseDTO characterResponseDTO) {
        return webClient.get()
                .uri(characterResponseDTO.origin().url())
                .retrieve()
                .bodyToMono(OriginResponseDTO.class)
                .map(originDetails -> {
                    Character character = new Character();
                    character.setId(characterResponseDTO.id());
                    character.setName(characterResponseDTO.name());
                    character.setStatus(characterResponseDTO.status());
                    character.setSpecies(characterResponseDTO.species());
                    character.setType(characterResponseDTO.type());
                    character.setEpisodeCount(characterResponseDTO.episode().size());

                    Character.Origin origin = new Character.Origin();
                    origin.setName(characterResponseDTO.origin().name());
                    origin.setUrl(characterResponseDTO.origin().url());
                    origin.setDimension(originDetails.dimension());
                    origin.setResidents(originDetails.residents());

                    character.setOrigin(origin);
                    return character;
                });
    }
}
