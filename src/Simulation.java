import Objects.*;

import java.io.File;

public class Simulation {
    public static void main(String[] args) {

        /**             NOTES
         *
         *
         * Decide whether to put snack price exception in superclass or subclass constructors
         * Enums for drink sugar content? with switchcase? https://www.w3schools.com/java/java_enums.asp#:~:text=An%20enum%20can%2C%20just%20like,but%20it%20can%20implement%20interfaces).
         *  Use switchcase for sugar content in drinks
         *
         *  when going reading through customers.txt, to deal with STAFF and STUDENT, use those to determine what kind of customer
         *  each line is, and then truncate it from the line/ignore it when splitting the line with scanner
         *
         *  Change the location of try-catches to ensure program continues to run after an issue?
         *
         *  Change chargeAccount methods to return the price of the item bought, not the remaining balance
         *
         *  maybe use hashmap for collections in snackshop class?
         *
         *  update accessor and mutators correctly
         *
         *
         */



//        System.out.println("Hello world!");
//        System.out.println("test");
//
//        try {
////            Snack s = new Snack("F/1231231", "a", 23);
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }

//        initialiseShop("UEA Shop", snacks.txt, customer.txt);


    }

    public static SnackShop initialiseShop(String shopName, File snackFile, File customerFile) {
        SnackShop newSnackShop = new SnackShop(shopName);

        // read snackFile
        // create snack object from each line

        // read customer file
        // create customer object from each line




        return newSnackShop;
    }


}
