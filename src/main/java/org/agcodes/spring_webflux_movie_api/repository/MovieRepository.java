package org.agcodes.spring_webflux_movie_api.repository;

import org.agcodes.spring_webflux_movie_api.model.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {
  Flux<Movie> findByGenresContaining(String genre);
}
