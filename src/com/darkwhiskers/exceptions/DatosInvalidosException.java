/**
 * @author  Mamba & Mike
 * Visit: https://darkwhiskers.org/
 * Repository: https://github.com/darkwhiskers
 * @since   ©2026
*/

package com.darkwhiskers.exceptions;

public class DatosInvalidosException extends InstitutoException {

    public DatosInvalidosException() {
        super();
    }

    public DatosInvalidosException(String dato) {
        super("Formato del dato inválido: '" + dato);
    }

    public DatosInvalidosException(String dato, Throwable cause) {
        super("Formato del dato inválido: '" + dato, cause);
    }

}
