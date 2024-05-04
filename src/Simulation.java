import Objects.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulation {
    public static void main(String[] args) {

        /**             NOTES
         *
         *
         * Decide whether to put snack price exception in superclass or subclass constructors
         *
         * Enums for drink sugar content? with switchcase? https://www.w3schools.com/java/java_enums.asp#:~:text=An%20enum%20can%2C%20just%20like,but%20it%20can%20implement%20interfaces).
         *  Use switchcase for sugar content in drinks
         *
         *  when going reading through customers.txt, to deal with STAFF and STUDENT, use those to determine what kind of customer
         *  each line is, and then truncate it from the line/ignore it when splitting the line with scanner
         *
         *  Change the location of try-catches to ensure program continues to run after an issue?
         *
         *  Change chargeAccount methods to return the price of the item bought, not the remaining balance - done i think
         *
         *  maybe use hashmap for collections in snackshop class? - tried and seems too much work
         *
         *  update accessor and mutators correctly
         *
         *
         *  FIX ADDING NEW STAFF CUSTOMER IN SIMULATION - done
         *
         *  Round up to nearest penny
         *
         *  Remove all customer/snack methods
         *
         *
         */



        File snackFile = new File("snacks.txt");
        File customerFile = new File("customers.txt");
        File transactionFile = new File("transactions.txt");
        SnackShop shop = initialiseShop("UEA Shop", snackFile, customerFile);

        simulateShop(shop, transactionFile);
//        test(shop, transactionFile);
        System.out.println();
//        shop.allCustomers();
//        shop.allSnacks();


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
                    if (snackValues[2].equals("hot")) {
                        //hot food
                        Food newFood = new Food(snackValues[0], snackValues[1], true, Integer.parseInt(snackValues[3]));
                        newSnackShop.addSnack(newFood);
                    } else if (snackValues[2].equals("cold")) {
                        // cold food
                        Food newFood = new Food(snackValues[0], snackValues[1], false, Integer.parseInt(snackValues[3]));
                        newSnackShop.addSnack(newFood);
                    }

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

                if (noOfValues == 2) {
                    Customer customer = new Customer(custValues[0], custValues[1]);
                    newSnackShop.addCustomer(customer);
                } else if (noOfValues == 3) {
                    Customer customer2 = new Customer(custValues[0], custValues[1], Integer.parseInt(custValues[2]));
                    newSnackShop.addCustomer(customer2);
                } else if (custValues[3].equals("STUDENT")) {
                    StudentCustomer newStu = new StudentCustomer(custValues[0], custValues[1], Integer.parseInt(custValues[2]));
                    newSnackShop.addCustomer(newStu);

                } else if (custValues[3].equals("STAFF")) {

                    switch(noOfValues) {
                        case 4: // STAFF no school
                            StaffCustomer newStaff2 = new StaffCustomer(custValues[0], custValues[1], Integer.parseInt(custValues[2]), "");
                            newSnackShop.addCustomer(newStaff2);
                            break;

                        case 5: // STAFF with school
                            StaffCustomer newStaff = new StaffCustomer(custValues[0], custValues[1], Integer.parseInt(custValues[2]), custValues[4]);
                            newSnackShop.addCustomer(newStaff);
                            break;
                    }
                }
            }

        } catch(Exception e) {
            System.out.println("Error: " + e);
        }

        return newSnackShop;
    }

//    public static void result(SnackShop shop) {
//        System.out.println("Largest base price of snack: " + shop.findLargestBasePrice());
//        System.out.println("Number of negative balances at shop: " + shop.countNegativeAccounts());
//        System.out.println("Median customer balance: " + shop.calculateMedianCustomerBalance());
//        System.out.println("Final shop turnover: " + shop.getShopTurnover());
//    }

    public static void simulateShop(SnackShop shop, File transactionFile) {
//        int x = 1;


        try {
            Scanner scanner = new Scanner(transactionFile);
            while (scanner.hasNextLine()) {
                System.out.println(); // to seperate lines
                String line = scanner.nextLine();
                String[] transactionValues = line.split(",");

//                System.out.println("\nProcess no: " + x);

                try {
                    switch (transactionValues[0]) {
                        case "PURCHASE":
                            if (shop.processPurchase(transactionValues[1], transactionValues[2])) {
                                System.out.println(shop.getCustomer(transactionValues[1]).getName() + " has successfully purchased " + shop.getSnack(transactionValues[2]).getName());

                            } else {
                                System.out.println("Transaction failed.");
                            }
                            break;

                        case "ADD_FUNDS":
                            shop.getCustomer(transactionValues[1]).addFunds(Integer.parseInt(transactionValues[2]));
                            System.out.println("Successfully added " + Integer.parseInt(transactionValues[2]) + " to " + shop.getCustomer(transactionValues[1]));
                            break;

                        case "NEW_CUSTOMER":
                            if (transactionValues[3].equals("STUDENT")) {
                                StudentCustomer newStu = new StudentCustomer(transactionValues[1], transactionValues[2], Integer.parseInt(transactionValues[4]));
                                shop.addCustomer(newStu);
                                System.out.println("Added new customer: " + newStu);

                            } else if (transactionValues[3].equals("STAFF")) {
                                int noOfValues = transactionValues.length;
                                switch (noOfValues) {
                                    case 5: // STAFF no school
                                        StaffCustomer newStaff = new StaffCustomer(transactionValues[1], transactionValues[2], Integer.parseInt(transactionValues[4]), "");
                                        shop.addCustomer(newStaff);
                                        System.out.println("Added new customer: " + newStaff);
                                        break;

                                    case 6: // STAFF with school
                                        StaffCustomer newStaff2 = new StaffCustomer(transactionValues[1], transactionValues[2], Integer.parseInt(transactionValues[5]), transactionValues[4]);
                                        shop.addCustomer(newStaff2);
                                        System.out.println("Added new customer: " + newStaff2);
                                        break;
                                }

                            } else {
                                int noOfValues = transactionValues.length;
                                switch (noOfValues) {
                                    case 3:
                                        Customer newCust = new Customer(transactionValues[1], transactionValues[2]);
                                        shop.addCustomer(newCust);
                                        System.out.println("Added new customer: " + newCust);
                                        break;

                                    case 4:
                                        Customer newCust2 = new Customer(transactionValues[1], transactionValues[2], Integer.parseInt(transactionValues[3]));
                                        shop.addCustomer(newCust2);
                                        System.out.println("Added new customer: " + newCust2);
                                        break;
                                }

                            }
                    }

                } catch (Exception e) {
                    System.out.println("Error: " + e);
                }
//                x++;
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }


        System.out.println("\nLargest base price of snack: " + shop.findLargestBasePrice());
        System.out.println("Number of negative balances at shop: " + shop.countNegativeAccounts());
        System.out.println("Median customer balance: " + shop.calculateMedianCustomerBalance());
        System.out.println("Final shop turnover: " + shop.getShopTurnover());
    }

}
