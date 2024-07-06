package org.example.rickandmortymobdevchallenge.domain.service;

import org.example.rickandmortymobdevchallenge.application.dto.CharacterResponseDTO;
import org.example.rickandmortymobdevchallenge.application.dto.OriginResponseDTO;
import org.example.rickandmortymobdevchallenge.application.usecase.CharacterReadService;
import org.example.rickandmortymobdevchallenge.application.usecase.OriginReadService;
import org.example.rickandmortymobdevchallenge.domain.model.Character;
import org.example.rickandmortymobdevchallenge.infrastructure.mapper.CharacterDTOToModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiCharacterService implements CharacterReadService {

    private final OriginReadService originReadService;

    private final CharacterDTOToModelMapper characterDTOToModelMapper;

    private final WebClient webClient;

    public ApiCharacterService(
            OriginReadService originReadService,
            CharacterDTOToModelMapper characterDTOToModelMapper,
            WebClient.Builder webClientBuilder, @Value("${character.api.base-url}") String baseUrl) {
        this.originReadService = originReadService;
        this.characterDTOToModelMapper = characterDTOToModelMapper;
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    @Override
    public Mono<Character> getCharacter(int characterId) {
        return webClient.get()
                .uri("/" + characterId)
                .retrieve()
                .bodyToMono(CharacterResponseDTO.class)
                .flatMap(characterDTO ->
                        originReadService.getOrigin(characterDTO.origin().url())
                                .map(originDetails -> characterDTOToModelMapper.apply(characterDTO, originDetails))
                );
    }
}
