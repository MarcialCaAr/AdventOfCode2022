package day3;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class day3_2 {
    public static void main(String[] args) {
        List<Character> firstSack;
        List<Character> secondtSack;
        List<Character> thirtSack;
        Set<Character> badge;


        // File fd = new File("day3/input_test.txt");
        File fd = new File("day3/input.txt");
        Scanner file = null;
        try {
            file = new Scanner(fd);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        int sum = 0;
        while (file.hasNextLine()) {
            firstSack = new ArrayList<>();
            secondtSack = new ArrayList<>();
            thirtSack = new ArrayList<>();
            badge = new HashSet<>();


            for(Character actual_char : file.nextLine().toCharArray()){
                firstSack.add(actual_char);
                badge.add(actual_char);
            }
            for(Character actual_char : file.nextLine().toCharArray()){
                secondtSack.add(actual_char);
                badge.add(actual_char);
            }
            for(Character actual_char : file.nextLine().toCharArray()){
                thirtSack.add(actual_char);
                badge.add(actual_char);
            }
            
            for(char element : badge){
                if(firstSack.contains(element) && secondtSack.contains(element) && thirtSack.contains(element)){
                    sum += realValue(element);
                }
            }
        }

        System.out.println("\n" + sum);
    }

    public static int realValue(char letter) {
        int letterValue = letter;
        if (letterValue > 64 && letterValue < 91)
            return letterValue - 38;
        if (letterValue > 96)
            return letterValue - 96;

        return -1;
    }
}
