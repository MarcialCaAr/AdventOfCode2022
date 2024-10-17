package day2;

import java.io.File;
import java.util.Scanner;

public class day2 {
    public static void main(String[] args) {

        // File fd = new File("day2/input_test.txt");
        File fd = new File("day2/input.txt");
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
                return 3 + 1;
            if(secondHand == 'Y')
                return 6 + 2;
            return 0 + 3;
        }
        if(firstHand == 'B'){
            if(secondHand == 'Y')
                return 3 + 2;
            if(secondHand == 'Z')
                return 6 + 3;
            return 0 + 1;
        }
        if(firstHand == 'C'){
            if(secondHand == 'Z')
                return 3 + 3;
            if(secondHand == 'X')
                return 6 + 1;
            return 0 + 2;
        }
        
        return -1;
    }
    
}
