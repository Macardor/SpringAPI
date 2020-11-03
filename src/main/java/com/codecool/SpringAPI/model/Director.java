package com.codecool.SpringAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Director {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private int rating;
    private boolean isActive = true;

    @ManyToMany(mappedBy = "directors", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @Transient
    private Set<Movie> movies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @JsonIgnore
    @JsonProperty(value = "is_active")
    public boolean isActive() { return isActive; }

    public void setActive(boolean active) {
        isActive = active;
    }
}
