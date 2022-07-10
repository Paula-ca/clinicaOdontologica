package com.example.clinicaOdontologica.service.impl;

import com.example.clinicaOdontologica.exceptions.BadRequestException;
import com.example.clinicaOdontologica.exceptions.GlobalExceptionHandler;
import com.example.clinicaOdontologica.model.*;

import com.example.clinicaOdontologica.repository.ITurnoRepository;
import com.example.clinicaOdontologica.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;

import java.util.HashSet;
import java.util.List;

import java.util.Set;

@Service
public class TurnoService implements ITurnoService {

    @Autowired
    private ITurnoRepository turnoRepository;


    @Autowired
    ObjectMapper mapper;

    @Override
    public void crearTurno(Turno turno) throws BadRequestException {
       if(findTurno(turno.getOdontologo(),turno.getPaciente())==null ){
           turnoRepository.save(turno);
       }else{
           throw new BadRequestException("");
       }
    }

    @Override
    public TurnoDTO getTurno(Long id) {
        TurnoDTO turnoDTO = null;
        Turno turno = turnoRepository.findById(id).get();

        if (turno != null) {
            turnoDTO = mapper.convertValue(turno,TurnoDTO.class);
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
    public Turno findTurno(Odontologo odontologo, Paciente paciente){
        Turno turno = null;
        List<Turno> turnos = turnoRepository.findAll();
        for(Turno turnoList:turnos) {
            if (turnoList.getOdontologo().getId()==odontologo.getId() &&turnoList.getPaciente().getId() == paciente.getId()){
                turno = turnoList;
            }
        }
        return turno;
    }
}
