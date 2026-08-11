/**
 * @author  Mamba & Mike
 * Visit: https://darkwhiskers.org/
 * Repository: https://github.com/darkwhiskers
 * @since ©2026
 */
package com.darkwhiskers.models;

import java.time.LocalDate;

public class Estudiante extends Persona {

    public Estudiante() {
        super();
    }

    public Estudiante(int dni, String nombre, String apellido, LocalDate fecNac) {
        super(dni, nombre, apellido, fecNac);
    }

    public Estudiante(int legajo, int dni, String nombre, String apellido, LocalDate fecNac) {
        super(legajo, dni, nombre, apellido, fecNac);
    }

    @Override
    public String toString() {
        return "Estudiante: {"
                + super.toString();
    }

}
