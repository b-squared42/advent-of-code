package de.b_sqr;

import javafx.util.Pair;

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
//        day9point2(heightmap);
        day9point2point2(heightmap);

    }

    private static void day9point2point2(int[][] heightmap) {
        Field field = new Field(heightmap);

        List<Integer> basinSizes = new ArrayList<>();
        Field.Positions lowPoints = field.getLowLevelPositions();
        for (Pair<Integer, Integer> originalLowPointsPair : lowPoints.getList()) {
            List<Pair<Integer, Integer>> forPairs = new ArrayList<>();
            forPairs.add(originalLowPointsPair);

            boolean somethingFound = true;
            while (somethingFound) {
                Field.Positions foundPositions = new Field.Positions();
                for (Pair<Integer, Integer> innerForPair : forPairs) {
                    int x = innerForPair.getKey(), y = innerForPair.getValue();
                    field.markPosition(x, y);
                    foundPositions.add(getSurroundingLowPoints(field, innerForPair));
                }
                somethingFound = !foundPositions.getList().isEmpty();
                forPairs = foundPositions.getList();
            }
            basinSizes.add(field.getAmountOfCurrentlyMarkedPositions());
            field.mdPrint();
            field.resetMarkedPositions();
        }
        basinSizes.sort(Comparator.reverseOrder());
        System.out.println(Arrays.toString(basinSizes.toArray()));
        System.out.println(Arrays.toString(basinSizes.subList(0,3).toArray()));
        int output = basinSizes.get(0) * basinSizes.get(1) * basinSizes.get(2);
        System.out.println("output = " + output);

    }

    private static void day9point1(int[][] heightmap) {
        Findings f = new Findings();
        for (int x = 0; x < heightmap.length; x++) {
            for (int y = 0; y < heightmap[0].length; y++) {
                if (isLowPoint(heightmap, x, y)) {
                    f.addPair(x,y);
                }
            }
        }
        int sum = 0;
        for (Pair<Integer, Integer> p : f.list) {
            sum += (heightmap[p.getKey()][p.getValue()]+1);
        }
        System.out.println("sum = " + sum);
    }
    private static void day9point2(int[][] heightmap) {
        List<Integer> list = new ArrayList<>();
        Findings f = new Findings();
        for (int x = 0; x < heightmap.length; x++) {
            for (int y = 0; y < heightmap[0].length; y++) {
                if (isLowPoint(heightmap, x, y)) {
                    f.addPair(x, y);
                }
            }
        }
        for (Pair<Integer, Integer> rootpair : f.list) {
            int x = rootpair.getKey(), y = rootpair.getValue();
            if (isLowPoint(heightmap, x, y)) {
//                    System.out.printf("(%1$d, %2$d) = %3$d\n", x, y, heightmap[x][y]);
                Findings findings = new Findings();
                findings.addPair(x, y);
                int[][] heightmapCOPY = deepCopyDoubleArray(heightmap);
                int counter = 0;
                do {
                    counter += findings.list.size();
                    findings.maxHeightMap(heightmapCOPY);
                    Findings b = findings;
                    findings = new Findings();
                    for (Pair<Integer, Integer> pair : b.list) {
                        findings = basin(heightmapCOPY, pair.getKey(), pair.getValue(), 0, findings);
                    }
                } while (!findings.list.isEmpty());
                list.add(counter);
            }
        }

        list.sort(Comparator.reverseOrder());
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println("list.size() = " + list.size());
        list = list.subList(0, 3);
        int result = list.get(0) * list.get(1) * list.get(2);
        System.out.println(result);
    }

    /**
     *
     * @param dir 0 = top | 1 = right | 2 = bottom | 3 = left
     */
    public static Findings basin(int[][] heightmap, int x, int y, int dir, Findings findings) {
        int upOrDown = 0, leftOrRight = 0;
        if (dir % 2 == 0) {
            upOrDown = dir == 0 ? -1 : +1;
        } else {
            leftOrRight = dir == 1 ? +1 : -1;
        }
        if (x + upOrDown > -1
                && x + upOrDown < heightmap.length
                && y + leftOrRight < heightmap[0].length
                && y + leftOrRight > -1
                && heightmap[x + upOrDown][y + leftOrRight] != 9
                && isLowPoint(heightmap, x + upOrDown, y + leftOrRight)
        ) {
            findings.addPair(x+upOrDown, y+leftOrRight);
        }

        if (dir != 3) {
            return basin(heightmap, x, y, ++dir, findings);
        }
        return findings;
    }

    public static Field.Positions getSurroundingLowPoints(Field f, Pair<Integer, Integer> origPair) {
        Field.Positions positions = new Field.Positions();
        int x = origPair.getKey(), y = origPair.getValue();
        if ((x - 1) >= 0 && f.isLowPoint(x - 1, y)) {
            positions.addPair(x - 1, y);
        }
        if ((y + 1) < f.getWidth() && f.isLowPoint(x, y + 1)) {
            positions.addPair(x, y + 1);
        }
        if ((x + 1) < f.getHeight() && f.isLowPoint(x + 1, y)) {
            positions.addPair(x + 1, y);
        }
        if ((y - 1) >= 0 && f.isLowPoint(x, y - 1)) {
            positions.addPair(x, y - 1);
        }
        return positions;
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

    public static int[][] deepCopyDoubleArray(int[][] array) {
        int[][] output = new int[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, output[i], 0, array[0].length);
        }
        return output;
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

    public static class Findings {
        List<Pair<Integer, Integer>> list = new ArrayList<>();

        public void addPair(int x, int y) {
            Pair<Integer, Integer> tempPair = new Pair<>(x,y);
            for (Pair<Integer, Integer> p : list) {
                if (p.equals(tempPair)) {
                    return;
                }
            }
            list.add(tempPair);
        }

        public void maxHeightMap(int[][] heightmapCOPY) {
            for (Pair<Integer, Integer> p : list) {
                heightmapCOPY[p.getKey()][p.getValue()] = 9;
            }
        }

        @Override
        public String toString() {
            String output = "[";
            for (Pair<Integer, Integer> p : list) {
                output += String.format("(%1$d, %2$d), ", p.getKey(), p.getValue());
            }
            return output + "]";
        }
    }
}
