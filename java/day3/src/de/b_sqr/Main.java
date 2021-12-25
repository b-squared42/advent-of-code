package de.b_sqr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String[] inputLines = readInput();
        assert inputLines != null;

        int length = inputLines[0].length();

        String[] oxyBinaries = inputLines;
        String[] co2Binaries = inputLines;
        for (int i = 0; i < length; i++) {
            if (oxyBinaries.length > 1) {
                int oxy1Counter = get1Counter(oxyBinaries, i);
                char oxyMostCommonValue;
                if (oxyBinaries.length - oxy1Counter <= oxy1Counter) {
                    oxyMostCommonValue = '1';
                } else {
                    oxyMostCommonValue = '0';
                }
                oxyBinaries = getStringsWithValueOnPosition(oxyBinaries, oxyMostCommonValue, i);
            }

            if (co2Binaries.length > 1) {
                int co21Counter = get1Counter(co2Binaries, i);
                char co2LeastCommonValue;
                if (co2Binaries.length - co21Counter <= co21Counter) {
                    co2LeastCommonValue = '0';
                } else {
                    co2LeastCommonValue = '1';
                }
                System.out.println("i = " + i);
                System.out.println("co2LeastCommonValue = " + co2LeastCommonValue);
                co2Binaries = getStringsWithValueOnPosition(co2Binaries, co2LeastCommonValue, i);
            }
        }

        System.out.println(oxyBinaries.length);
        System.out.println(co2Binaries.length);

        int oxy = Integer.parseInt(oxyBinaries[0], 2);
        int co2 = Integer.parseInt(co2Binaries[0], 2);

        System.out.println(oxy * co2);




    }

    public static String[] getStringsWithValueOnPosition(String[] strings, char value, int position) {
        List<String> output = new ArrayList<>();
        for (String s : strings) {
            if (s.charAt(position) == value) {
                output.add(s);
            }
        }
        return output.toArray(new String[0]);
    }

    public static int get1Counter(String[] strings, int position) {
        int counter = 0;
        for (String s : strings) {
            if (s.charAt(position) == '1') {
                counter++;
            }
        }
        return counter;
    }










    public static String[] readInput() {
        try {
            List<String> input = new ArrayList<>();
            File inputFile = new File("input.txt");
//            File inputFile = new File("input.txt");
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
