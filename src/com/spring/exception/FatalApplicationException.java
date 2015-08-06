package com.spring.exception;

/**
 * This exception class should be used only when a fatal application error occurs
 * 
 * @author 
 * @version $Id$
 */
public class FatalApplicationException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public FatalApplicationException() {
        super();
    }

    public FatalApplicationException(String message) {
        super(message);
    }

    public FatalApplicationException(Throwable cause) {
        super(cause);
    }

    public FatalApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
