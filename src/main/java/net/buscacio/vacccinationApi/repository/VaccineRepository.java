package net.buscacio.vacccinationApi.repository;

/*
 * @author Paula Buscacio
 * */

import net.buscacio.vacccinationApi.model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineRepository extends JpaRepository <Vaccine, Long> {
    Vaccine findVaccineByName(String name);
}
