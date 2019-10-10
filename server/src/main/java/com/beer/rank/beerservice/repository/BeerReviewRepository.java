package com.beer.rank.beerservice.repository;

import com.beer.rank.beerservice.model.BeerReview;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerReviewRepository extends CrudRepository<BeerReview, Integer> {
}
