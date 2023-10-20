package com.example.marcelo.mycars.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErroMessage {
    String message;
    Integer errorCode;
}
