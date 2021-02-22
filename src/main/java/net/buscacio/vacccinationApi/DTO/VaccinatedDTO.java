package net.buscacio.vacccinationApi.DTO;

/*
 * @author Paula Buscacio
 * */

import lombok.Data;

import java.time.LocalDate;

@Data
public class VaccinatedDTO {

    private Long id;
    private String nome;
    private Long municipio;
    private LocalDate data;
    private String vacina;

}
