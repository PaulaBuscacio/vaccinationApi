package net.buscacio.vacccinationApi.controller;

/*
 * @author Paula Buscacio
 * */

import net.buscacio.vacccinationApi.DTO.VaccinatedDTO;
import net.buscacio.vacccinationApi.mapper.VaccinationConverter;
import net.buscacio.vacccinationApi.model.Vaccinated;
import net.buscacio.vacccinationApi.service.CityService;
import net.buscacio.vacccinationApi.service.VaccinatedService;
import net.buscacio.vacccinationApi.service.VaccineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("vaccination/vaccinated")
public class VaccinatedController {

    private final VaccinatedService vaccinatedService;
    private final CityService cityService;
    private final VaccineService vaccineService;
    private final VaccinationConverter vaccinationConverter;

    public VaccinatedController(VaccinatedService vaccinatedService, CityService cityService, VaccineService vaccineService, VaccinationConverter vaccinationConverter) {
        this.vaccinatedService = vaccinatedService;
        this.cityService = cityService;
        this.vaccineService = vaccineService;
        this.vaccinationConverter = vaccinationConverter;
    }

    @GetMapping("")
    public ResponseEntity<List<VaccinatedDTO>> getAllVaccinated() {
        List<Vaccinated> vaccinatedList = this.vaccinatedService.getAllVaccinated();
        vaccinatedList.stream().map(this.vaccinationConverter::convertVaccinatedToDTO).collect(Collectors.toList());
        return new ResponseEntity(vaccinatedList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VaccinatedDTO> getVaccinatedById(@PathVariable Long id) {
        VaccinatedDTO vaccinatedDTO = this.vaccinationConverter.convertVaccinatedToDTO(this.vaccinatedService.getVaccinatedById(id));
        return vaccinatedDTO == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(vaccinatedDTO);
    }

    @PostMapping("")
    public ResponseEntity<VaccinatedDTO> saveVaccinated(@RequestBody VaccinatedDTO vaccinatedDTO) {
        Vaccinated mappedVaccinated = this.vaccinationConverter.convertVaccinatedDTOToEntity(vaccinatedDTO);
        return new ResponseEntity<>(this.vaccinationConverter.convertVaccinatedToDTO(this.vaccinatedService.saveVaccinated(
                mappedVaccinated, vaccinatedDTO.getMunicipio(), vaccinatedDTO.getVacina())), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VaccinatedDTO> updateVaccinated(@RequestBody VaccinatedDTO vaccinatedDTO, @PathVariable Long id) {
        Vaccinated vaccinated = this.vaccinatedService.getVaccinatedById(id);
        vaccinated.setName(vaccinatedDTO.getNome());
        vaccinated.setMunicipio(cityService.getCityById(vaccinatedDTO.getMunicipio()));
        vaccinated.setVacina(vaccineService.getVaccineByName(vaccinatedDTO.getVacina()));
        vaccinated.setDate(LocalDate.now());
        return new ResponseEntity<>(this.vaccinationConverter.convertVaccinatedToDTO(this.vaccinatedService.updateVaccinated(
                vaccinated)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVaccinatedById(@PathVariable Long id) {
        vaccinatedService.deleteVaccinated(id);
        return ResponseEntity.ok("Pessoa vacinada deletada com sucesso");
    }
}
