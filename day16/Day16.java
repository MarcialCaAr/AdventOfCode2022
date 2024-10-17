package day16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


import librerias.Tuple;

public class Day16 {

    private static Map<String, Integer> valves = new HashMap<>();
    private static Map<String, Map<String, Integer>> dists = new HashMap<>();
    private static List<String> nonEmpty = new ArrayList<>();
    private static Map<List<String>, Integer> cache = new HashMap<>();
    private static Map<String, Integer> indices = new HashMap<>();

    public static void main(String[] args) {
        Map<String, List<String>> tunnels = new HashMap<>();
        Set<String> visited = new HashSet<>();


        // File fd = new File("day16/input_test.txt");
        File fd = new File("day16/input.txt");
        Scanner file = null;
        Scanner line = null;
        try {
            file = new Scanner(fd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        while(file.hasNextLine()){
            line = new Scanner(file.nextLine().replaceAll("Valve ", "").replaceAll(" has flow rate=", " ").replaceAll("; tunnels lead to valves ", " ").replaceAll("; tunnel leads to valve ", " ").replaceAll(",",""));
            String valve = line.next();
            Integer preassure = line.nextInt();
            List<String> connections = new ArrayList<>();
            while(line.hasNext()){
                connections.add(line.next());
            }
            valves.put(valve, preassure);
            tunnels.put(valve, connections);
        }

        for (String valve : valves.keySet()) {
            if(!valve.equals("AA") && valves.get(valve) == 0){
                continue;
            }

            if(!valve.equals("AA")){
                nonEmpty.add(valve);
            }

            Map<String, Integer> aux = new HashMap<>();
            aux.put(valve, 0);
            aux.put("AA", 0);
            dists.put(valve, aux);
            visited.clear();
            visited.add(valve);

            List<Tuple<Integer, String>> queue = new ArrayList<>();
            queue.add(new Tuple<Integer,String>(0, valve));

            while(queue.size() > 0){
                Tuple<Integer, String> moveCost = queue.remove(0);
                Integer distance = moveCost.getFirst();
                String position = moveCost.getSecond();

                for (String neighbor : tunnels.get(position)) {
                    if(visited.contains(neighbor)){
                        continue;
                    }
                    visited.add(neighbor);
                    if(valves.get(neighbor) > 0){
                        // aux.clear();
                        aux.put(neighbor, distance+1);
                        dists.put(valve, aux);
                    }
                    queue.add(new Tuple<Integer,String>(distance+1, neighbor));
                }
            }
            dists.get(valve).remove(valve);
            if(!valve.equals("AA")){
                dists.get(valve).remove("AA");
            }
        }

        for(int i = 0; i < nonEmpty.size(); i++){
            indices.put(nonEmpty.get(i), i);
        }


        // List<String> prueba1 = new ArrayList<>();
        // List<String> prueba2 = new ArrayList<>();
        // prueba1.addAll(Arrays.asList("22","AA","1001"));
        // // prueba1.add("patata");
        // // prueba2.add("patata1");
        // // prueba2.add("patata");
        // prueba2.addAll(Arrays.asList("22","AA","1001"));

        // Map<List<String>, Integer> prueba3 = new HashMap<>();
        // prueba3.put(prueba1, 2);

        // System.out.println(prueba3);
        // System.out.println(prueba3.get(Arrays.asList(Integer.toString(22),"AA",Integer.toString(1001))));
        // System.out.println(prueba1.equals(prueba2));

        // System.out.println(tunnels);
        // System.out.println(indices);
        // System.out.println(dists);


        System.out.println(dfs(30, "AA", 0));

    }

    private static int dfs(int time, String valve, int bitmask){
        if(cache.containsKey(Arrays.asList(Integer.toString(time),valve,Integer.toString(bitmask)))){
            return cache.get(Arrays.asList(Integer.toString(time),valve,Integer.toString(bitmask)));
        }


        int maxVal = 0;
        for (String neighbor : dists.get(valve).keySet()) {
            int bit = 1 << indices.get(neighbor);
            if((bitmask & bit) > 0){
                continue;
            }

            int remTime = time - dists.get(valve).get(neighbor) - 1;
            if(remTime <= 0){
                continue;
            }
            maxVal = Math.max(maxVal,dfs(remTime, neighbor, bitmask | bit) + valves.get(neighbor) * remTime);
        }

        cache.put(Arrays.asList(Integer.toString(time),valve,Integer.toString(bitmask)), maxVal);
        return maxVal;
    }
    
}
