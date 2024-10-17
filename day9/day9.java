package day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import librerias.Tuple;

public class day9 {

    public static void main(String[] args) {

        boolean[][] hasPassed = new boolean[1000][1000];
        Tuple<Integer, Integer> posT = new Tuple<>(500, 500);
        Tuple<Integer, Integer> posH = new Tuple<>(500, 500);

        File fd = new File("day9/input_test.txt");
        // File fd = new File("day9/input.txt");
        Scanner file = null;
        try {
            file = new Scanner(fd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int aux = 0;
        char charAux;
        while (file.hasNextLine()) {
            charAux = file.next().charAt(0);
            aux = file.nextInt();
            switch (charAux) {
                case 'R':
                    for (int i = 0; i < aux; i++) {
                        // move the first joint one position to the right
                        posH.setFirst(posH.getFirst() + 1);
                        // checks if the second joint is near the first and if not moves it to the position it should be
                        if (!isNear(posT, posH)) {
                            posT.setFirst(posH.getFirst() - 1);
                            posT.setSecond(posH.getSecond());
                        }
                        hasPassed[posT.getFirst()][posT.getSecond()] = true;
                    }
                    break;

                case 'L':
                    for (int i = 0; i < aux; i++) {
                        // move the first joint one position to the left
                        posH.setFirst(posH.getFirst() - 1);
                        // checks if the second joint is near the first and if not moves it to the position it should be
                        if (!isNear(posT, posH)) {
                            posT.setFirst(posH.getFirst() + 1);
                            posT.setSecond(posH.getSecond());
                        }
                        hasPassed[posT.getFirst()][posT.getSecond()] = true;
                    }
                    break;

                case 'D':
                    for (int i = 0; i < aux; i++) {
                        // move the first joint one position down
                        posH.setSecond(posH.getSecond() - 1);
                        // checks if the second joint is near the first and if not moves it to the position it should be
                        if (!isNear(posT, posH)) {
                            posT.setFirst(posH.getFirst());
                            posT.setSecond(posH.getSecond() + 1);
                        }
                        hasPassed[posT.getFirst()][posT.getSecond()] = true;
                    }
                    break;

                case 'U':
                    for (int i = 0; i < aux; i++) {
                        // move the first joint one position up
                        posH.setSecond(posH.getSecond() + 1);
                        // checks if the second joint is near the first and if not moves it to the position it should be
                        if (!isNear(posT, posH)) {
                            posT.setFirst(posH.getFirst());
                            posT.setSecond(posH.getSecond() - 1);
                        }
                        hasPassed[posT.getFirst()][posT.getSecond()] = true;
                    }
                    break;

            }
        }

        int count = 0;
        for (int i = 0; i < hasPassed.length; i++) {
            for (int j = 0; j < hasPassed[i].length; j++) {
                if (hasPassed[i][j])
                    count++;
            }
        }

        System.out.println(count);
    }

    /* Prints the given array */
    public static void printBoard(boolean[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /*
     * Returns true if one tuple is near the other.
     * It's considered as near if both points in a bidimensional map are in the
     * proximity of maximum 1 distance away of the other one.
     */
    public static boolean isNear(Tuple<Integer, Integer> posT, Tuple<Integer, Integer> posH) {
        for (int i = posH.getFirst() - 1; i <= posH.getFirst() + 1; i++) {
            for (int j = posH.getSecond() - 1; j <= posH.getSecond() + 1; j++) {
                if (posT.getFirst() == i && posT.getSecond() == j)
                    return true;
            }
        }
        return false;
    }
}
