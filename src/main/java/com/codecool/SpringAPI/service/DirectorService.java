package com.codecool.SpringAPI.service;

import com.codecool.SpringAPI.exception.DirectorNotFoundException;
import com.codecool.SpringAPI.model.Director;
import com.codecool.SpringAPI.repository.DirectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService {
    private final DirectorRepository repository;

    public DirectorService(DirectorRepository repository) {
        this.repository = repository;
    }

    public List<Director> getAllDirectors(String search, Integer rating){
        if(search.equals("") && rating == null) return repository.findByIsActiveTrue();
        if(rating != null) return repository.findByIsActiveTrueAndRating(rating);
        return repository.findByIsActiveTrueAndFirstNameOrLastNameIgnoreCaseContains(search, search);
    }

    public Director addDirector(Director newDirector){
        return repository.save(newDirector);
    }

    public Director getDirector(Long id){
        return repository.findByIsActiveTrueAndId(id).orElseThrow(() -> new DirectorNotFoundException(id));
    }

    public Director updateDirector(Director newDirector, Long id){
        return repository.findById(id)
                .map(director -> {
                    director.setFirstName(newDirector.getFirstName());
                    director.setLastName(newDirector.getLastName());
                    director.setMovies(newDirector.getMovies());
                    director.setRating(newDirector.getRating());
                    director.setActive(newDirector.isActive());
                    return repository.save(director);
                }).orElseGet(() -> {
                    newDirector.setId(id);
                    return repository.save(newDirector);
                });
    }

    public void deleteDirector(Long id){
        repository.getOne(id).setActive(false);
    }
}
