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
        List<Vaccine> vaccineList = vaccineService.getAllVaccines();
        vaccineList.stream().map(vaccinationConverter :: convertVaccineToDTO).collect(Collectors.toList());
        return new ResponseEntity(vaccineList, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<VaccineDTO> findVaccineByName(@PathVariable String name) {
        VaccineDTO vaccineDTO = this.vaccinationConverter.convertVaccineToDTO((vaccineService.getVaccineByName(name)));
        return vaccineDTO == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(vaccineDTO);
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
        return new ResponseEntity<>(this.vaccinationConverter.convertVaccineToDTO(this.vaccineService.updateVaccine(vaccine)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVaccineById(@PathVariable Long id) {
        vaccineService.deleteVacine(id);
        return ResponseEntity.ok("Vacina deletada com sucesso");
    }
}
