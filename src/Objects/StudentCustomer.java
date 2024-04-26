package Objects;

import Exceptions.InvalidCustomerException;

public class StudentCustomer extends Customer {
    public StudentCustomer(int accountID, String name, int accountBalance) throws InvalidCustomerException {
        super(accountID, name, accountBalance);
        // accbalance can be up to negatuve 5
        // edit constructors for this

    }

    public StudentCustomer(int accountID, String name) {
        super(accountID, name);
    }

    public String toString() {
        return "STUDENT " + super.toString();
    }

    public static void main(String[] args) {


        try {
            StudentCustomer a = new StudentCustomer(33232, "Gordon", 50);
            System.out.println(a);
        } catch(Exception e) {
            System.out.println(e);
        }
    }


}
