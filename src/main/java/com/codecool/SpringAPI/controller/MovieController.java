package com.codecool.SpringAPI.controller;

import com.codecool.SpringAPI.exception.MovieNotFoundException;
import com.codecool.SpringAPI.model.Movie;
import com.codecool.SpringAPI.repository.MovieRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    private final MovieRepository repository;
    private final Logger log = LogManager.getLogger(MovieRepository.class);

    MovieController(MovieRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/movies")
    public List<Movie> all() {
        log.info("Getting all movies");
        return repository.findByisActive(true);
    }

    @PostMapping("/movies")
    public Movie newMovie(@RequestBody Movie newMovie) {
        log.info("Adding new movie: " + newMovie.getTitle());
        return repository.save(newMovie);
    }

    @GetMapping("/movies/{id}")
    public Movie one(@PathVariable Long id) {
        log.info("Getting movie id: " + id);
        return repository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    @PutMapping("/movies/{id}")
    public Movie replaceActor(@RequestBody Movie newMovie, @PathVariable Long id) {
        log.info("Put on movie id: " + id);
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
        log.info("Deleting movie id: " + id);
        repository.getOne(id).setActive(false);
    }
}
