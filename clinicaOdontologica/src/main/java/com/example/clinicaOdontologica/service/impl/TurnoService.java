package com.example.clinicaOdontologica.service.impl;

import com.example.clinicaOdontologica.exceptions.BadRequestException;
import com.example.clinicaOdontologica.exceptions.GlobalExceptionHandler;
import com.example.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologica.model.*;

import com.example.clinicaOdontologica.repository.IOdontologoRepository;
import com.example.clinicaOdontologica.repository.IPacienteRepository;
import com.example.clinicaOdontologica.repository.ITurnoRepository;
import com.example.clinicaOdontologica.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

import java.util.Set;

@Service
public class TurnoService implements ITurnoService {

    @Autowired
    private ITurnoRepository turnoRepository;
    @Autowired
    private IOdontologoRepository odontologoRepository;
    @Autowired
    private IPacienteRepository pacienteRepository;


    @Autowired
    ObjectMapper mapper;

    @Override
    public void crearTurno(Turno turno) throws BadRequestException {
        Odontologo odontologoTurno = turno.getOdontologo();
        Paciente pacienteTurno = turno.getPaciente();
    try{
        if(odontologoRepository.findById(odontologoTurno.getId())!=null && pacienteRepository.findById(pacienteTurno.getId())!=null) {
            turnoRepository.save(turno);}
    }catch(Exception e){
        throw new BadRequestException("El odontologo o paciente del turno no existe.");

    }
    }

    @Override
    public TurnoDTO getTurno(Long id) throws ResourceNotFoundException{
        TurnoDTO turnoDTO = null;
        try {
            Turno turno = turnoRepository.findById(id).get();

            if (turno != null) {
                turnoDTO = mapper.convertValue(turno, TurnoDTO.class);
            }
        }catch (Exception ex){
            throw new ResourceNotFoundException("El turno con id "+id+" no existe.");
        }
        return turnoDTO;
    }

    @Override
    public void modificarTurno(Turno turno) {
        turnoRepository.save(turno);
    }

    @Override
    public void eliminarTurno(Long id) {
        turnoRepository.deleteById(id);
    }

    @Override
    public Set<TurnoDTO> getAll() {
        List<Turno> turnos = turnoRepository.findAll();
        Set<TurnoDTO> turnosDTO = new HashSet<>();
        for(Turno turno :turnos){
            turnosDTO.add(mapper.convertValue(turno,TurnoDTO.class));
        }
        return turnosDTO;
    }

}
