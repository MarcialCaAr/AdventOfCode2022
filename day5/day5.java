package day5;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class day5 {

    @SuppressWarnings({"unchecked"})
    private static LinkedList<Character> stacks[] = new LinkedList[9];

    public static void main(String[] args) {

        for (int i = 0; i < stacks.length; i++) {
            stacks[i] = new LinkedList<Character>();
        }

        //     [D]    
        // [N] [C]    
        // [Z] [M] [P]
        //  1   2   3 

        // stacks[0].addAll(Arrays.asList('Z','N'));
        // stacks[1].addAll(Arrays.asList('M','C','D'));
        // stacks[2].addAll(Arrays.asList('P'));



        //                     [Q]     [P] [P]
        //                 [G] [V] [S] [Z] [F]
        //             [W] [V] [F] [Z] [W] [Q]
        //         [V] [T] [N] [J] [W] [B] [W]
        //     [Z] [L] [V] [B] [C] [R] [N] [M]
        // [C] [W] [R] [H] [H] [P] [T] [M] [B]
        // [Q] [Q] [M] [Z] [Z] [N] [G] [G] [J]
        // [B] [R] [B] [C] [D] [H] [D] [C] [N]
        // 1   2   3   4   5   6   7   8   9 

        stacks[0].addAll(Arrays.asList('B','Q','C'));
        stacks[1].addAll(Arrays.asList('R','Q','W','Z'));
        stacks[2].addAll(Arrays.asList('B','M','R','L','V'));
        stacks[3].addAll(Arrays.asList('C','Z','H','V','T','W'));
        stacks[4].addAll(Arrays.asList('D','Z','H','B','N','V','G'));
        stacks[5].addAll(Arrays.asList('H','N','P','C','J','F','V','Q'));
        stacks[6].addAll(Arrays.asList('D','G','T','R','W','Z','S'));
        stacks[7].addAll(Arrays.asList('C','G','M','N','B','W','Z','P'));
        stacks[8].addAll(Arrays.asList('N','J','B','M','W','Q','F','P'));

        // File fd = new File("day5/input_test.txt");
        File fd = new File("day5/input.txt");
        Scanner file = null;
        try {
            file = new Scanner(fd);

        } catch (Exception e) {
            System.out.println(e.toString());
        }


        
        int quantity, origin, destination;
        while(file.hasNextLine()){
            file.next();
            quantity = file.nextInt();
            file.next();
            origin = file.nextInt();
            file.next();
            destination = file.nextInt();
            moveCreate(quantity, origin-1, destination-1);
        }

        String result = "";
        for(List<?> stack : stacks){
            if(stack.size() > 0)
                result += stack.get(stack.size()-1);
        }


        System.out.println(result);

        
    }

    /* Given a quantity, a origin and a destination and habing a global array of lists 
     * Remove the last quantity of characters from origin and insert them in destination from last to first
     */
    private static void moveCreate(int quantity, int origin, int destination){
        for(int i = 0; i<quantity;i++){
            if(stacks[origin].size() == 0)
                return;
            stacks[destination].add(stacks[origin].remove(stacks[origin].size()-1));
        }
    }
    
}
