package org.example.rickandmortymobdevchallenge.application.dto;

import java.util.List;

public record OriginResponseDTO(

        String dimension,

        List<String> residents) {
}
