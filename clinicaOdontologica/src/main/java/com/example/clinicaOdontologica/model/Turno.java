package com.example.clinicaOdontologica.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

import java.sql.Date;
import java.sql.Time;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date fecha;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="hh:mm:ss")
    private Time hora;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "odontologo_id",nullable = false)
    private Odontologo odontologo;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "paciente_id",nullable = false)
    private Paciente paciente;

    public Long getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
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
