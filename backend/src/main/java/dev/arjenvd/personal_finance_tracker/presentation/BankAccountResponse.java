package dev.arjenvd.personal_finance_tracker.presentation;

import java.util.UUID;

public record BankAccountResponse(UUID id, String name, long balance) {
}
