package day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day12_2 {

    private static int finishPosX, finishPosY;
    private static Integer[][] moveCost;
    public static void main(String[] args) {

        int line = 0, column = 0;
        Character[][] board;


        // bad solutions: 482


        // File fd = new File("day12/input_test.txt");
        File fd = new File("day12/input.txt");
        Scanner file = null;
        try {
            file = new Scanner(fd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // set the array length
        while (file.hasNext()) {
            line++;
            column = file.nextLine().length();
        }
        board = new Character[line][column];
        moveCost = new Integer[line][column];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                moveCost[i][j] = Integer.MAX_VALUE;
            }
        }

        // restart the scanner to the start of the file
        try {
            file = new Scanner(fd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // introduce numbers in the array
        String aux = "";
        for(int i = 0; file.hasNext(); i++) {
            aux = file.nextLine();
            for (int j = 0; j < aux.length(); j++) {
                board[i][j] = aux.charAt(j);
            }
        }

        // iterate throuth all the array looking for starting and finishing points
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'S') {
                    board[i][j] = 'a';
                    
                }
                if(board[i][j] == 'E'){
                    finishPosX = i;
                    finishPosY = j;
                    board[i][j] = 'z';
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] == 'a'){
                    searchPath(board, i,j,0);
                }
            }
        }

        System.out.println(moveCost[finishPosX][finishPosY]);

    }

    
    public static void searchPath(Character[][] board,int posX, int posY, int moves){
        moveCost[posX][posY] = moves;
        
        if(posX == finishPosX && posY == finishPosY){
            // pathsCosts.add(moves);
            return;
        }
        // System.out.println(squares);
        // PrintingFormats.printBoardHorizontal(moveCost);
        // System.out.println();

        if(posX < board.length-1 && board[posX][posY] > board[posX+1][posY]-2){
            if (moves+1 < moveCost[posX+1][posY]) {
                searchPath(board, posX+1, posY, moves+1);
            }
        }
        if(posX > 0 && board[posX][posY] > board[posX-1][posY]-2){
            if (moves+1 < moveCost[posX-1][posY]) {
                searchPath(board, posX-1, posY, moves+1);
            }
        }
        if(posY < board[0].length-1 && board[posX][posY] > board[posX][posY+1]-2){
            if (moves+1 < moveCost[posX][posY+1]) {
                searchPath(board, posX, posY+1, moves+1);
            }
        }
        if(posY > 0 && board[posX][posY] > board[posX][posY-1]-2){
            if (moves+1 < moveCost[posX][posY-1]) {
                searchPath(board, posX, posY-1, moves+1);
            }
        }
    }
    
}
