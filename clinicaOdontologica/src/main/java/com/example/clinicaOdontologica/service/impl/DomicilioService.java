package com.example.clinicaOdontologica.service.impl;

import com.example.clinicaOdontologica.model.Domicilio;
import com.example.clinicaOdontologica.model.DomicilioDTO;
import com.example.clinicaOdontologica.model.Odontologo;
import com.example.clinicaOdontologica.model.OdontologoDTO;
import com.example.clinicaOdontologica.repository.IDomicilioRepository;
import com.example.clinicaOdontologica.service.IDomicilioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DomicilioService implements IDomicilioService {
    @Autowired
    private IDomicilioRepository domicilioRepository;
    @Autowired
    ObjectMapper mapper;

    @Override
    public void crearDomicilio(Domicilio domicilio) {
        domicilioRepository.save(domicilio);
    }

    @Override
    public DomicilioDTO getDomicilio(Long id) {
        DomicilioDTO domicilioDTO = null;
        Domicilio domicilio = domicilioRepository.findById(id).get();
        if (domicilio != null) {
            domicilioDTO = mapper.convertValue(domicilio,DomicilioDTO.class);
        }
        return domicilioDTO;
    }

    @Override
    public void modificarDomicilio(Domicilio domicilio) {
        domicilioRepository.save(domicilio);
    }

    @Override
    public void eliminarDomicilio(Long id) {
        domicilioRepository.deleteById(id);
    }

    @Override
    public Set<DomicilioDTO> getAll() {
        List<Domicilio> domicilios = domicilioRepository.findAll();
        Set<DomicilioDTO> domiciliosDTO = new HashSet<>();
        for(Domicilio domicilio :domicilios){
            domiciliosDTO.add(mapper.convertValue(domicilio,DomicilioDTO.class));
        }
        return domiciliosDTO;
    }
}
