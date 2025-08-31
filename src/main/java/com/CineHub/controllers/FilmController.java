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

    @GetMapping("/playing/{page}")
    public MovieResponse carteleraPeliculas(@PathVariable ("page") int page){

        return movieService.getNowPlaying(page);
    }

    @GetMapping("/trendingDayMovies/{page}")
    public MovieResponse trendingDay(@PathVariable ("page") int page){

        return movieService.getTrendingDayMovies(page);
    }

    @GetMapping("/topMovies")
    public MovieResponse topMovies(@RequestParam(defaultValue = "1") int page){
        return movieService.getTopMovies(page);
    }

    @GetMapping("/details/{id}")
    public FilmDetails detallesPelicula(@PathVariable ("id") int idPelicula){
        return movieService.getFilmDetails(idPelicula);
    }
}