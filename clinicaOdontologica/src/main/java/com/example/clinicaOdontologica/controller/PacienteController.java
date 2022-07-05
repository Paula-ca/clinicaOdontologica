package com.example.clinicaOdontologica.controller;


import com.example.clinicaOdontologica.model.PacienteDTO;
import com.example.clinicaOdontologica.model.Paciente;
import com.example.clinicaOdontologica.service.impl.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getPaciente(@PathVariable Long id){
        return ResponseEntity.ok().body(
                pacienteService.getPaciente(id));
    }

    @PostMapping
    public ResponseEntity<Paciente> crearPaciente(@RequestBody Paciente paciente){
        pacienteService.crearPaciente(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) {
        ResponseEntity response;
        try {
            pacienteService.eliminarPaciente(id);
            response = ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body(e.getMessage());
        }
        return response;
    }
    @PutMapping
    public ResponseEntity<Paciente> modificarPaciente(@RequestBody Paciente paciente){
        pacienteService.modificarPaciente(paciente);
        return ResponseEntity.ok().body(paciente);
    }
    @GetMapping
    public Collection<PacienteDTO> getAll() {
        return pacienteService.getAll();
    }



}
