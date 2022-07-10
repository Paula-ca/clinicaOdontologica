package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.exceptions.BadRequestException;
import com.example.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologica.model.TurnoDTO;
import com.example.clinicaOdontologica.model.Turno;

import com.example.clinicaOdontologica.service.impl.OdontologoService;
import com.example.clinicaOdontologica.service.impl.PacienteService;
import com.example.clinicaOdontologica.service.impl.TurnoService;
import org.apache.log4j.Logger;
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
    private static final Logger logger = Logger.getLogger(TurnoController.class);

    @Autowired
     TurnoService turnoService ;


    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> getTurno(@PathVariable Long id) throws ResourceNotFoundException {
        try{
            return ResponseEntity.ok().body(
                    turnoService.getTurno(id));
        }catch(Exception ex){
            throw new ResourceNotFoundException("El turno con id "+id+" no existe.");
        }

    }

    @PostMapping
    public ResponseEntity<?> crearTurno(@RequestBody Turno turno) throws BadRequestException {
        try{
            turnoService.crearTurno(turno);
            logger.info("El turno ha sido creado con exito.");
            return ResponseEntity.status(HttpStatus.CREATED).body(turno);
        }
        catch(Exception ex){
            logger.error("El turno que intenta crear ya existe o es invalido.");
            throw new BadRequestException("El turno que intenta crear ya existe o es invalido.");

        }
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
            logger.info("El turno ha sido eliminado con exito.");
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body(e.getMessage());
            logger.info("El turno con id "+id+" no existe.");
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<?> modificarTurno(@RequestBody Turno turno){
        turnoService.modificarTurno(turno);
        logger.info("El turno ha sido modificado con exito.");
        return ResponseEntity.ok(HttpStatus.OK);

    }
}
