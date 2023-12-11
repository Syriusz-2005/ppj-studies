import java.awt.geom.Point2D;
import java.util.*;

import Util.UtilManager;

public class Main {
    public static boolean isPrimeNumber(int n) {
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) return false;
        }

        return true;
    }

    public static void mutateStr(String str) {
        str = "hi";
    }

    public static double calculate() {
        return Math.pow(Math.sqrt(2.), 2) - 2;
    }

    public static void convertTemperature() {
        Scanner in = new Scanner(System.in);

        System.out.println("Insert the temperature in fahrenheites:");
        double tempFahrenheit;
        try {
            tempFahrenheit = in.nextDouble();
        } catch(InputMismatchException exception) {
            System.out.println("This value cannot be interpreted as a fahrenheit");
            Main.convertTemperature();
            in.close();
            return;
        }

        double tempCelsius = (tempFahrenheit - 32) / 1.8;

        System.out.println(tempCelsius);

        in.close();
    }

    public static void runTask5_5() {
        UtilManager utils = new UtilManager();

        int n = utils.waitForInt();

        if (n % 10 == 0) {
            System.out.println("The number cannot be a magnitude of 10");
            Main.runTask5_5();
            return;
        }
        
        if (n < 100 || n > 999) {
            System.out.println("The number must have 3 digits");
            Main.runTask5_5();
            return;
        }

        //easier solution
        String input = String.valueOf(n);
        String out = "";
        for (char c : input.toCharArray()) {
            out = c + out;
        }

        System.out.println("Result: " + out);
    }

    public static void runTask5_4() {
        UtilManager utils = new UtilManager();

        Point2D.Double p = utils.waitForPoint();

        //Could've done that without vectors, its just an opportunity to learn something new

        System.out.println("Enter the center point");
        Point2D.Double center = utils.waitForPoint();
 
        double r = utils.waitForDouble("Enter radius: ");

        boolean isInsideTheCircle = p.distance(center) <= r;

        System.out.println(isInsideTheCircle ? "The point is inside" : "The point is not inside");

        System.out.println(center.x - r < 0 && center.y + r > 0);
        boolean isInTopLeft = center.x - r < 0 && center.y + r > 0;
        boolean isInBottomLeft = center.x - r < 0 && center.y - r < 0;
        boolean isInTopRight = center.x + r > 0 && center.y + r > 0;
        boolean isInBottomRight = center.x + r > 0 && center.y + r > 0;


        System.out.println("The circle is in the: "
                + (isInTopLeft ? " top left" : "")
                + (isInBottomLeft ? " bottom left" : "")
                + (isInTopRight ? " top right" : "")
                + (isInBottomRight ? " bottom right" : "")
        );
    }

    public static void runTask8_5() {
        Scanner in = new Scanner(System.in);

        char c = in.next().charAt(0);
    }

    public static void task8_7() {
        UtilManager util = new UtilManager();
        double arc1 = util.waitForDouble("Enter arc 1:");
        double arc2 = util.waitForDouble("Enter arc 2:");
        double arc3 = util.waitForDouble("Enter arc 3:");


        if (arc1 + arc2 + arc3 == 180) {
            System.out.println("Can be created");


            if (arc1 < 90 || arc2 < 90 || arc3 < 90) {
                System.out.println("");
            }
        }


    }

    public static void runTask9_1() {
        UtilManager util = new UtilManager();

        double maxPoints1 = util.waitForDouble("Enter max points for test 1");
        double currPoints1 = util.waitForDouble("Enter current points for test 1");

        double maxPoints2 = util.waitForDouble("Enter max points for test 2");
        double currPoints2 = util.waitForDouble("Enter current points for test 2");


        double activityPercentage = util.waitForDouble("Enter the activity percentage (0-15)");
        if (activityPercentage > 15) {
            System.out.println("You cannot have more than 15% of activity points");
            runTask9_1();
            return;
        }

        if (currPoints1 > maxPoints1 || currPoints2 > maxPoints2) {
            System.out.println("You cannot have more points than max points");
            Main.runTask9_1();
        }

        double grade = 1;
        double percentage1 = currPoints1 / maxPoints1 * 100;
        double percentage2 = currPoints2 / maxPoints2 * 100;

        if (percentage1 < 50 || percentage2 < 50) {
            System.out.println("You are dead!");
            return;
        }

        double pointsSum = 0;

        pointsSum += percentage1 / 2 + percentage2 / 2 + activityPercentage;

        if (pointsSum < 62.5) {
            grade = 3;
        } else if (pointsSum < 75) {
            grade = 3.5;
        } else if (pointsSum < 81.25) {
            grade = 4;
        } else if (pointsSum < 87.5) {
            grade = 4.5;
        } else {
            grade = 5;
        }


        System.out.println("The student got " + grade);
    }

    public static void runTask2(int day, int month) {
        int daysSum = 0;

        for (int i = 1; i < month; i++) {
            int daysCountInMonth = switch (i) {
                case 1 -> 31;
                case 2 -> 28;
                case 3 -> 31;
                case 4 -> 30;
                case 5 -> 31;
                case 6 -> 30;
                case 7 -> 31;
                case 8 -> 31;
                case 9 -> 30;
                case 10 -> 31;
                case 11 -> 30;
                case 12 -> 31;
                default -> 30;
            };
            daysSum += daysCountInMonth;
        }
        daysSum += day;
        System.out.println(daysSum);
    }

    public static void runTask2Better(int day, int month) {
        int year = new Date().getYear();
        Date currDate = new Date(year, month, day);
        Date startDate = new Date(year, 1, 0);

        long diffInMs = currDate.getTime() - startDate.getTime();
        int diffInDays = (int) Math.floor((double) diffInMs / (1000 * 3600 * 24));


        System.out.println(diffInDays);
    }

    public static int findMax(int a, int b, int c) {
        if (a > b && a > c) {
            return a;
        }

        if (b > a && b > c) {
            return b;
        }

        return c;
    }

    public static void main(String[] args) {
        var square = new int[8][8];
        Task17.fillWithCircles(square, 0);
        UtilManager.displayArray(square);

        //binary search
        int[] arr = {1, 5, 9, 10, 18};
        System.out.println(Task17.binSearch(arr, 0, arr.length, -20));


        //permutations recursive
        var elements = new ArrayList<Integer>();

        elements.add(1);
        elements.add(2);

        var result = Task16.getPermutations(elements);
        int[] elements2 = {1, 2, 3};
        var result2 = Task16.getPermutations(elements2);
        System.out.println(result);
        for (var r : result2) {
            System.out.println(Arrays.toString(r));
        }

        //      Tasks13.runTask2();

//        System.out.println(findMax(20, 14, 16));

//        int[] arr = {1, 1, 3, 4, 5, 6};
//
//        int currentNumCount = 0;
//        for (int i = 0; i < arr.length; i++) {
//            if (++currentNumCount >= arr.length / 2) {
//                System.out.print("This value satisfies the conditions: " + arr[i]);
//                return;
//            }
//
//            if (i < arr.length - 1 && arr[i] != arr[i + 1]) {
//                currentNumCount = 0;
//            }
//        }



//        TicTacToe ticTacToe = new TicTacToe();
//        ticTacToe.doTurn(0, 0);
//        ticTacToe.doTurn(2, 1);
//        ticTacToe.doTurn(1, 1);
//        ticTacToe.doTurn(0, 1);
//        ticTacToe.doTurn(2, 2);


//        System.out.println(index);
//        System.out.println(Arrays.toString(result));
//        Nominal[] nominals = {
//                new Nominal(5),
//                new Nominal(2),
//                new Nominal(1),
//                new Nominal(.5),
//                new Nominal(.2),
//                new Nominal(.1),
//                new Nominal(.05),
//                new Nominal(.02),
//                new Nominal(.01),
//        };
//
//        NominalsManager manager = new NominalsManager(nominals);
//
//        manager.calculateFor(1.75);
//        manager.printResults();
//
//
//        //task
//
//        long[] arr = new long[10];
//
//        arr[0] = 0;
//        for (int i = 1; i < arr.length; i++) {
//            arr[i] = arr[i - 1] + 1;
//        }
//
//        int iterations = 0;
//        boolean runs = true;
//        while (runs) {
//            int index1 = UtilManager.randomInt(0, arr.length);
//            int index2 = UtilManager.randomInt(0, arr.length);
//
//            long val1 = arr[index1];
//            long val2 = arr[index2];
//            arr[index1] = val2;
//            arr[index2] = val1;
//
//            iterations++;
//
//            for (int i = 1; i < arr.length; i++) {
//                if (arr[i] - arr[i - 1] == 1) {
//                    break;
//                }
//                if (i == arr.length - 1) {
//                    runs = false;
//                }
//            }
//        }
//
//
//        System.out.println(Arrays.toString(arr));
//        System.out.println(iterations);

    }
}