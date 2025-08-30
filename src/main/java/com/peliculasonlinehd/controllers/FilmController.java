package com.peliculasonlinehd.controllers;

import com.peliculasonlinehd.dto.CreditsResponse;
import com.peliculasonlinehd.dto.MovieResponse;
import com.peliculasonlinehd.entity.Cast;
import com.peliculasonlinehd.entity.FilmDetails;
import com.peliculasonlinehd.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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