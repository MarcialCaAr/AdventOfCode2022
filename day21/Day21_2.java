package day21;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Day21_2 {

    private static Map<String, List<String>> table = new HashMap<>();
    private static Map<String, List<String>> tableCopy = new HashMap<>();

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

        tableCopy = new HashMap<>(table);


        for(long i = 3221245700000L; i < 3221245824370L ; i++){
            if(rootEquanls(i)){
                break;
            }
        }

        // for(long i = 0; i < 3221245824370L ; i++){
        //     if(i % 10000000 == 0)
        //     System.out.println(i / 10000000);
        //     if(rootEquanls(i)){
        //         break;
        //     }
        // }




        // for(long i = 500L; i > 0 ; i--){
        //     if(rootEquanls(i)){
        //         break;
        //     }
        // }
        // rootEquanls(3221245840009L);

        // System.out.println(getMonkeyValue("root"));
    }


    private static boolean rootEquanls(long value){

        List<String> monkeyFunction = table.get("root");
        table.put("humn", Arrays.asList(Long.toString(value)));

        // System.out.println(table);
        // System.out.println(tableCopy);
        // System.out.println("\n\n");
        long value1 = getMonkeyValue(monkeyFunction.get(0));
        long value2 = getMonkeyValue(monkeyFunction.get(2));
        if(value1 == value2){
            System.out.println(value);
            System.out.println(value1 + " " + value2);
            return true;
        }
        table = new HashMap<>(tableCopy);



        return false;
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
