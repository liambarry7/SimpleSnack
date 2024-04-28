import Objects.*;

import java.io.File;
import java.util.Scanner;

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

        File snackFile = new File("snacks.txt");
        File customerFile = new File("customers.txt");
        initialiseShop("UEA Shop", snackFile, customerFile);


    }

    public static SnackShop initialiseShop(String shopName, File snackFile, File customerFile) {
        SnackShop newSnackShop = new SnackShop(shopName);

        try {
            Scanner scanSnack = new Scanner(snackFile);
            while (scanSnack.hasNextLine()) {
                String line = scanSnack.nextLine();
                String[] snackValues = line.split("@");
                System.out.println(line);

                for (int i = 0; i < snackValues.length; i++) {
                    System.out.println(snackValues[i]);

                    if (snackValues[0].contains("D")) { // if drink
                        Drink newDrink = new Drink(snackValues[0], snackValues[1], snackValues[2], Integer.parseInt(snackValues[3]));
                        newSnackShop.addSnack(newDrink);

                    } else if (snackValues[0].contains("F")) {
                        Food newFood = new Food(snackValues[0], snackValues[1], Boolean.parseBoolean(snackValues[2]), Integer.parseInt(snackValues[3]));
                        newSnackShop.addSnack(newFood);

                    }
                }

                System.out.println();



            }

            System.out.println(newSnackShop.getSnack("D/7154984"));


        } catch(Exception e) {
            System.out.println("Error: " + e);
        }

        try {
            Scanner scanCust = new Scanner(customerFile);
            while (scanCust.hasNextLine()) {
                String line = scanCust.nextLine();
                String[] custValues = line.split("#");
                System.out.println(line);

                for (int i = 0; i < custValues.length; i++) {
                    System.out.println(custValues[i]);

                    if (custValues.length == 2) {
                        Customer newCustomer = new Customer(Integer.parseInt(custValues[0]), custValues[1]);
                        System.out.println(newCustomer);

                    } else if (custValues.length == 3) {
                        Customer newCustomer = new Customer(Integer.parseInt(custValues[0]), custValues[1], Integer.parseInt(custValues[2]));
                        System.out.println(newCustomer);

                    }
                }
            }


            System.out.println("ae");
        } catch(Exception e) {
            System.out.println("Error: " + e);
        }


        // read snackFile
        // create snack object from each line

        // read customer file
        // create customer object from each line



        return newSnackShop;
    }


}
