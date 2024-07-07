package org.example.rickandmortymobdevchallenge.domain.model;

import java.util.List;

public class Character {

    private int id;

    private String name;

    private String status;

    private String species;

    private String type;

    private int episodeCount;

    private Origin origin;

    public Character() {
    }

    public Character(int id, String name, String status, String species, String type, int episodeCount, Origin origin) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.species = species;
        this.type = type;
        this.episodeCount = episodeCount;
        this.origin = origin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
        this.episodeCount = episodeCount;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public static class Origin {

        private String name;

        private String url;

        private String dimension;

        private List<String> residents;

        public Origin() {
        }

        public Origin(String name, String url, String dimension, List<String> residents) {
            this.name = name;
            this.url = url;
            this.dimension = dimension;
            this.residents = residents;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDimension() {
            return dimension;
        }

        public void setDimension(String dimension) {
            this.dimension = dimension;
        }

        public List<String> getResidents() {
            return residents;
        }

        public void setResidents(List<String> residents) {
            this.residents = residents;
        }
    }
}
