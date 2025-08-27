package com.ugustavob.finsupppweb.DTO.user;

public record CreateUserResponseDTO(
        Long id,
        String name,
        String email,
        String password
) {
}
