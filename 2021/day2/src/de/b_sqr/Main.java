package de.b_sqr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String[] inputLines = readInput();
        assert inputLines != null;
        String[] direction = new String[inputLines.length];
        int[] value = new int[inputLines.length];

        for (int i = 0; i < inputLines.length; i++) {
            String[] split = inputLines[i].split(" ");
            direction[i] = split[0];
            value[i] = Integer.parseInt(split[1]);
        }

        int horizontal = 0;
        int depth = 0;
        int aim = 0;
        for (int i = 0; i < direction.length; i++) {
            switch (direction[i]) {
                case "forward":
                    horizontal += value[i];
                    depth += value[i] * aim;
                    break;
                case "up":
                    aim -= value[i];
                    break;
                case "down":
                    aim += value[i];
                    break;
            }
        }
        System.out.println("depth = " + depth);
        System.out.println("horizontal = " + horizontal);
        System.out.println(depth*horizontal);

    }












    public static String[] readInput() {
        try {
            List<String> input = new ArrayList<>();
            File inputFile = new File("input.txt");
//            File inputFile = new File("input_test.txt");
            System.out.println(inputFile.getAbsolutePath());
            Scanner myReader = new Scanner(inputFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                input.add(data);
            }
            myReader.close();
            return input.toArray(new String[0]);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }
}
