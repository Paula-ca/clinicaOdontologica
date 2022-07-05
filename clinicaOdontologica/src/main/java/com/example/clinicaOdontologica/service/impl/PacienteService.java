package com.example.clinicaOdontologica.service.impl;


import com.example.clinicaOdontologica.model.PacienteDTO;
import com.example.clinicaOdontologica.model.Paciente;
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
    public void crearPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
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
}
