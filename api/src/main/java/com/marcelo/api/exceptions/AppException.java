package com.marcelo.api.exceptions;

public class AppException extends RuntimeException {
    private final Integer codigo;

    public AppException(String message, Integer codigo) {
        super(message);
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }
}
