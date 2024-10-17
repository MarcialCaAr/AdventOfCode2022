package day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day7 {

    private static final int MAX_DIR_SIZE = 100000;
    private static int totalSum = 0;

    public static void main(String[] args) {
        File fd = new File("day7/input_test.txt");
        // File fd = new File("day7/input.txt");
        try {
            fileSize(new Scanner(fd));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(totalSum);
    }

    /*
     * Recursive method that calculates the actuall size of a file by iterating
     * inside it and suming all the directory sizes that are lower that a certain
     * threshold
     */
    public static int fileSize(Scanner file) {
        int sumSize = 0;
        String nextLine;
        String nextElement;
        Scanner lineScanner = null;

        while (file.hasNextLine()) {
            // start the line
            nextLine = file.nextLine();
            lineScanner = new Scanner(nextLine);
            // check if it goes up a directory and return the actual size of the directory
            if (lineScanner.nextLine().equals("$ cd ..")) {
                lineScanner.close();
                return sumSize;
            }
            // restart the line
            lineScanner.close();
            lineScanner = new Scanner(nextLine);
            nextElement = lineScanner.next();
            // check if is not a command of a directory (it will be so a file) and sum its
            // size to the direcotry size
            if (!nextElement.equals("$") && !nextElement.equals("dir")) {
                sumSize += Integer.parseInt(nextElement);
                continue;
            }
            // restart the line
            lineScanner.close();
            lineScanner = new Scanner(nextLine);
            // check if it goes down a directory and iterate over that directory, then add
            // his size to the total if its smaller than the threshold
            if (lineScanner.next().equals("$") && lineScanner.next().equals("cd") && !lineScanner.next().equals("..")) {
                int aux = fileSize(file);
                if (aux < MAX_DIR_SIZE) {
                    totalSum += aux;
                }
                sumSize += aux;
            }
        }

        // if it finishes the comand line and has not go out of a dir, sum the size of
        // the dir if is smaller than the threshold
        if (sumSize < MAX_DIR_SIZE) {
            totalSum += sumSize;
        }

        return sumSize;
    }

}
