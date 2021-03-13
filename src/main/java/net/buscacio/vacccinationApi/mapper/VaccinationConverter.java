package net.buscacio.vacccinationApi.mapper;

/*
 * @author Paula Buscacio
 * */

import net.buscacio.vacccinationApi.DTO.CityDTO;
import net.buscacio.vacccinationApi.DTO.StateDTO;
import net.buscacio.vacccinationApi.DTO.VaccinatedDTO;
import net.buscacio.vacccinationApi.DTO.VaccineDTO;
import net.buscacio.vacccinationApi.model.City;
import net.buscacio.vacccinationApi.model.State;
import net.buscacio.vacccinationApi.model.Vaccinated;
import net.buscacio.vacccinationApi.model.Vaccine;

import org.springframework.stereotype.Component;

@Component
public class VaccinationConverter {

    //convert Entities to DTOs
    public StateDTO convertStateToDTO(State state) {
        StateDTO stateDTO = new StateDTO();
        stateDTO.setId(state.getId());
        stateDTO.setNome(state.getName());
        stateDTO.setSigla(state.getUf());
        return stateDTO;
    }

    public CityDTO convertCityToDTO(City city) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setNome(city.getName());
        cityDTO.setUf(city.getEstado().getUf());
        return cityDTO;
    }

    public VaccineDTO convertVaccineToDTO(Vaccine vaccine) {
        VaccineDTO vaccineDTO = new VaccineDTO();
        vaccineDTO.setId(vaccine.getId());
        vaccineDTO.setNome(vaccine.getName());
        vaccineDTO.setOrigem(vaccine.getOrigin());
        return vaccineDTO;
    }

    public VaccinatedDTO convertVaccinatedToDTO(Vaccinated vaccinated) {
        VaccinatedDTO vaccinatedDTO = new VaccinatedDTO();
        vaccinatedDTO.setId(vaccinated.getId());
        vaccinatedDTO.setNome(vaccinated.getName());
        vaccinatedDTO.setMunicipio(vaccinated.getMunicipio().getId());
        vaccinatedDTO.setVacina(vaccinated.getVacina().getId());
        vaccinatedDTO.setData(vaccinated.getDate());
        return vaccinatedDTO;
    }


    //convert DTOs to Entities
    public State convertStateDTOToEntity(StateDTO stateDTO) {
        State state = new State();
        state.setId(stateDTO.getId());
        state.setName(stateDTO.getNome());
        state.setUf(stateDTO.getSigla());
        return state;
    }

    public City convertCityDTOToEntity(CityDTO cityDTO) {
        City city = new City();
        city.setId(cityDTO.getId());
        city.setName(cityDTO.getNome());
        return city;

    }

    public Vaccine convertVaccineDTOToEntity(VaccineDTO vaccineDTO) {
        Vaccine vaccine = new Vaccine();
        vaccine.setId(vaccineDTO.getId());
        vaccine.setName(vaccineDTO.getNome());
        vaccine.setOrigin(vaccineDTO.getOrigem());
        return vaccine;
    }

    public Vaccinated convertVaccinatedDTOToEntity(VaccinatedDTO vaccinatedDTO) {
        Vaccinated vaccinated = new Vaccinated();
        vaccinated.setId(vaccinatedDTO.getId());
        vaccinated.setName(vaccinatedDTO.getNome());
        vaccinated.setDate(vaccinatedDTO.getData());
        return vaccinated;
    }

}
