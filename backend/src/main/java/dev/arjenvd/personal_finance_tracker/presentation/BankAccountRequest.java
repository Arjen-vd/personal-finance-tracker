package dev.arjenvd.personal_finance_tracker.presentation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record BankAccountRequest(
        @NotNull
        @NotBlank
        @Size(max = 50)
        String name,

        @NotNull
        @PositiveOrZero
        Long balanceInCents
) {
}
