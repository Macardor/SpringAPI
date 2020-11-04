package com.codecool.SpringAPI.controller;

import com.codecool.SpringAPI.model.Actor;
import com.codecool.SpringAPI.repository.ActorRepository;
import com.codecool.SpringAPI.service.ActorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActorController {
    private final ActorService actorService;
    private final Logger log = LogManager.getLogger(ActorRepository.class);

    ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/actors")
    public List<Actor> all() {
        log.info("Getting all actors");
        return actorService.getAllActors();
    }

    @PostMapping("/actors")
    public Actor newActor(@RequestBody Actor newActor) {
        log.info("Adding new actor: " + newActor.getFirstName() + " " + newActor.getLastName());
        return actorService.addActor(newActor);
    }

    @GetMapping("/actors/{id}")
    public Actor one(@PathVariable Long id) {
        log.info("Getting actor id: " + id);
        return actorService.getActor(id);
    }

    @PutMapping("/actors/{id}")
    public Actor replaceActor(@RequestBody Actor newActor, @PathVariable Long id) {
        log.info("Put on actor id: " + id);
        return actorService.updateActor(newActor, id);
    }

    @DeleteMapping("/actors/{id}")
    public void deleteActor(@PathVariable Long id) {
        log.info("Deleting actor id: " + id);
        actorService.deleteActor(id);
    }
}
