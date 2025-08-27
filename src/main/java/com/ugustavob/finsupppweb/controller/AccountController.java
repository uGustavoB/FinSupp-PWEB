package com.ugustavob.finsupppweb.controller;

import com.ugustavob.finsupppweb.DTO.account.CreateAccountRequestDTO;
import com.ugustavob.finsupppweb.DTO.account.CreateAccountResponseDTO;
import com.ugustavob.finsupppweb.entities.AccountEntity;
import com.ugustavob.finsupppweb.useCases.accounts.CreateAccountUseCase;
import com.ugustavob.finsupppweb.useCases.accounts.DeleteAccountUseCase;
import com.ugustavob.finsupppweb.useCases.accounts.GetAllAccountsUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/accounts")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {
    private final CreateAccountUseCase createAccountUseCase;
    private final GetAllAccountsUseCase getAllAccountsUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;

    @GetMapping("/")
    public ResponseEntity<List<CreateAccountResponseDTO>> getAllAccounts() {
        List<AccountEntity> accounts = getAllAccountsUseCase.execute();

        return ResponseEntity.ok(accounts.stream().map(
                account -> new CreateAccountResponseDTO(
                        account.getId(),
                        account.getDescription(),
                        account.getBalance(),
                        account.getClosingDay(),
                        account.getPaymentDueDay(),
                        account.getUser().getId()
                )).toList(
        ));
    }

    @PostMapping("/")
    public ResponseEntity<CreateAccountResponseDTO> createAccount(@Valid @RequestBody CreateAccountRequestDTO createAccountRequestDTO) {
        AccountEntity account = createAccountUseCase.execute(createAccountRequestDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(account.getId())
                .toUri();

        return ResponseEntity.created(location).body(new CreateAccountResponseDTO(
                account.getId(),
                account.getDescription(),
                account.getBalance(),
                account.getClosingDay(),
                account.getPaymentDueDay(),
                account.getUser().getId()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        deleteAccountUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
