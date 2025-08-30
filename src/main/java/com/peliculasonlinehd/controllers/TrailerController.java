package com.peliculasonlinehd.controllers;

import com.peliculasonlinehd.service.TrailerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trailer")
@CrossOrigin(origins = "*")
public class TrailerController {

    private final TrailerService trailerService;

    public TrailerController(TrailerService trailerService) {

        this.trailerService = trailerService;
    }

    @GetMapping("/{idPelicula}")
    public String getTrailer(@PathVariable int idPelicula) {
        return trailerService.getTrailerById(idPelicula);
    }
}
