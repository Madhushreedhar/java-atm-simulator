package com.atm;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final Random random = new Random();

    private static final ArrayList<Account> accounts = new ArrayList<>();
    private static long nextAccountNumber = 100001;

    private static final long MAX_TRANSACTION_AMOUNT = 100000;
    private static final long WITHDRAWAL_MULTIPLE = 100;

    public static void main(String[] args) {

        while (true) {

            System.out.println("========== ATM SIMULATOR ==========");
            System.out.println();
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");
            System.out.println();
            System.out.print("Choose option: ");

            int choice = readInt();

            System.out.println();

            if (choice == 1) {

                login();

            } else if (choice == 2) {

                createAccount();

            } else if (choice == 3) {

                System.out.println("Thank you for using the ATM.");
                break;

            } else {

                System.out.println("Invalid option. Please choose 1, 2 or 3.");

            }

            System.out.println();
        }

        sc.close();
    }

    private static void createAccount() {

        String pin = generatePin();

        Account account = new Account(nextAccountNumber, pin);
        accounts.add(account);

        System.out.println("Account created successfully!");
        System.out.println();
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("PIN: " + pin);
        System.out.println("Balance: ₹" + account.getBalance());

        nextAccountNumber++;
    }

    private static String generatePin() {

        int number = random.nextInt(10000);

        return String.format("%04d", number);
    }

    private static void login() {

        System.out.print("Enter Account Number: ");
        long accountNumber = readLong();

        if (accountNumber <= 0) {
            System.out.println("Account number must be greater than 0.");
            return;
        }

        Account account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        for (int attempts = 1; attempts <= 3; attempts++) {

            String pin = readPin("Enter PIN: ");

            if (account.matchesPin(pin)) {
                System.out.println("Login successful!");
                atmMenu(account);
                return;
            }

            System.out.println("Incorrect PIN.");

            if (attempts < 3) {
                System.out.println("Attempts remaining: "
                        + (3 - attempts));
            }
        }

        System.out.println("Too many incorrect attempts.");
    }

    private static Account findAccount(long accountNumber) {

        for (Account account : accounts) {

            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }

        return null;
    }

    private static void atmMenu(Account account) {

        while (true) {

            System.out.println();
            System.out.println("========== ATM MENU ==========");
            System.out.println();
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transaction History");
            System.out.println("5. Change PIN");
            System.out.println("6. Logout");
            System.out.println();
            System.out.print("Choose option: ");

            int choice = readInt();

            if (choice == 1) {

                System.out.println("Current Balance: ₹"
                        + account.getBalance());

            } else if (choice == 2) {

                depositMoney(account);

            } else if (choice == 3) {

                withdrawMoney(account);

            } else if (choice == 4) {

                showTransactionHistory(account);

            } else if (choice == 5) {

                changePin(account);

            } else if (choice == 6) {

                System.out.println("Logged out successfully.");
                return;

            } else {

                System.out.println("Invalid option. Please choose 1 to 6.");

            }
        }
    }

    private static void depositMoney(Account account) {

        System.out.print("Enter deposit amount: ₹");
        long amount = readLong();

        if (amount <= 0) {
            System.out.println("Amount must be greater than ₹0.");
            return;
        }

        if (amount > MAX_TRANSACTION_AMOUNT) {
            System.out.println("Maximum deposit is ₹"
                    + MAX_TRANSACTION_AMOUNT
                    + " per transaction.");
            return;
        }

        account.deposit(amount);

        System.out.println("₹" + amount + " deposited successfully.");
        System.out.println("Current Balance: ₹"
                + account.getBalance());
    }

    private static void withdrawMoney(Account account) {

        System.out.print("Enter withdrawal amount: ₹");
        long amount = readLong();

        if (amount <= 0) {
            System.out.println("Amount must be greater than ₹0.");
            return;
        }

        if (amount % WITHDRAWAL_MULTIPLE != 0) {
            System.out.println("Withdrawal amount must be a multiple of ₹"
                    + WITHDRAWAL_MULTIPLE
                    + ".");
            return;
        }

        if (amount > MAX_TRANSACTION_AMOUNT) {
            System.out.println("Maximum withdrawal is ₹"
                    + MAX_TRANSACTION_AMOUNT
                    + " per transaction.");
            return;
        }

        if (!account.withdraw(amount)) {
            System.out.println("Insufficient balance.");
            return;
        }

        System.out.println("₹" + amount + " withdrawn successfully.");
        System.out.println("Current Balance: ₹"
                + account.getBalance());
    }

    private static void showTransactionHistory(Account account) {

        System.out.println();
        System.out.println("========== TRANSACTION HISTORY ==========");

        if (account.getTransactions().isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (int i = 0; i < account.getTransactions().size(); i++) {

            System.out.println((i + 1) + ". "
                    + account.getTransactions().get(i));
        }
    }

    private static void changePin(Account account) {

        String currentPin = readPin("Enter current PIN: ");

        if (!account.matchesPin(currentPin)) {
            System.out.println("Incorrect current PIN.");
            return;
        }

        String newPin = readPin("Enter new PIN: ");

        if (newPin.equals(currentPin)) {
            System.out.println(
                    "New PIN must be different from current PIN.");
            return;
        }

        String confirmPin = readPin("Confirm new PIN: ");

        if (!newPin.equals(confirmPin)) {
            System.out.println("PIN confirmation does not match.");
            return;
        }

        account.changePin(newPin);

        System.out.println("PIN changed successfully.");
    }

    private static String readPin(String prompt) {

        while (true) {

            System.out.print(prompt);

            String pin = sc.nextLine().trim();

            if (pin.matches("\\d{4}")) {
                return pin;
            }

            System.out.println("PIN must be exactly 4 digits.");
        }
    }

    private static int readInt() {

        while (true) {

            String input = sc.nextLine().trim();

            try {
                return Integer.parseInt(input);

            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid input. Please enter a valid number.");
            }
        }
    }

    private static long readLong() {

        while (true) {

            String input = sc.nextLine().trim();

            try {
                return Long.parseLong(input);

            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid input. Please enter a valid number.");
            }
        }
    }
}