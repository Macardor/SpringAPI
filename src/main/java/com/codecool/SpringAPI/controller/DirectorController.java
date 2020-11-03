package com.codecool.SpringAPI.controller;

import com.codecool.SpringAPI.exception.DirectorNotFoundException;
import com.codecool.SpringAPI.model.Director;
import com.codecool.SpringAPI.repository.DirectorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DirectorController {
    private final DirectorRepository repository;

    public DirectorController(DirectorRepository directorRepository) {
        this.repository = directorRepository;
    }

    @GetMapping("/directors")
    public List<Director> getAll() {
        return repository.findByisActive(true);
    }

    @PostMapping("/directors")
    public Director addDirector(@RequestBody Director newDirector){ return repository.save(newDirector); }

    @GetMapping("/directors/{id}")
    public Director getOneById(@PathVariable Long id){
        return repository.findById(id).orElseThrow(() -> new DirectorNotFoundException(id));
    }

    @PutMapping("/directors/{id}")
    public Director updateDirector(@RequestBody Director newDirector, @PathVariable Long id){
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

    @DeleteMapping("/directors/{id}")
    public void deleteDirector(@PathVariable Long id){
        repository.getOne(id).setActive(false);
    }
}
