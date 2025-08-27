package com.ugustavob.finsupppweb.useCases.accounts;

import com.ugustavob.finsupppweb.DTO.account.CreateAccountRequestDTO;
import com.ugustavob.finsupppweb.entities.AccountEntity;
import com.ugustavob.finsupppweb.repositories.AccountRepository;
import com.ugustavob.finsupppweb.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAccountUseCase {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountEntity execute(CreateAccountRequestDTO createAccountRequestDTO) {
        var user = userRepository.findById(createAccountRequestDTO.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        var account = new AccountEntity();
        account.setDescription(createAccountRequestDTO.description());
        account.setBalance(createAccountRequestDTO.balance());
        account.setClosingDay(createAccountRequestDTO.closingDay());
        account.setPaymentDueDay(createAccountRequestDTO.paymentDueDay());
        account.setUser(user);

        return accountRepository.save(account);
    }
}
