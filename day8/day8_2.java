package day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day8_2 {

    public static void main(String[] args) {

        int line = 0, column = 0;
        int[][] board;
        
        // File fd = new File("day8/input_test.txt");
        File fd = new File("day8/input.txt");
        Scanner file = null; 
        try {
            file = new Scanner(fd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // set the array length
        while (file.hasNext()) {
            column = file.nextLine().length();
            line++;
        }
        board = new int[line][column];

        // restart the scanner to the start of the file
        try {
            file = new Scanner(fd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // introduce numbers in the array
        String aux = "";
        int i = 0;
        while (file.hasNext()) {
            aux = file.nextLine();
            for (int j = 0; j < aux.length(); j++) {
                board[i][j] = Character.getNumericValue(aux.charAt(j));
            }
            i++;
        }

        // iterate through the array looking for the highs and keeping the one with highest visibility
        int maxVisibility = 0;
        for (i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (isHight(board, i, j)) {
                    maxVisibility = Math.max(maxVisibility, viewDistance(board, i, j));
                }
            }
        }
        

        System.out.println(maxVisibility);
    }

    /*
     * Iterate through and array from a given start possition returning true if its
     * a border or a corner and true if from the given point to any border all the
     * numbers are lower than the given number.
     * Returns false otherwise
     */
    public static int viewDistance(int[][] board, int posI, int posJ){
        int count = 0;
        int res = 1;
        // count view distance down
        for(int i = posI+1; i < board.length; i++){
            if(board[posI][posJ] > board[i][posJ])
                count++;
            else {
                count++;
                break;
            }
        }
        res *= count;
        count = 0;
        
        // count view distance up
        for(int i = posI-1; i >=0; i--){
            if(board[posI][posJ] > board[i][posJ])
                count++;
            else {
                count++;
                break;
            }
        }
        res *= count;
        count = 0;

        // count view distance right
        for(int i = posJ+1; i < board[0].length; i++){
            if(board[posI][posJ] > board[posI][i])
                count++;
            else {
                count++;
                break;
            }
        }
        res *= count;
        count = 0;

        // count view distance left
        for(int i = posJ-1; i >=0; i--){
            if(board[posI][posJ] > board[posI][i])
                count++;
            else {
                count++;
                break;
            }
        }
        res *= count;

        return res;
    }
    

    public static boolean isHight(int[][] board, int posI, int posJ) {
        // top left corner
        if (posI == 0 && posJ == 0)
                return true;
        // top right corner
        if (posI == 0 && posJ == board[0].length - 1)
                return true;
        // bottom right corner
        if (posI == board.length - 1 && posJ == board[0].length - 1)
                return true;
        // bottom left corner
        if (posI == board.length - 1 && posJ == 0)
                return true;

        // first line
        if (posI == 0)
                return true;
        // last line
        if (posI == board.length - 1)
                return true;
        // first column
        if (posJ == 0)
                return true;
        // last column
        if (posJ == board[0].length - 1)
                return true;

        // check visibility down
        boolean isLow = true;
        for(int i = posI+1; i < board.length; i++){
            if(board[posI][posJ] <= board[i][posJ])
            isLow = false;
        }
        if(isLow) return true; else isLow = true;
        
        // check visibility up
        for(int i = posI-1; i >=0; i--){
            if(board[posI][posJ] <= board[i][posJ])
            isLow = false;
        }
        if(isLow) return true; else isLow = true;

        // check visibility right
        for(int i = posJ+1; i < board[0].length; i++){
            if(board[posI][posJ] <= board[posI][i])
            isLow = false;
        }
        if(isLow) return true; else isLow = true;

        // check visibility left
        for(int i = posJ-1; i >=0; i--){
            if(board[posI][posJ] <= board[posI][i])
            isLow = false;
        }
        if(isLow) return true; else isLow = true;

        return false;
    }
}
