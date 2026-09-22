package com.atm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {

    private static final DateTimeFormatter TRANSACTION_TIME_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private final long accountNumber;
    private String pin;
    private long balance;
    private final List<String> transactions;

    public Account(long accountNumber, String pin) {

        if (accountNumber <= 0) {
            throw new IllegalArgumentException(
                    "Account number must be greater than 0.");
        }

        validatePin(pin);

        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public boolean matchesPin(String candidatePin) {
        return pin.equals(candidatePin);
    }

    public long getBalance() {
        return balance;
    }

    public List<String> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public void changePin(String newPin) {

        validatePin(newPin);

        pin = newPin;
    }

    public void deposit(long amount) {

        validatePositiveAmount(amount);

        if (amount > Long.MAX_VALUE - balance) {
            throw new IllegalArgumentException(
                    "Balance limit exceeded.");
        }

        balance += amount;

        addTransaction("Deposited ₹" + amount);
    }

    public boolean withdraw(long amount) {

        validatePositiveAmount(amount);

        if (amount > balance) {
            return false;
        }

        balance -= amount;

        addTransaction("Withdrawn ₹" + amount);

        return true;
    }

    private void addTransaction(String transaction) {

        String dateTime = LocalDateTime.now()
                .format(TRANSACTION_TIME_FORMAT);

        transactions.add(transaction
                + " | Balance: ₹" + balance
                + " | " + dateTime);
    }

    private void validatePin(String pin) {

        if (pin == null || !pin.matches("\\d{4}")) {
            throw new IllegalArgumentException(
                    "PIN must be exactly 4 digits.");
        }
    }

    private void validatePositiveAmount(long amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than ₹0.");
        }
    }
}