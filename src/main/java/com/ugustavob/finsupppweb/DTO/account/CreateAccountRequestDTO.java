package com.ugustavob.finsupppweb.DTO.account;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
public record CreateAccountRequestDTO(
        @NotBlank(message = "A descrição da conta é obrigatória")
        @Length(min = 1, max = 30, message = "A descrição deve ter entre 1 e 30 caracteres")
        @Schema(description = "Descrição da conta", example = "Minha conta de crédito")
        String description,

        @NotNull(message = "O saldo é obrigatório")
        @Positive(message = "O saldo deve ser positivo")
        @Min(value = 0, message = "O saldo deve ser positivo")
        @Max(value = 999999999, message = "O saldo deve ser menor que 1 bilhão")
        @Schema(description = "Saldo inicial da conta", example = "1000.00")
        Double balance,

        @NotNull(message = "O dia de fechamento é obrigatório")
        @Min(value = 1, message = "O dia de fechamento deve estar entre 1 e 31")
        @Max(value = 31, message = "O dia de fechamento deve estar entre 1 e 31")
        @Schema(description = "Dia de fechamento da conta", example = "3")
        Integer closingDay,

        @NotNull(message = "O dia de vencimento é obrigatório")
        @Min(value = 1, message = "O dia de vencimento deve estar entre 1 e 31")
        @Max(value = 31, message = "O dia de vencimento deve estar entre 1 e 31")
        @Schema(description = "Dia de vencimento da conta", example = "10")
        Integer paymentDueDay,

        @NotNull(message = "O ID do usuário é obrigatório")
        @Schema(description = "ID do usuário dono da conta", example = "1")
        Long userId
) {
}