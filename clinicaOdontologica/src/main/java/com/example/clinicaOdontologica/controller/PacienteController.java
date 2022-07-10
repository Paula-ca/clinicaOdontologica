package com.example.clinicaOdontologica.controller;


import com.example.clinicaOdontologica.exceptions.BadRequestException;
import com.example.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologica.model.PacienteDTO;
import com.example.clinicaOdontologica.model.Paciente;
import com.example.clinicaOdontologica.service.impl.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;
import java.util.Collection;


@RestController
@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    private static final Logger logger = Logger.getLogger(PacienteController.class);

    @Autowired
    PacienteService pacienteService;

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getPaciente(@PathVariable Long id)throws ResourceNotFoundException {
            return ResponseEntity.ok().body(
                    pacienteService.getPaciente(id));

    }

    @PostMapping
    public ResponseEntity<Paciente> crearPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        pacienteService.crearPaciente(paciente);
        logger.info("El paciente ha sido creado con exito.");
        return ResponseEntity.status(HttpStatus.CREATED).body(paciente);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) {
        ResponseEntity response;
        try {
            pacienteService.eliminarPaciente(id);
            response = ResponseEntity.ok(HttpStatus.OK);
            logger.info("El paciente ha sido eliminado con exito.");
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body(e.getMessage());
            logger.info("El paciente con id "+id+" no existe.");
        }
        return response;
    }
    @PutMapping
    public ResponseEntity<Paciente> modificarPaciente(@RequestBody Paciente paciente){
        pacienteService.modificarPaciente(paciente);
        logger.info("El paciente ha sido modificado con exito.");
        return ResponseEntity.ok().body(paciente);
    }
    @GetMapping
    public Collection<PacienteDTO> getAll() {
        return pacienteService.getAll();
    }



}
