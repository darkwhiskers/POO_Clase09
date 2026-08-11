/**
 * @author  Mamba & Mike
 * Visit: https://darkwhiskers.org/
 * Repository: https://github.com/darkwhiskers
 * @since ©2026
 */
package com.darkwhiskers.utils;

import com.darkwhiskers.exceptions.DatosInvalidosException;
import com.darkwhiskers.exceptions.FechaInvalidaException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Validador {

    public static LocalDate validarFecha(String fecha) throws FechaInvalidaException {
        try {
            return LocalDate.parse(fecha);
        } catch (DateTimeParseException e) {
            throw new FechaInvalidaException(fecha, e);
        }
    }

    public static String validarDato(String dato) throws DatosInvalidosException {
        if (dato == null || dato.trim().isEmpty()) {
            throw new DatosInvalidosException("El dato no puede estar vacío");
        }

        return dato;
    }
}
