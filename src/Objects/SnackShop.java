package Objects;

import java.util.ArrayList;

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
//        Customer currentCustomer =


        return false;
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
            Customer cust1 = new Customer(123456, "dave", 50);
            Customer cust2 = new Customer(165446, "ed", 50);
            Drink snack1 = new Drink("D/3238145", "can", 45);
            Food snack2 = new Food("F/3281435", "crisp", true, 60);

            System.out.println(a.toString());
            a.addCustomer(cust1);
            a.addCustomer(cust2);
            a.addSnack(snack1);
            a.addSnack(snack2);
            System.out.println(a.toString());

        } catch(Exception e) {
            System.out.println(e);
        }



    }
}
