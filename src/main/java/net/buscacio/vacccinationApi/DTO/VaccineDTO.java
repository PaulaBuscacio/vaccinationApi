package net.buscacio.vacccinationApi.DTO;

/*
 * @author Paula Buscacio
 * */

import lombok.Data;

@Data
public class VaccineDTO {

    private Long id;
    private String nome;
    private String origem;

}
