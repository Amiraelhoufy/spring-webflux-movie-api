package org.agcodes.spring_webflux_movie_api.config;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.agcodes.spring_webflux_movie_api.model.Movie;
import org.agcodes.spring_webflux_movie_api.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
  public class DataInitializer implements CommandLineRunner {

    private final MovieRepository movieRepository;

    @Override
    public void run(String... args) {
      movieRepository.deleteAll()
          .thenMany(
              Flux.just(
                  Movie.builder().title("Inception").genres(List.of("Sci-Fi", "Thriller"))
                      .releaseDate(LocalDate.of(2010, 7, 16))
                      .rating(8.8).build(),
                  Movie.builder().title("The Dark Knight").genres(List.of("Action", "Crime"))
                      .releaseDate(LocalDate.of(2008, 7, 18))
                      .rating(9.0).build(),
                  Movie.builder().title("Interstellar").genres(List.of("Sci-Fi", "Drama"))
                      .releaseDate(LocalDate.of(2014, 11, 7))
                      .rating(8.6).build()
              )
          )
          .flatMap(movieRepository::save)
          .subscribe(movie -> System.out.println("Inserted movie: " + movie));
    }
  }

