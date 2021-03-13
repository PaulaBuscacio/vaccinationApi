package net.buscacio.vacccinationApi.service;

/*
 * @author Paula Buscacio
 * */

import net.buscacio.vacccinationApi.exceptionhandler.RessourceException;
import net.buscacio.vacccinationApi.model.City;
import net.buscacio.vacccinationApi.model.State;
import net.buscacio.vacccinationApi.repository.CityRepository;
import net.buscacio.vacccinationApi.repository.StateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private CityRepository cityRepository;
    private StateRepository stateRepository;

    public CityService(CityRepository cityRepository, StateRepository stateRepository) {
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
    }


    public City saveCity(City city, String uf) {
        State state = stateRepository.findByUf(uf);
        city.setEstado(state);
        return cityRepository.save(city);
    }

    public void deleteCity(Long id) {
        City city = getCityById(id);
        cityRepository.deleteById(id);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCityById(Long id) {
        return cityRepository.findById(id).orElseThrow(() ->
            new RessourceException(String.format("Município com o id %d não encontrado", id)));
    }

}
