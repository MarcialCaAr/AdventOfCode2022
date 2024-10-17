package day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import librerias.Tuple;

public class day14 {
    // test case 24 moves
    //  start point 500,0
    
    private static Character[][] board = new Character[200][100];
    // private static Character[][] board = new Character[15][15];

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
            valueX1 = lineScanner.nextInt() - 490;
            valueY1 = lineScanner.nextInt();
            valueX2 = lineScanner.nextInt() - 490;
            valueY2 = lineScanner.nextInt();

            for(int x = Math.min(valueX1, valueX2); x <= Math.max(valueX1, valueX2); x++ ){
                for(int  y= Math.min(valueY1, valueY2); y <= Math.max(valueY1, valueY2); y++ ){
                    board[y][x] = '#';
                } 
            }
            
            while(lineScanner.hasNext()){
                valueX1 = valueX2;
                valueX2 = lineScanner.nextInt() - 490;
                valueY1 = valueY2;
                valueY2 = lineScanner.nextInt();
                for(int x = Math.min(valueX1, valueX2); x <= Math.max(valueX1, valueX2); x++ ){
                    for(int  y= Math.min(valueY1, valueY2); y <= Math.max(valueY1, valueY2); y++ ){
                        board[y][x] = '#';
                    } 
                }
            }
        }


        int sandDropped = 0;
        while(dropSand()){
            sandDropped++;
        }

        System.out.println(sandDropped);
    }

    private static boolean dropSand(){
        int posX = 10;
        int posY = 0;
        Tuple<Integer,Integer> nextMove;
        
        nextMove = nextMove(posX, posY);
        if(nextMove == null)
            return false;

        if(posX == nextMove.getFirst() && posY == nextMove.getSecond())
            return true;

        posX = nextMove.getFirst();
        posY = nextMove.getSecond();
        board[posY][posX] = 'o';



        return true;
    }



    private static Tuple<Integer,Integer> nextMove(int posX, int posY){
        if(posY == board.length-1)
            return null;

        if(board[posY+1][posX] == '.')
            return nextMove(posX, posY+1);

        if(board[posY+1][posX-1] == '.')
            return nextMove(posX-1, posY+1);
        
        if(board[posY+1][posX+1] == '.')
            return nextMove(posX+1, posY+1);

        return new Tuple<Integer,Integer>(posX, posY);
    }
}
