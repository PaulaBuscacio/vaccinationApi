package net.buscacio.vacccinationApi.model;

/*
 * @author Paula Buscacio
 * */

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = Vaccine.TABLE_NAME)
public class Vaccine {

    public static final String TABLE_NAME = "vacina";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "origem")
    private String origin;

    @OneToMany(targetEntity = Vaccinated.class, mappedBy = "vacina")
    private List<Vaccinated> vacinados;
}
