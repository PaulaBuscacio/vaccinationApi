package net.buscacio.vacccinationApi.controller;

/*
 * @author Paula Buscacio
 * */

import net.buscacio.vacccinationApi.DTO.CityDTO;
import net.buscacio.vacccinationApi.mapper.VaccinationConverter;
import net.buscacio.vacccinationApi.model.City;
import net.buscacio.vacccinationApi.service.CityService;
import net.buscacio.vacccinationApi.service.StateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("vaccination/city")
public class CityController {

    private final CityService cityService;
    private final StateService stateService;
    private final VaccinationConverter vaccinationConverter;

    public CityController(CityService cityService, StateService stateService, VaccinationConverter vaccinationConverter) {
        this.cityService = cityService;
        this.stateService = stateService;
        this.vaccinationConverter = vaccinationConverter;
    }

    @GetMapping("")
    public ResponseEntity<List<CityDTO>> getAllCities() {
        List<City> cities = cityService.getAllCities();
        cities.stream().map(vaccinationConverter::convertCityToDTO).collect(Collectors.toList());
        return new ResponseEntity(cities, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable Long id) {
        return new ResponseEntity(this.vaccinationConverter.convertCityToDTO(cityService.getCityById(id)), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CityDTO> saveCity(@RequestBody CityDTO cityDTO) {
        City mappedCity = this.vaccinationConverter.convertCityDTOToEntity(cityDTO);
        return new ResponseEntity<>(this.vaccinationConverter.convertCityToDTO(this.cityService.saveCity(mappedCity, cityDTO.getUF())), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDTO> updateCity(@PathVariable Long id, @RequestBody CityDTO cityDTO) {
        City city = cityService.getCityById(id);
        city.setName(cityDTO.getNome());
        city.setUf(cityDTO.getUF());
        return new ResponseEntity<>(this.vaccinationConverter.convertCityToDTO(this.cityService.updateCity(city)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCityById(@PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.ok("Cidade deletada com sucesso");
    }

}
