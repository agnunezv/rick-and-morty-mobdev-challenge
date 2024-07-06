package org.example.rickandmortymobdevchallenge.application.usecase;

import org.example.rickandmortymobdevchallenge.application.dto.OriginResponseDTO;
import reactor.core.publisher.Mono;

public interface OriginReadService {

    Mono<OriginResponseDTO> getOrigin(String originUrl);

}
