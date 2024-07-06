package org.example.rickandmortymobdevchallenge.infrastructure.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.rickandmortymobdevchallenge.application.usecase.CharacterReadService;
import org.example.rickandmortymobdevchallenge.domain.model.Character;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Tag(name = "Character Controller", description = "REST APIs related to Character Entity")
@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    private final CharacterReadService characterReadService;

    public CharacterController(CharacterReadService characterReadService) {
        this.characterReadService = characterReadService;
    }

    @Operation(summary = "Get character by ID", description = "Retrieve a character by its ID")
    @GetMapping("/{characterId}")
    public Mono<ResponseEntity<Character>> getCharacter(@PathVariable int characterId) {
        return characterReadService.getCharacter(characterId)
                .map(ResponseEntity::ok);
    }
}
