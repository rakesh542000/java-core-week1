package com.gptclass.javacoreweek1;

/* ----------- Custom Business Exceptions ----------- */

class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}

/* ----------- Domain Entity ----------- */

class BankAccount {

    private double balance;
    private final String passcode;

    public BankAccount(String passcode) {
        this.passcode = passcode;
        this.balance = 0;
    }

    private void validatePasscode(String input) {
        if (!passcode.equals(input)) {
            throw new InvalidCredentialsException("Invalid passcode");
        }
    }

    public void deposit(double amount, String passcode) {
        validatePasscode(passcode);

        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        balance += amount;
    }

    public void withdraw(double amount, String passcode) {
        validatePasscode(passcode);

        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        balance -= amount;
    }

    public double getBalance(String passcode) {
        validatePasscode(passcode);
        return balance;
    }
}

/* ----------- Service Layer ----------- */

class BankService {

    private final BankAccount account;

    public BankService(BankAccount account) {
        this.account = account;
    }

    public void deposit(double amount, String passcode) {
        account.deposit(amount, passcode);
    }

    public void withdraw(double amount, String passcode) {
        account.withdraw(amount, passcode);
    }

    public double getBalance(String passcode) {
        return account.getBalance(passcode);
    }
}

/* ----------- Controller / Main ----------- */

public class ExceptionDay6 {

    public static void main(String[] args) {

        BankService service = new BankService(new BankAccount("Rakesh@123"));

        try {
            service.deposit(200, "Rakesh@123");
            service.withdraw(300, "Rakesh@123");
        }
        catch (IllegalArgumentException |
               InsufficientBalanceException |
               InvalidCredentialsException e) {

            System.out.println("Operation failed: " + e.getMessage());
        }
    }
}
