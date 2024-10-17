package day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import librerias.Tuple;

public class day14_2 {
    // test case 24 moves
    //  start point 500,0
    
    private static Character[][] board = new Character[200][500];
    // private static Character[][] board = new Character[15][30];
    private static int maxY = 0;

    public static void main(String[] args) {


        // File fd = new File("day14/input_test.txt");
        File fd = new File("day14/input.txt");
        Scanner file = null;
        Scanner lineScanner = null;
        try {
            file = new Scanner(fd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                board[i][j] = '.';


        // first -490
        // seccond -0
        String line = "";
        int valueX1 = 0;
        int valueY1 = 0;
        int valueX2 = 0;
        int valueY2 = 0;
        while(file.hasNextLine()){
            line = file.nextLine().replaceAll("->", "").replaceAll(",", " ");
            lineScanner = new Scanner(line);
            valueX1 = lineScanner.nextInt()-300;
            valueY1 = lineScanner.nextInt();
            maxY = Math.max(maxY, valueY1);
            valueX2 = lineScanner.nextInt()-300;
            valueY2 = lineScanner.nextInt();
            maxY = Math.max(maxY, valueY2);


            for(int x = Math.min(valueX1, valueX2); x <= Math.max(valueX1, valueX2); x++ ){
                for(int  y= Math.min(valueY1, valueY2); y <= Math.max(valueY1, valueY2); y++ ){
                    board[y][x] = '#';
                } 
            }
            
            while(lineScanner.hasNext()){
                valueX1 = valueX2;
                valueX2 = lineScanner.nextInt()-300;
                valueY1 = valueY2;
                valueY2 = lineScanner.nextInt();
                maxY = Math.max(maxY, valueY2);
                for(int x = Math.min(valueX1, valueX2); x <= Math.max(valueX1, valueX2); x++ ){
                    for(int  y= Math.min(valueY1, valueY2); y <= Math.max(valueY1, valueY2); y++ ){
                        board[y][x] = '#';
                    } 
                }
            }
        }


        int sandDropped = 1;
        while(dropSand()){
            sandDropped++;
        }

        // PrintingFormats.printBoardHorizontal(board);
        System.out.println(sandDropped);
    }

    private static boolean dropSand(){
        int posX = 200;
        int posY = 0;
        Tuple<Integer,Integer> nextMove;
        
        nextMove = nextMove(posX, posY);

        if(posX == nextMove.getFirst() && posY == nextMove.getSecond())
            return false;

        posX = nextMove.getFirst();
        posY = nextMove.getSecond();
        board[posY][posX] = 'o';


        return true;
    }



    private static Tuple<Integer,Integer> nextMove(int posX, int posY){
        // board[posY][posX] = 'o';
        // PrintingFormats.printBoardHorizontal(board);
        // System.out.println();
        // board[posY][posX] = '.';

        if(posY == maxY+1)
            return new Tuple<Integer,Integer>(posX, posY);

        if(board[posY+1][posX] == '.')
            return nextMove(posX, posY+1);

        if(board[posY+1][posX-1] == '.')
            return nextMove(posX-1, posY+1);
        
        if(board[posY+1][posX+1] == '.')
            return nextMove(posX+1, posY+1);

        return new Tuple<Integer,Integer>(posX, posY);
    }
}
