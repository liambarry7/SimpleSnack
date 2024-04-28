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



        File snackFile = new File("snacks.txt");
        File customerFile = new File("customers.txt");
        SnackShop shop = initialiseShop("UEA Shop", snackFile, customerFile);
        shop.allCustomers();
        shop.allSnacks();


    }

    public static SnackShop initialiseShop(String shopName, File snackFile, File customerFile) {
        SnackShop newSnackShop = new SnackShop(shopName);

        // read snackFile
        // create snack object from each line
        try {
            Scanner scanSnack = new Scanner(snackFile);
            while (scanSnack.hasNextLine()) {
                String line = scanSnack.nextLine();
                String[] snackValues = line.split("@");

                if (snackValues[0].contains("D")) { // if drink
                    Drink newDrink = new Drink(snackValues[0], snackValues[1], snackValues[2], Integer.parseInt(snackValues[3]));
                    newSnackShop.addSnack(newDrink);

                } else if (snackValues[0].contains("F")) {
                    Food newFood = new Food(snackValues[0], snackValues[1], Boolean.parseBoolean(snackValues[2]), Integer.parseInt(snackValues[3]));
                    newSnackShop.addSnack(newFood);
                }
            }

        } catch(Exception e) {
            System.out.println("Error: " + e);
        }


        // read customer file
        // create customer object from each line
        try {
            Scanner scanCust = new Scanner(customerFile);
            while (scanCust.hasNextLine()) {
                String line = scanCust.nextLine();
                String[] custValues = line.split("#");

                int noOfValues = custValues.length;
                switch (noOfValues) {
                    case 2:
                        Customer newCust = new Customer(custValues[0], custValues[1]);
                        newSnackShop.addCustomer(newCust);
                    case 3:
                        Customer newCust2 = new Customer(custValues[0], custValues[1], Integer.parseInt(custValues[2]));
                        newSnackShop.addCustomer(newCust2);
                        break;
                    case 4:
                        StudentCustomer newStu = new StudentCustomer(custValues[0], custValues[1], Integer.parseInt(custValues[2]));
                        newSnackShop.addCustomer(newStu);
                        break;
                    case 5:
                        StaffCustomer newStaff = new StaffCustomer(custValues[0], custValues[1], Integer.parseInt(custValues[2]), custValues[4]);
                        newSnackShop.addCustomer(newStaff);
                        break;
                }
            }



        } catch(Exception e) {
            System.out.println("Error: " + e);
        }

        return newSnackShop;
    }

    public static void simulateShop(SnackShop shop, File transactionFile) {
        try {
            Scanner scan = new Scanner(transactionFile);




        } catch(Exception e) {
            System.out.println("Error: " + e);
        }
    }


}
