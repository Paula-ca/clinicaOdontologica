package com.example.clinicaOdontologica.service.impl;

import com.example.clinicaOdontologica.model.OdontologoDTO;
import com.example.clinicaOdontologica.model.Odontologo;
import com.example.clinicaOdontologica.repository.IOdontologoRepository;
import com.example.clinicaOdontologica.service.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OdontologoService implements IOdontologoService {
    @Autowired
    private IOdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;


    @Override
    public void crearOdontologo(Odontologo odontologo) {
       odontologoRepository.save(odontologo);

    }

    @Override
    public OdontologoDTO getOdontologo(Long id) {
        OdontologoDTO odontologoDTO = null;
        Odontologo odontologo = odontologoRepository.findById(id).get();
        if (odontologo != null) {
            odontologoDTO = mapper.convertValue(odontologo,OdontologoDTO.class);
        }
        return odontologoDTO;

    }

    @Override
    public void modificarOdontologo(Odontologo odontologo) {
        odontologoRepository.save(odontologo);
    }

    @Override
    public void eliminarOdontologo(Long id) {
        odontologoRepository.deleteById(id);
    }

    @Override
    public Set<OdontologoDTO> getAll() {
        List<Odontologo> odontologos = odontologoRepository.findAll();
        Set<OdontologoDTO> odontologosDTO = new HashSet<>();
        for(Odontologo odontologo :odontologos){
            odontologosDTO.add(mapper.convertValue(odontologo,OdontologoDTO.class));
        }
        return odontologosDTO;
    }
}
