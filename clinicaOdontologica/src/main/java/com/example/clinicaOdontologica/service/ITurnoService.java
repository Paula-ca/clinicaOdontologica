package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.model.TurnoDTO;
import com.example.clinicaOdontologica.model.Turno;

import java.time.LocalDateTime;
import java.util.Set;

public interface ITurnoService {
    void crearTurno(Long id_paciente, Long id_odontologo, LocalDateTime datetime);
    TurnoDTO getTurno(Long id);
    void modificarTurno(Turno turno);
    void eliminarTurno(Long id);
    Set<TurnoDTO> getAll();
}