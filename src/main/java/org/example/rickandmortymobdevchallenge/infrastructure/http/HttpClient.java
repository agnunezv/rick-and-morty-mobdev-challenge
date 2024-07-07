package org.example.rickandmortymobdevchallenge.infrastructure.http;

import reactor.core.publisher.Mono;

public interface HttpClient {

    <T> Mono<T> get(String uri, Class<T> responseType);

}
