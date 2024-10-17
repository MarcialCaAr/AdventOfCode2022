package day11;

import java.util.*;

public class day11_test2 {
    public class Main {
        static long[] op = new long[8];
        static long[] mod = new long[8];
    
        public static void parts(int part) {
            int ROUNDS = 0;
            if (part == 1)
                ROUNDS = 20;
    
            else
                ROUNDS = 10000;
            HashMap<Integer, ArrayList<Long>> monkeys = buildInput();
            long lcm = mod[0] * mod[1] * mod[2] * mod[3] * mod[4] * mod[5] * mod[6] * mod[7]; //all prime
            long[] counter = new long[8];
            for (int i = 0; i < ROUNDS; i++) {
                for (int j = 0; j < monkeys.size(); j++) {
                    ArrayList<Long> items = monkeys.get(j);
                    ListIterator<Long> iter = items.listIterator();
                    while (iter.hasNext()) {
                        long item = iter.next();
                        counter[j] = counter[j] + 1;
                        if (j == 0) {
                            item = item * op[0];
                            if (part == 1)
                                item = Math.round(item / 3);
                            item = item % lcm;
                            if (item % mod[0] == 0) {
                                monkeys.computeIfAbsent(1, k -> new ArrayList<>()).add(item);
                            } else
                                monkeys.computeIfAbsent(5, k -> new ArrayList<>()).add(item);
    
                        } else if (j == 1) {
                            item = item + op[1];
                            if (part == 1)
                                item = Math.round(item / 3);
                            item = item % lcm;
                            if (item % mod[1] == 0) {
                                monkeys.computeIfAbsent(5, k -> new ArrayList<>()).add(item);
                            } else
                                monkeys.computeIfAbsent(6, k -> new ArrayList<>()).add(item);
                        } else if (j == 2) {
                            item = item * op[2];
                            if (part == 1)
                                item = Math.round(item / 3);
                            item = item % lcm;
                            if (item % mod[2] == 0) {
                                monkeys.computeIfAbsent(0, k -> new ArrayList<>()).add(item);
                            } else
                                monkeys.computeIfAbsent(7, k -> new ArrayList<>()).add(item);
                        } else if (j == 3) {
                            item = item + op[3];
                            if (part == 1)
                                item = Math.round(item / 3);
                            item = item % lcm;
                            if (item % mod[3] == 0) {
                                monkeys.computeIfAbsent(7, k -> new ArrayList<>()).add(item);
                            } else
                                monkeys.computeIfAbsent(2, k -> new ArrayList<>()).add(item);
                        } else if (j == 4) {
                            item = item * item;
                            if (part == 1)
                                item = Math.round(item / 3);
                            item = item % lcm;
                            if (item % mod[4] == 0) {
                                monkeys.computeIfAbsent(2, k -> new ArrayList<>()).add(item);
                            } else
                                monkeys.computeIfAbsent(3, k -> new ArrayList<>()).add(item);
                        } else if (j == 5) {
                            item = item + op[5];
                            if (part == 1)
                                item = Math.round(item / 3);
                            item = item % lcm;
                            if (item % mod[5] == 0) {
                                monkeys.computeIfAbsent(4, k -> new ArrayList<>()).add(item);
                            } else
                                monkeys.computeIfAbsent(6, k -> new ArrayList<>()).add(item);
                        } else if (j == 6) {
                            item = item + op[6];
                            if (part == 1)
                                item = Math.round(item / 3);
                            item = item % lcm;
                            if (item % mod[6] == 0) {
                                monkeys.computeIfAbsent(4, k -> new ArrayList<>()).add(item);
                            } else
                                monkeys.computeIfAbsent(3, k -> new ArrayList<>()).add(item);
                        } else if (j == 7) {
                            item = item + op[7];
                            if (part == 1)
                                item = Math.round(item / 3);
                            item = item % lcm;
                            if (item % mod[7] == 0) {
                                monkeys.computeIfAbsent(1, k -> new ArrayList<>()).add(item);
                            } else
                                monkeys.computeIfAbsent(0, k -> new ArrayList<>()).add(item);
                        }
                        iter.remove();
                    }
                }
            }
    
            Arrays.sort(counter);
            System.out.println(counter[6] * counter[7]);
        }
    
        public static void main(String[] args) {
            parts(1);
            parts(2);
        }
    
        public static HashMap<Integer, ArrayList<Long>> buildInput() {
            HashMap<Integer, ArrayList<Long>> monkeys = new HashMap<Integer, ArrayList<Long>>();
            monkeys.put(0, new ArrayList<>(List.of(77L, 69L, 76L, 77L, 50L, 58L)));
            monkeys.put(1, new ArrayList<>(List.of(75L, 70L, 82L, 83L, 96L, 64L, 62L)));
            monkeys.put(2, new ArrayList<>(List.of(53L)));
            monkeys.put(3, new ArrayList<>(List.of(85L, 64L, 93L, 64L, 99L)));
            monkeys.put(4, new ArrayList<>(List.of(61L, 92L, 71L)));
            monkeys.put(5, new ArrayList<>(List.of(79L, 73L, 50L, 90L)));
            monkeys.put(6, new ArrayList<>(List.of(50L, 89L)));
            monkeys.put(7, new ArrayList<>(List.of(83L, 56L, 64L, 58L, 93L, 91L, 56L, 65L)));
    
            op[0] = 11L;
            mod[0] = 5L;
            op[1] = 8L;
            mod[1] = 17L;
            op[2] = 3L;
            mod[2] = 2L;
            op[3] = 4L;
            mod[3] = 7L;
            // op[4] = 3L;
            mod[4] = 3L;
            op[5] = 2L;
            mod[5] = 11L;
            op[6] = 3L;
            mod[6] = 13L;
            op[7] = 5L;
            mod[7] = 19L;
    
            return monkeys;
        }
    }
}