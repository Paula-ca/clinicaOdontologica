package com.example.clinicaOdontologica.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Setter
@Getter
public class Rol {

    @Id
    private Long id;
    private String nombre;
}
