package day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class sol {

    protected static String runPart2(final List<String> input) {
        final List<Packet> packets = new ArrayList<>();
        final Packet divider1 = new Packet("[[2]]");
        final Packet divider2 = new Packet("[[6]]");
        packets.add(divider1);
        packets.add(divider2);
        for (int i = 0; i < input.size(); i += 3) {
            packets.add(new Packet(input.get(i)));
            packets.add(new Packet(input.get(i + 1)));
        }
        Collections.sort(packets);
        return String.valueOf((packets.indexOf(divider1) + 1) * (packets.indexOf(divider2) + 1));
    }

    protected static String runPart1(final List<String> input) {
        int total = 0;
        for (int i = 0, pairNr = 1; i < input.size(); i += 3, pairNr++) {
            final Packet left = new Packet(input.get(i));
            final Packet right = new Packet(input.get(i + 1));
            total += left.compareTo(right) < 0 ? pairNr : 0;
        }
        return String.valueOf(total);
    }

    public static void main(final String... args) {
        List<String> input = getStringList("day13/input.txt");
        System.out.println(runPart1(input));
        System.out.println(runPart2(input));
    }


    private static List<String> getStringList(final String filename) {
		// try (Stream<String> stream = Files.lines(Paths.get("resources", filename))) {
		// 	return stream.collect(Collectors.toList());
		// } catch (final IOException e) {
		// 	e.printStackTrace();
		// }
		// return new ArrayList<>();

        File fd = new File(filename);
        Scanner file = null;
        try {
            file = new Scanner(fd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String> sol = new ArrayList<>();
        while(file.hasNextLine()){
            sol.add(file.nextLine());
        }

        return sol;
	}
}