package com.example.clinicaOdontologica.service.impl;


import com.example.clinicaOdontologica.exceptions.BadRequestException;
import com.example.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologica.dto.PacienteDTO;
import com.example.clinicaOdontologica.entity.Paciente;
import com.example.clinicaOdontologica.repository.IPacienteRepository;
import com.example.clinicaOdontologica.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;

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

            if (findByDni(paciente.getDni()) == false) {
                pacienteRepository.save(paciente);
            }else{
                throw new BadRequestException("El paciente que intenta crear ya existe o es inválido.");
        }
    }

    @Override
    public PacienteDTO getPaciente(Long id) throws ResourceNotFoundException{
        PacienteDTO pacienteDTO = null;
        try{
        Paciente paciente = pacienteRepository.findById(id).get();
        if (paciente != null) {
            pacienteDTO = mapper.convertValue(paciente, PacienteDTO.class);

        }
        }catch(Exception ex){
            throw new ResourceNotFoundException("El paciente con id "+id+" no existe.");
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
    public Boolean findByDni(String dni){
        boolean pacienteFind= false;
        List<Paciente> pacientes = pacienteRepository.findAll();
        for(Paciente paciente:pacientes){
            if(paciente.getDni().equals(dni)){
                pacienteFind = true;
            }
        }
        return pacienteFind;
    }}
