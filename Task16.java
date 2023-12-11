import java.sql.Array;
import java.util.ArrayList;

public class Task16 {

    public static ArrayList<ArrayList<Integer>> getPermutations(ArrayList<Integer> digits) {
        var outList = new ArrayList<ArrayList<Integer>>();
        if (digits.size() == 1) {
            outList.add(digits);
            return outList;
        }

        for (int i = 0; i < digits.size(); i++) {
            var digitsLeft = new ArrayList<Integer>();

            for (int j = 0; j < digits.size(); j++) {
                if (j != i) {
                    digitsLeft.add(digits.get(j));
                }
            }

            var remainingPermutations = getPermutations(digitsLeft);

            for (var permutation : remainingPermutations) {
                permutation.add(0, digits.get(i));

                outList.add(permutation);
            }
        }

        return outList;
    }

    public static ArrayList<int[]> getPermutations(int[] digits) {
        var outList = new ArrayList<int[]>();

        if (digits.length == 1) {
            outList.add(digits);
            return outList;
        }

        for (int i = 0; i < digits.length; i++) {
            var digitsLeft = new int[digits.length - 1];

            int index = 0;
            for (int j = 0; j < digits.length; j++) {
                if (j != i) {
                    digitsLeft[index++] = digits[j];
                }
            }

            var remainingPermutations = getPermutations(digitsLeft);

            for (var calculatedPermutation : remainingPermutations) {
                int[] permutation = new int[calculatedPermutation.length + 1];
                permutation[0] = digits[i];

                int pIndex = 1;
                for (int d : calculatedPermutation) {
                    permutation[pIndex++] = d;
                }

                outList.add(permutation);
            }
        }

        return outList;
    }
}
