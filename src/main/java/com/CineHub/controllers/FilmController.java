package com.CineHub.controllers;

import com.CineHub.dto.MovieResponse;
import com.CineHub.entity.FilmDetails;
import com.CineHub.service.MovieService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/peliculas")
@CrossOrigin(origins = "*")
public class FilmController {

    private final MovieService movieService;

    public FilmController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/upcoming")
    public MovieResponse proximasPeliculas(){
        return movieService.getUpcomingMovies();
    }

    @GetMapping("/playing")
    public MovieResponse carteleraPeliculas(){
        return movieService.getNowPlaying();
    }

    @GetMapping("/trendingDayMovies")
    public MovieResponse trendingDay(){
        return movieService.getTrendingDayMovies();
    }

    @GetMapping("/topMovies")
    public MovieResponse topMovies(){
        return movieService.getTopMovies();
    }

    @GetMapping("/details/{id}")
    public FilmDetails detallesPelicula(@PathVariable ("id") int idPelicula){
        return movieService.getFilmDetails(idPelicula);
    }
}