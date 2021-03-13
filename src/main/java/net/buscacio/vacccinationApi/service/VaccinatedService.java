package net.buscacio.vacccinationApi.service;

/*
 * @author Paula Buscacio
 * */

import net.buscacio.vacccinationApi.DTO.VaccinatedDTO;
import net.buscacio.vacccinationApi.exceptionhandler.RessourceException;
import net.buscacio.vacccinationApi.model.City;
import net.buscacio.vacccinationApi.model.Vaccinated;
import net.buscacio.vacccinationApi.model.Vaccine;
import net.buscacio.vacccinationApi.repository.CityRepository;
import net.buscacio.vacccinationApi.repository.VaccinatedRepository;
import net.buscacio.vacccinationApi.repository.VaccineRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class VaccinatedService {

    private VaccinatedRepository vaccinatedRepository;
    private CityRepository cityRepository;
    private VaccineRepository vaccineRepository;
    private CityService cityService;
    private VaccineService vaccineService;

    public VaccinatedService(VaccinatedRepository vaccinatedRepository, CityRepository cityRepository,
                             VaccineRepository vaccineRepository, CityService cityService, VaccineService vaccineService) {
        this.vaccinatedRepository = vaccinatedRepository;
        this.cityRepository = cityRepository;
        this.vaccineRepository = vaccineRepository;
        this.cityService = cityService;
        this.vaccineService = vaccineService;
    }

    public Vaccinated saveVaccinated(Vaccinated vaccinated, Long cityId, Long vacinaId) {
        City city = cityService.getCityById(cityId);
        Vaccine vaccine = vaccineService.getVaccineById(vacinaId);
        vaccinated.setMunicipio(city);
        vaccinated.setVacina(vaccine);
        vaccinated.setDate(LocalDate.now());
        return vaccinatedRepository.save(vaccinated);
    }

    public Vaccinated updateVaccinated(Vaccinated vaccinated) {
        return vaccinatedRepository.save(vaccinated);
    }

    public List<Vaccinated> getAllVaccinated() {
        return vaccinatedRepository.findAll();
    }

    public Vaccinated getVaccinatedById(Long id) {
        return vaccinatedRepository.findById(id).orElseThrow(() ->
                new RessourceException(String.format("Pessoa vacinada com o id %d não encontrada", id)));
    }

    public void deleteVaccinated(Long id) {
        Vaccinated vaccinated = getVaccinatedById(id);
        vaccinatedRepository.deleteById(id);
    }

}
