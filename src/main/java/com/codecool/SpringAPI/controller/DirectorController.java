package com.codecool.SpringAPI.controller;

import com.codecool.SpringAPI.model.Director;
import com.codecool.SpringAPI.repository.DirectorRepository;

import com.codecool.SpringAPI.service.DirectorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DirectorController {
    private final DirectorService directorService;
    private final Logger log = LogManager.getLogger(DirectorRepository.class);

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping("/directors")
    public List<Director> getAll(@RequestParam(value = "search", defaultValue = "") String search,
                                 @RequestParam(value = "rating", defaultValue = "") Integer rating) {
        log.info("Getting all directors");
        return directorService.getAllDirectors(search, rating);
    }

    @PostMapping("/directors")
    public Director addDirector(@RequestBody Director newDirector){
        log.info("Adding new director :" + newDirector.getFirstName() + " " + newDirector.getLastName());
        return directorService.addDirector(newDirector); }

    @GetMapping("/directors/{id}")
    public Director getOneById(@PathVariable Long id){
        log.info("Getting director with id: " + id);
        return directorService.getDirector(id);
    }

    @PutMapping("/directors/{id}")
    public Director updateDirector(@RequestBody Director newDirector, @PathVariable Long id){
        log.info("Updating director with id: " + id);
        return directorService.updateDirector(newDirector, id);
    }

    @DeleteMapping("/directors/{id}")
    public void deleteDirector(@PathVariable Long id){
        log.info("Deleting director with id: " + id);
        directorService.deleteDirector(id);
    }
}
