package Objects;

import Exceptions.InsufficientBalanceException;
import Exceptions.InvalidCustomerException;

public class StaffCustomer extends Customer {
    private String school;

    public StaffCustomer(String accountID, String name, int accountBalance, String school) throws InvalidCustomerException, InsufficientBalanceException {
        // Create Staff Customer object including a balance
        super(accountID, name, accountBalance);
        this.school = school;
    }

    public StaffCustomer(String accountID, String name, String school) throws InvalidCustomerException {
        // Create Staff Customer object excluding a given balance
        super(accountID, name);
        this.school = school;
    }

    public String toString() {
        return super.toString() + " STAFF " + school;
    }

    @Override
    public int chargeAccount(int snackPrice) throws InsufficientBalanceException {
        // Charge the customers account based on the price of the snack
        // snackPrice passed into the method should already have taxes/surplus applied
        // Staff get different discounts based on their school
        double discountedPrice = snackPrice;
        if (this.getSchool().equals(("CMP"))) {
            // 10% discount
            discountedPrice = discountedPrice * 0.9;
        } else if (this.getSchool().equals("BIO") || this.getSchool().equals("MTH")) {
            // 2% discount
            discountedPrice = discountedPrice * 0.98;
        }

        int finalPrice = (int)Math.ceil(discountedPrice);

        // Check account balance to ensure transaction will not go below 0
        int pendingBalance = this.getAccountBalance() - finalPrice;
        if (pendingBalance < 0) {
            throw new InsufficientBalanceException("Insufficient funds in account.");
        } else {
            this.accountBalance = pendingBalance;
        }

        return finalPrice;
    }

    public String getSchool() {
        // Returns Staff Customer's school
        return school;
    }

    public static void main(String[] args){
        try {
            StaffCustomer cust1 = new StaffCustomer("2020CV", "Kimi Raikkonen", 2500, "CMP");
            StaffCustomer cust2 = new StaffCustomer("45ASD7", "Jenson Button", 6,  "BIO");
            StaffCustomer cust3 = new StaffCustomer("23DBA3", "Nico Rosberg", "");
            System.out.println(cust1);
            System.out.println(cust2);
            System.out.println(cust3);

            // Staff name and accountID
            System.out.println("Cust1 Account ID: " + cust1.getAccountID());
            System.out.println("Cust1 Name: " + cust1.getName());

            // Staff account balance
            System.out.println("Cust1 account balance: " + cust1.getAccountBalance());
            System.out.println("Cust2 account balance: " + cust2.getAccountBalance());
            System.out.println("Cust3 account balance: " + cust3.getAccountBalance());

            // Staff school
            System.out.println("Cust1 school: " + cust1.getSchool());
            System.out.println("Cust2 school: " + cust2.getSchool());
            System.out.println("Cust3 school: " + cust3.getSchool());

            // Adding funds to the account
            cust1.addFunds(100);
            cust2.addFunds(100);
            cust3.addFunds(100);
            System.out.println("Cust1 after adding £1: " + cust1.getAccountBalance());
            System.out.println("Cust2 after adding £1: " + cust2.getAccountBalance());
            System.out.println("Cust3 after adding £1: " + cust3.getAccountBalance());

            // Charging the account
            cust1.chargeAccount(100);
            System.out.println("Cust1 after charging account £1: " + cust1.getAccountBalance());
            cust2.chargeAccount(100);
            System.out.println("Cust2 after charging account £1: " + cust2.getAccountBalance());
            cust3.chargeAccount(100);
            System.out.println("Cust3 after charging account £1: " + cust3.getAccountBalance());


        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
