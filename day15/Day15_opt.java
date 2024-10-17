package day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import librerias.Tuple;

public class Day15_opt {

    private static final int Y = 10;
    // private static final int Y = 2000000;
    
    public static void main(String[] args) {

        List<Tuple<Integer,Integer>> intervals = new ArrayList<>();

        // File fd = new File("day15/input_test.txt");
        File fd = new File("day15/input.txt");
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
                
                int lowX = sensorX - objetive;
                int hightX = sensorX + objetive;

                intervals.add(new Tuple<Integer,Integer>(lowX, hightX));
            }   
        }

        Collections.sort(intervals);

        List<Tuple<Integer,Integer>> queue = new ArrayList<>();

        for (Tuple<Integer,Integer> tuple : intervals) {
            if(queue.size() == 0){
                queue.add(tuple);
                continue;
            }
            int qhi = queue.get(queue.size()-1).getSecond();

            if(tuple.getFirst() > qhi + 1){
                queue.add(tuple);
                continue;
            }

            queue.get(queue.size()-1).setSecond(Math.max(qhi, tuple.getSecond()));
        }

        int blocked = 0;
        for (Tuple<Integer,Integer> tuple : queue) {
            blocked += tuple.getSecond() - tuple.getFirst();
        }
        
        System.out.println(blocked);
        System.out.println(queue);
    }
}
