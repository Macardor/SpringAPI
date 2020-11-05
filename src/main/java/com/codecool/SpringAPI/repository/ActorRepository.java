package com.codecool.SpringAPI.repository;

import com.codecool.SpringAPI.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Long> {

    List<Actor> findByIsActiveTrue();

    List<Actor> findByIsActiveTrueAndRating(Integer rating);

    List<Actor> findByIsActiveTrueAndFirstNameOrLastNameIgnoreCaseContains(String firstName, String lastName);

    Optional<Actor> findByIsActiveTrueAndId(Long id);

}
