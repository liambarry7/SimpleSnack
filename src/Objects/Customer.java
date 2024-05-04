package Objects;

import Exceptions.InsufficientBalanceException;
import Exceptions.InvalidCustomerException;

public class Customer {
    protected String accountID;
    protected String name;
    protected int accountBalance;

    public Customer(String accountID, String name, int accountBalance) throws InvalidCustomerException, InsufficientBalanceException {
        // Create Customer object including an accountBalance
        // Check accountID is 6 digits & accBalance is not negative
        if (accountID.length() != 6) {
            throw new InvalidCustomerException("Invalid accountID.");

        } else if (!(accountBalance >= 0)) {
            throw new InsufficientBalanceException("Invalid account balance.");

        } else {
            this.accountID = accountID;
            this.name = name;
            this.accountBalance = accountBalance;
        }
    }

    public Customer(String accountID, String name) throws InvalidCustomerException {
        // Create Customer object excluding an accountBalance
        //check accID is 6 digits
        if (accountID.length() != 6) {
            throw new InvalidCustomerException("Invalid accountID.");
        } else {
            this.accountID = accountID;
            this.name = name;
            this.accountBalance = 0;
        }
    }

    public String toString() {
        return accountID + " " + name + " " + accountBalance;
    }

    public void addFunds(int amount) {
        // Add funds to the customers account (specified by the parameter)
        if (amount > 0) {
            this.accountBalance = this.getAccountBalance() + amount;
        }
    }

    public int chargeAccount(int snackPrice) throws InsufficientBalanceException {
        // Charge the customers account based on the price of the snack
        // snackPrice passed into the method should already have taxes/surplus applied
        int pendingBalance = this.getAccountBalance() - snackPrice;
        if (pendingBalance < 0) {
            throw new InsufficientBalanceException("Insufficient funds in account.");
        } else {
            this.accountBalance = this.getAccountBalance() - snackPrice;
        }
        return snackPrice;
    }

    public String getAccountID() {
        // returns accountID
        return accountID;
    }

    public String getName() {
        // Returns the customer's name
        return name;
    }

    public int getAccountBalance() {
        // Returns the customer's balance
        return accountBalance;
    }

    public static void main(String[] args) {
        try {
            Customer cust1 = new Customer("45ND82", "David Maloney", 1500);
            Customer cust2 = new Customer("2H37Y9", "Robert Darkman");
            System.out.println(cust1);
            System.out.println(cust2);

            // Customer name and accountID
            System.out.println("Cust1 Account ID: " + cust1.getAccountID());
            System.out.println("Cust1 Name: " + cust1.getName());

            // Customer account balance
            System.out.println("Cust1 account balance: " + cust1.getAccountBalance());
            System.out.println("Cust2 account balance: " + cust1.getAccountBalance());

            // Adding funds to the account
            cust1.addFunds(100);
            cust2.addFunds(100);
            System.out.println("Cust1 after adding £1: " + cust1.getAccountBalance());
            System.out.println("Cust2 after adding £1: " + cust2.getAccountBalance());

            // Charging the account
            cust1.chargeAccount(80);
            System.out.println("Cust1 after charging account 80p: " + cust1.getAccountBalance());

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }
}
