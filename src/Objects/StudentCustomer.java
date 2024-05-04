package Objects;

import Exceptions.InsufficientBalanceException;
import Exceptions.InvalidCustomerException;

public class StudentCustomer extends Customer {
    public StudentCustomer(String accountID, String name, int accountBalance) throws InvalidCustomerException, InsufficientBalanceException {
        // Create student customer including a balance
        super(accountID, name);
        // Balance can be up to -£5
        if (accountBalance < -500) {
            throw new InsufficientBalanceException("Invalid account balance.");
        } else {
            this.accountBalance = accountBalance;
        }
    }

    public StudentCustomer(String accountID, String name) throws InvalidCustomerException {
        // Create student customer excluding a balance
        // Does not need to check if balance is below -£5 as default balance is 0
        super(accountID, name);
    }

    public String toString() {
        return super.toString() + " STUDENT";
    }

    @Override
    public int chargeAccount(int snackPrice) throws InsufficientBalanceException {
        // Charge the customers account based on the price of the snack
        // snackPrice passed into the method should already have taxes/surplus applied
        // students get 5% discount & allowed up to £-5 acc balance
        double discountedPrice = Math.ceil(snackPrice * 0.95);
        int finalPrice = (int)Math.ceil(discountedPrice);
        int pendingBalance = this.getAccountBalance() - finalPrice;

        // Check balance to ensure pending balance is not below -£5
        if (pendingBalance < -500) {
            throw new InsufficientBalanceException("Insufficient funds in account.");
        } else {
            this.accountBalance = pendingBalance;
        }

        return finalPrice;
    }

    public static double getDiscountAmount() {
        // Returns discount percentage for students
        return 0.05;
    }

    public static void main(String[] args) {
        try {
            StudentCustomer cust1 = new StudentCustomer("4IK83H", "Cameron Russell", 2500);
            StudentCustomer cust2 = new StudentCustomer("23DBA3", "Keke Rosberg");
            System.out.println(cust1);
            System.out.println(cust2);

            // Staff name and accountID
            System.out.println("Cust1 Account ID: " + cust1.getAccountID());
            System.out.println("Cust1 Name: " + cust1.getName());

            // Staff account balance
            System.out.println("Cust1 account balance: " + cust1.getAccountBalance());
            System.out.println("Cust2 account balance: " + cust2.getAccountBalance());

            // Adding funds to the account
            cust1.addFunds(100);
            cust2.addFunds(100);
            System.out.println("Cust1 after adding £1: " + cust1.getAccountBalance());
            System.out.println("Cust2 after adding £1: " + cust2.getAccountBalance());

            // Charging the account
            cust1.chargeAccount(100);
            System.out.println("Cust1 after charging account £1: " + cust1.getAccountBalance());

            System.out.println("Student discount: " + cust1.getDiscountAmount());


        } catch(Exception e) {
            System.out.println("Error: " + e);
        }
    }


}
