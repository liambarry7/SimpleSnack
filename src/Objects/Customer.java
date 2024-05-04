package Objects;

import Exceptions.InsufficientBalanceException;
import Exceptions.InvalidCustomerException;

public class Customer {
    protected String accountID;
    protected String name;
    protected int accountBalance;

    public Customer(String accountID, String name, int accountBalance) throws InvalidCustomerException, InsufficientBalanceException {
        //check accID is 6 digits & accBalance is not negative
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
        if (amount > 0) {
            this.accountBalance = this.getAccountBalance() + amount;
        }
    }

    public int chargeAccount(int snackPrice) throws InsufficientBalanceException {
        // snackPrice passed into the method should already have taxes/surplus applied
        int pendingBalance = this.getAccountBalance() - snackPrice;
        if (pendingBalance < 0) {
            throw new InsufficientBalanceException("Insufficient funds in account.");
        } else {
//            setAccountBalance(this.getAccountBalance() - snackPrice);
            this.accountBalance = this.getAccountBalance() - snackPrice;
        }
        return snackPrice;
    }

    public String getAccountID() {
        return accountID;
    }

    public String getName() {
        return name;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public static void main(String[] args) {
        try {
            Customer a = new Customer("121256", "DAve", 100);
            Customer b = new Customer("123456", "eggman");
            System.out.println(a);
            System.out.println(b);

            a.addFunds(20);
            b.addFunds(32);

            System.out.println(a.getAccountBalance());
            System.out.println(b.getAccountBalance());

            System.out.println("a balance before: " + a.getAccountBalance());
            System.out.println(a.chargeAccount(80));
            System.out.println("a balance after: " + a.getAccountBalance());

        } catch (Exception e) {
            System.out.println("Error - " + e);
        }

    }
}
