package com.beer.rank.beerservice.controller;

import com.beer.rank.beerservice.model.BeerReview;
import com.beer.rank.beerservice.service.BeerReviewService;

import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/reviews")
public class BeerReviewController {

    private final BeerReviewService service;

    @Autowired
    public BeerReviewController(BeerReviewService service) {
        this.service = service;
    }

    @PostMapping(value = "/{beerId:.+$}")
    public Mono<BeerReview> reviewBeer(@PathVariable Integer beerId, @RequestBody BeerReview review) {
        return service.reviewBeer(review, beerId);
    }
}
