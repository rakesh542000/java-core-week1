package com.gptclass.javacoreweek1;

public class ClientDetails {
    public static void main(String[] args) {
        BankAccount acc=new BankAccount(123123654,"Rakesh123");
        acc.deposit(10000,"Rakesh123");
        acc.deposit(10000,"Rakesh123");
        acc.withdraw(100,"Rakesh123");
        long balance=acc.getBalance("Rakesh123");
       acc.withdraw(100,"Rakesh123");


    }
}
