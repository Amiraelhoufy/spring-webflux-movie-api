package org.agcodes.spring_webflux_movie_api.service;

import lombok.RequiredArgsConstructor;
import org.agcodes.spring_webflux_movie_api.model.Movie;
import org.agcodes.spring_webflux_movie_api.repository.MovieRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MovieService {

  private final MovieRepository movieRepository;

  public Flux<Movie> getAllMovies(){
    return movieRepository.findAll();
  }
  public Mono<Movie> getMovieById(String id){
    return movieRepository.findById(id);
  }

  public Flux<Movie> getMoviesByGenre(String genre){
    return movieRepository.findByGenresContaining(genre);
  }
  public Mono<Movie> addMovie(Movie movie) {
    return movieRepository.save(movie);
  }

  public Mono<Movie> updateMovie(String id, Movie updatedMovie){
    return movieRepository.findById(id)
        .flatMap(existingMovie -> {
          existingMovie.setTitle(updatedMovie.getTitle());
          existingMovie.setGenres(updatedMovie.getGenres());
          existingMovie.setReleaseDate(updatedMovie.getReleaseDate());
          existingMovie.setRating(updatedMovie.getRating());
          return movieRepository.save(existingMovie);
        });
  }

  public Mono<Void> deleteMovie(String id){
    return movieRepository.deleteById(id);
  }
  
}
