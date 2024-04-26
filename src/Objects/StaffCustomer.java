package Objects;

import Exceptions.InsufficientBalanceException;
import Exceptions.InvalidCustomerException;

public class StaffCustomer extends Customer {
    private String school;

    public StaffCustomer(int accountID, String name, int accountBalance, String school) throws InvalidCustomerException, InsufficientBalanceException {
        super(accountID, name, accountBalance);
        this.school = school;
    }

    public StaffCustomer(int accountID, String name, String school) throws InvalidCustomerException {
        super(accountID, name);
        this.school = school;
    }

    public String toString() {
        return "STAFF " + super.toString() + " " + school;
    }

    @Override
    public int chargeAccount(int snackPrice) throws InsufficientBalanceException {
        // snackPrice passed into the method should already have taxes/surplus applied
        // Staff get different discounts based on their school
        double discountedPrice = snackPrice;
        if (this.getSchool().equals(("CMP"))) {
            // 10% discount
            discountedPrice = discountedPrice * 0.8;
        } else if (this.getSchool().equals("BIO") || this.getSchool().equals("MTH")) {
            // 2% discount
            discountedPrice = discountedPrice * 0.98;
        }

        // Check account balance
        int pendingBalance = this.getAccountBalance() - (int)Math.round(discountedPrice);
        if (pendingBalance < 0) {
            throw new InsufficientBalanceException("Insufficient funds in account.");
        } else {
            setAccountBalance(pendingBalance);
        }

        return (int)discountedPrice;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public static void main(String[] args){
        try {
            StaffCustomer a = new StaffCustomer(123123, "Jane", 100, "CMP");
            System.out.println(a);
            System.out.println(a.chargeAccount(100));
            System.out.println(a.getAccountBalance());

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
