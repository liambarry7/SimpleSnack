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
        // Creates SnackShop object
        this.shopName = shopName;
        this.shopTurnover = 0;
        this.customers = new ArrayList<>();
        this.snacks = new ArrayList<>();
    }

    public String toString() {
        return shopName + " " + shopTurnover + " " + customers + " " + snacks;
    }

    public void addCustomer(Customer newCustomer) throws InvalidCustomerException {
        // Adds new customer to snackShop customer arraylist
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getAccountID().equals(newCustomer.getAccountID())) {
                throw new InvalidCustomerException("New customer cannot be added - customer already exists.");
            }
        }
        customers.add(newCustomer);
    }

    public void addSnack(Snack newSnack) {
        // adds new snack to snackshop snack arraylist
        snacks.add(newSnack);
    }

    public Boolean processPurchase(String customerID, String snackID) throws InvalidCustomerException, InvalidSnackException {
        // Completes a purchase on a customers account based a given customerID and snackID
        // (these are used to find the appropriate values and call the appropiate methods
        Boolean purchaseComplete = false;

        Customer currentCustomer = this.getCustomer(customerID);
        Snack purchasedSnack = this.getSnack(snackID);

        try {
            this.shopTurnover = this.shopTurnover + currentCustomer.chargeAccount(purchasedSnack.calculatePrice()); // chargeAccount returns price of snack after discounts/surplus applied
            purchaseComplete = true;

        } catch (Exception e) {
            System.out.println("Error: " + e);
            purchaseComplete = false;
        }

        return purchaseComplete;
    }

    public Customer getCustomer(String customerID) throws InvalidCustomerException {
         // Returns the customer object of a given customerID
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
        // Returns the snack object of the given snackID
        Snack snack = null;
        for (int i = 0; i < snacks.size(); i++) {
            if (snacks.get(i).getSnackID().equals(snackID)) {
                snack = snacks.get(i);
            }
        }

        if (snack != null) {
            return snack;
        } else {
            throw new InvalidSnackException("Invalid snack ID.");
        }
    }

    public int findLargestBasePrice() {
        // Returns the largest base price of a snack in the snackshop snack arraylist
        int largestBasePrice = -1;
        for (int i = 0; i < snacks.size(); i++) {
            if (snacks.get(i).getBasePrice() > largestBasePrice) {
                largestBasePrice = snacks.get(i).getBasePrice();
            }
        }

        return largestBasePrice;
    }

    public int countNegativeAccounts() {
        // Returns the amount of accounts with a negative balance in the snackShop customer arraylist
        int noOfNegativeAccounts = 0;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getAccountBalance() < 0) {
                noOfNegativeAccounts++;
            }
        }
        return noOfNegativeAccounts;
    }

    public int calculateMedianCustomerBalance() {
        // Returns the median balance of accounts in the snackShop customer arraylist
        int[] median = new int[customers.size()];
        int medianValue = -1;

        for (int i = 0; i < customers.size(); i++) {
            median[i] = customers.get(i).getAccountBalance();
        }

        Arrays.sort(median);

        double middleIndex = ((double) median.length) / 2;
        if (middleIndex % 1 != 0) { // odd no of values, perfect middle (if list.length is odd and when /2, ends in .5)
            medianValue = median[(int)middleIndex];
        } else {
            // index before middle + index after middle (for an even no of values in list)
            medianValue = (median[(int)middleIndex-1] + median[(int)middleIndex]) / 2;
        }

        return medianValue;
    }

    public String getShopName() {
        // Returns the name of the snackshop
        return shopName;
    }

    public int getShopTurnover() {
        // Returns the SnackShop turnover
        return shopTurnover;
    }

    public void setShopName(String shopName) {
        // Changes the SnackShop name
        this.shopName = shopName;
    }

    public static void main(String[] args) {
        try {
            Customer cust1 = new Customer("45ND82", "David Maloney", 1500);
            Customer cust2 = new Customer("2H37Y9", "Robert Darkman");
            StaffCustomer staff1 = new StaffCustomer("2020CV", "Kimi Raikkonen", 2500, "CMP");
            StaffCustomer staff2 = new StaffCustomer("45ASD7", "Jenson Button", 600,  "BIO");
            StaffCustomer staff3 = new StaffCustomer("23DBA3", "Nico Rosberg", "");
            StudentCustomer stu1 = new StudentCustomer("4IK83H", "Cameron Russell", 2500);
            StudentCustomer stu2 = new StudentCustomer("23D4J3", "Keke Rosberg");

            Food chocolate = new Food("F/3218513", "Chocolate", false, 90);
            Drink fanta = new Drink("D/1232114", "Fanta", "high", 180);
            Drink water = new Drink("D/1252424", "Water", 100);

            SnackShop shop = new SnackShop("UEA shop");
            System.out.println(shop);

            // Add customers and snacks to shop
            shop.addCustomer(cust1);
            shop.addCustomer(cust2);
            shop.addCustomer(staff1);
            shop.addCustomer(staff2);
            shop.addCustomer(staff3);
            shop.addCustomer(stu1);
            shop.addCustomer(stu2);
            shop.addSnack(chocolate);
            shop.addSnack(fanta);
            shop.addSnack(water);
            System.out.println(shop);

            // Get snack and customer from shop
            System.out.println("\n" + shop.getSnack("F/3218513"));
            System.out.println(shop.getCustomer("23D4J3"));

            // turnover and purchases
            System.out.println("\nTurnover before purchases: " + shop.getShopTurnover());

            shop.processPurchase("23D4J3", "F/3218513");
            System.out.println(shop.getCustomer("23D4J3").getName() + " has successfully purchased " + shop.getSnack("F/3218513").getName() + " for £" + String.format("%.2f", (Double.valueOf(shop.getSnack("F/3218513").getBasePrice()) / 100)));

            shop.processPurchase("45ASD7", "D/1232114");
            System.out.println(shop.getCustomer("45ASD7").getName() + " has successfully purchased " + shop.getSnack("D/1232114").getName() + " for £" + String.format("%.2f", (Double.valueOf(shop.getSnack("D/1232114").getBasePrice()) / 100)));

            shop.processPurchase("2020CV", "D/1252424");
            System.out.println(shop.getCustomer("2020CV").getName() + " has successfully purchased " + shop.getSnack("D/1252424").getName() + " for £" + String.format("%.2f", (Double.valueOf(shop.getSnack("D/1252424").getBasePrice()) / 100)));

            shop.processPurchase("4IK83H", "D/1232114");
            System.out.println(shop.getCustomer("4IK83H").getName() + " has successfully purchased " + shop.getSnack("D/1232114").getName() + " for £" + String.format("%.2f", (Double.valueOf(shop.getSnack("D/1232114").getBasePrice()) / 100)));


            System.out.println("Turnover after purchases: " + shop.getShopTurnover());

            // Largest base price of snack
            System.out.println("\nLargest base price of snack: " + shop.findLargestBasePrice());

            // Median customer balance
            System.out.println("\nMedian customer balance: " + shop.calculateMedianCustomerBalance());

            // No. of Negative customer balances
            System.out.println("Number of negative customer balances: " + shop.countNegativeAccounts());

            //Changing shop name
            System.out.println("Shop name: " + shop.getShopName());
            shop.setShopName("Spar Store");
            System.out.println("Shop name: " + shop.getShopName());

        } catch(Exception e) {
            System.out.println(e);
        }
    }

}
