package com.example.clinicaOdontologica.service.impl;

import com.example.clinicaOdontologica.exceptions.BadRequestException;
import com.example.clinicaOdontologica.entity.Domicilio;
import com.example.clinicaOdontologica.entity.Paciente;
import com.example.clinicaOdontologica.dto.PacienteDTO;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Set;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class PacienteServiceTest {
    @Autowired
    PacienteService pacienteService;


    @Test
    public void crearPaciente() throws BadRequestException {
        Domicilio domicilio = new Domicilio("Avenida Siempre viva","742","Springfield","Minesota");

        Paciente paciente = new Paciente("Juan","Perez","20930234","juanPerez@gmail.com",new Date(2022-02-14),domicilio);

        pacienteService.crearPaciente(paciente);
        Set<PacienteDTO> pacientes = pacienteService.getAll();
        Assert.assertNotNull(pacientes.size());
    }




}
