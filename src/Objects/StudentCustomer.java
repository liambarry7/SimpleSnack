package Objects;

import Exceptions.InsufficientBalanceException;
import Exceptions.InvalidCustomerException;

public class StudentCustomer extends Customer {
    public StudentCustomer(int accountID, String name, int accountBalance) throws InvalidCustomerException, InsufficientBalanceException {
        super(accountID, name);
        // accbalance can be up to negatuve 5
        // edit constructors for this
        if (accountBalance < -500) {
            throw new InsufficientBalanceException("Invalid account balance.");
        } else {
            this.accountBalance = accountBalance;
        }

    }

    public StudentCustomer(int accountID, String name) throws InvalidCustomerException {
        super(accountID, name);
    }

    public String toString() {
        return "STUDENT " + super.toString();
    }

    @Override
    public int chargeAccount(int snackPrice) throws InsufficientBalanceException {
        // snackPrice passed into the method should already have taxes/surplus applied
        // students get 5% discount & allowed up to £-5 acc balance
        int pendingBalance = this.getAccountBalance() - (int)Math.round((snackPrice * 0.95));
        if (pendingBalance < -500) {
            throw new InsufficientBalanceException("Insufficient funds in account.");
        } else {
            setAccountBalance(pendingBalance);
        }

        return getAccountBalance();
    }

    public static double getDiscountAmount() {
        return 0.05;
    }

    public static void main(String[] args) {


        try {
            StudentCustomer a = new StudentCustomer(332232, "Gordon");
            StudentCustomer b = new StudentCustomer(123123, "a", -1);
            System.out.println(a);
            System.out.println(b);
            System.out.println(a.chargeAccount(10));


        } catch(Exception e) {
            System.out.println(e);
        }
    }


}