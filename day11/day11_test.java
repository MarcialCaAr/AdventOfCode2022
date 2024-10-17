package day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class day11_test {
    public static void main(String[] args) {

        ArrayList<Integer> monkeyPases = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            monkeyPases.add(0);
        }

        @SuppressWarnings({ "unchecked" })
        LinkedList<Integer> monkeys[] = new LinkedList[4];
        for (int i = 0; i < monkeys.length; i++) {
            monkeys[i] = new LinkedList<Integer>();
        }

        monkeys[0].addAll(Arrays.asList(79, 98));
        monkeys[1].addAll(Arrays.asList(54, 65, 75, 74));
        monkeys[2].addAll(Arrays.asList(79, 60, 97));
        monkeys[3].addAll(Arrays.asList(74));

        int itemWorry = 0;
        for (int i = 0; i < 20; i++) {
            for (int monkey = 0; monkey < monkeys.length; monkey++) {
                while(monkeys[monkey].size() != 0) {
                    itemWorry = operation(monkey, monkeys[monkey].removeFirst())/3;
                    monkeys[monkeyToSend(monkey, test(monkey, itemWorry))].add(itemWorry);
                    monkeyPases.set(monkey, monkeyPases.get(monkey)+1);
                }
                
            }
        }

        Collections.sort(monkeyPases,Collections.reverseOrder());
        System.out.println(monkeyPases.get(0) * monkeyPases.get(1));
        // System.out.println(monkeys[0].removeFirst());

    }

    public static int monkeyToSend(int monkey, boolean test){
        switch (monkey) {
            case 0:
                if (test)
                    return 2;
                else
                    return 3;
            case 1:
                if (test)
                    return 2;
                else
                    return 0;
            case 2:
                if (test)
                    return 1;
                else
                    return 3;
            case 3:
                if (test)
                    return 0;
                else
                    return 1;
        }

        return -1;
    }

    public static boolean test(int monkey, int worry) {
        switch (monkey) {
            case 0:
                if (worry % 23 == 0)
                    return true;
                else
                    return false;
            case 1:
                if (worry % 19 == 0)
                    return true;
                else
                    return false;
            case 2:
                if (worry % 13 == 0)
                    return true;
                else
                    return false;
            case 3:
                if (worry % 17 == 0)
                    return true;
                else
                    return false;
        }

        return false;
    }

    public static int operation(int monkey, int worry) {
        switch (monkey) {
            case 0:
                return worry * 19;
            case 1:
                return worry + 6;
            case 2:
                return worry * worry;
            case 3:
                return worry + 3;
        }

        return -1;
    }
}