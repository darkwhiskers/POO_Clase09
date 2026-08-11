/**
 * @author  Mamba & Mike
 * Visit: https://darkwhiskers.org/
 * Repository: https://github.com/darkwhiskers
 * @since ©2026
 */
package com.darkwhiskers.controllers;

import com.darkwhiskers.views.VistaPrincipal;

public class ControladorPrincipal implements Controller {
    
    private final ControladorEstudiante cEst;
    private final ControladorMateria cMat;
    private final VistaPrincipal vista;

    public ControladorPrincipal() {
        cEst =  new ControladorEstudiante();
        cMat =  new ControladorMateria();
        vista = new VistaPrincipal();
    }
    
    

    @Override
    public void iniciar() {
        int opcion;
        do {
            opcion = vista.menu();
            switch (opcion) {
                case 1 ->
                    cEst.iniciar();
                case 2 ->
                    cMat.iniciar();
            }
        } while (opcion != 0);
        System.out.println("Programa finalizado.");
    }

}
