package day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day10_2 {

    
    public static void main(String[] args) {
        char[][] screen = new char[6][40];

        // File fd = new File("day10/input_test.txt");
        File fd = new File("day10/input.txt");
        Scanner file = null;
        try {
            file = new Scanner(fd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int cicle = 0;
        int x = 1;
        int line = 0;
        while (file.hasNextLine()) {
            line = cicle / 40;

            if(cicle >= 240)
                break;

            if(isPrinteable(x,cicle%40)){
                screen[line][cicle % 40] = '#';
            } else {
                screen[line][cicle % 40] = '.';
            }
            cicle++;

            if (file.next().equals("addx")) {
                if(isPrinteable(x,cicle%40)){
                    screen[line][cicle % 40] = '#';
                } else {
                    screen[line][cicle % 40] = '.';
                }
                cicle++;
                
                x += file.nextInt();
            }
        }

        // System.out.println(signalStrength);
        printBoard(screen);
    }

    public static boolean isPrinteable(int sprite, int cicle){
        for(int i = sprite -1; i< sprite+2;i++){
            if(cicle == i)
                return true;
        }
        return false;
    }

    public static void printBoard(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}
