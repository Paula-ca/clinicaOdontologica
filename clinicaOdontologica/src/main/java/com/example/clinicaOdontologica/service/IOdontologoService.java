package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.exceptions.BadRequestException;
import com.example.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologica.dto.OdontologoDTO;
import com.example.clinicaOdontologica.entity.Odontologo;

import java.util.Set;

public interface IOdontologoService {
    void crearOdontologo(Odontologo odontologo)throws BadRequestException;
    OdontologoDTO getOdontologo(Long id)throws ResourceNotFoundException;
    void modificarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Long id);
    Set<OdontologoDTO> getAll();

}
