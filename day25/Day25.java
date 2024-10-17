package day25;

import java.io.File;
import java.util.*;



public class Day25 {

    private static final char[] DIGITS = {'0', '1', '2', '=', '-'};

    public static void main(String[] args) {

        Long totalSum = 0L;
        
        // File fd = new File("day25/input_test.txt");
        File fd = new File("day25/input.txt");
        // -1513746746          ---000-010101-
        Scanner file = null;
        try {
            file = new Scanner(fd);
        } catch (Exception e) {
            e.printStackTrace();
        }



        while(file.hasNextLine()){
            totalSum += getRealNumber(file.nextLine());
        }


        System.out.println(totalSum);

        int numberDigits = 20;
        String aux;

        while(true){
            System.out.println(numberDigits);
            aux = getCombinations(DIGITS, numberDigits, 7, "20=212=", totalSum);

            if(aux != null){
                System.out.println(aux);
                break;
            }
            numberDigits++;
        }


        System.out.println(getRealNumber("20=212=0000000000000"));

    }

    private static String getCombinations(char[] input, int size, int actualChar, String currentComb, Long number){
        if(actualChar == size){
            // System.out.println(currentComb);
            if(getRealNumber(currentComb) == number){
                return currentComb;
            } else {
                return null;
            }

        }

        String aux;
        for(int i = 0; i < input.length; i++){
            aux = getCombinations(input, size, actualChar+1, currentComb + input[i], number);

            if(aux != null){
                return aux;
            }

        }

        return null;
    } 


    private static long getRealNumber(String line){
        long num = 0;
        for(int i = 1; i <= line.length(); i++){
            num += realValue(line.charAt(line.length()-i)) * Math.pow(5, i-1);
        }

        return num;
    }


    private static int realValue(char number){
        if(Character.isDigit(number)){
            return Character.getNumericValue(number);
        }

        switch(number){
            case '-':
                return -1;

            case '=':
                return -2;
        }


        return -1;
    }
    
}
