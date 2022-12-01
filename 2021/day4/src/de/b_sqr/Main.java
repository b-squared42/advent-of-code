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

//        day4point1(inputLines);
        day4point2(inputLines);

    }

    private static void day4point1(String[] inputLines) {
        String[] drawn = inputLines[0].split(",");
        List<Board> boards = new ArrayList<>();

        for (int i = 2; i < inputLines.length; i+=6) {
            boards.add(new Board(Arrays.copyOfRange(inputLines, i, i+5)));
        }

        Board winnerBoard = null;
        int finalNumber = 0;
        loop: for (String draw : drawn) {
            for (Board b : boards) {
                if (b.draw(draw)) {
                    winnerBoard = b;
                    System.out.println(winnerBoard);
                    finalNumber = Integer.parseInt(draw);
                    break loop;
                }
            }
        }
        assert winnerBoard != null;
        int sum = winnerBoard.sumUnmarked();
        System.out.println("sum = " + sum);
        System.out.println("finalNumber = " + finalNumber);
        System.out.println(sum * finalNumber);
    }
    private static void day4point2(String[] inputLines) {
        String[] drawn = inputLines[0].split(",");
        List<Board> boards = new ArrayList<>();

        for (int i = 2; i < inputLines.length; i+=6) {
            boards.add(new Board(Arrays.copyOfRange(inputLines, i, i+5)));
        }

        List<Board> boardsWon = new ArrayList<>();
        int finalNumber = 0;
        loop: for (String draw : drawn) {
            for (Board b : boards) {
                if (b.draw(draw)) {
                    if (!boardsWon.contains(b)) {
                        boardsWon.add(b);
                        finalNumber = Integer.parseInt(draw);
                        if (boardsWon.size() == boards.size()) {
                            break loop;
                        }
                    }
                }
            }
        }
        Board lastWonBoard = boardsWon.get(boardsWon.size()-1);
        int sum = lastWonBoard.sumUnmarked();
        System.out.println("sum = " + sum);
        System.out.println("finalNumber = " + finalNumber);
        System.out.println(sum * finalNumber);
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

    public static class Board {
        private final String[][] field = new String[5][5];
        private final boolean[][] drawn = new boolean[5][5];

        public Board(String[] block) {
            for (int i = 0; i < block.length; i++) {
                String[] split = block[i].split(" ");
                List<String> splitted = new ArrayList<>();
                for (String s : split) {
                    if (!s.trim().isEmpty()) {
                        splitted.add(s);
                    }
                }
                field[i] = splitted.toArray(new String[0]);
            }
        }

        public boolean draw(String draw) {
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[0].length; j++) {
                    if (field[i][j].equals(draw)) {
                        drawn[i][j] = true;
                        break;
                    }
                }
            }
            return finished();
        }

        private boolean finished() {
            for (int i = 0; i < drawn.length; i++) {
                if (drawn[i][0] && drawn[i][1] && drawn[i][2] && drawn[i][3] && drawn[i][4]) {
                    return true;
                }
            }
            for (int i = 0; i < drawn[0].length; i++) {
                if (drawn[0][i] && drawn[1][i] && drawn[2][i] && drawn[3][i] && drawn[4][i]) {
                    return true;
                }
            }
            return false;
        }


        @Override
        public String toString() {
            String output = "";
            for (String[] sa : field) {
                for (String s : sa) {
                    output += s + " ";
                }
                output += "\n";
            }
            return output;
        }

        public String toDrawnString() {
            String output = "";
            for (boolean[] sa : drawn) {
                for (boolean s : sa) {
                    output += s + " ";
                }
                output += "\n";
            }
            return output;
        }

        public int sumUnmarked() {
            int sum = 0;
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[0].length; j++) {
                    if (!drawn[i][j])
                        sum += Integer.parseInt(field[i][j]);
                }
            }
            return sum;
        }
    }
}
