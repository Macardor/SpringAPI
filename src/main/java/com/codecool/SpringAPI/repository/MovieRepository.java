package com.codecool.SpringAPI.repository;

import com.codecool.SpringAPI.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}