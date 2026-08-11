/**
 * @author  Mamba & Mike
 * Visit: https://darkwhiskers.org/
 * Repository: https://github.com/darkwhiskers
 * @since ©2026
 */
package com.darkwhiskers.views;

import java.util.Scanner;

public class VistaMateria implements Vista {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int menu() {
        System.out.println("\n--- GESTIÓN DE MATERIAS ---");
        System.out.println("1. Agregar materia");
        System.out.println("2. Listar materias");
        System.out.println("3. Buscar materia por código");
        System.out.println("4. Actualizar materia");
        System.out.println("5. Eliminar materia");
        System.out.println("0. Volver");
        System.out.print("Opción: ");
        return scanner.nextInt();
    }

    @Override
    public void mostrarError(String mensaje) {
        System.out.println("ERROR: " + mensaje);
    }
    
    // =========================
    // MÉTODOS DE ENTRADA
    // =========================

    public int pedirCodigo() {
        System.out.print("Código: ");
        return scanner.nextInt();
    }

    public String pedirNombre() {
        scanner.nextLine(); // limpiar buffer
        System.out.print("Nombre de la materia: ");
        return scanner.nextLine();
    }

}
