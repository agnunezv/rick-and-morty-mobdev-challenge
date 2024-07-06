package org.example.rickandmortymobdevchallenge.infrastructure.controller;

import org.example.rickandmortymobdevchallenge.application.usecase.CharacterReadService;
import org.example.rickandmortymobdevchallenge.domain.model.Character;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CharacterController {

    private final CharacterReadService characterReadService;

    public CharacterController(CharacterReadService characterReadService) {
        this.characterReadService = characterReadService;
    }

    @GetMapping("/character/{characterId}")
    public Mono<ResponseEntity<Character>> getCharacter(@PathVariable int characterId) {
        return characterReadService.getCharacter(characterId)
                .map(ResponseEntity::ok);
    }
}
