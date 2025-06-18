package org.agcodes.spring_webflux_movie_api.repository;

import org.agcodes.spring_webflux_movie_api.model.Review;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ReviewRepository extends ReactiveMongoRepository <Review,String>{

  Flux<Review> findByMovieId(String movieId);
  Flux<Review> findByUserId(String userId);
}
