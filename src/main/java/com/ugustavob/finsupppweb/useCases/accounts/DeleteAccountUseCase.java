package com.ugustavob.finsupppweb.useCases.accounts;

import com.ugustavob.finsupppweb.entities.AccountEntity;
import com.ugustavob.finsupppweb.exception.exceptions.AccountNotFoundException;
import com.ugustavob.finsupppweb.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteAccountUseCase {
    private final AccountRepository accountRepository;

    public void execute(Long accountId) {
        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        accountRepository.delete(account);
    }
}
