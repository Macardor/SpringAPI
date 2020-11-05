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

    public List<Movie> getAllMovies(String search, Integer year, Integer rating){
        if (search.equals("") && year == null && rating == null){
            return repository.findByIsActiveTrue();
        }
        if(year != null && rating != null){
            return repository.findByIsActiveTrueAndYearAndRating(year, rating);
        }else if(year != null){
            return repository.findByIsActiveTrueAndYear(year);
        }else if(rating != null){
            return repository.findByIsActiveTrueAndRating(rating);
        }
        return repository.findByIsActiveTrueAndTitleOrDescriptionIgnoreCaseContains(search, search);
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