# Rick and Morty API Integration

## Description

This application is a service for integrating with the Rick and Morty API. It provides endpoints to fetch information about characters, including their origins and other related details.

## Project Structure

The application follows a clean and modular architecture, organized into the following packages:

- **application**
    - `dto` - Data Transfer Objects.
    - `service` - Core business logic.
- **domain**
    - `model` - Domain models.
    - `exception` - Domain-specific exceptions.
- **infrastructure**
    - `http` - HTTP client implementations.
    - `config` - Application configuration.
    - `controller` - API controllers.
    - `mapper` - Mappers.
- **RickAndMortyMobdevChallengeApplication.java** - Main application class.

## Prerequisites

- Java 17
- Spring Boot 3.x
- Gradle 8.x

## Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/rick-and-morty-api-integration.git
   cd rick-and-morty-api-integration

2. Build and run the application:
   ```sh
   ./gradlew build
   ./gradlew bootRun

## Endpoints

### Get Character Information

- **URL**: `/api/characters/{characterId}`
- **Method**: `GET`
- **Description**: Fetches detailed information about a character by ID.
- **Response**: `200 OK` with a JSON body containing the character details.

### Swagger API Documentation

You can access the Swagger UI for detailed API documentation at:
- **URL**: `http://localhost:8080/swagger-ui.html`

### Request and Response Example

#### Request

```sh
curl -X GET "http://localhost:8080/api/characters/1" -H "accept: application/json"
```

#### Response

```json
{
  "id": 1,
  "name": "Rick Sanchez",
  "status": "Alive",
  "species": "Human",
  "type": "",
  "episode_count": 41,
  "origin": {
    "name": "Earth (C-137)",
    "url": "https://rickandmortyapi.com/api/location/1",
    "dimension": "Dimension C-137",
    "residents": [
      "https://rickandmortyapi.com/api/character/38",
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
      "https://rickandmortyapi.com/api/character/394"
    ]
  }
}
```

## Testing

Tests are organized under the `src/test/java` directory. To run the tests, use the following command:

```sh
./gradlew test