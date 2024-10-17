package day17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import librerias.Figures.Figures;

public class Day17_2 {
    private static final int BOARD_LENGHT = 7;
    private static final long ROCKS_TO_FALL= 1000000000000L;
    // private static final long ROCKS_TO_FALL= 2022L;


    public static void main(String[] args) {
        int alturaReal = 0;
        int altura = 0;
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

        int rockType = 0;
        int move = 0;
        Figures figure;
        int difference = 0;
        for(Long i = 0L; i < 4995; i++){
            if(i % 1000000 == 0){
                System.out.println(ROCKS_TO_FALL-  i + "  " + board.size());
            }

            
            
            
            if(board.size() > 100){
                while(board.size() > 50){
                    board.remove(0);
                    altura--;
                    difference++;
                }
            }
            
            
            for(int k = board.size(); k < altura+7; k++){
                line = new Character[7];
                for(int j = 0; j < BOARD_LENGHT; j++)
                line[j] = '.';
                board.add(line);
            }
            
            figure = new Figures(altura+3, rockType);
            
            while(figure.isCanMove()){
                if(move == 0){
                    printBoard(board);
                    System.out.println(alturaReal + " " + i);
                    System.out.println("\n");
                }
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
            alturaReal = altura + difference;

            rockType = (rockType+1)%5;
        }


        System.out.println(alturaReal-5231);

    }
    

    public static void printBoard(List<Character[]> board){
        for(int i = board.size()-1; i >= 0; i--){
            for(int j = 0; j < board.get(i).length;j++){
                System.out.print(board.get(i)[j] + " ");
            }
            System.out.println();
        }
    }
}
