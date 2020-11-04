package com.codecool.SpringAPI.service;

import com.codecool.SpringAPI.exception.DirectorNotFoundException;
import com.codecool.SpringAPI.model.Movie;
import com.codecool.SpringAPI.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public List<Movie> getAllMovies(String title){
        if(!title.equals("")){
            List<Movie> movies = repository.findByisActive(true);
            movies.removeIf(movie -> !movie.getTitle().contains(title));
            return movies;
        }
        return repository.findByisActive(true);
    }

    public Movie addMovie(Movie newMovie){
        return repository.save(newMovie);
    }

    public Movie getMovie(Long id){
        return repository.findByIsActiveTrueAndId(id).orElseThrow(() -> new DirectorNotFoundException(id));
    }

    public Movie updateMovie(Movie newMovie, Long id){
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

    public void deleteMovie(Long id){
        repository.getOne(id).setActive(false);
    }
}