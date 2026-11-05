package dev.arjenvd.personal_finance_tracker.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "balance_in_cents", nullable = false)
    private long balanceInCents;

    public BankAccount() {

    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBalanceInCents() {
        return balanceInCents;
    }

    public void deposit(long amountInCents) {
        validateBalance(amountInCents);

        this.balanceInCents += amountInCents;
    }

    public void withdraw(long amountInCents) {
        validateBalance(amountInCents);

        if (this.balanceInCents - amountInCents < 0) {
            throw new IllegalStateException(
                    "Balance cant be below 0"
            );
        }
        this.balanceInCents -= amountInCents;
    }

    /**
     * Validates balance to be positive amount
     *
     * @param amountInCents amount in cents
     * @throws IllegalArgumentException if amount is negative
     */
    private void validateBalance(long amountInCents) {
        if (amountInCents <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be positive"
            );
        }
    }
}
