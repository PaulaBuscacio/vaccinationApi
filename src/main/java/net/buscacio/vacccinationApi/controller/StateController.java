package net.buscacio.vacccinationApi.controller;

/*
 * @author Paula Buscacio
 * */

import net.buscacio.vacccinationApi.DTO.StateDTO;
import net.buscacio.vacccinationApi.mapper.VaccinationConverter;
import net.buscacio.vacccinationApi.model.State;
import net.buscacio.vacccinationApi.service.StateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("vaccination/state")
public class StateController {

    private final StateService stateService;
    private final VaccinationConverter vaccinationConverter;

    public StateController(StateService stateService, VaccinationConverter vaccinationConverter) {
        this.stateService = stateService;
        this.vaccinationConverter = vaccinationConverter;
    }

    @GetMapping("")
    public ResponseEntity<List<StateDTO>> getAllStates() {
        List<State> states = this.stateService.getAllStates();
        List<StateDTO> stateDTOList = states.stream().map(this.vaccinationConverter::convertStateToDTO).collect(Collectors.toList());
        return new ResponseEntity(stateDTOList, HttpStatus.OK);
    }

    @GetMapping("/{uf}")
    public ResponseEntity<StateDTO> getStateByUf(@PathVariable String uf) {
       State state = this.stateService.getStateByUf(uf);
        return state == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(this.vaccinationConverter.convertStateToDTO(state));
    }

    @PostMapping("")
    public ResponseEntity<StateDTO> saveState(@RequestBody StateDTO stateDTO) {
        State mappedState = this.vaccinationConverter.convertStateDTOToEntity(stateDTO);
        return new ResponseEntity<>(this.vaccinationConverter.convertStateToDTO(this.stateService.saveState(mappedState)), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<StateDTO> updateState(@RequestBody StateDTO stateDTO, @PathVariable Long id) {
         State state = this.stateService.getStateById(id);
         state.setName(stateDTO.getNome());
         state.setUf(stateDTO.getSigla());
        return new ResponseEntity<>(this.vaccinationConverter.convertStateToDTO(this.stateService.saveState(state)), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStateById(@PathVariable Long id) {
        this.stateService.deleteState(id);
        return  ResponseEntity.ok("Estado deletado com sucesso");
    }

}
