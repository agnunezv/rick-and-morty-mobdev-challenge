package org.example.rickandmortymobdevchallenge.application.service;

import org.example.rickandmortymobdevchallenge.application.dto.CharacterResponseDTO;
import org.example.rickandmortymobdevchallenge.application.dto.OriginResponseDTO;
import reactor.core.publisher.Mono;

public interface OriginReadService {

    Mono<OriginResponseDTO> getOrigin(CharacterResponseDTO character);

}
