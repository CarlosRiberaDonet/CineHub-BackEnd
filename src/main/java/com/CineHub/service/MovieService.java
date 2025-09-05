package com.CineHub.service;

import com.CineHub.dto.MovieCastCrewReponse;
import com.CineHub.dto.MovieCreditsResponse;
import com.CineHub.dto.PeopleResponse;
import com.CineHub.entity.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.CineHub.dao.ApiDAO;
import com.CineHub.dto.MovieResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final ApiDAO apiDAO;
    private final ObjectMapper mapper = new ObjectMapper();

    public MovieService(ApiDAO apiDAO){
        this.apiDAO = apiDAO;
    }

    // Obtiene lista de próximos estrenos
    public MovieResponse getUpcomingMovies(){
        String upcomingMoviesUrl = "https://api.themoviedb.org/3/movie/upcoming";
        try{
            String json = apiDAO.getFromApiKey(upcomingMoviesUrl);
            return mapper.readValue(json, MovieResponse.class);
        }catch (Exception e){
            System.out.println("Error al obtener la lista de peliculas desde getUpcomingMovies");
            e.printStackTrace();
        }
        return null;
    }

    // Obtiene lista de películas en cartelera
    public MovieResponse getNowPlaying(int page){
        String nowPlayingUrl = "https://api.themoviedb.org/3/movie/now_playing";
        try{
            String json = apiDAO.getFromApi(nowPlayingUrl, page);
            return mapper.readValue(json, MovieResponse.class);
        }catch (Exception e){
            System.out.println("Error al obtener la lista de peliculas desde getNowPlaying");
            e.printStackTrace();
        }
        return null;
    }

    // Obtiene trending day de películas
    public MovieResponse getTrendingDayMovies(int page){
        String trendingDayMoviesUrl = "https://api.themoviedb.org/3/trending/movie/day?";
        try{
            String json = apiDAO.getFromApi(trendingDayMoviesUrl, page);
            return mapper.readValue(json, MovieResponse.class);
        }catch (Exception e){
            System.out.println("Error al obtener la lista de peliculas desde getTrendingDayMovies");
            e.printStackTrace();
        }
        return null;
    }

    // Obtiene las películas mejor valoradas
    public MovieResponse getTopMovies(int page){
        String topMoviesUrl = "https://api.themoviedb.org/3/movie/top_rated";
        try{
            String json = apiDAO.getFromApiKeyPage(topMoviesUrl, page);
            return mapper.readValue(json, MovieResponse.class);
        } catch (Exception e){
            System.out.println("Error al obtener la lista de peliculas desde getTopMovies");
            e.printStackTrace();
        }
        return null;
    }

    // Obtiene detalles de una pelicula mediante su id
    public FilmDetails getFilmDetails(int idPelicula){
        String detailsFilmUrl = "https://api.themoviedb.org/3/movie/" + idPelicula;
        try{
            String json = apiDAO.getFromApiKey(detailsFilmUrl);
            return mapper.readValue(json, FilmDetails.class);
        }catch (Exception e){
            System.out.println("Error al obtener la lista de peliculas desde getFilmDetails");
            e.printStackTrace();
        }
        return null;
    }

    public MovieResponse getRelatedMovies(int idPelicula){
        String relatedMoviesUrl = "https://api.themoviedb.org/3/movie/" + idPelicula + "/similar";
        try{
            String json = apiDAO.getFromApiKey(relatedMoviesUrl);
            return mapper.readValue(json, MovieResponse.class);
        }catch (Exception e){
            System.out.println("Error al obtener la lista de peliculas relacionadas desde getRelatedMovies");
            e.printStackTrace();
        }
        return null;
    }

    public MovieCastCrewReponse getFamousMovieCredits(int idFamous) {
        String famousMoviesUrl = "https://api.themoviedb.org/3/person/" + idFamous + "/movie_credits";
        try {
            String json = apiDAO.getFromApiKey(famousMoviesUrl);
            return mapper.readValue(json, MovieCastCrewReponse.class);
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de peliculas relacionadas desde getFamousMovieCredits");
            e.printStackTrace();
            return null;
        }
    }

    // Casteo la los objetos Cast y Crew a tipo List<Movie>
    /*public MovieResponse unifyCastAndCrew(MovieCastCrewReponse response) {
        List<MovieResponse> movies = new ArrayList<>();

        for (MovieCast c : response.getMovieCast()) {
            MovieResponse m = new Movie();
            m.setId(c.getId());
            m.setTitle(c.getTitle());
            m.setPosterPath(c.getPosterPath());
            m.setVoteAverage(c.getVoteAverage());
            m.setReleaseDate(c.getDate());
            movies.add(m);
        }

        for (MovieCrew c : response.getMovieCrew()) {
            Movie m = new Movie();
            m.setId(c.getId());
            m.setTitle(c.getTitle());
            m.setPosterPath(c.getPosterPath());
            m.setVoteAverage(c.getVoteAverage());
            m.setReleaseDate(c.getReleaseDate());
            movies.add(m);
        }
        return movies;
    }*/
}

