package com.example.clinicaOdontologica.service.impl;

import com.example.clinicaOdontologica.exceptions.BadRequestException;
import com.example.clinicaOdontologica.exceptions.GlobalExceptionHandler;
import com.example.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologica.model.OdontologoDTO;
import com.example.clinicaOdontologica.model.Odontologo;
import com.example.clinicaOdontologica.repository.IOdontologoRepository;
import com.example.clinicaOdontologica.service.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OdontologoService implements IOdontologoService {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);
    @Autowired
    private IOdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;


    @Override
    public void crearOdontologo(Odontologo odontologo) throws BadRequestException {
        if(findByMatricula(odontologo.getMatricula())== false) {
            odontologoRepository.save(odontologo);
        }else{
            throw new BadRequestException("El odontologo que intenta crear ya existe o es inválido.");
        }
    }

    @Override
    public OdontologoDTO getOdontologo(Long id) throws ResourceNotFoundException{
        OdontologoDTO odontologoDTO = null;
        try {
            Odontologo odontologo = odontologoRepository.findById(id).get();
            if (odontologo != null) {
                odontologoDTO = mapper.convertValue(odontologo, OdontologoDTO.class);
            }
        }catch (Exception ex){
            throw new ResourceNotFoundException("El odontologo con id "+id+" no existe.");
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
    public Boolean findByMatricula(Integer mat){
        boolean odontologoFind= false;
        List<Odontologo> odontologos = odontologoRepository.findAll();
        for(Odontologo odontologo:odontologos){
            if(odontologo.getMatricula().equals(mat)){
                odontologoFind = true;
            }
        }
        return odontologoFind;
    }
}
