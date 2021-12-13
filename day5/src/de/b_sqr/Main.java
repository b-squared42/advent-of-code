package de.b_sqr;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[] inputLines = readInput();
        assert inputLines != null;
//        day5point1(inputLines);
        day5point2(inputLines);

    }

    private static void day5point1(String[] inputLines) {
        List<int[]> from = new ArrayList<>();
        List<int[]> to = new ArrayList<>();
        for (String inputLine : inputLines) {
            String[] split = inputLine.split(" -> ");
            String[] fromString = split[0].split(",");
            String[] toString = split[1].split(",");
            from.add(new int[]{Integer.parseInt(fromString[0]), Integer.parseInt(fromString[1])});
            to.add(new int[]{Integer.parseInt(toString[0]), Integer.parseInt(toString[1])});
        }
        int biggestX = 0;
        int biggestY = 0;
        for (int i = 0; i < from.size(); i++) {
            if (biggestX < from.get(i)[0]) {
                biggestX = from.get(i)[0];
            }
            if (biggestX < to.get(i)[0]) {
                biggestX = to.get(i)[0];
            }
            if (biggestY < from.get(i)[1]) {
                biggestY = from.get(i)[1];
            }
            if (biggestY < to.get(i)[1]) {
                biggestY = to.get(i)[1];
            }
        }

        int[][] field = new int[biggestX+1][biggestY+1];
        for (int i = 0; i < from.size(); i++) {
            int x1 = from.get(i)[0];
            int y1 = from.get(i)[1];
            int x2 = to.get(i)[0];
            int y2 = to.get(i)[1];
            if (x1 == x2) {
                for (int y = 0; y < Math.abs(y1-y2)+1; y++) {
                    field[x1][Math.min(y1, y2)+y] += 1;
                }
            } else if (y1 == y2) {
                for (int x = 0; x < Math.abs(x1-x2)+1; x++) {
                    field[Math.min(x1, x2)+x][y1] += 1;
                }
            }
            System.out.printf("(%d,%d) -> (%d,%d)%n",x1,y1,x2,y2);
//            printDoubleArray(field);
        }
        int cnt = 0;
        for (int y = 0; y < field[0].length; y++) {
            for (int x = 0; x < field.length; x++) {
                if (field[x][y] > 1) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    private static void printDoubleArray(int[][] field) {
        for (int y = 0; y < field[0].length; y++) {
            for (int x = 0; x < field.length; x++) {
                if (field[x][y] == 0) {
                    System.out.print(".");
                } else
                    System.out.print(field[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void day5point2(String[] inputLines) {
        List<int[]> from = new ArrayList<>();
        List<int[]> to = new ArrayList<>();
        for (String inputLine : inputLines) {
            String[] split = inputLine.split(" -> ");
            String[] fromString = split[0].split(",");
            String[] toString = split[1].split(",");
            from.add(new int[]{Integer.parseInt(fromString[0]), Integer.parseInt(fromString[1])});
            to.add(new int[]{Integer.parseInt(toString[0]), Integer.parseInt(toString[1])});
        }
        int biggestX = 0;
        int biggestY = 0;
        for (int i = 0; i < from.size(); i++) {
            if (biggestX < from.get(i)[0]) {
                biggestX = from.get(i)[0];
            }
            if (biggestX < to.get(i)[0]) {
                biggestX = to.get(i)[0];
            }
            if (biggestY < from.get(i)[1]) {
                biggestY = from.get(i)[1];
            }
            if (biggestY < to.get(i)[1]) {
                biggestY = to.get(i)[1];
            }
        }

        int[][] field = new int[biggestX+1][biggestY+1];
        for (int i = 0; i < from.size(); i++) {
            int x1 = from.get(i)[0];
            int y1 = from.get(i)[1];
            int x2 = to.get(i)[0];
            int y2 = to.get(i)[1];
            if (x1 == x2) {
                for (int y = 0; y < Math.abs(y1-y2)+1; y++) {
                    field[x1][Math.min(y1, y2)+y] += 1;
                }
            } else if (y1 == y2) {
                for (int x = 0; x < Math.abs(x1-x2)+1; x++) {
                    field[Math.min(x1, x2)+x][y1] += 1;
                }
            } else {
                for (int p = 0; p < Math.abs(x1-x2)+1; p++) {
                    if ((x1 > x2 && y1 > y2) || (x1 < x2 && y1 < y2)) {
                        field[Math.min(x1, x2)+p][Math.min(y1, y2)+p] += 1;
                    } else if (x1 > x2 && y1 < y2) {
                        field[x1-p][y1+p] += 1;
                    } else if (x1 < x2 && y1 > y2) {
                        field[x2-p][y2+p] += 1;
                    }
                }
            }
            System.out.printf("(%d,%d) -> (%d,%d)%n",x1,y1,x2,y2);
//            printDoubleArray(field);
        }
        int cnt = 0;
        for (int y = 0; y < field[0].length; y++) {
            for (int x = 0; x < field.length; x++) {
                if (field[x][y] > 1) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
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
