package dev.arjenvd.personal_finance_tracker.presentation;

import dev.arjenvd.personal_finance_tracker.application.BankAccountService;
import dev.arjenvd.personal_finance_tracker.domain.BankAccount;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bankaccount")
public class BankAccountController {
    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
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

    private BankAccountResponse toBankAccountResponse(BankAccount bankAccount) {
        return new BankAccountResponse(
                bankAccount.getId(),
                bankAccount.getName(),
                bankAccount.getBalanceInCents()
        );
    }

}
