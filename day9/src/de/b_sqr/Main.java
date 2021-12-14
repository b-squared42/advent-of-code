package de.b_sqr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[] inputLines = readInput();
        assert inputLines != null;

        int[][] heightmap = new int[inputLines.length][inputLines[0].length()];
        for (int x = 0; x < heightmap.length; x++) {
            for (int y = 0; y < heightmap[0].length; y++) {
                heightmap[x][y] = Integer.parseInt(inputLines[x].substring(y, y+1));
            }
        }

//        day9point1(heightmap);
        day9point2(heightmap);

    }

    private static void day9point1(int[][] heightmap) {
        List<Integer> list = new ArrayList<>();
        for (int x = 0; x < heightmap.length; x++) {
            for (int y = 0; y < heightmap[0].length; y++) {
                if (isLowPoint(heightmap, x, y)) {
                    list.add(heightmap[x][y]);
                }
            }
        }
        int sum = 0;
        for (Integer i : list) {
            sum += (i+1);
        }
        System.out.println("sum = " + sum);
    }
    private static void day9point2(int[][] heightmap) {
        for (int x = 0; x < heightmap.length; x++) {
            for (int y = 0; y < heightmap[0].length; y++) {
                if (isLowPoint(heightmap, x, y)) {
                    int counter = 0;
                    counter += basin(heightmap, x, y, 0);
                    counter += basin(heightmap, x, y, 1);
                    counter += basin(heightmap, x, y, 2);
                    counter += basin(heightmap, x, y, 3);
                    System.out.println(counter);
                }
            }
        }
    }

    /**
     *
     * @param dir 0 = top | 1 = right | 2 = bottom | 3 = left
     */
    public static int basin(int[][] heightmap, int x, int y, int dir) {
        int counter = 0;
        if (dir % 2 == 0) {
            int upOrDown = dir == 0 ? -1 : +1;
            if (x+upOrDown > 0 && x+upOrDown < heightmap.length-1 && heightmap[x+upOrDown][y] != 9 && isLowPoint(heightmap, x+upOrDown, y)) {
                int[][] heightmapCOPY = Arrays.copyOf(heightmap, heightmap.length);
                heightmapCOPY[x][y+1] = 9;
                counter += 1 + basin(heightmapCOPY,x+upOrDown, y, 0);
                counter += basin(heightmapCOPY,x+upOrDown, y, 1);
                counter += basin(heightmapCOPY,x+upOrDown, y, 2);
                counter += basin(heightmapCOPY,x+upOrDown, y, 3);
            }
        } else {
            int leftOrRight = dir == 1 ? +1 : -1;
            if (y+leftOrRight < heightmap[0].length-1 && y+leftOrRight > 0 && heightmap[x][y+leftOrRight] != 9 && isLowPoint(heightmap, x, y+leftOrRight)) {
                int[][] heightmapCOPY = Arrays.copyOf(heightmap, heightmap.length);
                heightmapCOPY[x][y+1] = 9;
                counter += 1 + basin(heightmapCOPY,x, y+leftOrRight, 0);
                counter += basin(heightmapCOPY,x, y+leftOrRight, 1);
                counter += basin(heightmapCOPY,x, y+leftOrRight, 2);
                counter += basin(heightmapCOPY,x, y+leftOrRight, 3);
            }
        }
        return counter;
    }

    public static boolean isLowPoint(int[][] heightmap, int x, int y) {
        int[] adjacentLocations = new int[4];
        adjacentLocations[0] = 10;
        adjacentLocations[1] = 10;
        adjacentLocations[2] = 10;
        adjacentLocations[3] = 10;

        if (x > 0) adjacentLocations[0] = heightmap[x-1][y];
        if (y < heightmap[0].length-1) adjacentLocations[1] = heightmap[x][y+1];
        if (x < heightmap.length-1) adjacentLocations[2] = heightmap[x+1][y];
        if (y > 0) adjacentLocations[3] = heightmap[x][y-1];

        return heightmap[x][y] < adjacentLocations[0]
                && heightmap[x][y] < adjacentLocations[1]
                && heightmap[x][y] < adjacentLocations[2]
                && heightmap[x][y] < adjacentLocations[3];
    }

    public static void printDoubleArray(int[][] array) {
        String output = "";
        for (int[] ia : array) {
            for (int i: ia) {
                output += String.valueOf(i);
            }
            output += "\n";
        }
        System.out.println(output);
    }

    public static String[] readInput() {
        try {
            List<String> input = new ArrayList<>();
            File inputFile = new File("input_test.txt");
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
