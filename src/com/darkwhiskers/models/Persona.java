/**
 * @author  Mamba & Mike
 * Visit: https://darkwhiskers.org/
 * Repository: https://github.com/darkwhiskers
 * @since   ©2026
*/

package com.darkwhiskers.models;

import java.time.LocalDate;

public class Persona {
    
    private int legajo;
    private int dni;
    private String nombre;
    private String apellido;
    private LocalDate fecNac;

    public Persona() {
    }

    public Persona(int dni, String nombre, String apellido, LocalDate fecNac) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecNac = fecNac;
    }

    public Persona(int legajo, int dni, String nombre, String apellido, LocalDate fecNac) {
        this.legajo = legajo;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecNac = fecNac;
    }
    
    public int getLegajo() { return legajo; }
    public int getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public LocalDate getFecNac() { return fecNac; }

    public void setLegajo(int legajo) { this.legajo = legajo; }
    public void setDni(int dni) { this.dni = dni; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setFecNac(LocalDate fecNac) { this.fecNac = fecNac; }

    @Override
    public String toString() {
        return "{ legajo=" + legajo
                + ", dni='" + dni + '\''
                + ", nombre='" + nombre + '\''
                + ", apellido='" + apellido + '\''
                + ", fecha de nacimiento=" + fecNac + " }";
    }

}
