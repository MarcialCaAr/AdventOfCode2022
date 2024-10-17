package day22;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import librerias.Tuple;

public class Day22_2 {

    private static char[][] board;
    private static int direction = 0;

    public static void main(String[] args) {

        Tuple<Integer, Integer> actualPosition;
        List<Tuple<Integer, Character>> movemenList = new ArrayList<>();
        
        // File fd = new File("day22/input_test.txt");
        // 6032
        File fd = new File("day22/input.txt");
        // 133159 too hight
        // 132334 too hight

        // 120364 too low
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


        
        System.gc();

        // System.out.println("profundidad: " + board.length);
        
        for (Tuple<Integer, Character> movement : movemenList) {
            actualPosition = movePositionTo(actualPosition, movement.getFirst());
            if(movement.getSecond() != null)
            direction = changeDirection(movement.getSecond());
        }
        
        // printBoardHorizontal(board);
        // System.out.println(movemenList);

        // System.out.println(direction);
        // System.out.println(changeDirection(direction, 'R'));


        // System.out.println(actualPosition);
        int solution = 1000 * (actualPosition.getFirst()+1) + 4 * (actualPosition.getSecond()+1) + direction;
        System.out.println(solution);

        // System.out.println(movePositionTo(new Tuple<Integer,Integer>(1, 10), 0, 5));

        // System.out.println(getStartingPoint(board));
        // System.out.println(getFirstOnLine(board, 4));
        // System.out.println(getFirstOnColumn(board, 11));
        // System.out.println(getLastOnLine(board, 8));
        // System.out.println(getLastOnColumn(board, 12));
    }


    private static int changeDirection(Character rotation) {
        switch(rotation){
            case 'R':
                return (direction+1) % 4;
            case 'L':
                if(direction == 0){
                    return 3;
                }
                return direction-1;
        }

        return -1;
    }


    private static Tuple<Integer, Integer> movePositionTo(Tuple<Integer, Integer> actualPosition, int moves){


        int line = actualPosition.getFirst();
        int column = actualPosition.getSecond();

        switch(direction){
            case 0: // right move
                for(int i = moves; i > 0; i--){
                    if(column+2 > board[line].length){
                        if(line >= 0 && line < 50){
                            if(board[149-line][99] != '#'){
                                direction = 2;
                                return movePositionTo(new Tuple<Integer,Integer>(149-line, 99), i-1);
                            } else{
                                return new Tuple<Integer,Integer>(line, column);
                            }
                        }
                        if(line >= 50 && line < 100){
                            if(board[49][line+50] != '#'){
                                direction = 3;
                                return movePositionTo(new Tuple<Integer,Integer>(49, line+50), i-1);
                            } else{
                                return new Tuple<Integer,Integer>(line, column);
                            }
                        }
                        if(line >= 100 && line < 150){
                            if(board[Math.abs(line-150)][149] != '#'){
                                direction = 2;
                                return movePositionTo(new Tuple<Integer,Integer>(Math.abs(line-150), 149), i-1);
                            } else{
                                return new Tuple<Integer,Integer>(line, column);
                            }
                        }
                        if(line >= 150 && line < 200){
                            if(board[149][line - 100] != '#'){
                                direction = 3;
                                return movePositionTo(new Tuple<Integer,Integer>(149, line-100), i-1);
                            } else{
                                return new Tuple<Integer,Integer>(line, column);
                            }
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
                for(int i = moves; i > 0; i--){
                    if(column-1 < 0 || board[line][column-1] == ' '){
                        if(line >= 0 && line < 50){
                            if(board[149-line][0] != '#'){
                                direction = 0;
                                return movePositionTo(new Tuple<Integer,Integer>(line + 100, 0), i-1);
                            } else{
                                return new Tuple<Integer,Integer>(line, column);
                            }
                        }
                        if(line >= 50 && line < 100){
                            if(board[100][line-50] != '#'){
                                direction = 1;
                                return movePositionTo(new Tuple<Integer,Integer>(100, line-50), i-1);
                            } else{
                                return new Tuple<Integer,Integer>(line, column);
                            }
                        }
                        if(line >= 100 && line < 150){
                            if(board[Math.abs(line-150)][50] != '#'){
                                direction = 0;
                                return movePositionTo(new Tuple<Integer,Integer>(Math.abs(line-150), 50), i-1);
                            } else{
                                return new Tuple<Integer,Integer>(line, column);
                            }
                        }
                        if(line >= 150 && line < 200){
                            if(board[0][line-100] != '#'){
                                direction = 1;
                                return movePositionTo(new Tuple<Integer,Integer>(0, line-100), i-1);
                            } else{
                                return new Tuple<Integer,Integer>(line, column);
                            }
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
                for(int i = moves; i > 0; i--){
                    if(line+2 > board.length || board[line+1].length <= column){
                        if(column >= 0 && column < 50){
                            if(board[0][column+100] != '#'){
                                direction = 1;
                                return movePositionTo(new Tuple<Integer,Integer>(0, column+100), i-1);
                            } else{
                                return new Tuple<Integer,Integer>(line, column);
                            }
                        }
                        if(column >= 50 && column < 100){
                            if(board[column+100][49] != '#'){
                                direction = 2;
                                return movePositionTo(new Tuple<Integer,Integer>(column+100, 49), i-1);
                            } else{
                                return new Tuple<Integer,Integer>(line, column);
                            }
                        }
                        if(column >= 100 && column < 150){
                            if(board[column-50][99] != '#'){
                                direction = 2;
                                return movePositionTo(new Tuple<Integer,Integer>(column-50, 99), i-1);
                            } else{
                                return new Tuple<Integer,Integer>(line, column);
                            }
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
                for(int i = moves; i > 0; i--){
                    if(line-1 < 0 || board[line-1][column] == ' '){
                        if(column >= 0 && column < 50){
                            if(board[column+50][50] != '#'){
                                direction = 0;
                                return movePositionTo(new Tuple<Integer,Integer>(column+50, 50), i-1);
                            } else{
                                return new Tuple<Integer,Integer>(line, column);
                            }
                        }
                        if(column >= 50 && column < 100){
                            if(board[column+100][0] != '#'){
                                direction = 0;
                                return movePositionTo(new Tuple<Integer,Integer>(column+100, 0), i-1);
                            } else{
                                return new Tuple<Integer,Integer>(line, column);
                            }
                        }
                        if(column >= 100 && column < 150){
                            if(board[199][column-100] != '#'){
                                direction = 3;
                                return movePositionTo(new Tuple<Integer,Integer>(199, column-100), i);
                            } else{
                                return new Tuple<Integer,Integer>(line, column);
                            }
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
