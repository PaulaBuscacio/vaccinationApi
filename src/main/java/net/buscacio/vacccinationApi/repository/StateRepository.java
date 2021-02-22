package net.buscacio.vacccinationApi.repository;

/*
 * @author Paula Buscacio
 * */

import net.buscacio.vacccinationApi.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository <State, Long> {
    State findByUf(String uf);
}
