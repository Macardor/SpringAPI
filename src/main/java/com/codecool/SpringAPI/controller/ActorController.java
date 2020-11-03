package com.codecool.SpringAPI.controller;

import com.codecool.SpringAPI.exception.ActorNotFoundException;
import com.codecool.SpringAPI.model.Actor;
import com.codecool.SpringAPI.repository.ActorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActorController {
    private final ActorRepository repository;

    ActorController(ActorRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/actors")
    public List<Actor> all() {
        return repository.findAll();
    }

    @PostMapping("/actors")
    public Actor newActor(@RequestBody Actor newActor) {
        return repository.save(newActor);
    }

    // Single item

    @GetMapping("/actors/{id}")
    public Actor one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException(id));
    }

    @PutMapping("/actors/{id}")
    public Actor replaceActor(@RequestBody Actor newActor, @PathVariable Long id) {
        return repository.findById(id)
                .map(actor -> {
                    actor.setFirstName(newActor.getFirstName());
                    actor.setLastName(newActor.getLastName());
                    actor.setRating(newActor.getRating());
                    return repository.save(actor);
                })
                .orElseGet(() -> {
                    newActor.setId(id);
                    return repository.save(newActor);
                });
    }

    @DeleteMapping("/actors/{id}")
    void deleteActor(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
