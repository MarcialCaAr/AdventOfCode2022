package day15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import librerias.Tuple;

public class Executter implements Runnable {

    private final int M = 4000000;
    private int startM;
    private int finalM;
    private List<Tuple<Integer, Integer>> intervals;
    private List<String> lines;


    public Executter(int startM, int finalM, List<String> lines){
        this.startM = startM;
        this.finalM = finalM;
        this.intervals = new ArrayList<>();
        this.lines = lines;
    }

    @Override
    public void run() {
        Scanner lineScanner = null;
        for (int y = startM; y < finalM; y++) {
            // System.out.println("nueva iteracion: " + y);
            intervals = new ArrayList<>();

            for (String line : lines) {
                lineScanner = new Scanner(line);

                int sensorX = lineScanner.nextInt();
                int sensorY = lineScanner.nextInt();
                int beaconX = lineScanner.nextInt();
                int beaconY = lineScanner.nextInt();

                int distance = Math.abs(sensorX - beaconX) + Math.abs(sensorY - beaconY);
                int objetive = distance - Math.abs(sensorY - y);

                if (objetive < 0)
                    continue;

                int lowX = sensorX - objetive;
                int hightX = sensorX + objetive;

                intervals.add(new Tuple<Integer, Integer>(lowX, hightX));
            }

            Collections.sort(intervals);

            List<Tuple<Integer, Integer>> queue = new ArrayList<>();

            for (Tuple<Integer, Integer> tuple : intervals) {
                if (queue.size() == 0) {
                    queue.add(tuple);
                    continue;
                }
                int qhi = queue.get(queue.size() - 1).getSecond();

                if (tuple.getFirst() > qhi + 1) {
                    queue.add(tuple);
                    continue;
                }

                queue.get(queue.size() - 1).setSecond(Math.max(qhi, tuple.getSecond()));
            }

            int x = 0;
            for (Tuple<Integer, Integer> tuple : queue) {
                if (x < tuple.getFirst()) {
                    System.out.println(x * 4000000 + y);
                    return;
                }
                x = Math.max(x, tuple.getSecond() + 1);
                if (x > M)
                    break;
            }
        }
        
    }
    
}
