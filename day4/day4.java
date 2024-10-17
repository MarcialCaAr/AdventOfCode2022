package day4;

import java.io.File;
import java.util.Scanner;

public class day4 {

    public static void main(String[] args) {
        // File fd = new File("day4/input_test.txt");
        File fd = new File("day4/input.txt");
        Scanner file = null;
        Scanner line;
        try {
            file = new Scanner(fd);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        int count = 0;
        int count2 = 0;

        while (file.hasNext()) {
            // format the line to have only numbers in each line and set it to a scanner
            line = new Scanner(file.next().replaceAll("-", " ").replaceAll(",", " "));
            // Iterate throught the line counthing the lines in wich the numbers overlab in
            // certain way
            while (line.hasNextInt()) {
                int startZone1 = line.nextInt();
                int finishZone1 = line.nextInt();
                int startZone2 = line.nextInt();
                int finishZone2 = line.nextInt();

                if ((startZone1 <= startZone2 && finishZone1 >= finishZone2)
                        || (startZone1 >= startZone2 && finishZone1 <= finishZone2))
                    count++;

                if ((finishZone1 >= startZone2 && startZone1 <= startZone2)
                        || (finishZone2 >= startZone1 && startZone2 <= startZone1))
                    count2++;
            }
        }

        System.out.println("question 1:\n" + count);
        System.out.println("question 2:\n" + count2);

    }

}
