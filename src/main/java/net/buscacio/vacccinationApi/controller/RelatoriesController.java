package net.buscacio.vacccinationApi.controller;

/*
 * @author Paula Buscacio
 * */

import net.buscacio.vacccinationApi.service.RelatoriesService;
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

    public RelatoriesController(RelatoriesService relatoriesService) {
        this.relatoriesService = relatoriesService;
    }

    @GetMapping("city/{id}")
    public ResponseEntity<String> getTotalVaccinatedCity(@PathVariable Long id) {
        return new ResponseEntity<>(this.relatoriesService.getTotalVaccinatedCity(id), HttpStatus.OK);
    }

    @GetMapping("state/{uf}")
    public ResponseEntity<String> geTtotalVaccinatedState(@PathVariable String uf) {
        return new ResponseEntity<String>(this.relatoriesService.geTtotalVaccinatedState(uf), HttpStatus.OK);
    }


    @GetMapping("vaccine/{name}")
    public ResponseEntity<String> getTotalVaccinatedVaccine(@PathVariable String name) {
        return new ResponseEntity<>(this.relatoriesService.getTotalVaccinatedVaccine(name), HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<String> getTotalVaccinated() {
        return new ResponseEntity<>(this.relatoriesService.getTotalVaccinated(), HttpStatus.OK);
    }
}
