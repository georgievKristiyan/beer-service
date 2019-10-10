package com.beer.rank.beerservice.controller;

import java.util.List;

import com.beer.rank.beerservice.model.Beer;
import com.beer.rank.beerservice.service.BeerService;

import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController()
@RequestMapping(value = "/beers")
public class BeerController {

    private final BeerService beerService;

    @Autowired
    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping
    public Mono<List<Beer>> listBeers() {
        return beerService.list();
    }

    @GetMapping(value = "/{id:.+$}")
    public Mono<Beer> getBeer(@PathVariable Integer id) {
        return beerService.getById(id)
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Beer '%s' not found.", id))));
    }

    @PostMapping
    public Mono<Beer> createBeer(@RequestBody Beer beer) {
        return beerService.createOrUpdate(beer);
    }

}
