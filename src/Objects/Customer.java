package Objects;

import Exceptions.InsufficientBalanceException;
import Exceptions.InvalidCustomerException;

public class Customer {
    protected int accountID;
    protected String name;
    protected int accountBalance;

    public Customer(int accountID, String name, int accountBalance) {
        try {
            //check accID is 6 digits & accBalance is not negative
            if (Integer.toString(accountID).length() != 6) {
                throw new InvalidCustomerException("Invalid accountID.");

            } else if (!(accountBalance >= 0)) {
                throw new InvalidCustomerException("Invalid account balance.");

            } else {
                this.accountID = accountID;
                this.name = name;
                this.accountBalance = accountBalance;
            }

        } catch(Exception e) {
            System.out.println(e);
        }
//        //check accID is 6 digits & accBalance is not negative
//        if (Integer.toString(accountID).length() != 6) {
//            throw new InvalidCustomerException("Invalid accountID.");
//
//        } else if (!(accountBalance >= 0)) {
//            throw new InvalidCustomerException("Invalid account balance.");
//
//        } else {
//            this.accountID = accountID;
//            this.name = name;
//            this.accountBalance = accountBalance;
//        }
    }

    public Customer(int accountID, String name)  {
        try {
            //check accID is 6 digits
            if (Integer.toString(accountID).length() != 6) {
                throw new InvalidCustomerException("Invalid accountID.");
            } else {
                this.accountID = accountID;
                this.name = name;
                this.accountBalance = 0;
            }

        } catch(Exception e) {
            System.out.println(e);
        }
//        //check accID is 6 digits
//        if (Integer.toString(accountID).length() != 6) {
//            throw new InvalidCustomerException("Invalid accountID.");
//        } else {
//            this.accountID = accountID;
//            this.name = name;
//            this.accountBalance = 0;
//        }
    }

    public String toString() {
        return accountID + " " + name + " " + accountBalance;
    }

    public void addFunds(int amount) {
        if (amount > 0) {
            setAccountBalance(this.getAccountBalance() + amount);
        }
    }

    public int chargeAccount(int snackPrice) {
        // snackPrice passed into the method should already have taxes/surplus applied
        int pendingBalance = this.getAccountBalance() - snackPrice;
        try {
            if (pendingBalance < 0) {
                throw new InsufficientBalanceException("Insufficient funds in account.");
            } else {
                setAccountBalance(this.getAccountBalance() - snackPrice);
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        // snackPrice passed into the method should already have taxes/surplus applied
//        int pendingBalance = this.getAccountBalance() - snackPrice;
//        if (pendingBalance < 0) {
//            throw new InsufficientBalanceException("Insufficient funds in account.");
//        } else {
//            setAccountBalance(this.getAccountBalance() - snackPrice);
//        }
        return getAccountBalance();
    }

    public int getAccountID() {
        return accountID;
    }

    public String getName() {
        return name;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }


    public static void main(String[] args) {

        Customer a = new Customer(121256, "DAve", 100);
        Customer b = new Customer(123456, "eggman");
        System.out.println(a);
        System.out.println(b);

        a.addFunds(20);
        b.addFunds(32
        );
        System.out.println(a.getAccountBalance());
        System.out.println(b.getAccountBalance());

        System.out.println("a balance before: " + a.getAccountBalance());
        a.chargeAccount(180);
        System.out.println("a balance after: " + a.getAccountBalance());

//        try {
//            Customer a = new Customer(121256, "DAve", 100);
//            Customer b = new Customer(123456, "eggman");
//            System.out.println(a);
//            System.out.println(b);
//
//            a.addFunds(20);
//            b.addFunds(32
//            );
//            System.out.println(a.getAccountBalance());
//            System.out.println(b.getAccountBalance());
//
//            System.out.println("a balance before: " + a.getAccountBalance());
//            a.chargeAccount(180);
//            System.out.println("a balance after: " + a.getAccountBalance());
//
//        } catch (Exception e) {
//            System.out.println("Error - " + e);
//        }

    }
}
