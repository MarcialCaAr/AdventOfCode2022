package day21;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Day21 {

    private static Map<String, List<String>> table = new HashMap<>();

    public static void main(String[] args) {

        
        // File fd = new File("day21/input_test.txt");
        File fd = new File("day21/input.txt");
        Scanner file = null;
        Scanner line = null;
        try {
            file = new Scanner(fd);
        } catch (Exception e) {
            e.printStackTrace();
        }


        while(file.hasNextLine()){
            line = new Scanner(file.nextLine().replaceAll(":", ""));
            String monkeyName = line.next();
            List<String> monkeyJob = new ArrayList<>();
            while(line.hasNext()){
                monkeyJob.add(line.next());
            }
            table.put(monkeyName, monkeyJob);
        }


        // System.out.println(table);
        System.out.println(getMonkeyValue("root"));
    }


    private static Long getMonkeyValue(String monkeyName){
        if(table.get(monkeyName).size() == 1){
            return Long.parseLong(table.get(monkeyName).get(0));
        }

        List<String> monkeyFunction = table.get(monkeyName);

        Long monkeyValue = monkeyOperation(getMonkeyValue(monkeyFunction.get(0)), monkeyFunction.get(1).charAt(0), getMonkeyValue(monkeyFunction.get(2)));

        table.put(monkeyName, Arrays.asList(Long.toString(monkeyValue)));
        return monkeyValue;
    }

    private static Long monkeyOperation(Long valueMonkey1, char operation, Long valueMonkey2){
        switch(operation){
            case '+':
                return valueMonkey1 + valueMonkey2;
            
            case '-':
                return valueMonkey1 - valueMonkey2;

            case '*':
                return valueMonkey1 * valueMonkey2;

            case '/':
                return valueMonkey1 / valueMonkey2;
        }

        return -1L;
    }
}
