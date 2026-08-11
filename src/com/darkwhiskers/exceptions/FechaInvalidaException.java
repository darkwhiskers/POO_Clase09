/**
 * @author  Mamba & Mike
 * Visit: https://darkwhiskers.org/
 * Repository: https://github.com/darkwhiskers
 * @since   ©2026
*/

package com.darkwhiskers.exceptions;

public class FechaInvalidaException extends InstitutoException {

    public FechaInvalidaException() {
        super();
    }

    public FechaInvalidaException(String fecha) {
        super("Formato de fecha inválido: '" + fecha + "'. Debe ser AAAA-MM-DD");
    }

    public FechaInvalidaException(String fecha, Throwable cause) {
        super("Formato de fecha inválido: '" + fecha + "'. Debe ser AAAA-MM-DD", cause);
    }
    
    

}
