package Objects;

import Exceptions.InvalidSnackException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Drink extends Snack {

    private String sugarContent; // 24p - high, 18p - low, 0p - none

    public Drink(String snackID, String name, String sugarContent, int basePrice) throws InvalidSnackException {
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
        return sugarContent;
    }

    @Override
    public int calculatePrice() {
        // basePrice + sugar tax
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
            Drink a = new Drink("D/1232114", "Fanta", "high", 180);
            Drink b = new Drink("D/1252424", "Water", 100);
            System.out.println(a);
            System.out.println(b);

            //test calculatePrice()
            System.out.println("Snack a total price: " + a.calculatePrice());
            System.out.println("Snack b total price: " + b.calculatePrice());

        } catch(Exception e) {
            System.out.println("Error - " + e);
        }
    }
}
