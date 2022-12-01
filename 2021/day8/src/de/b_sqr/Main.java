package de.b_sqr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[] inputLines = readInput();
        assert inputLines != null;
//        day8point1(inputLines);
        day8point2(inputLines);

    }

    private static void day8point1(String[] inputLines) {
        String[][] signalPatterns = new String[inputLines.length][10];
        String[][] outputValues = new String[inputLines.length][4];
        for (int i = 0; i < inputLines.length; i++) {
            String[] split = inputLines[i].trim().split("\\|");
            signalPatterns[i] = split[0].trim().split(" ");
            outputValues[i] = split[1].trim().split(" ");
        }

        int counter = 0;
        for (int i = 0; i < signalPatterns.length; i++) {
            Map<String, String> dic = new HashMap<>();
            String[] pattern = signalPatterns[i];
            String[] values = outputValues[i];
            dic.put("a", getA(pattern));
            dic.put("c", getC(pattern));
            dic.put("d", getD(pattern));
            dic.put("e", getE(pattern));
            dic.put("f", getF(pattern, dic.get("c")));
            dic.put("g", getG(pattern, dic.get("a"), dic.get("c"), dic.get("d"), dic.get("f")));
            String acdefg = dic.get("a")+dic.get("c")+dic.get("d")+dic.get("e")+dic.get("f")+dic.get("g");
            dic.put("b", Utils.getDifLettersUniqueInA("abcdefg", acdefg)[0]);


            for (String s : outputValues[i]) {
                if (Utils.getDigit(s, dic).contains("1")
                        || Utils.getDigit(s, dic).contains("4")
                        || Utils.getDigit(s, dic).contains("7")
                        || Utils.getDigit(s, dic).contains("8")
                ) {
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }
    private static void day8point2(String[] inputLines) {
        String[][] signalPatterns = new String[inputLines.length][10];
        String[][] outputValues = new String[inputLines.length][4];
        for (int i = 0; i < inputLines.length; i++) {
            String[] split = inputLines[i].trim().split("\\|");
            signalPatterns[i] = split[0].trim().split(" ");
            outputValues[i] = split[1].trim().split(" ");
        }

        int counter = 0;
        for (int i = 0; i < signalPatterns.length; i++) {
            Map<String, String> dic = new HashMap<>();
            String[] pattern = signalPatterns[i];
            String[] values = outputValues[i];
            dic.put("a", getA(pattern));
            dic.put("c", getC(pattern));
            dic.put("d", getD(pattern));
            dic.put("e", getE(pattern));
            dic.put("f", getF(pattern, dic.get("c")));
            dic.put("g", getG(pattern, dic.get("a"), dic.get("c"), dic.get("d"), dic.get("f")));
            String acdefg = dic.get("a")+dic.get("c")+dic.get("d")+dic.get("e")+dic.get("f")+dic.get("g");
            dic.put("b", Utils.getDifLettersUniqueInA("abcdefg", acdefg)[0]);

            String output = "";
            for (String s : outputValues[i]) {
                output += Utils.getDigit(s, dic);
            }
            counter += Integer.parseInt(output);
        }
        System.out.println(counter);
    }


    private static String getA(String[] pattern) {
        String one = Utils.getStringsWithLength(pattern, 2)[0];
        String seven = Utils.getStringsWithLength(pattern, 3)[0];
        return Utils.getDifLettersUniqueInA(seven, one)[0];
    }
    public static String getC(String[] pattern) {
        String one = Utils.getStringsWithLength(pattern, 2)[0];
        return Utils.getDifLettersUniqueInA(one, Utils.get6(pattern))[0];
    }
    public static String getF(String[] pattern, String c) {
        String one = Utils.getStringsWithLength(pattern, 2)[0];
        return Utils.getDifLettersUniqueInA(one, c)[0];
    }
    public static String getD(String[] pattern) {
        String four = Utils.getStringsWithLength(pattern, 4)[0];
        String six = Utils.get6(pattern);
        String[] stringsWithLength6 = Utils.getStringsWithLength(pattern, 6);
        stringsWithLength6 = Utils.removeFromArray(stringsWithLength6, six);
        for (String s : stringsWithLength6) {
            if (Utils.getDifLettersUniqueInA(four, s).length != 0) {
                return Utils.getDifLettersUniqueInA(four, s)[0];
            }
        }
        return "";
    }
    public static String getG(String[] pattern, String a, String c, String d, String f) {
        String string = a+c+d+f;
        return Utils.getDifLettersUniqueInA(Utils.get3(pattern), string)[0];
    }
    public static String getE(String[] pattern) {
        String three = Utils.get3(pattern);
        String four = Utils.getStringsWithLength(pattern, 4)[0];
        String eight = Utils.getStringsWithLength(pattern, 7)[0];
        String threeAndFour = three + four;
        return Utils.getDifLettersUniqueInA(eight, threeAndFour)[0];
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
