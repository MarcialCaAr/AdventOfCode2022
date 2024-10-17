package day6;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class day6_2 {
    public static void main(String[] args) {
        // File fd = new File("day6/input_test.txt");
        File fd = new File("day6/input.txt");
        Scanner file = null;
        try {
            file = new Scanner(fd);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        
        System.out.println(getMarker(file.nextLine()));
        // System.out.println(getMarker(file.nextLine()));
        // System.out.println(getMarker(file.nextLine()));
        // System.out.println(getMarker(file.nextLine()));
        // System.out.println(getMarker(file.nextLine()));
        
    }

    public static int getMarker(String line){
        Set<Character> nonRepeatedCharacters;
        for(int i = 13;i<line.length()-1;i++){
            nonRepeatedCharacters = new HashSet<>();
            for(int j = 13; j>=0;j--)
                nonRepeatedCharacters.add(line.charAt(i-j));
            if(nonRepeatedCharacters.size() == 14){
                return i+1;
            }
        }
        return -1;
    }
    
}
