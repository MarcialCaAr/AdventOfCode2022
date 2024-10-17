package day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day15 {

    private static final int Y = 10;
    // private static final int Y = 2000000;
    
    public static void main(String[] args) {

        Set<Integer> known = new HashSet<>();
        Set<Integer> cannot = new HashSet<>();

        File fd = new File("day15/input_test.txt");
        // File fd = new File("day15/input.txt");
        Scanner file = null;
        Scanner lineScanner = null;
        try {
            file = new Scanner(fd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        while (file.hasNextLine()) {
            lineScanner = new Scanner(file.nextLine().replaceAll("Sensor at x=", "").replaceAll(", y=", " ").replaceAll(": closest beacon is at x=", " "));
            while(lineScanner.hasNext()){
                int sensorX = lineScanner.nextInt();
                int sensorY = lineScanner.nextInt();
                int beaconX = lineScanner.nextInt();
                int beaconY = lineScanner.nextInt();

                int distance = Math.abs(sensorX-beaconX) + Math.abs(sensorY-beaconY);
                int objetive = distance - Math.abs(sensorY - Y);

                if(objetive < 0)
                    continue;

                for(int x = sensorX - objetive; x < sensorX + objetive; x++)
                    cannot.add(x);

                if(beaconY == Y)
                    known.add(beaconX);

            }   
        }

        // cannot.removeAll(known);
        System.out.println(cannot.size());
    }
}
