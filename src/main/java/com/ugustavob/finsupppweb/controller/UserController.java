package com.ugustavob.finsupppweb.controller;

import com.ugustavob.finsupppweb.DTO.user.CreateUserRequestDTO;
import com.ugustavob.finsupppweb.DTO.user.CreateUserResponseDTO;
import com.ugustavob.finsupppweb.entities.UserEntity;
import com.ugustavob.finsupppweb.useCases.user.CreateUserUseCase;
import com.ugustavob.finsupppweb.useCases.user.GetUserUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<CreateUserResponseDTO> getUserById(@PathVariable Long id) {
        UserEntity user = getUserUseCase.execute(id);

        return ResponseEntity.status(HttpStatus.OK).body(new CreateUserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPassword()
        ));
    }

    @PostMapping("/")
    public ResponseEntity<CreateUserResponseDTO> createUser(@Valid @RequestBody CreateUserRequestDTO createUserRequestDTO) {
        UserEntity user = createUserUseCase.execute(createUserRequestDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).body(new CreateUserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPassword()
        ));
    }
}
