package com.ugustavob.finsupppweb.DTO.exceptions;

public record UnprocessableEntityExceptionDTO(
        String description,
        String field
) {
}