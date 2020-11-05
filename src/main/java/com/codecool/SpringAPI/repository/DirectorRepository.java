package com.codecool.SpringAPI.repository;

import com.codecool.SpringAPI.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DirectorRepository extends JpaRepository<Director, Long> {

    List<Director> findByIsActiveTrue();

    List<Director> findByIsActiveTrueAndRating(Integer rating);

    List<Director> findByIsActiveTrueAndFirstNameOrLastNameIgnoreCaseContains(String firstName, String lastName);

    Optional<Director> findByIsActiveTrueAndId(Long id);
}
