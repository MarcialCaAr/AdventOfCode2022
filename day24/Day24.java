package day24;

import java.io.File;
import java.util.*;

import librerias.*;

public class Day24 {

    private static int maxCount = 0;

    private static int column, line;

    public static void main(String[] args) {
        Tuple<Integer, Integer> actualPos = new Tuple<Integer, Integer>(0, 1);
        List<Triple<Character, Integer, Integer>> board = new ArrayList<>();

        File fd = new File("day24/input_test2.txt");
        // File fd = new File("day24/input_test.txt");
        // File fd = new File("day24/input.txt");

        Scanner file = null;
        try {
            file = new Scanner(fd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        line = 0;
        while (file.hasNextLine()) {
            column = 0;
            for (char actualChar : file.nextLine().toCharArray()) {
                if (actualChar != '#' && actualChar != '.') {
                    board.add(new Triple<Character, Integer, Integer>(actualChar, line, column));
                }
                column++;
            }
            line++;
        }

        // movePos(copyBoard(board),0);
        // System.out.println(board);
        // printBoard(actualPos, board);

        System.out.println(movePos(actualPos, board,0));

        // System.out.println(board);
    }

    private static int movePos(Tuple<Integer, Integer> actualPos, List<Triple<Character, Integer, Integer>> board, int count) {
        if(actualPos.getFirst() == line-2 && actualPos.getSecond() == column-2){
            
            maxCount = Math.max(count, maxCount);
            return count;
        }

        // System.out.println();
        // printBoard(actualPos, board);

        moveBlizzards(board);
        // System.out.println();
        // printBoard(actualPos, board);

        while (true) {
            if (nextMove(actualPos, board) != null) {
                actualPos = nextMove(actualPos, board);
                movePos(actualPos, copyBoard(board),count+1);
            } else {
                // printBoard(actualPos, board);
                moveBlizzards(board);
                if (nextMove(actualPos, board) != null) {
                    actualPos = nextMove(actualPos, board);
                    movePos(actualPos, copyBoard(board),count+1);
                }
                if (board.contains(new Triple<Character, Integer, Integer>('.', actualPos.getFirst(), actualPos.getSecond()))) {
                    return -1;
                }
            }
        }
        // movePos(board, moves+1);
    }

    private static Tuple<Integer, Integer> nextMove(Tuple<Integer, Integer> actualPos,
            List<Triple<Character, Integer, Integer>> board) {

        if (actualPos.getFirst() != line - 2 && !board.contains(
                new Triple<Character, Integer, Integer>('.', actualPos.getFirst() + 1, actualPos.getSecond()))) {
            return new Tuple<Integer, Integer>(actualPos.getFirst() + 1, actualPos.getSecond());
        }

        if (actualPos.getSecond() != column - 2 && !board.contains(
                new Triple<Character, Integer, Integer>('.', actualPos.getFirst(), actualPos.getSecond() + 1))) {
            return new Tuple<Integer, Integer>(actualPos.getFirst(), actualPos.getSecond() + 1);
        }

        if (actualPos.getFirst() != 1 && !board.contains(
                new Triple<Character, Integer, Integer>('.', actualPos.getFirst() - 1, actualPos.getSecond()))) {
            return new Tuple<Integer, Integer>(actualPos.getFirst() - 1, actualPos.getSecond());
        }


        if (actualPos.getSecond() != 1 && !board.contains(
                new Triple<Character, Integer, Integer>('.', actualPos.getFirst(), actualPos.getSecond() - 1))) {
            return new Tuple<Integer, Integer>(actualPos.getFirst(), actualPos.getSecond() - 1);
        }

        return null;
    }

    private static List<Triple<Character, Integer, Integer>> copyBoard(
            List<Triple<Character, Integer, Integer>> board) {
        List<Triple<Character, Integer, Integer>> aux = new ArrayList<>();
        for (Triple<Character, Integer, Integer> triple : board) {
            aux.add(new Triple<>(triple.getFirst(), triple.getSecond(), triple.getThird()));
        }

        return aux;
    }

    private static void moveBlizzards(List<Triple<Character, Integer, Integer>> board) {
        for (Triple<Character, Integer, Integer> triple : board) {
            switch (triple.getFirst()) {
                case '>':
                    if (triple.getThird() == column - 2) {
                        triple.setThird(1);
                        break;
                    }
                    triple.setThird(triple.getThird() + 1);
                    break;

                case '<':
                    if (triple.getThird() == 1) {
                        triple.setThird(column - 2);
                        break;
                    }
                    triple.setThird(triple.getThird() - 1);
                    break;

                case '^':
                    if (triple.getSecond() == 1) {
                        triple.setSecond(line - 2);
                        break;
                    }
                    triple.setSecond(triple.getSecond() - 1);
                    break;

                case 'v':
                    if (triple.getSecond() == line - 2) {
                        triple.setSecond(1);
                        break;
                    }
                    triple.setSecond(triple.getSecond() + 1);
                    break;

            }
        }
    }

    @SuppressWarnings("unused")
    private static void printBoard(Tuple<Integer, Integer> actualPos, List<Triple<Character, Integer, Integer>> board) {
        Character[][] printBoard = new Character[line][column];
        for (int i = 0; i < printBoard.length; i++) {
            for (int j = 0; j < printBoard[i].length; j++) {
                printBoard[i][j] = '.';
            }
        }

        for (int i = 0; i < printBoard.length; i++) {
            printBoard[i][0] = '#';
            for (int j = 0; j < printBoard[i].length; j++) {
                printBoard[0][j] = '#';
                printBoard[printBoard.length - 1][j] = '#';
            }
            printBoard[i][column - 1] = '#';
        }

        printBoard[0][1] = ',';
        printBoard[line - 1][column - 2] = ',';
        printBoard[actualPos.getFirst()][actualPos.getSecond()] = 'E';

        for (Triple<Character, Integer, Integer> triple : board) {
            // if(printBoard[triple.getSecond()][triple.getThird()] != '.'){
            // printBoard[triple.getSecond()][triple.getThird()] =
            // (char)((Character.getNumericValue(printBoard[triple.getSecond()][triple.getThird()])
            // + 1) + '0');
            // continue;
            // }
            printBoard[triple.getSecond()][triple.getThird()] = '+';
        }

        PrintingFormats.printBoardHorizontal(printBoard);
    }
}
