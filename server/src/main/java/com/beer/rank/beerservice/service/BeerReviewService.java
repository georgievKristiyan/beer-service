package com.beer.rank.beerservice.service;

import com.beer.rank.beerservice.model.BeerReview;

import reactor.core.publisher.Mono;

public interface BeerReviewService {
    Mono<BeerReview> reviewBeer(BeerReview review, Integer beerId);
}
