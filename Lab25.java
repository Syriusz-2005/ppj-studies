import java.io.*;
import java.util.Scanner;

class MyScaner {
    InputStream in;

    public MyScaner(InputStream in) {
        this.in = in;
    }

    private boolean matches(char c, char[] values) {
        for (char option : values) {
            if (c == option) {
                return true;
            }
        }

        return false;
    }

    private String next(char[] separators) {
        String str = "";
        while (true) {
            try {
                int byteVal = in.read();
                char c = (char) byteVal;
                if (matches(c, separators)) {
                    return str;
                }
                str += c;
            } catch (IOException err) {
                System.out.println(err);
            }
        }
    }

    public String nextWord() {
        char[] separators = {' ', '\n'};
        return next(separators);
    }

    public String nextLine() {
        char[] separators = {'\n'};
        return next(separators);
    }

    public int nextInt() {
        //I'm not sure if it's the best solution, but it works!
        String word = nextWord();
        return Integer.parseInt(word);
    }

    public int nextPositiveInt() throws Exception {
        int val = nextInt();
        if (val <= 0) {
            throw new Exception("The value is not positive");
        }

        return val;
    }
}

public class Lab25 {
    private static void task1() {
        InputStream in = System.in;
        try {
            int val = in.read();
            System.out.println(Integer.toBinaryString(val));
        } catch (IOException err) {
            System.out.println(err);
        }
    }

    private static void task2() {
        var scanner = new MyScaner(System.in);

        try {
            int number = scanner.nextPositiveInt();
            System.out.println(number);
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    private static void task3() {
        try {
            FileReader file = new FileReader("./content.txt");
            FileOutputStream file2 = new FileOutputStream("./content2.txt");

            int totalValue = 0;
            int offset = 0;

            while (true) {
                int c = file.read();
                if (c == -1) {
                    break;
                }
                int val = (char) c == '0' ? 0 : 1;
                totalValue = totalValue << 1 | val;
                offset++;
            }
            System.out.println(Integer.toBinaryString(totalValue));
            for (int i = 0; i < offset; i+= 8) {
                file2.write(totalValue >> offset);
            }
            file2.close();
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public static void main(String[] args) {
        task3();
    }
}
