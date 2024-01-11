public class Lab24 {
    public static void main(String[] args) {
        int value = 0b1001110;
        value = 127;
        System.out.println(tobinary(value));
        System.out.println(toQuad(value));
        System.out.println(toOct(value));
        System.out.println(toHex(value));
    }

    public static String tobinary(int value) {
        int n = value;

        int mask = 0b1;
        String str = "";
        while (n >= 1) {
            int val = n & mask;
            str = val + str;
            n = n >> 1;
        }

        return str;
    }

    public static String toQuad(int value) {
        int n = value;


        int mask = 0b11;
        String str = "";
        while (n >= 1) {
            int val = n & mask;
            str = val + str;
            n = n >> 2;
        }

        return str;
    }

    public static String toOct(int value) {
        int n = value;


        int mask = 0b111;
        String str = "";
        while (n >= 1) {
            int val = n & mask;
            str = val + str;
            n = n >> 3;
        }

        return str;
    }

    private static String getHex(int n) {
        return switch (n) {
            case 10 -> "a";
            case 11 -> "b";
            case 12 -> "c";
            case 13 -> "d";
            case 14 -> "e";
            case 15 -> "f";
            default -> Integer.toString(n);
        };
    }

    public static String toHex(int value) {
        int n = value;

        int mask = 0b1111;
        String str = "";
        while (n >= 1) {
            int val = n & mask;
            str = getHex(val) + str;
            n = n >> 4;
        }

        return str;
    }
}
