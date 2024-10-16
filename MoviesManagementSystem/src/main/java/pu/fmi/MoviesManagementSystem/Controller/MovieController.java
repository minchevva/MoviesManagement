package pu.fmi.MoviesManagementSystem.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pu.fmi.MoviesManagementSystem.DTO.MovieDTO;
import pu.fmi.MoviesManagementSystem.Entity.Actor;
import pu.fmi.MoviesManagementSystem.Entity.Director;
import pu.fmi.MoviesManagementSystem.Entity.Genre;
import pu.fmi.MoviesManagementSystem.Entity.Movie;
import pu.fmi.MoviesManagementSystem.Repository.ActorRepository;
import pu.fmi.MoviesManagementSystem.Repository.DirectorRepository;
import pu.fmi.MoviesManagementSystem.Repository.GenreRepository;
import pu.fmi.MoviesManagementSystem.Repository.MovieRepository;
import pu.fmi.MoviesManagementSystem.Service.MovieService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;
    private final GenreRepository genreRepository;
    private final DirectorRepository directorRepository;
    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public MovieController(MovieService movieService,
                           GenreRepository genreRepository,
                           DirectorRepository directorRepository,
                           ActorRepository actorRepository, MovieRepository movieRepository) {
        this.movieService = movieService;
        this.genreRepository = genreRepository;
        this.directorRepository = directorRepository;
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id) {
        Optional<Movie> movie = movieService.getMovieById(id);
        return movie.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addMovie(@RequestBody MovieDTO movieDTO) {
        try {
            Movie movie = new Movie();
            movie.setTitle(movieDTO.getTitle());
            movie.setReleaseDate(movieDTO.getReleaseDate());

            Genre genre = genreRepository.findById(movieDTO.getGenreId())
                    .orElseThrow(() -> new RuntimeException("Genre not found"));
            movie.setGenre(genre);

            Director director = directorRepository.findById(movieDTO.getDirectorId())
                    .orElseThrow(() -> new RuntimeException("Director not found"));
            movie.setDirector(director);

            Movie savedMovie = movieService.addMovie(movie);

            // Създаване на Map с ограничени полета за отговор
            Map<String, Object> response = new HashMap<>();
            response.put("movieId", savedMovie.getMovieId());
            response.put("title", savedMovie.getTitle());
            response.put("releaseDate", savedMovie.getReleaseDate());
            response.put("genre", savedMovie.getGenre().getName());
            response.put("director", savedMovie.getDirector().getFirstName() + " " + savedMovie.getDirector().getLastName());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }





    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable int id, @RequestBody Movie movie) {
        try {
            Movie updatedMovie = movieService.updateMovie(id, movie);
            return ResponseEntity.ok(updatedMovie);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
