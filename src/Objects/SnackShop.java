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
            SnackShop a = new SnackShop("name");
            Customer cust1 = new Customer("cu1000", "dave", 1000);
            Customer cust2 = new Customer("cust50", "ed", 50);
            StaffCustomer staff = new StaffCustomer("sf1000", "al", 1000, "CMP");
            StaffCustomer staff2 = new StaffCustomer("sf1000", "ai", 1000, "BIO");
            StudentCustomer stu = new StudentCustomer("stud-1", "ed", -1);
            StudentCustomer stu2 = new StudentCustomer("stu-20", "fi", -500);


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
//            System.out.println("\n" + a.getCustomer("123456"));
            System.out.println();

            System.out.println("turnover before: " + a.getShopTurnover());

            a.processPurchase("cu1000", "D/3238145");
            System.out.println(cust1);

            a.processPurchase("sf1000", "D/3238145");
            System.out.println(staff);

            System.out.println(a.processPurchase("sf1000", "D/3238145"));

            System.out.println(a.processPurchase("stu-20", "D/3238145"));
            System.out.println(stu2);


            System.out.println("turnover after: " + a.getShopTurnover());

            System.out.println("Largest Base Price: " + a.findLargestBasePrice());
            System.out.println("No of negative accounts: " + a.countNegativeAccounts());

            System.out.println("median acc balancee: " + a.calculateMedianCustomerBalance());
            a.calculateMedianCustomerBalance();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

}
