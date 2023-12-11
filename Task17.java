import Util.UtilManager;

import java.util.ArrayList;
import java.util.Arrays;

public class Task17 {

    public static void run() {
        var square = new int[8][8];
        Task17.fillWithRings(square, 0);
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
    }

    public static void fillWithRings(int[][] arr, int ring) {
        int ringValue = ring + 1;

        for (int i = ring; i < arr.length - ring; i++) {
            arr[ring][i] = ringValue;
        }

        for (int i = ring; i < arr.length - ring; i++) {
            arr[i][arr.length - ringValue] = ringValue;
        }

        for (int i = ring; i < arr.length - ring; i++) {
            arr[arr.length - ringValue][i] = ringValue;
        }

        for (int i = ring; i < arr.length - ring; i++) {
            arr[i][ring] = ringValue;
        }

        if (ring < arr.length / 2) {
            fillWithRings(arr, ringValue);
        }
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                int nextElement = arr[j + 1];
                if (arr[j] > nextElement) {
                    arr[j + 1] = arr[j];
                    arr[j] = nextElement;
                }
            }
        }
    }

    public static void bubbleSortRe(int[] arr, int n) {
        if (n < 1) return;

        for (int i = 0; i < n - 1; i++) {
            int nextElement = arr[i + 1];
            if (arr[i] > nextElement) {
                arr[i + 1] = arr[i];
                arr[i] = nextElement;
            }
        }

        bubbleSortRe(arr, n - 1);
    }

    public static Integer binSearch(int[] arr, int begin, int end, int value) {
        int middleIndex = begin + (end - begin) / 2;
        System.out.print(middleIndex + " " + begin + " " + end + "\n");


        if (arr[middleIndex] == value) {
            return middleIndex;
        } else if (end - begin <= 1) {
            return null;
        } else if (arr[middleIndex] > value) {
            return binSearch(arr, begin, middleIndex, value);
        }

        return binSearch(arr, middleIndex, end, value);
    }
}
