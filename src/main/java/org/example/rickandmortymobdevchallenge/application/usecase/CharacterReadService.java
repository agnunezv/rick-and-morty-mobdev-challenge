package org.example.rickandmortymobdevchallenge.application.usecase;

import org.example.rickandmortymobdevchallenge.domain.model.Character;
import reactor.core.publisher.Mono;

public interface CharacterReadService {

    Mono<Character> getCharacter(int characterId);

}
