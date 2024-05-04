package Objects;

import Exceptions.InsufficientBalanceException;
import Exceptions.InvalidCustomerException;

public class StaffCustomer extends Customer {
    private String school;

    public StaffCustomer(String accountID, String name, int accountBalance, String school) throws InvalidCustomerException, InsufficientBalanceException {
        super(accountID, name, accountBalance);
        this.school = school;
    }

    public StaffCustomer(String accountID, String name, String school) throws InvalidCustomerException {
        super(accountID, name);
        this.school = school;
    }

    public String toString() {
        return super.toString() + " STAFF " + school;
    }

    @Override
    public int chargeAccount(int snackPrice) throws InsufficientBalanceException {
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

        // Check account balance
        int pendingBalance = this.getAccountBalance() - finalPrice;
        if (pendingBalance < 0) {
            throw new InsufficientBalanceException("Insufficient funds in account.");
        } else {
            this.accountBalance = pendingBalance;
        }

        return finalPrice;
    }

    public String getSchool() {
        return school;
    }

    public static void main(String[] args){
        try {
            StaffCustomer a = new StaffCustomer("123123", "Jane", 100, "CMP");
            StaffCustomer b = new StaffCustomer("123123", "Jane", 100, null);
            System.out.println(a);
            System.out.println(a.chargeAccount(100));
            System.out.println(a.getAccountBalance());

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
