package org.agcodes.spring_webflux_movie_api.model;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movies") // Marks a class as a MongoDB document
@Data               // generates getters, setters, toString, equals, hashCode
@NoArgsConstructor  // no-arg constructor
@AllArgsConstructor // all-args constructor
@Builder            // builder pattern
public class Movie {

  @Id
  private String id;
  private String title;
  private List<String> genres;
  private LocalDate releaseDate;
  private Double rating;
}
