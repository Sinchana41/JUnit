package com.cabinvoicegenerator;

public class InvalidRideException extends RuntimeException {
    private  String message;

    public InvalidRideException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
