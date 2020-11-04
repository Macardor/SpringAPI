package com.codecool.SpringAPI.repository;

import com.codecool.SpringAPI.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Long> {

    List<Actor> findByisActive(boolean isActive);

    Optional<Actor> findByIsActiveTrueAndId(Long id);

}
