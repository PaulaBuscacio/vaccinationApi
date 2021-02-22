package net.buscacio.vacccinationApi.repository;

/*
 * @author Paula Buscacio
 * */

import net.buscacio.vacccinationApi.model.Vaccinated;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinatedRepository extends JpaRepository <Vaccinated, Long> {}
