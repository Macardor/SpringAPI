package com.codecool.SpringAPI.repository;

import com.codecool.SpringAPI.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByisActive(boolean isActive);

    Optional<Movie> findByIsActiveTrueAndId(Long id);

}