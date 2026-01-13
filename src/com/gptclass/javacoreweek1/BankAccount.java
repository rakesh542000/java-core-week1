package com.gptclass.javacoreweek1;

public class BankAccount {
    private long accountNumber;
    private long balance;
    private String password;

    public long getAccountNumber() {
        return accountNumber;
    }

    public BankAccount(long accountNumber, String password) {
        this.accountNumber = accountNumber;
        this.password=password;
        this.balance=0;
    }
    private void validation(String password){
        if(!this.password.equals(password)){
            throw new SecurityException("Invalid Password!");
        }
    }

    public long getBalance(String password) {
        validation(password);
        return balance;
    }

    //Methods
    public void deposit(long amount,String password){
        validation(password);
        if(amount<=0){
            throw new IllegalArgumentException("Please enter a valid amount");
        }
        balance += amount;
    }
    public void withdraw(long amount, String password){
        validation(password);
        if(amount<=0){
            throw new IllegalArgumentException("Please enter a valid amount");
        }
        if(amount>balance){
            throw new IllegalStateException("Balance is not sufficient");
        }
        balance -= amount;
    }
}
