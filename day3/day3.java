package day3;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class day3 {
    public static void main(String[] args) {

        // List<Character> firstSack;
        HashMap<Character,Integer> firstSack;

        // File fd = new File("day3/input_test.txt");
        File fd = new File("day3/input.txt");
        Scanner file = null;
        try {
            file = new Scanner(fd);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        String line = "";
        int sum = 0;
        while (file.hasNextLine()) {
            firstSack = new HashMap<>();
            line = file.nextLine();
            for (int i = 0; i <= line.length() / 2-1; i++) {
                firstSack.put(line.charAt(i),realValue(line.charAt(i)));
            }
            if(compareSacks(line, firstSack) != -1){
                sum += compareSacks(line, firstSack);
            }  else{System.out.println("no coincidences");}
        

        }
        // vJrwpWtwJgWr hcsFMMfFFhFp

        System.out.println("\n" + sum);
    }

    public static int compareSacks(String line, Map<Character,Integer> firstSack) {
        for (int i = line.length() / 2; i < line.length(); i++) {
            if(firstSack.containsKey(line.charAt(i))){
                return firstSack.get(line.charAt(i));
            }
        }
        return -1;
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
