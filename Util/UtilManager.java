package Util;
import java.awt.geom.Point2D.Double;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


public class UtilManager {
    public static void displayArray(float[][] arr) {
        for (float[] row : arr) {
            for (float item : row) {
                System.out.print(item + " | ");
            }
            System.out.println();
        }
    }

    public static void displayArray(int[][] arr) {
        for (var row : arr) {
            System.out.print(Arrays.toString(row));
            System.out.println();
        }
    }

    public static void displayArray(double[][] arr) {
        for (var row : arr) {
            for (var item : row) {
                System.out.print(item + " | ");
            }
            System.out.println();
        }
    }

    public static void displayArray(long[][] arr) {
        for (var row : arr) {
            for (var item : row) {
                System.out.print(item + " | ");
            }
            System.out.println();
        }
    }

    public static void displayArray(String[][] arr) {
        for (var row : arr) {
            for (var item : row) {
                System.out.print(item + " | ");
            }
            System.out.println();
        }
    }

    public static void displayArray(char[][] arr) {
        for (var row : arr) {
            for (var item : row) {
                System.out.print(item + " | ");
            }
            System.out.println();
        }
    }

    public UtilManager() {}

    public static double randomDouble(int min, int max) {
        return Math.random() * (max - min) + min;
    }

    public static int randomInt(int min, int max) {
        return (int) Math.round(randomDouble(min, max));
    }

    public int waitForInt() {
        System.out.println("Enter an integer:");
        
        Scanner in = new Scanner(System.in);

        try {
            int out = in.nextInt();
            in.close();
            return out;
        } catch(InputMismatchException err) {
            System.out.println("You've entered an incorrect value. Expected int.");
            return waitForInt();
        } finally {
            in.close();
        }
    }

    public double waitForDouble(String... message) {
        if (message.length != 0) {
            System.out.println(message[0]);
        }

        Scanner in = new Scanner(System.in);

        try {
            double out = in.nextDouble();
            return out;
        } catch(InputMismatchException err) {
            System.out.println("You've entered an incorrect value. Expected float.");
            return waitForDouble(message);
        }
    }

    public Double waitForPoint() {
        System.out.println("Enter x:");
        double x = waitForDouble();
        System.out.println("Enter y:");
        double y = waitForDouble();

        Double p = new Double();
        System.out.println("Entered point: [" + x + ", " + y + "]");
        p.setLocation(x, y);

        return p;
    }
}
