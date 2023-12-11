import Util.UtilManager;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Tasks13 {

    private static int[][] arr = {
            {1, 0, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1}
    };

    private static void printIntArr(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static int[] flatArray2() {
        // java type system is confusing, took me some time to understand the type errors
        Stream<int[]> str = Arrays.stream(arr);

        IntStream flatStream = str.flatMapToInt((row) -> Arrays.stream(row));

        int[] outArray = flatStream.toArray();
        printIntArr(outArray);
        return outArray;
    }

    public static int[] flatArray() {

        int arrSize = 0;
        for (int[] row : arr) {
            arrSize += row.length;
        }

        int[] outArray = new int[arrSize];

        int index = 0;
        for (int[] row : arr) {
            for (int item : row) {
                outArray[index++] = item;
            }
        }

        printIntArr(outArray);
        return outArray;
    }

    private static int[] resizeArray(int[] arr) {
        int[] resizedNewArr = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            resizedNewArr[i] = arr[i];
        }

        return resizedNewArr;
    }

    public static void runTask2() {
        int colSize = UtilManager.randomInt(1, 10);
        int rowSize = UtilManager.randomInt(1, 10);


        int[][] arr = new int[colSize][rowSize];

        for (int[] row : arr) {
            for (int i = 0; i < row.length; i++) {
                row[i] = UtilManager.randomInt(0, 10);
            }
        }

        for (int[] row : arr) {
            printIntArr(row);
        }

        int[][] outArray = new int[colSize][];

        for (int j = 0; j < arr.length; j++) {
            int[] row = arr[j];
            int newLength = 0;
            // would love to use probably a list here
            int[] newRow = new int[0];


            rowLoop: for (int i = 0; i < row.length; i++) {
                for (int k = 0; k < row.length; k++) {
                    int potentialDuplicate = row[k];
                    if (k != i && potentialDuplicate == row[i]) {
                        continue rowLoop;
                    }
                }


                newLength++;
                if (newRow.length < newLength) {
                    newRow = resizeArray(newRow);
                }
                newRow[newLength - 1] = row[i];
            }

            outArray[j] = newRow;
        }

        System.out.println("\n\n");
        for (int[] row : outArray) {
            printIntArr(row);
        }
    }
}
