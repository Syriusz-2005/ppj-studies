public class Word {
    // This class is useless and doesn't make any sense
    private char[] chars;
    private int i;

    Word() {
        chars = new char[100];
        i = 0;
    }

    public void addChar(char c) {
        chars[i] = c;
        i++;
    }

    public void show() {
        for (char c : chars) {
            System.out.print(c);
        }
    }

    public int length() {
        return i;
    }
}
