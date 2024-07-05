package org.example.rickandmortymobdevchallenge.application.dto;

import java.util.List;

public record CharacterDTO(
        int id,
        String name,
        String status,
        String species,
        String type,
        String gender,
        OriginDTO origin,
        LocationDTO location,
        String image,
        List<String> episode,
        String url,
        String created) {}
