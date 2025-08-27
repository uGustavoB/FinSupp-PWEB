package com.ugustavob.finsupppweb.DTO.user;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public record CreateUserRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        @Length(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
        String name,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "O e-mail deve ser válido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Length(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres")
        String password
) {
}