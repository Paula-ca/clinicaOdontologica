package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.model.TurnoDTO;
import com.example.clinicaOdontologica.model.Turno;

import com.example.clinicaOdontologica.service.impl.OdontologoService;
import com.example.clinicaOdontologica.service.impl.PacienteService;
import com.example.clinicaOdontologica.service.impl.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Collection;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
     TurnoService turnoService ;
     PacienteService pacienteService ;
     OdontologoService odontologoService ;

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> getTurno(@PathVariable Long id){
        return ResponseEntity.ok().body(
                turnoService.getTurno(id));
    }

    @PostMapping("/{id_paciente}/{id_odontologo}/{fecha}/{hora}")
    public ResponseEntity<?> crearTurno(@PathVariable Long id_paciente, @PathVariable Long id_odontologo, @PathVariable Date fecha, @PathVariable Time hora){
        turnoService.crearTurno(id_paciente,id_odontologo,fecha,hora);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Collection<TurnoDTO>> getAll() {
        return ResponseEntity.ok(turnoService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id){
        ResponseEntity response;
        try {
            turnoService.eliminarTurno(id);
            response = ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body(e.getMessage());
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<?> modificarTurno(@RequestBody Turno turno){
        turnoService.modificarTurno(turno);
        return ResponseEntity.ok(HttpStatus.OK);

    }
}
