package day18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day18_2 {

    private static Integer[][][] board = new Integer[25][25][25];
    
    public static void main(String[] args) {

        long surface = 0L;
        List<Integer[]> cubes = new ArrayList<>();
        
        // File fd = new File("day18/input_test.txt");
        File fd = new File("day18/input.txt");
        Scanner file = null;
        Scanner line = null;
        try {
            file = new Scanner(fd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                for(int k = 0; k < board[i][j].length; k++){
                    board[i][j][k] = 0;
                }
            }
        }


        while(file.hasNextLine()){
            line = new Scanner(file.nextLine().replaceAll(",", " "));
            Integer[] cube = new Integer[3];

            cube[0] = line.nextInt()+1;
            cube[1] = line.nextInt()+1;
            cube[2] = line.nextInt()+1;

            if(board[cube[0]][cube[1]][cube[2]] == 1){
                continue; 
            }
            board[cube[0]][cube[1]][cube[2]] = 1;
            cubes.add(cube);
        }
        
        Integer[][][] board2 = new Integer[25][25][25];
        for(int i = 1; i < board.length-1; i++){
            for(int j = 1; j < board[i].length-1; j++){
                for(int k = 1; k < board[i][j].length-1; k++){
                    if(board[i][j][k] == 0){
                        board2 = copyBoard(board);
                        if(isCenter(board2, i, j, k)){
                            fillCenter(i, j, k);
                        }
                    }
                }
            }
        }
        

        for (Integer[] cube : cubes) {       
            int cubeX = cube[0], cubeY = cube[1], cubeZ = cube[2];

            if(board[cubeX+1][cubeY][cubeZ] == 0)
                surface++;
            if(board[cubeX-1][cubeY][cubeZ] == 0)
                surface++;
            if(board[cubeX][cubeY+1][cubeZ] == 0)
                surface++;
            if(board[cubeX][cubeY-1][cubeZ] == 0)
                surface++;
            if(board[cubeX][cubeY][cubeZ+1] == 0)
                surface++;
            if(board[cubeX][cubeY][cubeZ-1] == 0)
                surface++;
        }

        System.out.println(surface);
    }

    private static Integer[][][] copyBoard(Integer[][][] board){
        Integer[][][] board2 = new Integer[25][25][25];
        for(int i2 = 0; i2 < board2.length; i2++){
            for(int j2 = 0; j2 < board2[i2].length; j2++){
                for(int k2 = 0; k2 < board2[i2][j2].length; k2++){
                    board2[i2][j2][k2] = board[i2][j2][k2];
                }
            }
        }
        return board2;
    }

    private static boolean isCenter(Integer[][][] board2, int posX, int posY, int posZ){
        board2[posX][posY][posZ] = 1;

        if(posX == 1 || posY == 1 || posZ == 1)
            return false;
        
        if(posX == 23 || posY == 23 || posZ == 23)
            return false;

        if(board2[posX+1][posY][posZ] == 0)
            if(!isCenter(board2, posX+1, posY, posZ)){
                return false;
            }

        if(board2[posX-1][posY][posZ] == 0)
            if(!isCenter(board2, posX-1, posY, posZ)){
                return false;
            }

        if(board2[posX][posY+1][posZ] == 0)
            if(!isCenter(board2, posX, posY+1, posZ)){
                return false;
            }
        
        if(board2[posX][posY-1][posZ] == 0)
            if(!isCenter(board2, posX, posY-1, posZ)){
                return false;
            }

        if(board2[posX][posY][posZ+1] == 0)
            if(!isCenter(board2, posX, posY, posZ+1)){
                return false;
            }
        
        if(board2[posX][posY][posZ-1] == 0)
            if(!isCenter(board2, posX, posY, posZ-1)){
                return false;
            }

        return true;
    }

    private static Integer[][][] fillCenter(int posX, int posY, int posZ) {
        board[posX][posY][posZ] = 1;

        if(board[posX+1][posY][posZ] == 0)
            fillCenter(posX+1, posY, posZ);

        if(board[posX-1][posY][posZ] == 0)
            fillCenter(posX-1, posY, posZ);

        if(board[posX][posY+1][posZ] == 0)
            fillCenter(posX, posY+1, posZ);
        
        if(board[posX][posY-1][posZ] == 0)
            fillCenter(posX, posY-1, posZ);

        if(board[posX][posY][posZ+1] == 0)
            fillCenter(posX, posY, posZ+1);
        
        if(board[posX][posY][posZ-1] == 0)
            fillCenter(posX, posY, posZ-1);

        return board;
    }

}
