package day7;

import java.io.File;
import java.util.Scanner;

public class day7_2 {

    private static int smallestDir = 70000000;

    public static void main(String[] args) {
        File fd = new File("day7/input_test.txt");
        // File fd = new File("day7/input.txt");
        Scanner file = null;
        try {
            file = new Scanner(fd);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        int unsedSpace = 70000000 - fileSize(file, 80000000);
        int requiredSpace = 30000000 - unsedSpace;

        try {
            file = new Scanner(fd);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        fileSize(file, requiredSpace);
        System.out.println(smallestDir);
    }

    /*
     * Recursive method that calculates the actuall size of a file by iterating
     * inside it and keeping the size of a subdirectory that is beetween 2 given
     * thresholds
     */
    public static int fileSize(Scanner file, int requiredSpace) {
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
                if (sumSize >= requiredSpace && sumSize <= smallestDir) {
                    smallestDir = sumSize;
                }
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
            // check if it goes down a directory and iterate over that directory and sum its
            // size to actual directory
            if (lineScanner.next().equals("$") && lineScanner.next().equals("cd") && !lineScanner.next().equals("..")) {
                int aux = fileSize(file, requiredSpace);
                sumSize += aux;
            }
        }

        // check if the actuall directory has a size beetween the thresholds and if so,
        // set it as the new smallestDir
        if (sumSize >= requiredSpace && sumSize <= smallestDir) {
            smallestDir = sumSize;
        }

        return sumSize;
    }

}
