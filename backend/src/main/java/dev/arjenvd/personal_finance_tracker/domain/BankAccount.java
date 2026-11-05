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

    public BankAccount(String name, long initialBalanceInCents) {
        validateAmount(initialBalanceInCents);

        this.name = name;
        this.balanceInCents = initialBalanceInCents;

    }

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
        validateAmount(amountInCents);

        this.balanceInCents += amountInCents;
    }

    public void withdraw(long amountInCents) {
        validateAmount(amountInCents);

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
    private void validateAmount(long amountInCents) {
        if (amountInCents <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be positive"
            );
        }
    }
}
