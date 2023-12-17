import java.awt.geom.Point2D;
import java.util.*;
import java.util.concurrent.TimeUnit;

import SDF.SDFTerminalRenderer;
import SDF.SDFWorld;
import Vector.Vector3;
import Vector.FloatVec3;

import Util.UtilManager;
public class Main {
    public static boolean isPrimeNumber(int n) {
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) return false;
        }

        return true;
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

    public static void runTask8_5() {
        Scanner in = new Scanner(System.in);

        char c = in.next().charAt(0);
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
        var world = new SDFWorld();
        var renderer = new SDFTerminalRenderer(world);

        renderer.render();
    }
}