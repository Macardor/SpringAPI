package com.codecool.SpringAPI.controller;

import com.codecool.SpringAPI.model.Actor;
import com.codecool.SpringAPI.service.ActorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActorController {
    private final ActorService actorService;

    ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/actors")
    public List<Actor> all(@RequestParam(value = "search", defaultValue = "") String search,
                           @RequestParam(value = "rating", defaultValue = "") Integer rating) {
        return actorService.getAllActors(search, rating);
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
