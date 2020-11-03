package com.codecool.SpringAPI.controller;

import com.codecool.SpringAPI.exception.MovieNotFoundException;
import com.codecool.SpringAPI.model.Movie;
import com.codecool.SpringAPI.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    private final MovieRepository repository;
    private Logger logger = LoggerFactory.getLogger(MovieController.class);

    MovieController(MovieRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/movies")
    public List<Movie> all() {
        logger.error("Error");
        List<Movie> movies = repository.findAll();
        movies.removeIf(movie -> !movie.isActive());
        return movies;
    }

    @PostMapping("/movies")
    public Movie newMovie(@RequestBody Movie newMovie) {
        return repository.save(newMovie);
    }

    // Single item

    @GetMapping("/movies/{id}")
    public Movie one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    @PutMapping("/movies/{id}")
    public Movie replaceActor(@RequestBody Movie newMovie, @PathVariable Long id) {
        return repository.findById(id)
                .map(movie -> {
                    movie.setTitle(newMovie.getTitle());
                    movie.setDescription(newMovie.getDescription());
                    movie.setYear(newMovie.getYear());
                    movie.setRating(newMovie.getRating());
                    return repository.save(movie);
                })
                .orElseGet(() -> {
                    newMovie.setId(id);
                    return repository.save(newMovie);
                });
    }

    @DeleteMapping("/movies/{id}")
    void deleteMovie(@PathVariable Long id) {
        repository.getOne(id).setActive(false);
    }
}
