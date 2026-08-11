/**
 * @author  Mamba & Mike
 * Visit: https://darkwhiskers.org/
 * Repository: https://github.com/darkwhiskers
 * @since ©2026
 */
package com.darkwhiskers.controllers;

import com.darkwhiskers.exceptions.DatosInvalidosException;
import com.darkwhiskers.models.Materia;
import com.darkwhiskers.daos.MateriaDAO;
import com.darkwhiskers.utils.Validador;
import com.darkwhiskers.views.VistaMateria;

public class ControladorMateria implements Controller {

    private final MateriaDAO dao;
    private final VistaMateria vista;

    public ControladorMateria() {
        dao = new MateriaDAO();
        vista = new VistaMateria();
    }

    @Override
    public void iniciar() {
        int opcion;
        do {
            opcion = vista.menu();

            switch (opcion) {
                case 1 ->
                    agregarMateria();
                case 2 ->
                    listarMaterias();
                case 3 ->
                    buscarMateria();
                case 4 ->
                    actualizarMateria();
                case 5 -> 
                    eliminarMateria();
            }
        } while (opcion != 0);
    }

    // =========================
    // MÉTODOS PRIVADOS
    // =========================
    private void agregarMateria() {
        try {
            String nombre = Validador.validarDato(vista.pedirNombre());
            dao.create(new Materia(nombre));
            System.out.println("Materia creada correctamente");
        } catch (DatosInvalidosException e) {
            vista.mostrarError(e.getMessage());
        }

    }
    
    private void listarMaterias() {
        dao.findAll().forEach(System.out::println);
    }
    
    private void buscarMateria() {
        int codigo = vista.pedirCodigo();
        System.out.println(dao.findById(codigo));
    }

    private void actualizarMateria() {
        try {
            int codigo = vista.pedirCodigo();
            String nombre = Validador.validarDato(vista.pedirNombre());
            dao.update(new Materia(codigo, nombre));
        } catch (DatosInvalidosException e) {
            vista.mostrarError(e.getMessage());
        }

    }
    
    private void eliminarMateria() {
        int codigo = vista.pedirCodigo();
        dao.delete(codigo);
    }

}
