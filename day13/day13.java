package day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day13 {
    
    public static void main(String[] args) {
        ArrayList<String> input = new ArrayList<>();

        // File fd = new File("day13/input_test.txt");
        File fd = new File("day13/input.txt");
        Scanner file = null;
        try {
            file = new Scanner(fd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String aux = "";
        while(file.hasNextLine()){
            aux = file.nextLine();
            if(aux.equals("")){
                continue;
            }

            input.add(aux);
        }

// .replaceAll("(?m)^\\s*\\n", "");
        // String lines = input.get(9).replace("", " "); 
        String lines = getElements2(new Scanner(input.get(14).replace("", " "))).substring(1);
        System.out.println(lines);

        // System.out.println(lines);

        part1(input);
    }

    public static void part1(List<String> input){
        String left, right;
        Scanner leftScanner, rightScanner;
        int res = 0;


        for(int i = 0; i < input.size(); i++){
            left = getElements2(new Scanner(input.get(i).replace("", " ")));
            i++;
            right = getElements2(new Scanner(input.get(i).replace("", " ")));
            leftScanner = new Scanner(left);
            rightScanner = new Scanner(right); 


            if(checkOrder(leftScanner, rightScanner,input.get(i-1),input.get(i)))
                res += (i+1)/2;
        }
        System.out.println(res);
    }

    public static boolean checkOrder(Scanner leftScanner, Scanner rightScanner, String left, String right){
        while(true){
            if(!leftScanner.hasNext()){
                if(left.length() < right.length())
                    return true;
                else
                    return false;
            }
            if(!rightScanner.hasNext()){
                return false;
            }
            left = leftScanner.nextLine();
            right = rightScanner.nextLine();

            for(int j = 0; j < Math.max(left.length(), right.length()) ; j++){
                if(j == left.length() && left.length() < right.length()){
                    return true;
                } else if (j == right.length() && left.length() > right.length())
                    return false;
                if(left.charAt(j) < right.charAt(j)){
                    return true;
                } else if (left.charAt(j) > right.charAt(j)){
                    return false;
                }
            }
        }
    }


    public static String getElements2(Scanner element){
        String returnValue = "";
        
        char actualChar;
        while(element.hasNext()){
            actualChar = element.next().charAt(0);
            if(actualChar == ']')
                return returnValue;
            if(actualChar == '[')
                returnValue += "\n" + getElements2(element);
            if(actualChar == ']')
                return returnValue;
            if(actualChar == '[')
                returnValue += "\n";
            else if(actualChar == ',')
                returnValue += "";
            else
                returnValue += actualChar;
                
        }

        return returnValue;
    }
}
