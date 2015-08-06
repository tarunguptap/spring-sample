package com.spring.exception;

public class ValidationException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected int code = -1;
    protected String message;

    public ValidationException() {
    }

    public ValidationException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
