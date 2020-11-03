package com.codecool.SpringAPI.repository;

import com.codecool.SpringAPI.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
