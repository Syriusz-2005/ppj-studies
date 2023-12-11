import java.util.Arrays;

public class Task17 {

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
