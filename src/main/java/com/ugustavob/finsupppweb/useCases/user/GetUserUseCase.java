package com.ugustavob.finsupppweb.useCases.user;

import com.ugustavob.finsupppweb.entities.UserEntity;
import com.ugustavob.finsupppweb.exception.exceptions.UserNotFoundException;
import com.ugustavob.finsupppweb.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetUserUseCase {
    private final UserRepository userRepository;

    public UserEntity execute(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
    }
}
