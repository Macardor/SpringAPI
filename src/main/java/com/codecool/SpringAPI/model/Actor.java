package com.codecool.SpringAPI.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Actor {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private int rating;

    @ManyToMany(mappedBy = "actors", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @Transient
    private Set<Movie> movies;

    public Actor(String firstName, String lastName, int rating){
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
    }

    public Actor(){

    }


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
}
