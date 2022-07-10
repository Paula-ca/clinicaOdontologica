package com.example.clinicaOdontologica.service.impl;


import com.example.clinicaOdontologica.exceptions.BadRequestException;
import com.example.clinicaOdontologica.exceptions.GlobalExceptionHandler;
import com.example.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologica.model.Odontologo;
import com.example.clinicaOdontologica.model.PacienteDTO;
import com.example.clinicaOdontologica.model.Paciente;
import com.example.clinicaOdontologica.repository.IPacienteRepository;
import com.example.clinicaOdontologica.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class PacienteService implements IPacienteService {


    @Autowired
    private IPacienteRepository pacienteRepository;
    @Autowired
    ObjectMapper mapper;

    @Override
    public void crearPaciente(Paciente paciente) throws BadRequestException {
        if(findByDni(paciente.getDni())==null){
            pacienteRepository.save(paciente);
        }else{
            throw new BadRequestException("");
        }
    }

    @Override
    public PacienteDTO getPaciente(Long id) {
        PacienteDTO pacienteDTO = null;
        Paciente paciente = pacienteRepository.findById(id).get();
        if (paciente != null) {
            pacienteDTO = mapper.convertValue(paciente, PacienteDTO.class);
        }
        return pacienteDTO;
    }
    @Override
    public void modificarPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    @Override
    public void eliminarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public Set<PacienteDTO> getAll() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        Set<PacienteDTO> pacientesDTO = new HashSet<>();
        for(Paciente paciente :pacientes){
            pacientesDTO.add(mapper.convertValue(paciente,PacienteDTO.class));
        }
        return pacientesDTO;

    }
    public Paciente findByDni(String dni){
        Paciente pacienteFind= null;
        List<Paciente> pacientes = pacienteRepository.findAll();
        for(Paciente paciente:pacientes){
            if(paciente.getDni().equals(dni)){
                pacienteFind = paciente;
            }
        }
        return pacienteFind;
    }}
