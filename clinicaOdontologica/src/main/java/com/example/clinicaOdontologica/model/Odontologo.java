package com.example.clinicaOdontologica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Odontologos")
public class Odontologo {
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name="odontologo_sequence",sequenceName = "odontologo_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "odontologo_sequence")
    private Long id;

    private String nombre;
    private String apellido;
    private Integer matricula;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "odontologo")
    @JsonIgnore
    private Set<Turno> turnos=new HashSet<>();


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Long getId() {
        return id;
    }

    public Set<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(Set<Turno> turnos) {
        this.turnos = turnos;
    }
}


