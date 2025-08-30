package com.peliculasonlinehd.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peliculasonlinehd.dao.ApiDAO;
import com.peliculasonlinehd.dto.CreditsResponse;
import com.peliculasonlinehd.dto.PeopleResponse;
import com.peliculasonlinehd.entity.Cast;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleService {

    private final ApiDAO apiDAO;
    private final ObjectMapper mapper = new ObjectMapper();

    public PeopleService(ApiDAO apiDAO){

        this.apiDAO = apiDAO;
    }

    // Obtiene lista de famosos
    public PeopleResponse getFamousPeople(){
        String peopleUrl = "https://api.themoviedb.org/3/person/popular";
        try{
            String json = apiDAO.getFromApi(peopleUrl);
            return mapper.readValue(json, PeopleResponse.class);
        } catch(Exception e){
            System.out.println("Error al obtener la lista de famosos desde getFamousPeople");
            e.printStackTrace();
        }
        return null;
    }

    public CreditsResponse getCast(int idPelicula){
        String castUrl = "https://api.themoviedb.org/3/movie/" + idPelicula + "/credits";
        try{
            String json = apiDAO.getFromApiKey(castUrl);
            return mapper.readValue(json, CreditsResponse.class);
        } catch(Exception e){
            System.out.println("Error al obtener la lista de cast desde getCast");
            e.printStackTrace();
        }
        return null;
    }
}
