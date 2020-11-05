package com.codecool.SpringAPI.controller;

import com.codecool.SpringAPI.model.Movie;
import com.codecool.SpringAPI.repository.MovieRepository;
import com.codecool.SpringAPI.service.MovieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    private final MovieService movieService;
    private final Logger log = LogManager.getLogger(MovieRepository.class);

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public List<Movie> all(@RequestParam(value = "search", defaultValue = "") String search,
                           @RequestParam(value = "year", defaultValue = "") Integer year,
                           @RequestParam(value = "rating", defaultValue = "") Integer rating) {
        log.info("Getting all movies: " + search);
        return movieService.getAllMovies(search, year, rating);
    }

    @PostMapping("/movies")
    public Movie newMovie(@RequestBody Movie newMovie) {
        log.info("Adding new movie: " + newMovie.getTitle());
        return movieService.addMovie(newMovie);
    }

    @GetMapping("/movies/{id}")
    public Movie one(@PathVariable Long id) {
        log.info("Getting movie id: " + id);
        return movieService.getMovie(id);
    }

    @PutMapping("/movies/{id}")
    public Movie replaceActor(@RequestBody Movie newMovie, @PathVariable Long id) {
        log.info("Put on movie id: " + id);
        return movieService.updateMovie(newMovie, id);
    }

    @DeleteMapping("/movies/{id}")
    void deleteMovie(@PathVariable Long id) {
        log.info("Deleting movie id: " + id);
        movieService.deleteMovie(id);
    }
}
