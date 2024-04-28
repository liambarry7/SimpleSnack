package Objects;

import Exceptions.InvalidCustomerException;
import Exceptions.InvalidSnackException;

import java.util.ArrayList;
import java.util.Arrays;

public class SnackShop {
    private String shopName;
    private int shopTurnover;

    private ArrayList<Customer> customers;
    private ArrayList<Snack> snacks;

    public SnackShop(String shopName) {
        this.shopName = shopName;
        this.shopTurnover = 0;
        this.customers = new ArrayList<>();
        this.snacks = new ArrayList<>();
    }

    public String toString() {
        return shopName + " " + shopTurnover + " " + customers + " " + snacks;
    }

    public void addCustomer(Customer newCustomer) {

        customers.add(newCustomer);
    }

    public void addSnack(Snack newSnack) {
        snacks.add(newSnack);
    }

    public Boolean processPurchase(String customerID, String snackID) {
        try {
            Customer currentCustomer = this.getCustomer(customerID);
            Snack purchasedSnack = this.getSnack(snackID);

            System.out.println(currentCustomer.chargeAccount(purchasedSnack.basePrice));
            this.shopTurnover = shopTurnover + currentCustomer.chargeAccount(purchasedSnack.basePrice);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return false;
    }

    public Customer getCustomer(String customerID) throws InvalidCustomerException {
        Customer customer = null;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getAccountID().equals(customerID)) {
                customer = customers.get(i);
            }
        }

        if (customer != null) {
            return customer;
        } else {
            throw new InvalidCustomerException("Invalid customer ID.");
        }
    }

    public Snack getSnack(String snackID) throws InvalidSnackException {
        Snack snack = null;
        for (int i = 0; i < snacks.size(); i++) {
            if (snacks.get(i).getSnackID().equals(snackID)) {
                snack = snacks.get(i);
            }
        }

        if (snack != null) {
            return snack;
        } else {
            throw new InvalidSnackException("invalid snack ID.");
        }
    }

    public int findLargestBasePrice() {
        int largestBasePrice = -1;
        for (int i = 0; i < snacks.size(); i++) {
            if (snacks.get(i).getBasePrice() > largestBasePrice) {
                largestBasePrice = snacks.get(i).getBasePrice();
            }
        }

        return largestBasePrice;
    }

    public int countNegativeAccounts() {
        int noOfNegativeAccounts = 0;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getAccountBalance() < 0) {
                noOfNegativeAccounts++;
            }
        }
        return noOfNegativeAccounts;
    }

    public int calculateMedianCustomerBalance() {
        int[] medium = new int[customers.size()];
        int medianValue = -1;


        for (int i = 0; i < customers.size(); i++) {
            medium[i] = customers.get(i).getAccountBalance();
        }

        Arrays.sort(medium);


        double middleIndex = ((double) medium.length) / 2;
        if (middleIndex % 1 != 0) { // odd no of values, perfect middle (if list.length is odd and when /2, ends in .5)
            medianValue = medium[(int)middleIndex];
        } else {
            // index before middle + index after middle (for an even no of values in list)
            // casting to int always rounds up
            medianValue = (medium[(int)middleIndex-1] + medium[(int)middleIndex]) / 2;
        }

        return medianValue;
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

    public static void main(String[] args) {
        try {
            SnackShop a = new SnackShop("name");
            Customer cust1 = new Customer("123456", "dave", 1000);
            Customer cust2 = new Customer("165446", "ed", 50);
            StaffCustomer staff = new StaffCustomer("123457", "al", 1000, "CMP");
            StaffCustomer staff2 = new StaffCustomer("423457", "ai", 1000, "BIO");
            StudentCustomer stu = new StudentCustomer("123458", "ed", -1);
            StudentCustomer stu2 = new StudentCustomer("987654", "fi", -20);


            Drink snack1 = new Drink("D/3238145", "can", 200);
            Food snack2 = new Food("F/3281435", "crisp", true, 250);

            System.out.println(a.toString());
            a.addCustomer(cust1);
            a.addCustomer(cust2);
            a.addCustomer(staff);
            a.addCustomer(staff2);
            a.addCustomer(stu);
            a.addCustomer(stu2);
            a.addSnack(snack1);
            a.addSnack(snack2);
            System.out.println(a.toString());


            System.out.println("\n" + a.getSnack("F/3281435"));
            System.out.println("\n" + a.getCustomer("123456"));
            System.out.println();

            System.out.println("turnover before: " + a.getShopTurnover());

            a.processPurchase("123456", "D/3238145");
            System.out.println(cust1);

            a.processPurchase("123457", "D/3238145");
            System.out.println(staff);

            a.processPurchase("123458", "D/3238145");
            System.out.println(stu);

            System.out.println("turnover after: " + a.getShopTurnover());

            System.out.println("Largest Base Price: " + a.findLargestBasePrice());
            System.out.println("No of negative accounts: " + a.countNegativeAccounts());

            System.out.println("median acc balancee: " + a.calculateMedianCustomerBalance());
            a.calculateMedianCustomerBalance();

        } catch(Exception e) {
            System.out.println(e);
        }



    }

    public void allCustomers() {
        for (int i = 0; i < this.customers.size(); i++) {
            System.out.println(customers.get(i));
        }
        System.out.println(customers.size());
    }

    public void allSnacks() {
        for (int i = 0; i < this.snacks.size(); i++) {
            System.out.println(snacks.get(i));
        }
        System.out.println(snacks.size());
    }
}
