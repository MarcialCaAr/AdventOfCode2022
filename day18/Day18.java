package day18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day18 {

    // 882 answer is too low
    
    public static void main(String[] args) {
        
        long surface = 0L;

        Integer[][][] board = new Integer[25][25][25];
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
                for(int k = 0; k < board[j].length; k++){
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
            // surface += 6;
            cubes.add(cube);
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


    // private static void
}



// for (Integer[] cube : cubes) {       
//     int cubeX = cube[0]+1, cubeY = cube[1]+1, cubeZ = cube[2]+1;
//     if(board[cubeX][cubeY][cubeZ] == 1){
//         continue; 
//     }
//     surface += 6;
//     if(board[cubeX+1][cubeY][cubeZ] == 1)
//         surface -= 2;
//     if(board[cubeX-1][cubeY][cubeZ] == 1)
//         surface -= 2;
//     if(board[cubeX][cubeY+1][cubeZ] == 1)
//         surface -= 2;
//     if(board[cubeX][cubeY-1][cubeZ] == 1)
//         surface -= 2;
//     if(board[cubeX][cubeY][cubeZ+1] == 1)
//         surface -= 2;
//     if(board[cubeX][cubeY][cubeZ-1] == 1)
//         surface -= 2;


//     board[cubeX][cubeY][cubeZ] = 1;
// }
