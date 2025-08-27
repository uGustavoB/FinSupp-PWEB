package com.ugustavob.finsupppweb.useCases.user;

import com.ugustavob.finsupppweb.DTO.user.CreateUserRequestDTO;
import com.ugustavob.finsupppweb.entities.UserEntity;
import com.ugustavob.finsupppweb.exception.exceptions.UserAlreadyExistsException;
import com.ugustavob.finsupppweb.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity execute(CreateUserRequestDTO createUserRequestDTO) {
        Optional<UserEntity> user = userRepository.findByEmail((createUserRequestDTO.email()));

        if (user.isPresent()) {
            throw new UserAlreadyExistsException("Usuário já existe com esse email");
        }

        UserEntity newUser = new UserEntity();
        newUser.setName(createUserRequestDTO.name());
        newUser.setEmail(createUserRequestDTO.email());
        newUser.setPassword(passwordEncoder.encode(createUserRequestDTO.password()));

        return userRepository.save(newUser);
    }
}
