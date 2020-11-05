package com.codecool.SpringAPI.controller;

import com.codecool.SpringAPI.model.Movie;
import com.codecool.SpringAPI.service.MovieService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public List<Movie> all(@RequestParam(value = "title", defaultValue = "") String title) {
        return movieService.getAllMovies(title);
    }

    @PostMapping("/movies")
    public Movie newMovie(@RequestBody Movie newMovie) {
        return movieService.addMovie(newMovie);
    }

    @GetMapping("/movies/{id}")
    public Movie one(@PathVariable Long id) {
        return movieService.getMovie(id);
    }

    @PutMapping("/movies/{id}")
    public Movie replaceActor(@RequestBody Movie newMovie, @PathVariable Long id) {
        return movieService.updateMovie(newMovie, id);
    }

    @DeleteMapping("/movies/{id}")
    void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
}
