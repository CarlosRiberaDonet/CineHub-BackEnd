package com.CineHub.controllers;


import com.CineHub.entity.Famous;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.CineHub.dto.CreditsResponse;
import com.CineHub.dto.PeopleResponse;
import com.CineHub.service.PeopleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/famous")
@CrossOrigin(origins = "*")
public class PeopleController {

    private final PeopleService peopleService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PeopleController(PeopleService peopleService){
        this.peopleService = peopleService;
    }

    // Devuelve famosos más populares
    @GetMapping("mostPopular")
    public PeopleResponse getMostPopularPeopleList(){

        return peopleService.getFamousPeople();
    }

    // Devuelve información de actores/actrices
    @GetMapping("/{id}")
    public Famous getPeopleInfo(@PathVariable("id") int id){
        return peopleService.getFamousById(id);
    }

    // Devuelve reparto principal de una película
    @GetMapping("/credits/{id}")
    public CreditsResponse getCredits(@PathVariable("id") int idPelicula){

        return peopleService.getCast(idPelicula);
    }
}
