import java.util.Arrays;

public class Task17 {

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
