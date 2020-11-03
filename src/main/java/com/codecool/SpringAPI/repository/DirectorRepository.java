package com.codecool.SpringAPI.repository;

import com.codecool.SpringAPI.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DirectorRepository extends JpaRepository<Director, Long> {

    List<Director> findByisActive(boolean isActive);
}
