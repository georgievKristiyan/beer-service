package com.beer.rank.beerservice.controller;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/health")
public class ReadinessAndLivenessController {

    @GetMapping(value = "/readiness")
    public Mono<Void> readiness() {
        return Mono.empty();
    }

    @GetMapping(value = "/liveness")
    public Mono<Void> liveness() {
        return Mono.empty();
    }
}
