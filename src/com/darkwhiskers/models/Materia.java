/**
 * @author  Mamba & Mike
 * Visit: https://darkwhiskers.org/
 * Repository: https://github.com/darkwhiskers
 * @since ©2026
 */
package com.darkwhiskers.models;

public class Materia {

    private int codigo;
    private String nombreMateria;

    public Materia() { }

    public Materia(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public Materia(int codigo, String nombreMateria) {
        this.codigo = codigo;
        this.nombreMateria = nombreMateria;
    }

    public int getCodigo() { return codigo; }
    public String getNombreMateria() { return nombreMateria; }

    public void setCodigo(int codigo) { this.codigo = codigo; }
    public void setNombreMateria(String nombreMateria) { this.nombreMateria = nombreMateria; }

    @Override
    public String toString() {
        return "Materia{" + "codigo=" + codigo + ", nombreMateria='" + nombreMateria + '\'' + '}';
    }
}
