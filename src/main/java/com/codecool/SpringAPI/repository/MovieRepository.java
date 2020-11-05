package com.codecool.SpringAPI.repository;

import com.codecool.SpringAPI.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByIsActiveTrue();

    List<Movie> findByIsActiveTrueAndTitleOrDescriptionIgnoreCaseContains(String searchTitle, String searchDesc);

    List<Movie> findByIsActiveTrueAndYearAndRating(Integer year, Integer rating);

    List<Movie> findByIsActiveTrueAndYear(Integer year);

    List<Movie> findByIsActiveTrueAndRating(Integer rating);

    Optional<Movie> findByIsActiveTrueAndId(Long id);

}