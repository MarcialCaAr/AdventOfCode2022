package day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class day11 {
    public static void main(String[] args) {

        ArrayList<Integer> monkeyPases = new ArrayList<>(8);
        for (int i = 0; i < 8; i++) {
            monkeyPases.add(0);
        }

        @SuppressWarnings({ "unchecked" })
        LinkedList<Integer> monkeys[] = new LinkedList[8];
        for (int i = 0; i < monkeys.length; i++) {
            monkeys[i] = new LinkedList<Integer>();
        }

        monkeys[0].addAll(Arrays.asList(77, 69, 76, 77, 50, 58));
        monkeys[1].addAll(Arrays.asList(75, 70, 82, 83, 96, 64, 62));
        monkeys[2].addAll(Arrays.asList(53));
        monkeys[3].addAll(Arrays.asList(85, 64, 93, 64, 99));
        monkeys[4].addAll(Arrays.asList(61, 92, 71));
        monkeys[5].addAll(Arrays.asList(79, 73, 50, 90));
        monkeys[6].addAll(Arrays.asList(50, 89));
        monkeys[7].addAll(Arrays.asList(83, 56, 64, 58, 93, 91, 56, 65));

        int itemWorry = 0;
        for (int i = 0; i < 20; i++) {
            for (int monkey = 0; monkey < monkeys.length; monkey++) {
                while (monkeys[monkey].size() != 0) {
                    itemWorry = operation(monkey, monkeys[monkey].removeFirst()) / 3;
                    monkeys[monkeyToSend(monkey, test(monkey, itemWorry))].add(itemWorry);
                    monkeyPases.set(monkey, monkeyPases.get(monkey) + 1);
                }

            }
        }

        Collections.sort(monkeyPases, Collections.reverseOrder());
        System.out.println(monkeyPases.get(0) * monkeyPases.get(1));
        // System.out.println(monkeys[0].removeFirst());

    }

    public static int monkeyToSend(int monkey, boolean test) {
        switch (monkey) {
            case 0:
                if (test) return 1; else return 5;
            case 1:
                if (test) return 5; else return 6;
            case 2:
                if (test) return 0; else return 7;
            case 3:
                if (test) return 7;else return 2;
            case 4:
                if (test) return 2; else return 3;
            case 5:
                if (test) return 4; else return 6;
            case 6:
                if (test) return 4; else return 3;
            case 7:
                if (test) return 1; else return 0;
        }

        return -1;
    }

    public static boolean test(int monkey, int worry) {
        switch (monkey) {
            case 0:
                if (worry % 5 == 0) return true; else return false;
            case 1:
                if (worry % 17 == 0) return true; else return false;
            case 2:
                if (worry % 2 == 0) return true; else return false;
            case 3:
                if (worry % 7 == 0) return true; else return false;
            case 4:
                if (worry % 3 == 0) return true; else return false;
            case 5:
                if (worry % 11 == 0) return true; else return false;
            case 6:
                if (worry % 13 == 0) return true; else return false;
            case 7:
                if (worry % 19 == 0) return true; else return false;
        }

        return false;
    }

    public static int operation(int monkey, int worry) {
        switch (monkey) {
            case 0:
                return worry * 11;
            case 1:
                return worry + 8;
            case 2:
                return worry * 3;
            case 3:
                return worry + 4;
            case 4:
                return worry * worry;
            case 5:
                return worry + 2;
            case 6:
                return worry + 3;
            case 7:
                return worry + 5;
        }

        return -1;
    }
}