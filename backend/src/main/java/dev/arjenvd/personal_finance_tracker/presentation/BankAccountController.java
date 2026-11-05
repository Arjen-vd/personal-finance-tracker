package dev.arjenvd.personal_finance_tracker.presentation;

import dev.arjenvd.personal_finance_tracker.application.BankAccountService;
import dev.arjenvd.personal_finance_tracker.domain.BankAccount;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bankaccount")
public class BankAccountController {
    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BankAccountResponse create(@Valid @RequestBody BankAccountRequest bankAccountRequest) {
        BankAccount bankAccount = bankAccountService.save(bankAccountRequest);

        return toBankAccountResponse(bankAccount);
    }

    @GetMapping
    public List<BankAccountResponse> findAll() {
        List<BankAccount> bankAccounts = bankAccountService.findAll();
        List<BankAccountResponse> responses = new ArrayList<>();

        for (BankAccount bankAccount : bankAccounts) {
            responses.add(toBankAccountResponse(bankAccount));
        }
        return responses;
    }

    @GetMapping("/{id}")
    public BankAccountResponse findById(@PathVariable UUID id) {
        BankAccount bankAccount = bankAccountService.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
        return toBankAccountResponse(bankAccount);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBankAccount(@PathVariable UUID id) {
        if (!bankAccountService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private BankAccountResponse toBankAccountResponse(BankAccount bankAccount) {
        return new BankAccountResponse(
                bankAccount.getId(),
                bankAccount.getName(),
                bankAccount.getBalanceInCents()
        );
    }

}
