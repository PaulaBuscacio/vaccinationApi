package net.buscacio.vacccinationApi.service;

/*
 * @author Paula Buscacio
 * */

import net.buscacio.vacccinationApi.model.State;
import net.buscacio.vacccinationApi.repository.StateRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StateService {

    private StateRepository stateRepository;

    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public State saveState(State state) {
        return stateRepository.save(state);
    }

    public State updateState(State state) {
        return stateRepository.save(state);
    }

    public void deleteState(Long id) {
         stateRepository.deleteById(id);
    }

    public List<State> getAllStates() {
        return stateRepository.findAll();
    }

    public State getStateById(Long id) {
        return stateRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Estado com o id %d não encontrado", id)));
    }

    public State getStateByUf(String uf) {
        return stateRepository.findByUf(uf);
    }
}
