package model;

public class Account {
    private int accountNumber;
    private String firstName;
    private String lastName;
    private int pin;
    private double balance;

    // Constructor
    public Account(int accountNumber, String firstName, String lastName, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pin = pin;
        this.balance = balance;
    }

    // Getters and setters
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    // Deposit funds into the account
    public void deposit(double amount) {
        balance += amount;
    }

    // Withdraw funds from the account if sufficient balance is available
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            System.out.println("Insufficient funds");
            return false;
        }
    }
}
