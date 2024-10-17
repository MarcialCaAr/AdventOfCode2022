package day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import librerias.Tuple;

public class Day15_2 {

    // private static final int M = 20;
    private static final int M = 4000000;
    private static List<Integer> lines = new ArrayList<>();

    public static void main(String[] args) {


        List<Tuple<Integer, Integer>> intervals = new ArrayList<>();


        // File fd = new File("day15/input_test.txt");
        File fd = new File("day15/input.txt");
        Scanner file = null;
        try {
            file = new Scanner(fd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        Scanner lineScanner = null;
        while (file.hasNextLine()) {
            lineScanner = new Scanner(file.nextLine().replaceAll("Sensor at x=", "").replaceAll(", y=", " ")
            .replaceAll(": closest beacon is at x=", " "));
            while(lineScanner.hasNextLine()){
                lines.add(lineScanner.nextInt());
            }
        }


        int qhi;
        List<Tuple<Integer, Integer>> queue = new ArrayList<>();
        int sensorX = 0;
        int sensorY = 0;
        int beaconX = 0;
        int beaconY = 0;
        for (long y = 0; y < M+1; y++) {
            // System.out.println(y);
            intervals.clear();

            for (int i = 0; i < lines.size(); i += 4) {
                    sensorX = lines.get(i);
                    sensorY = lines.get(i+1);
                    beaconX = lines.get(i+2);
                    beaconY = lines.get(i+3);


                int distance = Math.abs(sensorX - beaconX) + Math.abs(sensorY - beaconY);
                int objetive = distance - Math.abs(sensorY - (int)y);

                if (objetive < 0)
                    continue;

                int lowX = sensorX - objetive;
                int hightX = sensorX + objetive;

                intervals.add(new Tuple<Integer, Integer>(lowX, hightX));
            }

            Collections.sort(intervals);

            queue.clear();

            for (Tuple<Integer, Integer> tuple : intervals) {
                if (queue.size() == 0) {
                    queue.add(tuple);
                    continue;
                }
                qhi = queue.get(queue.size() - 1).getSecond();

                if (tuple.getFirst() > qhi + 1) {
                    queue.add(tuple);
                    continue;
                }

                queue.get(queue.size() - 1).setSecond(Math.max(qhi, tuple.getSecond()));
            }

            // System.out.println(queue);

            Long x = 0L;
            for (Tuple<Integer, Integer> tuple : queue) {
                if (x < tuple.getFirst()) {
                    Long aux = (x * 4000000L + y);
                    System.out.println(aux);
                    return;
                }
                x = Math.max(x, tuple.getSecond() + 1);
                if (x > M)
                    break;
            }
        }
        
        
        
    }
}
