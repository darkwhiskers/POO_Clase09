/**
 * @author  Mamba & Mike
 * Visit: https://darkwhiskers.org/
 * Repository: https://github.com/darkwhiskers
 * @since ©2026
 */
package com.darkwhiskers.controllers;

import com.darkwhiskers.exceptions.DatosInvalidosException;
import com.darkwhiskers.exceptions.FechaInvalidaException;
import com.darkwhiskers.exceptions.EntidadDuplicadaException;
import com.darkwhiskers.models.Estudiante;
import com.darkwhiskers.daos.EstudianteDAO;
import com.darkwhiskers.utils.Validador;
import com.darkwhiskers.views.VistaEstudiante;
import java.time.LocalDate;

public class ControladorEstudiante implements Controller {

    private final EstudianteDAO dao;
    private final VistaEstudiante vista;

    public ControladorEstudiante() {
        dao = new EstudianteDAO();
        vista = new VistaEstudiante();
    }

    @Override
    public void iniciar() {
        int opcion;
        do {
            opcion = vista.menu();

            switch (opcion) {

                case 1 -> agregarEstudiante();

                case 2 -> listarEstudiantes();

                case 3 -> buscarEstudianteLegajo();

                case 4 -> buscarEstudianteDni();

                case 5 -> actualizarEstudiante();

                case 6 -> eliminarEstudiante();
            }

        } while (opcion != 0);
    }
    
    // =========================
    // MÉTODOS PRIVADOS
    // =========================

    private void agregarEstudiante() {
        try {
            int dni = vista.pedirDni();

            if (dao.findByDni(dni) != null) {
                throw new EntidadDuplicadaException(dni);
            }

            String nombre = Validador.validarDato(vista.pedirNombre());
            String apellido = Validador.validarDato(vista.pedirApellido());

            LocalDate fecha = pedirFecha();

            dao.create(new Estudiante(dni, nombre, apellido, fecha));
            System.out.println("Estudiante creado correctamente");

        } catch (EntidadDuplicadaException | DatosInvalidosException e) {
            vista.mostrarError(e.getMessage());
        }
    }
    
    private void listarEstudiantes() {
        dao.findAll().forEach(System.out::println);
    }
    
    
    
    private void buscarEstudianteLegajo() {
        int legajo = vista.pedirLegajo();
        System.out.println(dao.findById(legajo));
    }
    
    
    private void buscarEstudianteDni() {
        int dni = vista.pedirDni();
        System.out.println(dao.findByDni(dni));
    }

    private void actualizarEstudiante() {
        try {
            int legajo = vista.pedirLegajo();
            int dni = vista.pedirDni();

            String nombre = Validador.validarDato(vista.pedirNombre());
            String apellido = Validador.validarDato(vista.pedirApellido());

            LocalDate fecha = pedirFecha();

            dao.update(new Estudiante(legajo, dni, nombre, apellido, fecha));

        } catch (DatosInvalidosException e) {
            vista.mostrarError(e.getMessage());
        }
    }
    
    private void eliminarEstudiante() {
        int legajo = vista.pedirLegajo();
        dao.delete(legajo);
    }

    private LocalDate pedirFecha() {
        while (true) {
            try {
                String fechaStr = vista.pedirFecha();
                return Validador.validarFecha(fechaStr);

            } catch (FechaInvalidaException e) {
                vista.mostrarError(e.getMessage());
            }
        }
    }

}
