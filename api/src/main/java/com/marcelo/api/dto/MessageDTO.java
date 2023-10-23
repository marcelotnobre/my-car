package com.marcelo.api.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class MessageDTO {

    private String message;
    private Integer errorCode;

    public MessageDTO(String message, Integer errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
