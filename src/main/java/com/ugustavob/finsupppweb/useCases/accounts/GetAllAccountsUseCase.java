package com.ugustavob.finsupppweb.useCases.accounts;

import com.ugustavob.finsupppweb.entities.AccountEntity;
import com.ugustavob.finsupppweb.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllAccountsUseCase {
    private final AccountRepository accountRepository;

    public List<AccountEntity> execute() {
        return accountRepository.findAll();
    }
}
