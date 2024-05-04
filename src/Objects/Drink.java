package Objects;

import Exceptions.InvalidSnackException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Drink extends Snack {

    private String sugarContent; // 24p - high, 18p - low, 0p - none

    public Drink(String snackID, String name, String sugarContent, int basePrice) throws InvalidSnackException {
        // Constructor to create Drink object including sugar content
        super(snackID, name, basePrice);

        //regex to check snackID is valid
        Pattern snackIDregex = Pattern.compile("D/\\d\\d\\d\\d\\d\\d\\d"); //Letter 'D' followed by '/', then 7 digits
        Matcher matcher = snackIDregex.matcher(snackID);

        if (!matcher.matches()) {
            throw new InvalidSnackException("Invalid SnackID.");
        } else if (basePrice <= 0) {
            throw new InvalidSnackException("Invalid snack price.");
        } else {
            this.sugarContent = sugarContent;
        }
    }

    public Drink(String snackID, String name, int basePrice) throws InvalidSnackException {
        // Constructor to create Drink object excluding sugar content
        super(snackID, name, basePrice);

        //regex to check snackID is valid
        Pattern snackIDregex = Pattern.compile("D/\\d\\d\\d\\d\\d\\d\\d"); //Letter 'D' followed by '/', then 7 digits
        Matcher matcher = snackIDregex.matcher(snackID);

        if (!matcher.matches()) {
            throw new InvalidSnackException("Invalid SnackID.");
        } else if (basePrice <= 0) {
            throw new InvalidSnackException("Invalid snack price.");
        } else {
            this.sugarContent = "none";
        }

    }

    public String toString() {
        return snackID + " " + name + " " + sugarContent + " " + basePrice;
    }

    public String getSugarContent() {
        // Returns sugar content value
        return sugarContent;
    }

    @Override
    public int calculatePrice() {
        // Calculate price of snack bases on sugar content (basePrice + sugar tax)
        int totalPrice = 0;
        if (this.sugarContent.equals("high")) {
            totalPrice = this.basePrice + 24;
        } else if (this.sugarContent.equals("low")) {
            totalPrice = this.basePrice + 18;
        } else if (this.sugarContent.equals("none")) {
            totalPrice = this.basePrice;
        }
        return totalPrice;
    }


    public static void main(String[] args) {
        try {
            Drink fanta = new Drink("D/1232114", "Fanta", "high", 180);
            Drink water = new Drink("D/1252424", "Water", 100);
            System.out.println(fanta);
            System.out.println(water);

            System.out.println("SnackID: " + fanta.getSnackID());
            System.out.println("Name: " + fanta.getName());
            System.out.println("Fanta Sugar content: " + fanta.getSugarContent());
            System.out.println("Water Sugar content: " + water.getSugarContent());
            System.out.println("Base price: " + fanta.getBasePrice());

            //test calculatePrice()
            System.out.println("Fanta total price: " + fanta.calculatePrice());
            System.out.println("Water total price: " + water.calculatePrice());

        } catch(Exception e) {
            System.out.println("Error - " + e);
        }
    }
}
