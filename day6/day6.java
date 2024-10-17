package day6;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class day6 {
    public static void main(String[] args) {
        // File fd = new File("day6/input_test.txt");
        File fd = new File("day6/input.txt");
        Scanner file = null;
        try {
            file = new Scanner(fd);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        // INPUT TEST
        // for(int i = 0; i<5;i++){
        // System.out.println(getMarker2(file.nextLine()));
        // }

        String line = file.nextLine();
        System.out.println(getMarker(line));
        // System.out.println(getMarker2(line));
    }

    /*
     * Introduce all elements for the line in a set (cannot contain repeated
     * elements) and return the first spot where there are 4 consecutive characters
     * not repeated
     */
    public static int getMarker(String line) {
        Set<Character> nonRepeatedCharacters;
        for (int i = 3; i < line.length() - 1; i++) {
            nonRepeatedCharacters = new HashSet<>();
            nonRepeatedCharacters.add(line.charAt(i - 3));
            nonRepeatedCharacters.add(line.charAt(i - 2));
            nonRepeatedCharacters.add(line.charAt(i - 1));
            nonRepeatedCharacters.add(line.charAt(i));
            if (nonRepeatedCharacters.size() == 4) {
                return i + 1;
            }
        }
        return -1;
    }

    /*
     * Introduce all elements for the line in a set (cannot contain repeated
     * elements) and return the first spot where there are 12 consecutive characters
     * not repeated
     */
    public static int getMarker2(String line) {
        Set<Character> nonRepeatedCharacters;
        for (int i = 13; i < line.length() - 1; i++) {
            nonRepeatedCharacters = new HashSet<>();
            for (int j = 13; j >= 0; j--)
                nonRepeatedCharacters.add(line.charAt(i - j));
            if (nonRepeatedCharacters.size() == 14) {
                return i + 1;
            }
        }
        return -1;
    }

}
