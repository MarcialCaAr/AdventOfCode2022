package day17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import librerias.Figures.Figures;

public class Day17 {
    private static final int BOARD_LENGHT = 7;

    public static void main(String[] args) {
        int altura = 0;
        int rocksToFall = 2022;
        List<Character[]> board = new ArrayList<>();
        Character[] line;
        
        // File fd = new File("day17/input_test.txt");
        File fd = new File("day17/input.txt");
        Scanner file = null;
        try {
            file = new Scanner(fd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        

        char[] input = file.nextLine().toCharArray();



        // board.get(0)[2] = 'a';
        printBoard(board);

        int rockType = 0;
        int move = 0;
        Figures figure;
        for(int i = 0; i < rocksToFall; i++){
            for(int k = board.size(); k < altura+7; k++){
                line = new Character[7];
                for(int j = 0; j < BOARD_LENGHT; j++)
                line[j] = '.';
                board.add(line);
            }
            
            figure = new Figures(altura+3, rockType);

            while(figure.isCanMove()){
                if(input[move] == '<')
                    board = figure.moveLeft(board);
                else
                    board = figure.moveRight(board);
                
                board = figure.moveDown(board);
                move = (move+1)%input.length;
                // printBoard(board);
                // System.out.println();
                // System.out.println(rockType);
            }

            altura = Math.max(altura, figure.getUpSide()+1);

            rockType = (rockType+1)%5;
        }


        System.out.println(altura);

    }
    

    private static void printBoard(List<Character[]> board){
        for(int i = board.size()-1; i >= 0; i--){
            for(int j = 0; j < board.get(i).length;j++){
                System.out.print(board.get(i)[j] + " ");
            }
            System.out.println();
        }
    }
}
