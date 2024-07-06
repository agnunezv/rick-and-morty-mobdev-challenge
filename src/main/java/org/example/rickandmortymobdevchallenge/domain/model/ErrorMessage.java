package org.example.rickandmortymobdevchallenge.domain.model;

public class ErrorMessage {

    private String status;

    private String message;

    public String getStatus() {
        return status;
    }

    public ErrorMessage setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorMessage setMessage(String message) {
        this.message = message;
        return this;
    }
}