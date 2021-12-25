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
        int[] inputValues = new int[inputLines.length];
        for (int i = 0; i < inputLines.length; i++) {
            inputValues[i] = Integer.parseInt(inputLines[i]);
        }
        int count = 0;
        int lastGroupsValue = Integer.MAX_VALUE;
        for (int i = 2; i < inputValues.length; i++) {
            int newGroupValue = inputValues[i] + inputValues[i-1] + inputValues[i-2];
            if (newGroupValue > lastGroupsValue) {
                count++;
            }
            lastGroupsValue = newGroupValue;
        }
        System.out.println(count);
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
