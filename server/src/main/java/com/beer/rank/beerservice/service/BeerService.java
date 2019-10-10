package com.beer.rank.beerservice.service;

import java.util.List;

import com.beer.rank.beerservice.model.Beer;

import reactor.core.publisher.Mono;

public interface BeerService {

    Mono<Beer> createOrUpdate(Beer beer);

    Mono<List<Beer>> list();

    Mono<Beer> getById(Integer id);
}
