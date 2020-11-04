package com.codecool.SpringAPI.controller;

import com.codecool.SpringAPI.exception.DirectorNotFoundException;
import com.codecool.SpringAPI.model.Director;
import com.codecool.SpringAPI.repository.DirectorRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DirectorController {
    private final DirectorRepository repository;
    private final Logger logger = LogManager.getLogger(DirectorRepository.class);

    public DirectorController(DirectorRepository directorRepository) {
        this.repository = directorRepository;
    }

    @GetMapping("/directors")
    public List<Director> getAll() {
        logger.info("Getting all directors");
        return repository.findByisActive(true);
    }

    @PostMapping("/directors")
    public Director addDirector(@RequestBody Director newDirector){
        logger.info("Adding director");
        return repository.save(newDirector); }

    @GetMapping("/directors/{id}")
    public Director getOneById(@PathVariable Long id){
        logger.info("Getting director by id");
        return repository.findById(id).orElseThrow(() -> new DirectorNotFoundException(id));
    }

    @PutMapping("/directors/{id}")
    public Director updateDirector(@RequestBody Director newDirector, @PathVariable Long id){
        logger.info("Updating director");
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
        logger.info("Deleting director");
        repository.getOne(id).setActive(false);
    }
}
