package net.buscacio.vacccinationApi.service;

/*
 * @author Paula Buscacio
 * */

import net.buscacio.vacccinationApi.model.Vaccine;
import net.buscacio.vacccinationApi.repository.VaccineRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class VaccineService {
    private VaccineRepository vaccineRepository;

    public VaccineService(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    public Vaccine saveVaccine(Vaccine vaccine) {
        return vaccineRepository.save(vaccine);
    }

    public Vaccine updateVaccine(Vaccine vaccine) {
         return vaccineRepository.save(vaccine);
    }

    public List<Vaccine> getAllVaccines() {
        return vaccineRepository.findAll();
    }

      public Vaccine getVaccineByName(String name) {
          return vaccineRepository.findVaccineByName(name);
    }

    public void deleteVacine(Long id) {
         vaccineRepository.deleteById(id);
    }

    public Vaccine getVaccineById(Long id) {
        return vaccineRepository.findById(id).orElseThrow(() ->
         new EntityNotFoundException(String.format("Vacina com o id %d não encontrado", id)));
    }
}
