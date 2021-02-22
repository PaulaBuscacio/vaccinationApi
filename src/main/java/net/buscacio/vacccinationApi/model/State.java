package net.buscacio.vacccinationApi.model;

/*
 * @author Paula Buscacio
 * */

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = State.TABLE_NAME)
public class State {

    public static final String TABLE_NAME = "estado";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "uf")
    private String uf;

    @JsonIgnore
    @OneToMany(targetEntity = City.class, mappedBy = "estado")
    private List<City> cities;

}
