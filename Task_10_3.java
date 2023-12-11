import Util.UtilManager;

import java.util.Date;

public class Task_10_3 {
    public static void run() {
        UtilManager utils = new UtilManager();

        double amount = utils.waitForDouble("Enter the amount");

        int amountRepresentation = (int) (amount * 100);

        int fivesCount = 0;
        int twosCount = 0;
        int onesCount = 0;
        int fiftyGroshesCount = 0;
        int twentyGroshesCount = 0;
        int tenGroshesCount = 0;
        int fiveGroshesCount = 0;
        int twoGroshesCount = 0;
        int oneGroshCount = 0;


        while (amountRepresentation > 0) {

            if (amountRepresentation >= 500) {
                fivesCount++;
                amountRepresentation -= 500;
            } else if (amountRepresentation >= 200) {
                twosCount++;
                amountRepresentation -= 200;
            } else if (amountRepresentation >= 100) {
                onesCount++;
                amountRepresentation -= 100;
            } else if (amountRepresentation >= 50) {
                fiftyGroshesCount++;
                amountRepresentation -= 50;
            } else if (amountRepresentation >= 20) {
                twentyGroshesCount++;
                amountRepresentation -= 20;
            } else if (amountRepresentation >= 10) {
                tenGroshesCount++;
                amountRepresentation -= 10;
            } else if (amountRepresentation >= 5) {
                fiveGroshesCount++;
                amountRepresentation -= 5;
            } else if (amountRepresentation >= 2) {
                twoGroshesCount++;
                amountRepresentation -= 2;
            } else {
                oneGroshCount++;
                amountRepresentation -= 1;
            }
        }

        System.out.println("5 * " + fivesCount);
        System.out.println("2 * " + twosCount);
        System.out.println("1 * " + onesCount);
        System.out.println("50gr * " + fiftyGroshesCount);
        System.out.println("20gr * " + twentyGroshesCount);
        System.out.println("10gr * " + tenGroshesCount);
        System.out.println("5gr * " + fiveGroshesCount);
        System.out.println("2gr * " + twoGroshesCount);
        System.out.println("1gr * " + oneGroshCount);

    }

    public static void runBetter() {

    }
}
