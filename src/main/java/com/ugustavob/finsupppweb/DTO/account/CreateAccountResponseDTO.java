package com.ugustavob.finsupppweb.DTO.account;

public record CreateAccountResponseDTO(
        Integer id,
        String description,
        Double balance,
        Integer closingDay,
        Integer paymentDueDay,
        Long userId
) {
}
