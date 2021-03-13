package net.buscacio.vacccinationApi.controller;

/*
 * @author Paula Buscacio
 * */

import net.buscacio.vacccinationApi.model.State;
import net.buscacio.vacccinationApi.model.Vaccine;
import net.buscacio.vacccinationApi.service.RelatoriesService;
import net.buscacio.vacccinationApi.service.StateService;
import net.buscacio.vacccinationApi.service.VaccineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vaccination/relatories")
public class RelatoriesController {

    private final RelatoriesService relatoriesService;
    private final StateService stateService;
    private final VaccineService vaccineService;

    public RelatoriesController(RelatoriesService relatoriesService, StateService stateService, VaccineService vaccineService) {
        this.relatoriesService = relatoriesService;
        this.stateService = stateService;
        this.vaccineService = vaccineService;
    }

    @GetMapping("city/{id}")
    public ResponseEntity<String> getTotalVaccinatedCity(@PathVariable Long id) {
        return new ResponseEntity<>(this.relatoriesService.getTotalVaccinatedCity(id), HttpStatus.OK);
    }

    @GetMapping("state/{uf}")
    public ResponseEntity<String> geTtotalVaccinatedState(@PathVariable String uf) {
        State state = this.stateService.getStateByUf(uf);
        return state == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(this.relatoriesService.geTtotalVaccinatedState(uf));
    }


    @GetMapping("vaccine/{name}")
    public ResponseEntity<String> getTotalVaccinatedVaccine(@PathVariable String name) {
        Vaccine vaccine = this.vaccineService.getVaccineByName(name);
        return vaccine == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(this.relatoriesService.getTotalVaccinatedVaccine(name));
    }

    @GetMapping("/total")
    public ResponseEntity<String> getTotalVaccinated() {
        return new ResponseEntity<>(this.relatoriesService.getTotalVaccinated(), HttpStatus.OK);
    }
}
