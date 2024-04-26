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

//        if (matcher.matches()) {
//            this.hotFood = hotFood;
//
//        } else {
//            throw new InvalidSnackException("Invalid snackID");
//        }

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
        // basePrice + surplus charge
        // Math.round to nearest penny
        return (int) Math.round((this.basePrice + (this.basePrice * surchargePercentage)));
    }

//    public void test() {
//        int a = (int) (this.basePrice + (this.basePrice * surchargePercentage));
//        double b = this.basePrice + (this.basePrice * surchargePercentage);
//        System.out.println("as int: " + a);
//        System.out.println("as dbl: " + b);
//
//        int x = (int) Math.round(b);
//        System.out.println("b with round " + x);
//
//    }

    public Boolean getHotFood() {
        return hotFood;
    }

    public double getSurchargePercentage() {
        return surchargePercentage;
    }

    public void setHotFood(Boolean hotFood) {
        this.hotFood = hotFood;
    }



    public static void main(String[] args) {
        try {
            Food a = new Food("F/3218513", "Chocolate", false, -1);
            System.out.println(a);

            System.out.println(a.calculatePrice());
//            a.test();


        } catch (Exception e) {
            System.out.println("Error - " + e);
        }

    }
}
