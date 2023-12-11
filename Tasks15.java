import Util.Command;
import Util.CommandInterpreter;
import Util.UtilManager;

import java.util.ArrayList;


public class Tasks15 {

    public static void runTask1() {
        float[][] arr = new float[8][8];

        for (float[] row : arr) {
            for (int i = 0; i < row.length; i++) {
                row[i] = (float) 1;
            }
        }

        UtilManager.displayArray(arr);

        float firstSum = 0;
        for (int i = 0; i < arr.length; i++) {
            firstSum += arr[i][i];
        }

        float secondSum = 0;
        for (int i = 0; i < arr.length; i++) {
            secondSum += arr[arr.length - i - 1][i];
        }

        System.out.println(firstSum);
        System.out.println(secondSum);
    }


    public static void runTask5() {
        var results = new ArrayList<int[]>();

        Command[] commands = {
                new Command("r", (line) -> {
                    int[] newRoll = {
                            UtilManager.randomInt(1, 6),
                            UtilManager.randomInt(1, 6)
                    };

                    results.add(newRoll);
                    System.out.println("Added new roll");

                    return true;
                }),
                new Command("s", (line) -> {
                    System.out.println("Rolls history:");

                    for (var result : results) {
                        System.out.println(result[0] + " " + result[1]);
                    }
                    return true;
                }),
                new Command("q", (line) -> {
                    System.out.println("Quitting the program...");
                    return false;
                })
        };

        CommandInterpreter interpreter = new CommandInterpreter(commands);

        interpreter.listen(">");
    }
}
