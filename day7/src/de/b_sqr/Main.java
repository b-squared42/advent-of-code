package de.b_sqr;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[] inputLines = readInput();
        assert inputLines != null;
//        day7point1(inputLines);
        day7point2(inputLines);

    }

    private static void day7point1(String[] inputLines) {
        String[] inputStr = inputLines[0].split(",");
        int[] input = new int[inputStr.length];
        for (int i = 0; i < input.length; i++) {
            input[i] = Integer.parseInt(inputStr[i]);
        }

        int median = getMedian(input);

        int steps = 0;
        for (int i : input) {
            if (median > i) {
                steps += median-i;
            } else {
                steps += i-median;
            }
        }

        System.out.println(steps);


    }
    private static void day7point2(String[] inputLines) {
        String[] inputStr = inputLines[0].split(",");
        int[] input = new int[inputStr.length];
        for (int i = 0; i < input.length; i++) {
            input[i] = Integer.parseInt(inputStr[i]);
        }

        int max = Arrays.stream(input).max().getAsInt();
        int min = Arrays.stream(input).min().getAsInt();
        double mid = (double)max/2 - (double)min/2;
        System.out.println("max = " + max);
        System.out.println("min = " + min);
        System.out.println("mid = " + mid);

        List<Integer> range = fillList(min, max);
        int minSteps = Integer.MAX_VALUE;
        int minStepsI = 0;
        int cnt = 0;
        for (int posTest : range) {
            System.out.println("Durchlauf " + ++cnt + " von " + range.size());
            int steps = 0;
            for (int inp : input) {
                if (posTest > inp) {
                    steps += littleGauss(posTest-inp);
                } else {
                    steps += littleGauss(inp-posTest);
                }
            }
            if (steps < minSteps) {
                minSteps = steps;
                minStepsI = posTest;
            }
        }

        System.out.println("minSteps = " + minSteps);
        System.out.println("minStepsI = " + minStepsI);
    }

    private static List<Integer> fillList(int min, int max) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < max - min; i++) {
            list.add(min+i);
        }
        return list;
    }

    public static int littleGauss(int number) {
        return (number * number + number)/2;
    }

    public static int getMedian(int[] input) {
        Arrays.sort(input);
        System.out.println(Arrays.toString(input));
        int median;
        if (input.length % 2 == 1) {
            int i = (int) Math.ceil((double)input.length/2);
            median = input[i-1];
        } else {
            int i = input.length/2;
            median = (input[i-1] + input[i]) / 2;
        }
        return median;
    }

    public static String[] readInput() {
        try {
            List<String> input = new ArrayList<>();
            File inputFile = new File("input.txt");
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
