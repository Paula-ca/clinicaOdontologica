package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.exceptions.BadRequestException;
import com.example.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologica.dto.TurnoDTO;
import com.example.clinicaOdontologica.entity.Turno;

import java.util.Set;

public interface ITurnoService {
    void crearTurno(Turno turno) throws BadRequestException;
    TurnoDTO getTurno(Long id)throws ResourceNotFoundException;
    void modificarTurno(Turno turno);
    void eliminarTurno(Long id);
    Set<TurnoDTO> getAll();
}
