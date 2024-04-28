package Objects;

import Exceptions.InvalidCustomerException;
import Exceptions.InvalidSnackException;

import java.util.ArrayList;
import java.util.HashMap;

public class testShop {
    private String shopName;
    private int shopTurnover;

    private HashMap<Integer, Customer> customers;
    private HashMap<String, Snack> snacks;

    public testShop(String shopName) {
        this.shopName = shopName;
        this.shopTurnover = 0;
        this.customers = new HashMap<Integer, Customer>();
        this.snacks = new HashMap<String, Snack>();
    }

    public String toString() {
        return shopName + " " + shopTurnover + " " + customers + " " + snacks;
    }

    public void addCustomer(Customer newCustomer) {
        customers.put(newCustomer.getAccountID(), newCustomer);
    }

    public void addSnack(Snack newSnack) {
        snacks.put(newSnack.getSnackID(), newSnack);
    }

    public Boolean processPurchase(String customerID, String snackID) throws InvalidCustomerException, InvalidSnackException {
//        Customer currentCustomer = customers.get(Integer.parseInt(customerID));
        Customer currentCustomer = this.getCustomer(customerID);
//        if (currentCustomer == null) {
//            throw new InvalidCustomerException("Customer ID invalid.");
//        }

//        Snack purchasedSnack = snacks.get(snackID);
        Snack purchasedSnack = this.getSnack(snackID);
//        if (purchasedSnack == null) {
//            throw new InvalidSnackException("Invalid snack ID.");
//        }

        try {
            System.out.println(currentCustomer.chargeAccount(purchasedSnack.basePrice));
            this.shopTurnover = shopTurnover + currentCustomer.chargeAccount(purchasedSnack.basePrice);
            return true;

        } catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Customer getCustomer(String customerID) throws InvalidCustomerException {
        Customer customer = customers.get(Integer.parseInt(customerID));
        if (customer == null) {
            throw new InvalidCustomerException("Invalid customer ID.");
        }
        return customer;
    }

    public Snack getSnack(String snackID) throws InvalidSnackException {
        Snack snack = snacks.get(snackID);
        if (snack == null) {
            throw new InvalidSnackException("Invalid snack ID.");
        }
        return snack;
    }


    public int returnLargestBasePrice() {
        // cycle through list of snacks
        // compare each one - if larger, store, if lesser, ignore




        Snack currentSnack;
//
//        for (Snack i : snacks.values()) { // i = key
//            System.out.println(snacks.get(i));
//            currentSnack = snacks.get(i);
//            Snack nextSnack = snacks.get(i+1);
//
//            if (nextSnack == null) {
//                System.out.println("end loop");
//            } else if (currentSnack.getBasePrice() > nextSnack.getBasePrice()) {
//                System.out.println("ah");
//            }
//        }

        return -1;
    }

    public int countNegativeAccounts() {
        return -1;
    }

    public int calculateMedianCustomerBalance() {
        return -1;
    }

    public String getShopName() {
        return shopName;
    }

    public int getShopTurnover() {
        return shopTurnover;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void h() {
        // testing hashmap
        System.out.println(customers.get(123456));
        System.out.println(snacks.get("F/3281435"));
    }

    public static void main(String[] args) {
        try {
            testShop a = new testShop("name");
            Customer cust1 = new Customer(123454, "dave", 50);
            StaffCustomer cust2 = new StaffCustomer(123456, "al", 1000, "CMP");
            StudentCustomer stu = new StudentCustomer(123123, "ed", 50);
            Drink snack1 = new Drink("D/3238145", "can", 100);
            Food snack2 = new Food("F/3281435", "crisp", true, 60);

//            System.out.println(a + "bbb");
            a.addCustomer(cust1);
            a.addCustomer(cust2);
            a.addCustomer(stu);
            a.addSnack(snack1);
            a.addSnack(snack2);
//            System.out.println(a + "asd");
//            a.h();
//            System.out.println("\n\n");
//
            a.processPurchase("123456", "D/3238145");

            System.out.println(a.getCustomer("123456"));


            a.returnLargestBasePrice();

        } catch(Exception e) {
            System.out.println(e);
        }



    }
}
