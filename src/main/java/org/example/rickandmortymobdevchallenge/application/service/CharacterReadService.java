package org.example.rickandmortymobdevchallenge.application.service;

import org.example.rickandmortymobdevchallenge.domain.model.Character;
import reactor.core.publisher.Mono;

public interface CharacterReadService {

    Mono<Character> getCharacter(int characterId);

}
