package main;

import model.Account;
import model.Bank;
import java.util.Random;
import java.util.Scanner;

public class BankSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        // Display welcome message
        bank.displayWelcomeMessage();

        // Loop to keep the program running until the user chooses to exit
        while (true) {
            // Prompt user to choose an option
            System.out.println("Choose an option:");
            System.out.println("1. I have an account");
            System.out.println("2. Create a new account");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Logic to access existing account
                    accessExistingAccount(scanner, bank);
                    break;

                case 2:
                    // Logic to create a new account
                    createAccount(scanner, bank);
                    break;

                case 3:
                    // Exit the program
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    // Method to create a new account
    private static void createAccount(Scanner scanner, Bank bank) {
        // Generate random account number
        int accountNumber = generateAccountNumber();
        System.out.println("Your new account number is: " + accountNumber);

        // Prompt user for account details
        System.out.print("Enter your first name: ");
        String firstName = scanner.next();
        System.out.print("Enter your last name: ");
        String lastName = scanner.next();
        System.out.print("Enter your desired PIN (4 digits): ");
        int pin = scanner.nextInt();
        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();

        // Create new account
        Account newAccount = new Account(accountNumber, firstName, lastName, pin, initialDeposit);
        boolean created = bank.addAccount(newAccount);

        if (created) {
            System.out.println("Account created successfully");
        } else {
            System.out.println("Failed to create account. Please try again later.");
        }
    }

    // Method to generate a random 6-digit account number
    private static int generateAccountNumber() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }

    // Method to access an existing account
    private static void accessExistingAccount(Scanner scanner, Bank bank) {
        System.out.print("Enter your account number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter your PIN: ");
        int pin = scanner.nextInt();

        // Check if the account exists and the PIN is correct
        Account accessedAccount = bank.getAccount(accountNumber);
        if (accessedAccount != null && accessedAccount.getPin() == pin) {
            System.out.println("Access granted!");

            // Display menu and perform actions based on user's choice
            while (true) {
                System.out.println("Menu:");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Transfer");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Your balance: " + accessedAccount.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        accessedAccount.deposit(depositAmount);
                        System.out.println("Deposit successful. Updated balance: " + accessedAccount.getBalance());
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawalAmount = scanner.nextDouble();
                        if (accessedAccount.withdraw(withdrawalAmount)) {
                            System.out
                                    .println("Withdrawal successful. Updated balance: " + accessedAccount.getBalance());
                        }
                        break;
                    case 4:
                        System.out.print("Enter recipient's account number: ");
                        int recipientAccountNumber = scanner.nextInt();
                        System.out.print("Enter transfer amount: ");
                        double transferAmount = scanner.nextDouble();
                        bank.transfer(accessedAccount.getAccountNumber(), recipientAccountNumber, transferAmount);
                        break;
                    case 5:
                        System.out.println("Exiting account menu...");
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        } else {
            System.out.println("Invalid account number or PIN.");
        }
    }

}
