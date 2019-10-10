package com.beer.rank.beerservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.beer.rank.beerservice.model.Beer;
import com.beer.rank.beerservice.repository.BeerRepository;
import com.beer.rank.beerservice.service.BeerService;
import com.sun.org.apache.bcel.internal.generic.ARETURN;

import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;

@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository repository;

    public BeerServiceImpl(BeerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Beer> createOrUpdate(Beer beer) {
        Beer save = repository.save(beer);
        return Mono.just(save);
    }

    @Override
    public Mono<List<Beer>> list() {
        List<Beer> beers = new ArrayList<>();
        repository.findAll().forEach(beers::add);

        return Mono.just(beers);
    }

    @Override
    public Mono<Beer> getById(Integer id) {
        return repository.findById(id)
                .map(Mono::just)
                .orElse(Mono.empty());
    }
}
