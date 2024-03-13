package model;

import java.util.HashMap;

public class Bank {
    private HashMap<Integer, Account> accounts;

    // Constructor
    public Bank() {
        accounts = new HashMap<>();
    }

    // Add new account to the bank if account number is unique
    public boolean addAccount(Account account) {
        if (!accounts.containsKey(account.getAccountNumber())) {
            accounts.put(account.getAccountNumber(), account);
            return true;
        } else {
            System.out.println("Account number is already in use. Please try another.");
            return false;
        }
    }

    // Transfer funds from one account to another
    public void transfer(int fromAccountNumber, int toAccountNumber, double amount) {
        Account fromAccount = accounts.get(fromAccountNumber);
        Account toAccount = accounts.get(toAccountNumber);

        if (fromAccount != null && toAccount != null) {
            if (fromAccount.withdraw(amount)) {
                toAccount.deposit(amount);
                System.out.println("Transfer successful");
            }
        } else {
            System.out.println("Invalid account number");
        }
    }

    // Get account by account number
    public Account getAccount(int accountNumber) {
        return accounts.get(accountNumber);
    }

    // Get account balance
    public double getAccountBalance(int accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            return account.getBalance();
        } else {
            System.out.println("Invalid account number");
            return -1;
        }
    }

    // Display welcome message
    public void displayWelcomeMessage() {
        System.out.println("Welcome to Our Bank!");
        System.out.println("*******************************");
        System.out.println("We're here to serve you.");
        System.out.println("*******************************");
    }
}
