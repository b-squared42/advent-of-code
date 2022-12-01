package de.b_sqr;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[] inputLines = readInput();
        assert inputLines != null;
        day6point1(inputLines);
//        day6point2(inputLines);

    }

    private static void day6point1(String[] inputLines) {
        String[] inputStr = inputLines[0].split(",");
        int[] input = new int[inputStr.length];
        for (int i = 0; i < input.length; i++) {
            input[i] = Integer.parseInt(inputStr[i]);
        }

        double[] buckets = new double[9];
        for (int i : input) {
            buckets[i]++;
        }

        for (int day = 0; day < 256; day++) {
            double temp = buckets[0];
            buckets[0] = buckets[1];
            buckets[1] = buckets[2];
            buckets[2] = buckets[3];
            buckets[3] = buckets[4];
            buckets[4] = buckets[5];
            buckets[5] = buckets[6];
            buckets[6] = buckets[7] + temp;
            buckets[7] = buckets[8];
            buckets[8] = temp;
        }

        double count = 0;
        for (double bucket : buckets) {
            count += bucket;
        }
        System.out.println(count);
        System.out.print(new BigDecimal(count).toPlainString());
    }
    private static void day6point2(String[] inputLines) {

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
