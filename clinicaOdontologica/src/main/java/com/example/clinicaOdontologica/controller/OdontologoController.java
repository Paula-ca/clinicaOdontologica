package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.exceptions.BadRequestException;
import com.example.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologica.dto.OdontologoDTO;
import com.example.clinicaOdontologica.entity.Odontologo;

import com.example.clinicaOdontologica.service.impl.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private static final Logger logger = Logger.getLogger(OdontologoController.class);

    @Autowired
    OdontologoService odontologoService;


    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDTO> getOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
            return ResponseEntity.ok().body(
                   odontologoService.getOdontologo(id));
    }

    @PostMapping
    public ResponseEntity<Odontologo> crearOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
            odontologoService.crearOdontologo(odontologo);
            logger.info("El odontologo ha sido creado con exito.");
            return ResponseEntity.status(HttpStatus.CREATED).body(odontologo);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) {
        ResponseEntity response;
        try {
            odontologoService.eliminarOdontologo(id);
            response = ResponseEntity.ok(HttpStatus.OK);
            logger.info("El odontologo ha sido eliminado con exito.");
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body(e.getMessage());
            logger.info("El odontologo con id "+id+" no existe.");
        }
        return response;
    }
    @PutMapping
    public ResponseEntity<Odontologo> modificarOdontologo(@RequestBody Odontologo odontologo){
        odontologoService.modificarOdontologo(odontologo);
        logger.info("El odontologo ha sido modificado con exito.");
        return ResponseEntity.ok().body(odontologo);
    }
    @GetMapping
    public Collection<OdontologoDTO> getAll() {
        return odontologoService.getAll();
    }
}
