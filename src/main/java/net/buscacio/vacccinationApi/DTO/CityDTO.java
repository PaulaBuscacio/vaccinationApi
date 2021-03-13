package net.buscacio.vacccinationApi.DTO;

/*
 * @author Paula Buscacio
 * */

import lombok.Data;

@Data
public class CityDTO {

    private Long id;
    private String nome;
    private String uf;
}
