package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.model.Domicilio;
import com.example.clinicaOdontologica.model.DomicilioDTO;

import java.util.Set;

public interface IDomicilioService {
    void crearDomicilio(Domicilio domicilio);
    DomicilioDTO getDomicilio(Long id);
    void modificarDomicilio(Domicilio domicilio);
    void eliminarDomicilio(Long id);
    Set<DomicilioDTO> getAll();
}
