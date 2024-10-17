package day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import librerias.Tuple;

public class day9_2 {

    public static void main(String[] args) {

        boolean[][] hasPassed = new boolean[1000][1000];
        @SuppressWarnings("unchecked")
        Tuple<Integer, Integer>[] snaps = new Tuple[10];
        snaps[0] = (new Tuple<>(500, 500));
        snaps[1] = (new Tuple<>(500, 500));
        snaps[2] = (new Tuple<>(500, 500));
        snaps[3] = (new Tuple<>(500, 500));
        snaps[4] = (new Tuple<>(500, 500));
        snaps[5] = (new Tuple<>(500, 500));
        snaps[6] = (new Tuple<>(500, 500));
        snaps[7] = (new Tuple<>(500, 500));
        snaps[8] = (new Tuple<>(500, 500));
        snaps[9] = (new Tuple<>(500, 500));

        // File fd = new File("day9/input_test2.txt");
        File fd = new File("day9/input.txt");
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
                        snaps[0].setFirst(snaps[0].getFirst() + 1);
                        for (int j = 0; j < snaps.length - 1; j++) {
                            // checks if one joint is near the next one, and if it then moves it to the offset it should take.
                            if (!isNear(snaps[j], snaps[j + 1])) {
                                snaps[j + 1].setFirst(
                                        snaps[j + 1].getFirst() + nextMove(snaps[j], snaps[j + 1]).getFirst());
                                snaps[j + 1].setSecond(
                                        snaps[j + 1].getSecond() + nextMove(snaps[j], snaps[j + 1]).getSecond());
                            }
                        }
                        // Set as true one position on the solution array
                        hasPassed[snaps[9].getFirst()][snaps[9].getSecond()] = true;
                    }
                    break;

                case 'L':
                    for (int i = 0; i < aux; i++) {
                        // move the first joint one position to the left
                        snaps[0].setFirst(snaps[0].getFirst() - 1);
                        for (int j = 0; j < snaps.length - 1; j++) {
                            // checks if one joint is near the next one, and if it then moves it to the offset it should take.
                            if (!isNear(snaps[j], snaps[j + 1])) {
                                snaps[j + 1].setFirst(
                                        snaps[j + 1].getFirst() + nextMove(snaps[j], snaps[j + 1]).getFirst());
                                snaps[j + 1].setSecond(
                                        snaps[j + 1].getSecond() + nextMove(snaps[j], snaps[j + 1]).getSecond());
                            }
                        }
                        // Set as true one position on the solution array
                        hasPassed[snaps[9].getFirst()][snaps[9].getSecond()] = true;
                    }
                    break;

                case 'D':
                    for (int i = 0; i < aux; i++) {
                        // move the first joint one position down
                        snaps[0].setSecond(snaps[0].getSecond() + 1);
                        for (int j = 0; j < snaps.length - 1; j++) {
                            // checks if one joint is near the next one, and if it then moves it to the offset it should take.
                            if (!isNear(snaps[j], snaps[j + 1])) {
                                snaps[j + 1].setFirst(
                                        snaps[j + 1].getFirst() + nextMove(snaps[j], snaps[j + 1]).getFirst());
                                snaps[j + 1].setSecond(
                                        snaps[j + 1].getSecond() + nextMove(snaps[j], snaps[j + 1]).getSecond());
                            }
                        }
                        // Set as true one position on the solution array
                        hasPassed[snaps[9].getFirst()][snaps[9].getSecond()] = true;
                    }
                    break;

                case 'U':
                    for (int i = 0; i < aux; i++) {
                        // move the first joint one position up
                        snaps[0].setSecond(snaps[0].getSecond() - 1);
                        for (int j = 0; j < snaps.length - 1; j++) {
                            // checks if one joint is near the next one, and if it then moves it to the offset it should take.
                            if (!isNear(snaps[j], snaps[j + 1])) {
                                snaps[j + 1].setFirst(
                                        snaps[j + 1].getFirst() + nextMove(snaps[j], snaps[j + 1]).getFirst());
                                snaps[j + 1].setSecond(
                                        snaps[j + 1].getSecond() + nextMove(snaps[j], snaps[j + 1]).getSecond());
                            }
                        }
                        // Set as true one position on the solution array
                        hasPassed[snaps[9].getFirst()][snaps[9].getSecond()] = true;
                    }
                    break;

            }
        }

        // iterates over the solutions array to check all the dots the last joint has touch,
        int count = 0;
        for (int i = 0; i < hasPassed.length; i++) {
            for (int j = 0; j < hasPassed[i].length; j++) {
                if (hasPassed[i][j])
                    count++;
            }
        }

        System.out.println(count);
    }

    /* Prints the given array in a formated way on the terminal */
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
    public static boolean isNear(Tuple<Integer, Integer> posHight, Tuple<Integer, Integer> posLow) {
        for (int i = posHight.getFirst() - 1; i <= posHight.getFirst() + 1; i++) {
            for (int j = posHight.getSecond() - 1; j <= posHight.getSecond() + 1; j++) {
                if (posLow.getFirst().equals(i) && posLow.getSecond().equals(j))
                    return true;
            }
        }
        return false;
    }

    /*
     * Returns a tuple indicating the offset the second tuple showd move to be near
     * the first one.
     */
    public static Tuple<Integer, Integer> nextMove(Tuple<Integer, Integer> posHight, Tuple<Integer, Integer> posLow) {
        int i, j;
        if (posHight.getFirst().equals(posLow.getFirst())) {
            i = 0;
        } else if (posHight.getFirst() > posLow.getFirst()) {
            i = 1;
        } else {
            i = -1;
        }

        if (posHight.getSecond().equals(posLow.getSecond())) {
            j = 0;
        } else if (posHight.getSecond() > posLow.getSecond()) {
            j = 1;
        } else {
            j = -1;
        }
        return new Tuple<Integer, Integer>(i, j);
    }
}
