package org.agcodes.spring_webflux_movie_api.model;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "review")
@Data               // generates getters, setters, toString, equals, hashCode
@NoArgsConstructor  // no-arg constructor
@AllArgsConstructor // all-args constructor
@Builder            // builder pattern
public class Review {
  @Id
  private String id;

// Manual reference to Movie
  private String movieId;
  private String userId;
  private String comment;
  private Integer rating;
  private Instant createdAt;
}
