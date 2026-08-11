/**
 * @author  Mamba & Mike
 * Visit: https://darkwhiskers.org/
 * Repository: https://github.com/darkwhiskers
 * @since   ©2026
*/

package com.darkwhiskers.exceptions;

public class EntidadDuplicadaException extends InstitutoException {

    public EntidadDuplicadaException() {
        super();
    }

    public EntidadDuplicadaException(int dni) {
        super("Usuario con DNI: " + dni + " ya existe.");
    }

    public EntidadDuplicadaException(int dni, Throwable cause) {
        super("Usuario con DNI: " + dni + " ya existe.", cause);
    }
    

}
