package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.entity.Domicilio;
import com.example.clinicaOdontologica.dto.DomicilioDTO;

import java.util.Set;

public interface IDomicilioService {
    void crearDomicilio(Domicilio domicilio);
    DomicilioDTO getDomicilio(Long id);
    void modificarDomicilio(Domicilio domicilio);
    void eliminarDomicilio(Long id);
    Set<DomicilioDTO> getAll();
}
