package org.agcodes.spring_webflux_movie_api.controller;

import java.nio.charset.StandardCharsets;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler {
  @org.springframework.web.bind.annotation.ExceptionHandler(ServerWebInputException.class)
  public Mono<Void> handleBadRequest(ServerWebInputException ex, ServerWebExchange exchange) {
    exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
    exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
    byte[] bytes = ex.getReason().getBytes(StandardCharsets.UTF_8);
    return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
        .bufferFactory().wrap(bytes)));
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(ResponseStatusException.class)
  public Mono<Void> handleResponseStatusException(ResponseStatusException ex, ServerWebExchange exchange) {
    exchange.getResponse().setStatusCode(ex.getStatusCode());
    exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
    byte[] bytes = ex.getReason().getBytes(StandardCharsets.UTF_8);
    return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
        .bufferFactory().wrap(bytes)));
  }


}
