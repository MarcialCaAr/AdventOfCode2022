package day20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import librerias.Tuple;

public class Day20 {

    private static final int LOOKING_POS1 = 1000, LOOKING_POS2 = 2000, LOOKING_POS3 = 3000;

    public static void main(String[] args) {

        List<Tuple<Integer, Boolean>> list = new LinkedList<>();

        // File fd = new File("day20/input_test.txt");
        File fd = new File("day20/input.txt");
        Scanner file = null;
        try {
            file = new Scanner(fd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (file.hasNextLine()) {
            list.add(new Tuple<Integer, Boolean>(file.nextInt(), false));
        }

        // int num = 1-2;
        // num = num % 7;
        // if (num < 0) {
        //     num = 7 + num - 1;
        // }

        // System.out.println(num);

        list = orderList(list);

        int value0 = list.indexOf(new Tuple<Integer,Boolean>(0, true));
        int value1 = list.get((LOOKING_POS1 + value0)% list.size()).getFirst();
        int value2 = list.get((LOOKING_POS2 + value0)% list.size()).getFirst();
        int value3 = list.get((LOOKING_POS3 + value0)% list.size()).getFirst();


        System.out.println(value1+value2+value3);
        

        // System.out.println(list);
    }

    private static List<Tuple<Integer, Boolean>> orderList(List<Tuple<Integer, Boolean>> list){
        for (int j = 0; j < list.size(); j++) {
            for (int i = 0; i < list.size(); i++) {
                Tuple<Integer, Boolean> tuple = list.get(i);
                if (tuple.getSecond()) {
                    continue;
                }

                if(tuple.getFirst() == 0){
                    list.get(i).setSecond(true);
                    break;
                }

                // System.out.println(list);

                list.remove(tuple);
                tuple.setSecond(true);
                int newPos = i + tuple.getFirst();
                newPos = (newPos) % list.size();
                if(newPos == 0){
                    newPos = list.size();   
                }else if (newPos < 0) {
                    newPos = list.size() + newPos;
                }

                list.add(newPos, tuple);
                break;
            }
        }

        return list;
    }


}
