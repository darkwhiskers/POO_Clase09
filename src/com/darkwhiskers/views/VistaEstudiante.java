/**
 * @author  Mamba & Mike
 * Visit: https://darkwhiskers.org/
 * Repository: https://github.com/darkwhiskers
 * @since ©2026
 */
package com.darkwhiskers.views;

import java.util.Scanner;

public class VistaEstudiante implements Vista {
    
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int menu() {
        System.out.println("\n--- GESTIÓN DE ESTUDIANTES ---");
        System.out.println("1. Agregar estudiante");
        System.out.println("2. Listar estudiantes");
        System.out.println("3. Buscar estudiante por legajo");
        System.out.println("4. Buscar estudiante por DNI");
        System.out.println("5. Actualizar estudiante");
        System.out.println("6. Eliminar estudiante");
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

    public int pedirDni() {
        System.out.print("DNI: ");
        return scanner.nextInt();
    }

    public int pedirLegajo() {
        System.out.print("Legajo: ");
        return scanner.nextInt();
    }

    public String pedirNombre() {
        scanner.nextLine(); // limpiar buffer
        System.out.print("Nombre: ");
        return scanner.nextLine();
    }

    public String pedirApellido() {
        System.out.print("Apellido: ");
        return scanner.nextLine();
    }

    public String pedirFecha() {
        System.out.print("Fecha (YYYY-MM-DD): ");
        return scanner.nextLine();
    }

}
