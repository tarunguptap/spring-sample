package com.spring.error;


public class CustomError {

    public static enum ErrorCode {
        INTERNAL_ERROR_GENERIC(5000), INVALID_REQUEST(5001), USER_NOT_FOUND(5002);
        protected int errorCode;

        /**
         * @return the errorCode
         */
        public int getErrorCode() {
            return errorCode;
        }

        private ErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }
    }

    protected int code;
    protected String message;

    public CustomError(int errorCode, String errorMessage) {
        this.setCode(errorCode);
        this.setMessage(errorMessage);
    }

    public CustomError() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
