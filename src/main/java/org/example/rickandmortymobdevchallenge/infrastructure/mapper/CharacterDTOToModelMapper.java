package org.example.rickandmortymobdevchallenge.infrastructure.mapper;


import org.example.rickandmortymobdevchallenge.application.dto.CharacterResponseDTO;
import org.example.rickandmortymobdevchallenge.application.dto.OriginResponseDTO;
import org.example.rickandmortymobdevchallenge.domain.model.Character;

import java.util.function.BiFunction;

public class CharacterDTOToModelMapper implements BiFunction<CharacterResponseDTO, OriginResponseDTO, Character> {

    @Override
    public Character apply(CharacterResponseDTO characterResponseDTO, OriginResponseDTO originResponseDTO) {
        Character character = new Character();
        character.setId(characterResponseDTO.id());
        character.setName(characterResponseDTO.name());
        character.setStatus(characterResponseDTO.status());
        character.setSpecies(characterResponseDTO.species());
        character.setType(characterResponseDTO.type());
        character.setEpisodeCount(characterResponseDTO.episode().size());

        Character.Origin origin = new Character.Origin();
        origin.setName(characterResponseDTO.origin().name());
        origin.setUrl(characterResponseDTO.origin().url());
        origin.setDimension(originResponseDTO.dimension());
        origin.setResidents(originResponseDTO.residents());

        character.setOrigin(origin);
        return character;
    }
}
