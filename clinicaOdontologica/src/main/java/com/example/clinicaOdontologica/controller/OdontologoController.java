package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.model.OdontologoDTO;
import com.example.clinicaOdontologica.model.Odontologo;

import com.example.clinicaOdontologica.service.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    OdontologoService odontologoService;


    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDTO> getOdontologo(@PathVariable Long id){
        return ResponseEntity.ok().body(
                odontologoService.getOdontologo(id));
    }

    @PostMapping
    public ResponseEntity<Odontologo> crearOdontologo(@RequestBody Odontologo odontologo){
        odontologoService.crearOdontologo(odontologo);
        return ResponseEntity.status(HttpStatus.CREATED).body(odontologo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) {
        ResponseEntity response;
        try {
            odontologoService.eliminarOdontologo(id);
            response = ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body(e.getMessage());
        }
        return response;
    }
    @PutMapping
    public ResponseEntity<Odontologo> modificarOdontologo(@RequestBody Odontologo odontologo){
        odontologoService.modificarOdontologo(odontologo);
        return ResponseEntity.ok().body(odontologo);
    }
    @GetMapping
    public Collection<OdontologoDTO> getAll() {
        return odontologoService.getAll();
    }
}
