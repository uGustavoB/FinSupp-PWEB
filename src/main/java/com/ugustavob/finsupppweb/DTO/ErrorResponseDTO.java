package com.ugustavob.finsupppweb.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponseDTO {
    private int code;
    private String message;
    @Builder.Default
    private String type = "Error";
    private List<Object> dataList;

    public ErrorResponseDTO(int code, String message, String type) {
        this.code = code;
        this.message = message;
        this.type = type;
    }
}