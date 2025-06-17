# ✅ Project Task: Reactive Movie Recommendation API
### 💡 Objective:
- Build a non-blocking REST API that allows users to:

- Fetch a list of movies.
- Get personalized movie recommendations.
- Submit movie reviews.
- Stream reviews in real-time (server-sent events).

### 📦 Tech Stack
- Spring Boot + WebFlux

- Reactive MongoDB (with Spring Data Reactive Repositories)

- Project Reactor (Flux, Mono)

- Lombok (for boilerplate)

- Spring Boot DevTools (optional)

- Docker (optional: for MongoDB)

- Postman or curl (for testing)

### 🧩 Features and Endpoints
Endpoint	Description	Method
`/movies`	Get all movies	GET
`/movies/{id}`	Get a single movie	GET
`/movies/recommendations/{userId}`	Recommend movies for a user	GET
`/movies/{movieId}/reviews`	Post a review for a movie	POST
`/movies/{movieId}/reviews/stream`	Stream reviews for a movie (SSE)	GET

### 📂 Example Data Model
```java
@Data
@Document
public class Movie {
    @Id
    private String id;
    private String title;
    private List<String> genres;
    private Integer year;
}

@Data
@Document
public class Review {
    @Id
    private String id;
    private String movieId;
    private String userId;
    private String comment;
    private Integer rating;
    private Instant createdAt;
}
```
### ⚙️ Key Learning Goals
- Concept	Covered In
- Mono, Flux	All service methods
- @RestController + reactive endpoints	REST layer
- Spring Data Reactive MongoDB	Data access
- Backpressure & streaming	SSE endpoint
- Functional vs Annotated routing (optional advanced)	Can compare both
- Error handling & fallback with onErrorResume	Add in review submission
- Testing with WebTestClient	Write unit & integration tests

### 🔥 Optional Interview Enhancements
- Implement circuit breaker with Resilience4j.

- Use WebClient to call another fake microservice (like a UserService).

- Add rate limiting or retry.

- Create Docker Compose with MongoDB + Spring Boot.

- Secure endpoints with Spring Security WebFlux and JWT.

### 🧠 Interview Tip:
- During interviews, be ready to explain:

- Why use WebFlux instead of MVC?

- Difference between Flux and Mono.

- How backpressure is handled.

- Why blocking calls in reactive pipelines are dangerous.

- MongoDB vs RDBMS for reactive apps.
