package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.exceptions.BadRequestException;
import com.example.clinicaOdontologica.model.OdontologoDTO;
import com.example.clinicaOdontologica.model.Odontologo;

import java.util.Set;

public interface IOdontologoService {
    void crearOdontologo(Odontologo odontologo)throws BadRequestException;
    OdontologoDTO getOdontologo(Long id);
    void modificarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Long id);
    Set<OdontologoDTO> getAll();

}
