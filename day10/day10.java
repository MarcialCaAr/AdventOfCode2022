package day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day10 {

    public static void main(String[] args) {
        // File fd = new File("day10/input_test.txt");
        File fd = new File("day10/input.txt");
        Scanner file = null;
        try {
            file = new Scanner(fd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int cicle = 0;
        int signalStrength = 0;
        int x = 1;
        while (file.hasNextLine()) {
            // adds 1 to the cicle count
            cicle++;
            // checks if it is on a count spot and if so adds its value to the signalStrenght
            if (cicle % 40 == 20 && cicle < 221)
                signalStrength += x * cicle;

            // checks if the 
            if (file.next().equals("addx")) {
                cicle++;
                if (cicle % 40 == 20 && cicle < 221)
                    signalStrength += x * cicle;
                x += file.nextInt();
            }
        }

        System.out.println(signalStrength);
    }

}
