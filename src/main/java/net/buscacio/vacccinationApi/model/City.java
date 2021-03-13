package net.buscacio.vacccinationApi.model;

/*
 * @author Paula Buscacio
 * */

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = City.TABLE_NAME)
public class City {
    public static final String TABLE_NAME = "cidade";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    @ManyToOne(targetEntity = State.class)
    private State estado;

    @OneToMany(targetEntity = Vaccinated.class, mappedBy = "municipio")
    private List<Vaccinated> vacinados;

}
