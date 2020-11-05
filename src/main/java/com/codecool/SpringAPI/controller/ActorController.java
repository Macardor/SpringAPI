package com.codecool.SpringAPI.controller;

import com.codecool.SpringAPI.model.Actor;
import com.codecool.SpringAPI.service.ActorService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
public class ActorController {
    private final ActorService actorService;

    ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/actors")
    public List<Actor> all() {
        return actorService.getAllActors();
    }

    @PostMapping("/actors")
    public Actor newActor(@RequestBody Actor newActor) {
        return actorService.addActor(newActor);
    }

    @GetMapping("/actors/{id}")
    public Actor one(@PathVariable Long id) {
        return actorService.getActor(id);
    }

    @PutMapping("/actors/{id}")
    public Actor replaceActor(@RequestBody Actor newActor, @PathVariable Long id) {
        return actorService.updateActor(newActor, id);
    }

    @DeleteMapping("/actors/{id}")
    public void deleteActor(@PathVariable Long id) {
        actorService.deleteActor(id);
    }
}
