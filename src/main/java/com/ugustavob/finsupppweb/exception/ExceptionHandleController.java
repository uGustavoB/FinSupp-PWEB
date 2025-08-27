package com.ugustavob.finsupppweb.exception;

import com.ugustavob.finsupppweb.DTO.ErrorResponseDTO;
import com.ugustavob.finsupppweb.DTO.exceptions.UnprocessableEntityExceptionDTO;
import com.ugustavob.finsupppweb.exception.exceptions.AccountNotFoundException;
import com.ugustavob.finsupppweb.exception.exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandleController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<Object> dto = new ArrayList<>();

        e.getFieldErrors().forEach(error -> {
            dto.add(new UnprocessableEntityExceptionDTO(error.getDefaultMessage(), error.getField()));
        });

        return new ResponseEntity<>(ErrorResponseDTO.builder()
                .message("Validation error")
                .code(422)
                .dataList(dto)
                .build(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return new ResponseEntity<>(ErrorResponseDTO.builder().message(e.getMessage()).code(409).build(),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleAccountNotFoundException(AccountNotFoundException e) {
        return new ResponseEntity<>(ErrorResponseDTO.builder().message(e.getMessage()).code(404).build(),
                HttpStatus.NOT_FOUND);
    }
}
