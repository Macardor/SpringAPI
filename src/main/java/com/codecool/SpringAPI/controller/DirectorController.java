package com.codecool.SpringAPI.controller;

import com.codecool.SpringAPI.model.Director;
import com.codecool.SpringAPI.service.DirectorService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
public class DirectorController {
    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping("/directors")
    public List<Director> getAll(@RequestParam(value = "search", defaultValue = "") String search,
                                 @RequestParam(value = "rating", defaultValue = "") Integer rating) {
        return directorService.getAllDirectors(search, rating);
    }

    @PostMapping("/directors")
    public Director addDirector(@RequestBody Director newDirector){
        return directorService.addDirector(newDirector); }

    @GetMapping("/directors/{id}")
    public Director getOneById(@PathVariable Long id){
        return directorService.getDirector(id);
    }

    @PutMapping("/directors/{id}")
    public Director updateDirector(@RequestBody Director newDirector, @PathVariable Long id){
        return directorService.updateDirector(newDirector, id);
    }

    @DeleteMapping("/directors/{id}")
    public void deleteDirector(@PathVariable Long id){
        directorService.deleteDirector(id);
    }
}
