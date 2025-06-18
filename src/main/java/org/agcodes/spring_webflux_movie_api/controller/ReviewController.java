package org.agcodes.spring_webflux_movie_api.controller;

import lombok.RequiredArgsConstructor;
import org.agcodes.spring_webflux_movie_api.model.Movie;
import org.agcodes.spring_webflux_movie_api.model.Review;
import org.agcodes.spring_webflux_movie_api.service.ReviewService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;

  @GetMapping("/reviews")
  public Flux<Review> getAllReviews(){
    return reviewService.getAllReviews();
  }

  @GetMapping(value= "/movies/{movieId}/reviews/stream", produces =  MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Review> streamReviews(@PathVariable String movieId){
      return reviewService.streamReviewsByMovieId(movieId);
  }
  @GetMapping("/movies/{movieId}/reviews")
  public Flux<Review> getReviewsByMovieId(@PathVariable String movieId){
    return reviewService.getReviewsByMovieId(movieId);
  }
  @PostMapping("/movies/{movieId}/reviews")
  public Mono<Review> addReview(@PathVariable String movieId, @RequestBody Review review){
    return reviewService.addReview(movieId,review);
  }
  @GetMapping("/movies/recommendations/{userId}")
  public Flux<Movie> recommendMoviesBasedOnReviews(@PathVariable String userId){
    return reviewService.recommendMoviesForUser(userId);
  }
}
