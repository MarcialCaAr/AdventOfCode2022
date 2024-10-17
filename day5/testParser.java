package day5;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class testParser {

    @SuppressWarnings({"unchecked"})
    private static LinkedList<Character> stacks[] = new LinkedList[9];

    public static void main(String[] args) {

        for (int i = 0; i < stacks.length; i++) {
            stacks[i] = new LinkedList<Character>();
        }

        ArrayList<String> lines = new ArrayList<>();

        File fd = new File("day5/inputParser2.txt");
        // File fd = new File("day5/inputParser.txt");
        Scanner file = null;
        try {
            file = new Scanner(fd);
    
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        while(file.hasNextLine()){
            lines.add(file.nextLine());

            if(lines.get(lines.size()-1) == ""){
                lines.remove(lines.size()-1);
                lines.remove(lines.size()-1);
                break;
            }
        }


        for (int i = lines.size()-1; i >= 0; i--) {
            for(int k = 0, j = 1; j < lines.get(i).length(); k++, j+=4){
                if(Character.isLetter(lines.get(i).charAt(j))){
                    stacks[k].add(lines.get(i).charAt(j));
                }
            }
        }

        // for (LinkedList<?> stack : stacks) {
        //     System.out.println(stack);
        // }


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
