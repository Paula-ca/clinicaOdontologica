package com.example.clinicaOdontologica.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name="Turnos")
public class Turno {
    @Autowired
    ObjectMapper mapper;

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name="turno_sequence",sequenceName = "turno_sequence")
    @GeneratedValue(generator = "turno_sequence",strategy = GenerationType.SEQUENCE)
    private Long id;

    private LocalDateTime fechaYHora;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "odontologo_id",nullable = false)
    private Odontologo odontologo;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "paciente_id",nullable = false)
    private Paciente paciente;

    public Long getId() {
        return id;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {

        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
