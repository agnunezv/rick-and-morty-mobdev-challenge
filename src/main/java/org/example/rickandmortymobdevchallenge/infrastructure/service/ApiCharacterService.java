package org.example.rickandmortymobdevchallenge.infrastructure.service;

import org.example.rickandmortymobdevchallenge.application.dto.CharacterResponseDTO;
import org.example.rickandmortymobdevchallenge.application.service.CharacterReadService;
import org.example.rickandmortymobdevchallenge.application.service.OriginReadService;
import org.example.rickandmortymobdevchallenge.domain.model.Character;
import org.example.rickandmortymobdevchallenge.infrastructure.http.HttpClient;
import org.example.rickandmortymobdevchallenge.infrastructure.mapper.CharacterDTOToModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ApiCharacterService implements CharacterReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiOriginService.class);

    private final CharacterDTOToModelMapper characterDTOToModelMapper;

    private final HttpClient httpClient;

    private final OriginReadService originReadService;

    public ApiCharacterService(OriginReadService originReadService,
                               CharacterDTOToModelMapper characterDTOToModelMapper,
                               HttpClient httpClient) {
        this.originReadService = originReadService;
        this.characterDTOToModelMapper = characterDTOToModelMapper;
        this.httpClient = httpClient;
    }

    @Override
    public Mono<Character> getCharacter(int characterId) {
        return httpClient.get("/" + characterId, CharacterResponseDTO.class)
                .flatMap(characterDTO ->
                        originReadService.getOrigin(characterDTO)
                                .map(originDetails -> characterDTOToModelMapper.apply(characterDTO, originDetails))
                                .onErrorResume(e -> {
                                    LOGGER.error("Error fetching origin: {} with error: {}", characterDTO.origin().name(), e.getMessage());
                                    return Mono.just(characterDTOToModelMapper.apply(characterDTO, null));
                                })
                );
    }
}
