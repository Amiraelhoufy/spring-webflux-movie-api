package org.agcodes.spring_webflux_movie_api.service;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.agcodes.spring_webflux_movie_api.model.Movie;
import org.agcodes.spring_webflux_movie_api.model.Review;
import org.agcodes.spring_webflux_movie_api.repository.MovieRepository;
import org.agcodes.spring_webflux_movie_api.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReviewService {

  private final ReviewRepository reviewRepository;
  private final MovieRepository movieRepository;

  public Flux<Review> getAllReviews() {
    return reviewRepository.findAll();
  }
  public Mono<Review> addReview(String movieId, Review review) {
    return movieRepository.findById(movieId)
        .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found")))
        .flatMap(
            movie -> {
              review.setMovieId(movie.getId());
              return reviewRepository.save(review);
            }
        );
  }
  public Flux<Review> getReviewsByMovieId(String movieId) {
    return reviewRepository.findByMovieId(movieId);
  }

  public Flux<Review> streamReviewsByMovieId(String movieId) {
    return reviewRepository.findByMovieId(movieId)
        .delayElements(Duration.ofSeconds(1)); // Simulated stream
  }

  public Flux<Movie> recommendMoviesForUser(String userId) {
    return reviewRepository.findByUserId(userId)
        .filter(review -> review.getRating() > 3)
        .flatMap(review -> movieRepository.findById(review.getMovieId()))
        .map(Movie::getGenres)
        .distinct()
        .collectList()
        .flatMapMany(genres -> {
      if (genres.isEmpty()) {    // User has no good reviews → return top-rated movies
        return movieRepository.findAll()
            .filter(movie -> movie.getRating() > 3);
      } else {
        // User liked genres → recommend same genres with high rating
        return movieRepository.findAll()
            .filter(movie -> movie.getRating() > 3 && genres.contains(movie.getGenres()));
      }
    });
  }
}
