package Kolos;

class NotValidException extends RuntimeException {
    public NotValidException(String msg) {
        super(msg);
    }
}

class Computer {
    protected int[] numbers = {};

    public void addDigit(int digit) {
        if (digit < 0 || digit > 9) throw new NotValidException("The integer " + digit + " is out of bounds!");

        var newNumbers = new int[numbers.length + 1];
        for (int i = 0; i < numbers.length; i++) {
            newNumbers[i] = numbers[i];
        }
        newNumbers[numbers.length] = digit;
        numbers = newNumbers;
    }

    public int[] compute() {
        // not optimal, but works
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length - 1; j++) {
                int next = numbers[j + 1];
                if (numbers[j] < next) {
                    numbers[j + 1] = numbers[j];
                    numbers[j] = next;
                }
            }
        }
        return numbers;
    }
}

class SuperComputer extends Computer {
    @Override
    public int[] compute() {
        int[] sortedNumbers = super.compute();

        // this basically implements an ArrayList. So why not use It like this?
        var originalNumbers = new Computer();

        for (int i = 0; i < sortedNumbers.length; i++) {
            if (i > 0) {
                boolean isOriginal = sortedNumbers[i] != sortedNumbers[i - 1];
                if (isOriginal) {
                    originalNumbers.addDigit(sortedNumbers[i]);
                }
            } else {
                originalNumbers.addDigit(sortedNumbers[i]);
            }
        }

        return originalNumbers.numbers;
    }
}

public class Kolos {
    private static int randomInt(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    public static void main(String[] args) {
        Computer[] arr = {
                new Computer(),
                new SuperComputer(),
        };

        for (var computer : arr) {
            for (int i = 0; i < 10; i++) {
                try {
                    computer.addDigit(randomInt(-10, 10));
                } catch (NotValidException err) {
                    i--;
                }
            }
        }

        for (var computer : arr) {
            int[] result = computer.compute();
            for (int digit : result) {
                System.out.print(digit + " ");
            }
            System.out.println();
        }
    }
}
