package com.codecool.SpringAPI.repository;

import com.codecool.SpringAPI.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DirectorRepository extends JpaRepository<Director, Long> {

    List<Director> findByIsActiveTrue();

    Optional<Director> findByIsActiveTrueAndId(Long id);
}
