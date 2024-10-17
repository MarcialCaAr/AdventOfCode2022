package day17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import librerias.Figures.Figures_opt;

public class Day17_2_opt {
    // private static final long ROCKS_TO_FALL= 1000000000000L;
    private static final long ROCKS_TO_FALL= 2022L;


    public static void main(String[] args) {
        long altura = 0L;
        Long[] alturas = {-1L,-1L,-1L,-1L,-1L,-1L,-1L};

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
        Figures_opt figure;
        for(Long i = 0L; i < ROCKS_TO_FALL; i++){
            if(i % 1000000 == 0){
                System.out.println(ROCKS_TO_FALL-i);
            }
            
            figure = new Figures_opt(altura+3, rockType);

            while(figure.isCanMove()){
                if(input[move] == '<')
                    figure.moveLeft(alturas);
                else
                    figure.moveRight(alturas);
                
                figure.moveDown(alturas);
                move = (move+1)%input.length;
                // printBoard(board);
                // System.out.println();
                // System.out.println(rockType);
            }

            alturas = figure.getAlturas(alturas);

            altura = Math.max(altura, figure.getUpSide()+1);

            rockType = (rockType+1)%5;
        }

        System.out.println( Arrays.toString(alturas));
        System.out.println(altura);

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
