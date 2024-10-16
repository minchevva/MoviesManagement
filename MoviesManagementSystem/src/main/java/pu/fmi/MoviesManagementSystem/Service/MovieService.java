package pu.fmi.MoviesManagementSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pu.fmi.MoviesManagementSystem.Entity.Movie;
import pu.fmi.MoviesManagementSystem.Repository.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(int id) {
        return movieRepository.findById(id);
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(int id, Movie updatedMovie) {
        return movieRepository.findById(id).map(movie -> {
            movie.setTitle(updatedMovie.getTitle());
            movie.setReleaseDate(updatedMovie.getReleaseDate());
            movie.setGenre(updatedMovie.getGenre());
            movie.setDirector(updatedMovie.getDirector());
            return movieRepository.save(movie);
        }).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }
}
/*@Service: Декларира, че този клас е бизнес компонент, управляващ логиката на приложението.
Методи:
getAllMovies(): Извиква findAll() от репозиторието, за да върне всички филми.
getMovieById(int id): Използва findById() и връща Optional обект, който улеснява обработката на липсващи записи.
addMovie(Movie movie): Използва save(), за да създаде нов филм.
updateMovie(int id, Movie movieDetails): Търси филм по ID, актуализира полетата, и използва save() за съхранение на промените.
deleteMovie(int id): Извиква deleteById(), за да изтрие филм по ID.*/
