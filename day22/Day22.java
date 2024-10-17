package day22;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import librerias.Tuple;

public class Day22 {

    private static char[][] board;

    public static void main(String[] args) {

        Tuple<Integer, Integer> actualPosition;
        int direction = 0;
        List<Tuple<Integer, Character>> movemenList = new ArrayList<>();
        
        // File fd = new File("day22/input_test.txt");
        // 6032
        File fd = new File("day22/input.txt");
        // 136054
        Scanner file = null;
        try {
            file = new Scanner(fd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int lines = 0;
        while(file.nextLine() != ""){
            lines++;
        }
        board = new char[lines][0];

        try {
            file = new Scanner(fd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i = 0; i < lines; i++){
            board[i] = file.nextLine().toCharArray();
        }

        file.nextLine();
        String number = "";
        char[] elements = file.nextLine().toCharArray();
        for(int i = 0; i < elements.length; i++){
            while(i < elements.length && Character.isDigit(elements[i])){
                number += elements[i];
                i++;
            }
            if(i >= elements.length){
                movemenList.add(new Tuple<Integer,Character>(Integer.parseInt(number), null));
                break;
            }
            movemenList.add(new Tuple<Integer,Character>(Integer.parseInt(number), elements[i]));
            number = "";
        }
        actualPosition = getStartingPoint();


        for (Tuple<Integer, Character> movement : movemenList) {
            actualPosition = movePositionTo(actualPosition, direction, movement.getFirst());
            if(movement.getSecond() != null)
            direction = changeDirection(direction, movement.getSecond());
        }


        int solution = 1000 * (actualPosition.getFirst()+1) + 4 * (actualPosition.getSecond()+1) + direction;
        System.out.println(solution);
    }


    private static int changeDirection(int actualDirection, Character rotation) {
        switch(rotation){
            case 'R':
                return (actualDirection+1) % 4;
            case 'L':
                if(actualDirection == 0){
                    return 3;
                }
                return actualDirection-1;
        }

        return -1;
    }


    private static Tuple<Integer, Integer> movePositionTo(Tuple<Integer, Integer> actualPosition, int direction, int moves){


        int line = actualPosition.getFirst();
        int column = actualPosition.getSecond();

        switch(direction){
            case 0: // right move
                for(int i = 0; i < moves; i++){
                    if(column+2 > board[line].length){
                        if(board[line][getFirstOnLine(line)] != '#'){
                            column = getFirstOnLine(line);
                            continue;
                        } else{
                            return new Tuple<Integer,Integer>(line, column);
                        }
                    }
                    if(board[line][column+1] != '#'){
                        column++;
                    } else{
                        return new Tuple<Integer,Integer>(line, column);
                    }
                }
                break;


            case 2: // left move
                for(int i = 0; i < moves; i++){
                    if(column-1 < 0 || board[line][column-1] == ' '){
                        if(board[line][getLastOnLine(line)] != '#'){
                            column = getLastOnLine(line);
                            continue;
                        } else{
                            return new Tuple<Integer,Integer>(line, column);
                        }
                    }
                    if(board[line][column-1] != '#'){
                        column--;
                    } else{
                        return new Tuple<Integer,Integer>(line, column);
                    }
                }
                break;


            case 1: // down move
                for(int i = 0; i < moves; i++){
                    if(line+2 > board.length || board[line+1].length <= column){
                        if(board[getFirstOnColumn(column)][column] != '#'){
                            line = getFirstOnColumn(column);
                            continue;
                        } else{
                            return new Tuple<Integer,Integer>(line, column);
                        }
                    }
                    if(board[line+1][column] != '#'){
                        line++;
                    } else{
                        return new Tuple<Integer,Integer>(line, column);
                    }
                }
                break;

            case 3: // up move
                for(int i = 0; i < moves; i++){
                    if(line-1 < 0 || board[line-1][column] == ' '){
                        if(board[getLastOnColumn(column)][column] != '#'){
                            line = getLastOnColumn(column);
                            continue;
                        } else{
                            return new Tuple<Integer,Integer>(line, column);
                        }
                    }
                    if(board[line-1][column] != '#'){
                        line--;
                    } else{
                        return new Tuple<Integer,Integer>(line, column);
                    }
                }
                break;
        }

        return new Tuple<Integer,Integer>(line, column);
    }


    private static int getFirstOnColumn(int column){
        for(int i = 0; i < board.length; i++){
            if(column > board[i].length-1){
                continue;
            }
            if(board[i][column] != ' '){
                return i;
            }
        }

        return -1;
    }

    private static int getLastOnColumn(int column){
        for(int i = board.length-1; i >= 0; i--){
            if(column > board[i].length-1){
                continue;
            }
            if(board[i][column] != ' '){
                return i;
            }
        }

        return -1;
    }


    private static int getFirstOnLine(int line){
        for(int i = 0; i < board[line].length; i++){
            if(board[line][i] != ' '){
                return i;
            }
        }

        return -1;
    }

    private static int getLastOnLine(int line){
        return board[line].length-1;
    }


    private static Tuple<Integer, Integer> getStartingPoint(){
        for(int i = 0; i < board.length; i++){
            if(board[0][i] == '.'){
                return new Tuple<Integer,Integer>(0, i);
            }
        }

        return null;
    }

    @SuppressWarnings("unused")
    private static void printBoardHorizontal(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    
}
