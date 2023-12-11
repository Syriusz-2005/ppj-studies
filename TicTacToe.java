
enum Player {
    Cross,
    Circle,
}

public class TicTacToe {
    public static int rowSize = 3;
    private byte[] board = new byte[9];

    private Player turn = Player.Cross;

    private static int getIndex(int x, int y) {
        return x + y * TicTacToe.rowSize;
    }

    private static int[] getCoords(int index) {
        int x = index % TicTacToe.rowSize;
        int y = (index / TicTacToe.rowSize) % TicTacToe.rowSize;
        int[] result = {x, y};
        return result;
    }

    private byte getItemAt(int x, int y) {
        return board[TicTacToe.getIndex(x, y)];
    }

    private void setItemAt(int x, int y, byte item) {
        board[TicTacToe.getIndex(x, y)] = item;
    }

    public byte getCurrentPlayerByte() {
        return (byte) (turn == Player.Cross ? 2 : 1);
    }

    private boolean isEmpty(byte field) {
        return field != 1 && field != 2;
    }

    public Player checkForWin() {
        for (int x = 0; x < rowSize; x++) {
            byte col1 = getItemAt(x, 0);
            byte col2 = getItemAt(x, 1);
            byte col3 = getItemAt(x, 2);
            if (col1 == col2 && col1 == col3 && !isEmpty(col1) && !isEmpty(col2) && !isEmpty(col3)) {
                return turn;
            }
        }

        for (int y = 0; y < rowSize; y++) {
            byte row1 = getItemAt(0, y);
            byte row2 = getItemAt(1, y);
            byte row3 = getItemAt(2, y);
            if (row1 == row2 && row1 == row3 && !isEmpty(row1) && !isEmpty(row2) && !isEmpty(row3)) {
                return turn;
            }
        }

        byte cross1 = getItemAt(0, 0);
        byte cross2 = getItemAt(1, 1);
        byte cross3 = getItemAt(2, 2);

        byte cross4 = getItemAt(0, 2);
        byte cross5 = getItemAt(2, 0);

        if (
                (cross1 == cross2 && cross1 == cross3 && !isEmpty(cross1) && !isEmpty(cross2) && !isEmpty(cross3))
                || (cross4 == cross2 && cross4 == cross5 && !isEmpty(cross4) && !isEmpty(cross2) && !isEmpty(cross5))
        ) {
            return turn;
        }

        return null;
    }

    public void displayBoard() {
        for (int x = 0; x < rowSize; x++) {
            for (int y = 0; y < rowSize; y++) {
                byte item = getItemAt(x, y);
                char itemChar = item == 2 ? 'x' : item == 1 ? 'o' : '-';
                System.out.print(itemChar + " ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    public void doTurn(int x, int y) {
        byte currField = getItemAt(x, y);
        if (currField == 1 || currField == 2) {
            System.out.println("This field is not empty");
            return;
        }
        setItemAt(x, y, getCurrentPlayerByte());
        displayBoard();

        Player winner = checkForWin();

        if (winner != null) {
            System.out.println(winner);
            System.out.println("You've won!");
        }

        if (turn == Player.Cross) {
            turn = Player.Circle;
        } else {
            turn = Player.Cross;
        }
    }
}
