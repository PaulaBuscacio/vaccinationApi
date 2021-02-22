package net.buscacio.vacccinationApi.repository;

/*
 * @author Paula Buscacio
 * */

import net.buscacio.vacccinationApi.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository <City, Long> {

}

