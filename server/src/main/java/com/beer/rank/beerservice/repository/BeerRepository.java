package com.beer.rank.beerservice.repository;

import com.beer.rank.beerservice.model.Beer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends CrudRepository<Beer, Integer> {
}
