package org.example.rickandmortymobdevchallenge.utils;

import org.apache.logging.log4j.util.Strings;
import org.example.rickandmortymobdevchallenge.application.dto.CharacterResponseDTO;
import org.example.rickandmortymobdevchallenge.application.dto.OriginResponseDTO;
import org.example.rickandmortymobdevchallenge.domain.model.Character;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class TestUtils {

    public static Character createCharacter() {
        return new Character(
                1,
                "Rick Sanchez",
                "Alive",
                "Human",
                Strings.EMPTY,
                51,
                new Character.Origin(
                        "Earth (C-137)",
                        "https://rickandmortyapi.com/api/location/1",
                        "Dimension C-137",
                        List.of("https://rickandmortyapi.com/api/character/38",
                                "https://rickandmortyapi.com/api/character/45",
                                "https://rickandmortyapi.com/api/character/71",
                                "https://rickandmortyapi.com/api/character/82",
                                "https://rickandmortyapi.com/api/character/83",
                                "https://rickandmortyapi.com/api/character/92",
                                "https://rickandmortyapi.com/api/character/112",
                                "https://rickandmortyapi.com/api/character/114",
                                "https://rickandmortyapi.com/api/character/116",
                                "https://rickandmortyapi.com/api/character/117",
                                "https://rickandmortyapi.com/api/character/120",
                                "https://rickandmortyapi.com/api/character/127",
                                "https://rickandmortyapi.com/api/character/155",
                                "https://rickandmortyapi.com/api/character/169",
                                "https://rickandmortyapi.com/api/character/175",
                                "https://rickandmortyapi.com/api/character/179",
                                "https://rickandmortyapi.com/api/character/186",
                                "https://rickandmortyapi.com/api/character/201",
                                "https://rickandmortyapi.com/api/character/216",
                                "https://rickandmortyapi.com/api/character/239",
                                "https://rickandmortyapi.com/api/character/271",
                                "https://rickandmortyapi.com/api/character/302",
                                "https://rickandmortyapi.com/api/character/303",
                                "https://rickandmortyapi.com/api/character/338",
                                "https://rickandmortyapi.com/api/character/343",
                                "https://rickandmortyapi.com/api/character/356",
                                "https://rickandmortyapi.com/api/character/394")
                )
        );
    }

    public static Character createCharacterWithoutDimensionAndResidents() {
        return new Character(
                1,
                "Rick Sanchez",
                "Alive",
                "Human",
                Strings.EMPTY,
                51,
                new Character.Origin(
                        "Earth (C-137)",
                        "https://rickandmortyapi.com/api/location/1",
                        Strings.EMPTY,
                        List.of()
                )
        );
    }

    public static CharacterResponseDTO createCharacterResponseDTO() {
        return new CharacterResponseDTO(
                1,
                "Rick Sanchez",
                "Alive",
                "Human",
                Strings.EMPTY,
                List.of("https://rickandmortyapi.com/api/episode/1",
                        "https://rickandmortyapi.com/api/episode/2",
                        "https://rickandmortyapi.com/api/episode/3",
                        "https://rickandmortyapi.com/api/episode/4",
                        "https://rickandmortyapi.com/api/episode/5",
                        "https://rickandmortyapi.com/api/episode/6",
                        "https://rickandmortyapi.com/api/episode/7",
                        "https://rickandmortyapi.com/api/episode/8",
                        "https://rickandmortyapi.com/api/episode/9",
                        "https://rickandmortyapi.com/api/episode/10",
                        "https://rickandmortyapi.com/api/episode/11",
                        "https://rickandmortyapi.com/api/episode/12",
                        "https://rickandmortyapi.com/api/episode/13",
                        "https://rickandmortyapi.com/api/episode/14",
                        "https://rickandmortyapi.com/api/episode/15",
                        "https://rickandmortyapi.com/api/episode/16",
                        "https://rickandmortyapi.com/api/episode/17",
                        "https://rickandmortyapi.com/api/episode/18",
                        "https://rickandmortyapi.com/api/episode/19",
                        "https://rickandmortyapi.com/api/episode/20",
                        "https://rickandmortyapi.com/api/episode/21",
                        "https://rickandmortyapi.com/api/episode/22",
                        "https://rickandmortyapi.com/api/episode/23",
                        "https://rickandmortyapi.com/api/episode/24",
                        "https://rickandmortyapi.com/api/episode/25",
                        "https://rickandmortyapi.com/api/episode/26",
                        "https://rickandmortyapi.com/api/episode/27",
                        "https://rickandmortyapi.com/api/episode/28",
                        "https://rickandmortyapi.com/api/episode/29",
                        "https://rickandmortyapi.com/api/episode/30",
                        "https://rickandmortyapi.com/api/episode/31",
                        "https://rickandmortyapi.com/api/episode/32",
                        "https://rickandmortyapi.com/api/episode/33",
                        "https://rickandmortyapi.com/api/episode/34",
                        "https://rickandmortyapi.com/api/episode/35",
                        "https://rickandmortyapi.com/api/episode/36",
                        "https://rickandmortyapi.com/api/episode/37",
                        "https://rickandmortyapi.com/api/episode/38",
                        "https://rickandmortyapi.com/api/episode/39",
                        "https://rickandmortyapi.com/api/episode/40",
                        "https://rickandmortyapi.com/api/episode/41",
                        "https://rickandmortyapi.com/api/episode/42",
                        "https://rickandmortyapi.com/api/episode/43",
                        "https://rickandmortyapi.com/api/episode/44",
                        "https://rickandmortyapi.com/api/episode/45",
                        "https://rickandmortyapi.com/api/episode/46",
                        "https://rickandmortyapi.com/api/episode/47",
                        "https://rickandmortyapi.com/api/episode/48",
                        "https://rickandmortyapi.com/api/episode/49",
                        "https://rickandmortyapi.com/api/episode/50",
                        "https://rickandmortyapi.com/api/episode/51"),
                new CharacterResponseDTO.OriginDTO("Earth (C-137)", "https://rickandmortyapi.com/api/location/1")
        );
    }

    public static CharacterResponseDTO createCharacterResponseDTOWithUnknownOrigin() {
        return new CharacterResponseDTO(
                1,
                "Rick Sanchez",
                "Alive",
                "Human",
                Strings.EMPTY,
                List.of("https://rickandmortyapi.com/api/episode/1",
                        "https://rickandmortyapi.com/api/episode/2",
                        "https://rickandmortyapi.com/api/episode/3",
                        "https://rickandmortyapi.com/api/episode/4",
                        "https://rickandmortyapi.com/api/episode/5",
                        "https://rickandmortyapi.com/api/episode/6",
                        "https://rickandmortyapi.com/api/episode/7",
                        "https://rickandmortyapi.com/api/episode/8",
                        "https://rickandmortyapi.com/api/episode/9",
                        "https://rickandmortyapi.com/api/episode/10",
                        "https://rickandmortyapi.com/api/episode/11",
                        "https://rickandmortyapi.com/api/episode/12",
                        "https://rickandmortyapi.com/api/episode/13",
                        "https://rickandmortyapi.com/api/episode/14",
                        "https://rickandmortyapi.com/api/episode/15",
                        "https://rickandmortyapi.com/api/episode/16",
                        "https://rickandmortyapi.com/api/episode/17",
                        "https://rickandmortyapi.com/api/episode/18",
                        "https://rickandmortyapi.com/api/episode/19",
                        "https://rickandmortyapi.com/api/episode/20",
                        "https://rickandmortyapi.com/api/episode/21",
                        "https://rickandmortyapi.com/api/episode/22",
                        "https://rickandmortyapi.com/api/episode/23",
                        "https://rickandmortyapi.com/api/episode/24",
                        "https://rickandmortyapi.com/api/episode/25",
                        "https://rickandmortyapi.com/api/episode/26",
                        "https://rickandmortyapi.com/api/episode/27",
                        "https://rickandmortyapi.com/api/episode/28",
                        "https://rickandmortyapi.com/api/episode/29",
                        "https://rickandmortyapi.com/api/episode/30",
                        "https://rickandmortyapi.com/api/episode/31",
                        "https://rickandmortyapi.com/api/episode/32",
                        "https://rickandmortyapi.com/api/episode/33",
                        "https://rickandmortyapi.com/api/episode/34",
                        "https://rickandmortyapi.com/api/episode/35",
                        "https://rickandmortyapi.com/api/episode/36",
                        "https://rickandmortyapi.com/api/episode/37",
                        "https://rickandmortyapi.com/api/episode/38",
                        "https://rickandmortyapi.com/api/episode/39",
                        "https://rickandmortyapi.com/api/episode/40",
                        "https://rickandmortyapi.com/api/episode/41",
                        "https://rickandmortyapi.com/api/episode/42",
                        "https://rickandmortyapi.com/api/episode/43",
                        "https://rickandmortyapi.com/api/episode/44",
                        "https://rickandmortyapi.com/api/episode/45",
                        "https://rickandmortyapi.com/api/episode/46",
                        "https://rickandmortyapi.com/api/episode/47",
                        "https://rickandmortyapi.com/api/episode/48",
                        "https://rickandmortyapi.com/api/episode/49",
                        "https://rickandmortyapi.com/api/episode/50",
                        "https://rickandmortyapi.com/api/episode/51"),
                new CharacterResponseDTO.OriginDTO("unknown", Strings.EMPTY)
        );
    }

    public static OriginResponseDTO createOriginResponseDTO() {
        return new OriginResponseDTO(
                "Dimension C-137",
                List.of("https://rickandmortyapi.com/api/character/38",
                        "https://rickandmortyapi.com/api/character/45",
                        "https://rickandmortyapi.com/api/character/71",
                        "https://rickandmortyapi.com/api/character/82",
                        "https://rickandmortyapi.com/api/character/83",
                        "https://rickandmortyapi.com/api/character/92",
                        "https://rickandmortyapi.com/api/character/112",
                        "https://rickandmortyapi.com/api/character/114",
                        "https://rickandmortyapi.com/api/character/116",
                        "https://rickandmortyapi.com/api/character/117",
                        "https://rickandmortyapi.com/api/character/120",
                        "https://rickandmortyapi.com/api/character/127",
                        "https://rickandmortyapi.com/api/character/155",
                        "https://rickandmortyapi.com/api/character/169",
                        "https://rickandmortyapi.com/api/character/175",
                        "https://rickandmortyapi.com/api/character/179",
                        "https://rickandmortyapi.com/api/character/186",
                        "https://rickandmortyapi.com/api/character/201",
                        "https://rickandmortyapi.com/api/character/216",
                        "https://rickandmortyapi.com/api/character/239",
                        "https://rickandmortyapi.com/api/character/271",
                        "https://rickandmortyapi.com/api/character/302",
                        "https://rickandmortyapi.com/api/character/303",
                        "https://rickandmortyapi.com/api/character/338",
                        "https://rickandmortyapi.com/api/character/343",
                        "https://rickandmortyapi.com/api/character/356",
                        "https://rickandmortyapi.com/api/character/394")
        );
    }

    public static WebClientResponseException createNotFoundException() {
        return WebClientResponseException.create(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                new HttpHeaders(),
                null,
                StandardCharsets.UTF_8
        );
    }

    public static WebClientResponseException createInternalServerErrorException() {
        return WebClientResponseException.create(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                new HttpHeaders(),
                null,
                StandardCharsets.UTF_8
        );
    }

    public static String createOriginResponse() {
        return "{\n" +
                "  \"dimension\": \"Dimension C-137\",\n" +
                "  \"residents\": [\n" +
                "    \"https://rickandmortyapi.com/api/character/38\",\n" +
                "    \"https://rickandmortyapi.com/api/character/45\",\n" +
                "    \"https://rickandmortyapi.com/api/character/71\",\n" +
                "    \"https://rickandmortyapi.com/api/character/82\",\n" +
                "    \"https://rickandmortyapi.com/api/character/83\",\n" +
                "    \"https://rickandmortyapi.com/api/character/92\",\n" +
                "    \"https://rickandmortyapi.com/api/character/112\",\n" +
                "    \"https://rickandmortyapi.com/api/character/114\",\n" +
                "    \"https://rickandmortyapi.com/api/character/116\",\n" +
                "    \"https://rickandmortyapi.com/api/character/117\",\n" +
                "    \"https://rickandmortyapi.com/api/character/120\",\n" +
                "    \"https://rickandmortyapi.com/api/character/127\",\n" +
                "    \"https://rickandmortyapi.com/api/character/155\",\n" +
                "    \"https://rickandmortyapi.com/api/character/169\",\n" +
                "    \"https://rickandmortyapi.com/api/character/175\",\n" +
                "    \"https://rickandmortyapi.com/api/character/179\",\n" +
                "    \"https://rickandmortyapi.com/api/character/186\",\n" +
                "    \"https://rickandmortyapi.com/api/character/201\",\n" +
                "    \"https://rickandmortyapi.com/api/character/216\",\n" +
                "    \"https://rickandmortyapi.com/api/character/239\",\n" +
                "    \"https://rickandmortyapi.com/api/character/271\",\n" +
                "    \"https://rickandmortyapi.com/api/character/302\",\n" +
                "    \"https://rickandmortyapi.com/api/character/303\",\n" +
                "    \"https://rickandmortyapi.com/api/character/338\",\n" +
                "    \"https://rickandmortyapi.com/api/character/343\",\n" +
                "    \"https://rickandmortyapi.com/api/character/356\",\n" +
                "    \"https://rickandmortyapi.com/api/character/394\"\n" +
                "  ]\n" +
                "}";
    }
}
