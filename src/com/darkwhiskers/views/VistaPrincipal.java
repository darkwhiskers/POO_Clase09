/**
 * @author  Mamba & Mike
 * Visit: https://darkwhiskers.org/
 * Repository: https://github.com/darkwhiskers
 * @since ©2026
 */
package com.darkwhiskers.views;

import java.util.Scanner;

public class VistaPrincipal implements Vista {
    
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int menu() {
        System.out.println("\n===== SISTEMA INSTITUTO =====");
        System.out.println("1. Gestionar Estudiantes");
        System.out.println("2. Gestionar Materias");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
        return scanner.nextInt();
    }

    @Override
    public void mostrarError(String mensaje) {
        System.out.println("ERROR: " + mensaje);
    }

}
