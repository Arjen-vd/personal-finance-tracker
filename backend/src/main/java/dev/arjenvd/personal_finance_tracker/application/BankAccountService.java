package dev.arjenvd.personal_finance_tracker.application;

import dev.arjenvd.personal_finance_tracker.data.BankAccountRepository;
import dev.arjenvd.personal_finance_tracker.domain.BankAccount;

import dev.arjenvd.personal_finance_tracker.presentation.BankAccountRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Transactional
    public BankAccount save(BankAccountRequest bankAccountRequest) {
        BankAccount bankAccount = new BankAccount(bankAccountRequest.name(), bankAccountRequest.balanceInCents());

        return bankAccountRepository.save(bankAccount);

    }

    @Transactional(readOnly = true)
    public List<BankAccount> findAll() {
        return bankAccountRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<BankAccount> findById(UUID id) {
        return bankAccountRepository.findById(id);
    }

    @Transactional
    public BankAccount update(UUID id, BankAccount updatedBankAccount) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bank account not found"));

        bankAccount.setName(updatedBankAccount.getName());

        return bankAccount;
    }

    @Transactional
    public boolean delete(UUID id) {
        Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(id);

        if (optionalBankAccount.isEmpty()) {
            return false;
        }

        BankAccount bankAccount = optionalBankAccount.get();

        bankAccountRepository.delete(bankAccount);
        return true;
    }

}
