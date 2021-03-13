package net.buscacio.vacccinationApi.controller;

/*
 * @author Paula Buscacio
 * */

import net.buscacio.vacccinationApi.DTO.VaccineDTO;
import net.buscacio.vacccinationApi.mapper.VaccinationConverter;
import net.buscacio.vacccinationApi.model.Vaccine;
import net.buscacio.vacccinationApi.service.VaccineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("vaccination/vaccine")
public class VaccineController {

    private final VaccineService vaccineService;
    private final VaccinationConverter vaccinationConverter;

    public VaccineController(VaccineService vaccineService, VaccinationConverter vaccinationConverter) {
        this.vaccineService = vaccineService;
        this.vaccinationConverter = vaccinationConverter;
    }

    @GetMapping("")
    public ResponseEntity<List<VaccineDTO>> getAllVaccines() {
        List<Vaccine> vaccines = vaccineService.getAllVaccines();
        return new ResponseEntity(vaccines.stream().map(vaccinationConverter :: convertVaccineToDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<VaccineDTO> findVaccineByName(@PathVariable String name) {
        Vaccine vaccine = this.vaccineService.getVaccineByName(name);
        return vaccine == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(this.vaccinationConverter.convertVaccineToDTO(vaccine));

    }

    @PostMapping("")
    public ResponseEntity<VaccineDTO> saveVaccine(@RequestBody VaccineDTO vaccineDTO) {
        Vaccine mappedVaccine = this.vaccinationConverter.convertVaccineDTOToEntity(vaccineDTO);
        return new ResponseEntity<>(this.vaccinationConverter.convertVaccineToDTO(this.vaccineService.saveVaccine(mappedVaccine)),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    private ResponseEntity<VaccineDTO> updateVaccine(@PathVariable Long id, @RequestBody VaccineDTO vaccineDTO) {
        Vaccine vaccine = this.vaccineService.getVaccineById(id);
        vaccine.setName(vaccineDTO.getNome());
        vaccine.setOrigin(vaccineDTO.getOrigem());
        return new ResponseEntity<>(this.vaccinationConverter.convertVaccineToDTO(this.vaccineService.saveVaccine(vaccine)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVaccineById(@PathVariable Long id) {
        vaccineService.deleteVacine(id);
        return ResponseEntity.ok("Vacina deletada com sucesso");
    }
}
