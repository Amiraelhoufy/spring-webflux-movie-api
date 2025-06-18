package org.agcodes.spring_webflux_movie_api.controller;

import lombok.RequiredArgsConstructor;
import org.agcodes.spring_webflux_movie_api.model.Movie;
import org.agcodes.spring_webflux_movie_api.service.MovieService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

  private final MovieService movieService;

  @GetMapping
  public Flux<Movie> getAllMovies() {
    return movieService.getAllMovies();
  }
  @GetMapping("/{id}")
  public Mono<Movie> getMovieById(@PathVariable String id){
    return movieService.getMovieById(id);
  }

  @GetMapping("/genre/{genre}")
  public Flux<Movie> getMoviesByGenre(@PathVariable String genre){
    return movieService.getMoviesByGenre(genre);
  }

  @PostMapping
  public Mono<Movie> addMovie(@RequestBody Movie movie){
    return movieService.addMovie(movie);
  }

  @PutMapping("/{id}")
  public Mono<Movie> updateMovie(@PathVariable String id,@RequestBody Movie movie){
    return movieService.updateMovie(id,movie);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> deleteMovie(@PathVariable String id){
    return movieService.deleteMovie(id);
  }

}
