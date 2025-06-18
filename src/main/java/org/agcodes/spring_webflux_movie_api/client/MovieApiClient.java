package org.agcodes.spring_webflux_movie_api.client;

import org.agcodes.spring_webflux_movie_api.model.Movie;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class MovieApiClient {
  private final WebClient webClient;
  public MovieApiClient(WebClient.Builder webClientBuilder){
    this.webClient = webClientBuilder
        .baseUrl("http://localhost:8080")
        .build();
  }

  public Flux<Movie> getMovies(){
    return webClient.get()
        .uri("/api/movies")
        .retrieve()
        .bodyToFlux(Movie.class);
  }
}
