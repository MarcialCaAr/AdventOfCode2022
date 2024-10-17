package day20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import librerias.Tuple;

public class Day20_opt {

    private static List<Long> order = new ArrayList<>();

    private static final int LOOKING_POS1 = 1000, LOOKING_POS2 = 2000, LOOKING_POS3 = 3000;
    private static int id0;

    public static void main(String[] args) {

        List<Tuple<Long, Integer>> list = new LinkedList<>();

        // File fd = new File("day20/input_test.txt");
        File fd = new File("day20/input.txt");
        Scanner file = null;
        try {
            file = new Scanner(fd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int index = 0;
        while (file.hasNextLine()) {
            Long number = file.nextLong();
            list.add(new Tuple<Long, Integer>(number, index));
            order.add(number);
            
            index++;
        }
        

        list = orderList(list);


        int value0 = list.indexOf(new Tuple<Long, Integer>(0L, id0));
        Long value1 = list.get((LOOKING_POS1 + value0)% list.size()).getFirst();
        Long value2 = list.get((LOOKING_POS2 + value0)% list.size()).getFirst();
        Long value3 = list.get((LOOKING_POS3 + value0)% list.size()).getFirst();

        // System.out.println(value0 + " " + value1 + " " + value2 + " " + value3);
        System.out.println(value1+value2+value3);

    }

    private static List<Tuple<Long, Integer>> orderList(List<Tuple<Long, Integer>> list) {
        for (int i = 0; i < order.size(); i++) {
            Long number = order.get(i);
            Tuple<Long, Integer> tuple = list.get(list.indexOf(new Tuple<Long, Integer>(number, i)));

            if(tuple.getFirst() == 0){
                id0 = tuple.getSecond();
                continue;
            }

            Long newPos = list.indexOf(tuple) + tuple.getFirst();
            list.remove(tuple);
            newPos = (newPos) % list.size();
            if (newPos == 0) {
                newPos = (long) list.size();
            } else if (newPos < 0) {
                newPos = list.size() + newPos;
            }

            list.add(newPos.intValue(), tuple);
        }

        return list;
    }

}
