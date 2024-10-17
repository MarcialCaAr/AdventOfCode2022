package librerias;

public class PrintingFormats {
    /* Prints the given array */
    public static void printBoardHorizontal(Object[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "");
            }
            System.out.println();
        }
    }


    /* Prints the given array */
    public static void printBoardVertical(Object[][] board) {
        for (int j = 0; j < board[0].length; j++) {
            for (int i = 0; i < board.length; i++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
