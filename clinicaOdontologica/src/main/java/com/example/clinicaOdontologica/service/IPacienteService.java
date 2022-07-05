package com.example.clinicaOdontologica.service;


import com.example.clinicaOdontologica.model.PacienteDTO;

import com.example.clinicaOdontologica.model.Paciente;

import java.util.Set;

public interface IPacienteService {
    void crearPaciente(Paciente paciente);
    PacienteDTO getPaciente(Long id);
    void modificarPaciente(Paciente paciente);
    void eliminarPaciente(Long id);
    Set<PacienteDTO> getAll();
}
