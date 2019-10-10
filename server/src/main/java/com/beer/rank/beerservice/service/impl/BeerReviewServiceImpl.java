package com.beer.rank.beerservice.service.impl;

import java.util.Collection;
import java.util.Optional;
import java.util.OptionalDouble;

import com.beer.rank.beerservice.model.Beer;
import com.beer.rank.beerservice.model.BeerReview;
import com.beer.rank.beerservice.repository.BeerReviewRepository;
import com.beer.rank.beerservice.service.BeerReviewService;
import com.beer.rank.beerservice.service.BeerService;

import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BeerReviewServiceImpl implements BeerReviewService {

    private final BeerReviewRepository repository;
    private final BeerService beerService;

    @Autowired
    public BeerReviewServiceImpl(BeerReviewRepository repository,
            BeerService beerService) {
        this.repository = repository;
        this.beerService = beerService;
    }

    @Override
    public Mono<BeerReview> reviewBeer(BeerReview review, Integer beerId) {
        return beerService.getById(beerId)
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Beer '%s' not found.", beerId))))
                .map(beer -> {
                    review.setBeer(beer);
                    calculateRank(beer, review);
                    return repository.save(review);
                });
    }

    private void calculateRank(Beer beer, BeerReview review) {
        Optional.ofNullable(beer.getReviews())
                .map(beerReviews -> {
                    beerReviews.add(review);
                    return beerReviews;
                })
                .map(Collection::stream)
                .map(beerReviewStream -> beerReviewStream.mapToInt(BeerReview::getRank).average())
                .orElse(OptionalDouble.empty())
                .ifPresent(value -> {
                    beer.setRank(value);
                    beerService.createOrUpdate(beer);
                });

    }

}
