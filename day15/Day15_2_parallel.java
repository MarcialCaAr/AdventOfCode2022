package day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Day15_2_parallel {

    // private static final int M = 20;
    // private static final int M = 4000000;
    private static List<String> lines = new ArrayList<>();

    public static void main(String[] args) {


        // File fd = new File("day15/input_test.txt");
        File fd = new File("day15/input.txt");
        Scanner file = null;
        try {
            file = new Scanner(fd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        while (file.hasNextLine()) {
            lines.add(file.nextLine().replaceAll("Sensor at x=", "").replaceAll(", y=", " ")
            .replaceAll(": closest beacon is at x=", " "));
        }

        ExecutorService executioner;
        executioner = Executors.newFixedThreadPool(10);
        executioner.execute(new Executter(0,400000,lines));
        executioner.execute(new Executter(400000,800000,lines));
        executioner.execute(new Executter(800000,1200000,lines));
        executioner.execute(new Executter(1200000,1600000,lines));
        executioner.execute(new Executter(1600000,2000000,lines));

        executioner.execute(new Executter(2000000,2400000,lines));
        executioner.execute(new Executter(2400000,2800000,lines));
        executioner.execute(new Executter(2800000,2200000,lines));
        executioner.execute(new Executter(2200000,2600000,lines));
        executioner.execute(new Executter(3600000,4000001,lines));

        // executioner.execute(new Executter(0,2,lines));
        // executioner.execute(new Executter(2,4,lines));
        // executioner.execute(new Executter(4,6,lines));
        // executioner.execute(new Executter(6,8,lines));
        // executioner.execute(new Executter(8,10,lines));

        // executioner.execute(new Executter(10,12,lines));
        // executioner.execute(new Executter(12,14,lines));
        // executioner.execute(new Executter(14,16,lines));
        // executioner.execute(new Executter(16,18,lines));
        // executioner.execute(new Executter(18,21,lines));



        executioner.shutdown();
        

        
        
        
        
    }
}
