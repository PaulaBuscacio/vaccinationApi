package net.buscacio.vacccinationApi.service;

/*
 * @author Paula Buscacio
 * */

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

    public VaccinatedService(VaccinatedRepository vaccinatedRepository, CityRepository cityRepository,
                             VaccineRepository vaccineRepository, CityService cityService) {
        this.vaccinatedRepository = vaccinatedRepository;
        this.cityRepository = cityRepository;
        this.vaccineRepository = vaccineRepository;
        this.cityService = cityService;
    }

    public Vaccinated saveVaccinated(Vaccinated vaccinated, Long id, String vacina) {
        City city = cityService.getCityById(id);
        Vaccine vaccine = vaccineRepository.findVaccineByName(vacina);
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
                new EntityNotFoundException(String.format("Pessoa vacinada com o id %d não encontrada", id)));
    }

    public void deleteVaccinated(Long id) {
        vaccinatedRepository.deleteById(id);
    }
}
