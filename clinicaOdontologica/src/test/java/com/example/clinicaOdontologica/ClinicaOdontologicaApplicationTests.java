package com.example.clinicaOdontologica;

import com.example.clinicaOdontologica.controller.PacienteController;
import com.example.clinicaOdontologica.entity.Domicilio;
import com.example.clinicaOdontologica.entity.Odontologo;
import com.example.clinicaOdontologica.entity.Paciente;
import com.example.clinicaOdontologica.entity.Turno;
import com.example.clinicaOdontologica.exceptions.BadRequestException;
import com.example.clinicaOdontologica.service.impl.OdontologoService;
import com.example.clinicaOdontologica.service.impl.PacienteService;
import com.example.clinicaOdontologica.service.impl.TurnoService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.sql.Time;


@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class ClinicaOdontologicaApplicationTests {
    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void cargarDataSet() throws BadRequestException {
        Domicilio domicilio = new Domicilio("Mansilla", "3231", "Maipu", "Mendoza");
        Paciente paciente = new Paciente("Josefina", "Rodriguez", "6647657", "josero@gmail.com", new Date(2022-04-17), domicilio);
        Odontologo odontologo = new Odontologo("Roberto", "Gomez", 678647);
        Turno turno = new Turno(new Date(2022-03-17), new Time(17), odontologo, paciente);
        pacienteService.crearPaciente(paciente);
        odontologoService.crearOdontologo(odontologo);
        turnoService.crearTurno(turno);
    }

    @Test
    public void validarQueDevuelvaUnListadoDeTurnos() throws Exception {

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());

    }

}
