package net.buscacio.vacccinationApi.model;

/*
 * @author Paula Buscacio
 * */

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = Vaccinated.TABLE_NAME)
public class Vaccinated {

    public static final String TABLE_NAME = "vacinados";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    @JsonIgnore
    @ManyToOne(targetEntity = City.class)
    private City municipio;

    @JsonIgnore
    @ManyToOne(targetEntity = Vaccine.class)
    private Vaccine vacina;

    @Column(name = "data")
    private LocalDate date;

}
