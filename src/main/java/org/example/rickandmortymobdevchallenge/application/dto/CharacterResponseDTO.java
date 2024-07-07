package org.example.rickandmortymobdevchallenge.application.dto;

import java.util.List;

public record CharacterResponseDTO(

        int id,

        String name,

        String status,

        String species,

        String type,

        List<String> episode,

        OriginDTO origin) {

    public record OriginDTO(

            String name,

            String url) {
    }
}
