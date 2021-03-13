package net.buscacio.vacccinationApi.service;

/*
 * @author Paula Buscacio
 * */

import net.buscacio.vacccinationApi.model.City;
import net.buscacio.vacccinationApi.model.State;
import net.buscacio.vacccinationApi.model.Vaccine;
import org.springframework.stereotype.Service;


@Service
public class RelatoriesService {

    private final StateService stateService;
    private final CityService cityService;
    private final VaccineService vaccineService;
    private final VaccinatedService vaccinatedService;


    public RelatoriesService(StateService stateService, CityService cityService, VaccineService vaccineService,
                                VaccinatedService vaccinatedService) {
        this.stateService = stateService;
        this.cityService = cityService;
        this.vaccineService = vaccineService;
        this.vaccinatedService = vaccinatedService;
    }

    public String getTotalVaccinatedCity(Long id) {
        City city = cityService.getCityById(id);
        String message = "Total de vacinados no município " + city.getName() + ": " + city.getVacinados().size();
        return  message;
    }

    public String geTtotalVaccinatedState(String uf) {
        
        State state = stateService.getStateByUf(uf);
        Integer vacinated;
        Integer sum = 0;

        for (City city : state.getCities()) {
            vacinated = city.getVacinados().size();
            sum = sum + vacinated;
        }
        String message = "Total de vacinados no Estado " + state.getName() + ": " + sum;
        return message;


    }

    public String getTotalVaccinatedVaccine(String name) {

        Vaccine vaccine = vaccineService.getVaccineByName(name);
        String message = "Total de vacinados pela vacina " + vaccine.getName() + ": " + vaccine.getVacinados().size();
        return message;

    }

    public String getTotalVaccinated() {
        Integer total = vaccinatedService.getAllVaccinated().size();
        String message = "Total de vacinados: " + total;
        return message;
    }
}
