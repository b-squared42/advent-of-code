package de.b_sqr;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private final int[][] map;
    private final boolean[][] markedPositions;

    public Field(int[][] map) {
        this.map = map;
        markedPositions = new boolean[map.length][map[0].length];
    }

    public void resetMarkedPositions() {
        for (int x = 0; x < markedPositions.length; x++) {
            for (int y = 0; y < markedPositions[0].length; y++) {
                markedPositions[x][y] = false;
            }
        }
    }
    public void markPosition(int x, int y) {
        markedPositions[x][y] = true;
    }
    public int getAmountOfCurrentlyMarkedPositions() {
        int counter = 0;
        for (boolean[] markedPositionArr : markedPositions) {
            for (boolean markedPosition : markedPositionArr) {
                if (markedPosition) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public Positions getLowLevelPositions() {
        Positions positions = new Positions();
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                if (isLowPoint(x, y)) {
                    positions.addPair(x,y);
                }
            }
        }
        return positions;
    }
    public boolean isLowPoint(int x, int y) {
        if (map[x][y] == 9 || markedPositions[x][y]) {
            return false;
        }
        int[] adjacentLocations = new int[4];
        adjacentLocations[0] = 10;
        adjacentLocations[1] = 10;
        adjacentLocations[2] = 10;
        adjacentLocations[3] = 10;

        if (x > 0) adjacentLocations[0] = markedPositions[x-1][y] ? 10 : map[x-1][y];
        if (y < map[0].length-1) adjacentLocations[1] = markedPositions[x][y+1] ? 10 : map[x][y+1];
        if (x < map.length-1) adjacentLocations[2] = markedPositions[x+1][y] ? 10 : map[x+1][y];
        if (y > 0) adjacentLocations[3] = markedPositions[x][y-1] ? 10 : map[x][y-1];

        return map[x][y] < adjacentLocations[0]
                && map[x][y] < adjacentLocations[1]
                && map[x][y] < adjacentLocations[2]
                && map[x][y] < adjacentLocations[3];
    }

    public int getWidth() {
        return map[0].length;
    }
    public int getHeight() {
        return map.length;
    }
    public void mdPrint() {
        String output = "";
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                if (markedPositions[x][y]) {
                    output += "***"+map[x][y]+"***";
                } else {
                    output += map[x][y];
                }
            }
            output += "<br>\n";
        }
        System.out.println(output);
    }

    public static class Positions {
        private final List<Pair<Integer, Integer>> list = new ArrayList<>();

        public void addPair(int x, int y) {
            Pair<Integer, Integer> tempPair = new Pair<>(x,y);
            for (Pair<Integer, Integer> p : list) {
                if (p.equals(tempPair)) {
                    return;
                }
            }
            list.add(tempPair);
        }

        public void add(Positions pos) {
            for (Pair<Integer, Integer> pair : pos.getList()) {
                addPair(pair.getKey(), pair.getValue());
            }
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

        public List<Pair<Integer, Integer>> getList() {
            return list;
        }
    }
}
