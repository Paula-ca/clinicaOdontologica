package com.example.clinicaOdontologica.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDTO {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date fecha;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="hh:mm:ss")
    private Time hora;
    private Paciente paciente;
    private Odontologo odontologo;

    public TurnoDTO() {
    }


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

    public Long getPaciente_id() {
        return paciente.getId();
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Long getOdontologo_id() {
        return odontologo.getId();
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }
}
