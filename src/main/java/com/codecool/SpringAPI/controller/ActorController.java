package com.codecool.SpringAPI.controller;

import com.codecool.SpringAPI.exception.ActorNotFoundException;
import com.codecool.SpringAPI.model.Actor;
import com.codecool.SpringAPI.repository.ActorRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActorController {
    private final ActorRepository repository;
    private final Logger log = LogManager.getLogger(ActorRepository.class);

    ActorController(ActorRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/actors")
    public List<Actor> all() {
        log.info("Getting all actors");
        List<Actor> actors = repository.findAll();
        actors.removeIf(actor -> !actor.isActive());
        return actors;
    }

    @PostMapping("/actors")
    public Actor newActor(@RequestBody Actor newActor) {
        log.info("Adding new actor: " + newActor.getFirstName() + " " + newActor.getLastName());
        return repository.save(newActor);
    }

    // Single item

    @GetMapping("/actors/{id}")
    public Actor one(@PathVariable Long id) {
        log.info("Getting actor id: " + id);
        return repository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException(id));
    }

    @PutMapping("/actors/{id}")
    public Actor replaceActor(@RequestBody Actor newActor, @PathVariable Long id) {
        log.info("Put on actor id: " + id);
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
        log.info("Deleting actor id: " + id);
        repository.getOne(id).setActive(false);
    }
}
