package org.example.rickandmortymobdevchallenge.infrastructure.controller;

import org.example.rickandmortymobdevchallenge.application.dto.CharacterDTO;
import org.example.rickandmortymobdevchallenge.domain.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping("/character/{character-id}")
    public ResponseEntity<CharacterDTO> getCharacterCustomResponse(@PathVariable("character-id") int characterId) {
        return ResponseEntity.ok(characterService.getCharacter(characterId).block());
    }
}
