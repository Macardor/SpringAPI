package com.codecool.SpringAPI.repository;

import com.codecool.SpringAPI.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {

    List<Actor> findByisActive(boolean isActive);
}
