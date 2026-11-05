package dev.arjenvd.personal_finance_tracker.data;

import dev.arjenvd.personal_finance_tracker.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {
}
