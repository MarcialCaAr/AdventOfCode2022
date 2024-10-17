package day1;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class day1_2 {
    public static void main(String[] args) {
        List<Integer> data = new ArrayList<>();

        // File fd = new File("day1/input_test.txt");
        File fd = new File("day1/input.txt");
        Scanner file = null;
        try {
            file = new Scanner(fd);

        } catch (Exception e) {
            System.out.println(e.toString());
        }


        String aux = "";
        int i = 0;
        while (file.hasNextLine()) {
            data.add(0);
            aux = file.nextLine();
            if(aux != ""){
                data.set(i, data.get(i)+Integer.parseInt(aux));
            } else {i++;}
        }

        Collections.sort(data, Collections.reverseOrder());

        int sum = 0;
        for(i = 0; i < 3;i++)
            sum += data.get(i);

        System.out.println(sum);

        
    }
}
