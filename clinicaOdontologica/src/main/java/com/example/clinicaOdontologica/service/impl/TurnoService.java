package com.example.clinicaOdontologica.service.impl;

import com.example.clinicaOdontologica.model.*;
import com.example.clinicaOdontologica.repository.IOdontologoRepository;
import com.example.clinicaOdontologica.repository.IPacienteRepository;
import com.example.clinicaOdontologica.repository.ITurnoRepository;
import com.example.clinicaOdontologica.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TurnoService implements ITurnoService {

    @Autowired
    private ITurnoRepository turnoRepository;
    @Autowired
    private IPacienteRepository pacienteRepository;
    @Autowired
    private IOdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public void crearTurno(Long id_paciente, Long id_odontologo, Date fecha , Time hora) {
        Turno turno = new Turno();
        turno.setFecha(fecha);
        turno.setHora(hora);
        turno.setOdontologo(odontologoRepository.findById(id_odontologo).get());
        turno.setPaciente(pacienteRepository.findById(id_paciente).get());

        turnoRepository.save(turno);
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
}
