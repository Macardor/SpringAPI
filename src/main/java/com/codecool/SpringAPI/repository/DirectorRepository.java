package com.codecool.SpringAPI.repository;

import com.codecool.SpringAPI.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
}
