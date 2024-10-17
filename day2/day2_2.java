package day2;

import java.io.File;
import java.util.Scanner;

public class day2_2 {
    public static void main(String[] args) {

        File fd = new File("day2/input_test.txt");
        // File fd = new File("day2/input.txt");
        Scanner file = null;
        try {
            file = new Scanner(fd);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        int sum = 0;
        while(file.hasNextLine()){
            sum += checkWins(file.next().charAt(0),file.next().charAt(0));
        }

        System.out.println(sum);
        // A rock       X   1
        // B paper      Y   2
        // C scissors   Z   3
    }

    public static int checkWins(char firstHand, char secondHand){
        if(firstHand == 'A'){
            if(secondHand == 'X')
                return 0 + 3;
            if(secondHand == 'Y')
                return 3 + 1;
            return 6 + 2;
        }
        if(firstHand == 'B'){
            if(secondHand == 'X')
                return 0 + 1;
            if(secondHand == 'Y')
                return 3 + 2;
            return 6 + 3;
        }
        if(firstHand == 'C'){
            if(secondHand == 'X')
                return 0 + 2;
            if(secondHand == 'Y')
                return 3 + 3;
            return 6 + 1;
        }
        
        return -1;
    }
    
}
