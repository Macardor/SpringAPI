package com.codecool.SpringAPI.service;

import com.codecool.SpringAPI.exception.ActorNotFoundException;
import com.codecool.SpringAPI.model.Actor;
import com.codecool.SpringAPI.repository.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {
    private final ActorRepository repository;

    public ActorService(ActorRepository repository) {
        this.repository = repository;
    }

    public List<Actor> getAllActors(){
        return repository.findByisActive(true);
    }

    public Actor addActor(Actor newActor){
        return repository.save(newActor);
    }

    public Actor getActor(Long id){
        return repository.findByIsActiveTrueAndId(id).orElseThrow(() -> new ActorNotFoundException(id));
    }

    public Actor updateActor(Actor newActor, Long id){
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

    public void deleteActor(Long id){
        repository.getOne(id).setActive(false);
    }
}
