package Objects;

import Exceptions.InvalidSnackException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Food extends Snack {
    private Boolean hotFood;
    public final double surchargePercentage = 0.1;

    public Food(String snackID, String name, Boolean hotFood, int basePrice) throws InvalidSnackException {
        super(snackID, name, basePrice);

        //regex to check snackID is valid
        Pattern snackIDregex = Pattern.compile("F/\\d\\d\\d\\d\\d\\d\\d"); //Letter 'F' followed by '/', then 7 digits
        Matcher matcher = snackIDregex.matcher(snackID);

        if (!matcher.matches()) {
            throw new InvalidSnackException("Invalid SnackID.");
        } else if (basePrice <= 0) {
            throw new InvalidSnackException("Invalid snack price.");
        } else {
            this.hotFood = hotFood;
        }
    }

    public String toString() {
        return snackID + " " + name + " " + hotFood + " " + basePrice;
    }

    @Override
    public int calculatePrice() {
        // Calculates price of a food snack (basePrice + surplus charge)
        // Math.ceil to round up to nearest penny
        int finalPrice = 0;
        if (this.hotFood == true) {
            finalPrice = this.basePrice + (int) Math.ceil(this.basePrice * 0.1);
        } else if (this.hotFood == false) {
            finalPrice = this.basePrice;
        }
        return finalPrice;
    }

    public Boolean getHotFood() {
        // Returns if food is hot or cold (true or false)
        return hotFood;
    }

    public double getSurchargePercentage() {
        // Returns the surcharge percentage for hot foods
        return surchargePercentage;
    }

    public static void main(String[] args) {
        try {
            Food chocolate = new Food("F/3218513", "Chocolate", false, 90);
            System.out.println(chocolate);
            System.out.println("SnackID: " + chocolate.getSnackID());
            System.out.println("Name: " + chocolate.getName());
            System.out.println("Is hot food? " + chocolate.getHotFood());
            System.out.println("Snack base price: " + chocolate.getBasePrice());
            System.out.println("Surcharge Percentage: " + chocolate.getSurchargePercentage());
            System.out.println("Snack price: " + chocolate.calculatePrice());


        } catch (Exception e) {
            System.out.println("Error:  " + e);
        }

    }
}
