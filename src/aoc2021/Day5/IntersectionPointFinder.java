package aoc2021.Day5;

import aoc2020.utils.FileUtils;
import aoc2021.utils.StringUtils;

import java.util.List;

public class IntersectionPointFinder {

    protected static String inputPath1 = "C:\\MyDrive\\Study\\My_Github_Repo\\Advent-of-Code\\src\\aoc2021\\Day5\\input1";
    protected static String inputPath2 = "C:\\MyDrive\\Study\\My_Github_Repo\\Advent-of-Code\\src\\aoc2021\\Day5\\input2";

    protected static int matrix[][];

    public static void main(String[] args) {
        List<String> input1 = FileUtils.readInputFile(inputPath1);
        List<String> input2 = FileUtils.readInputFile(inputPath2);

        matrix = new int[10][10];
        System.out.println(getTotalIntersectingPoints(input1));
        matrix = new int[1000][1000];
        System.out.println(getTotalIntersectingPoints(input2));
    }

    public static int getTotalIntersectingPoints(List<String> input) {
        for (String in : input) {
            int coordinates[][] = getCoordinates(in);
            int x1 = coordinates[0][0], y1 = coordinates[0][1];
            int x2 = coordinates[1][0], y2 = coordinates[1][1];

            if (x1 == x2) {
                plotHorizontal(x1, y1, y2);
            } else if (y1 == y2) {
                plotVertical(x1, y1, x2);
            }
        }
        return findOverlappingCount();
    }

    protected static int findOverlappingCount() {
        int count = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                if (matrix[row][col] >= 2) {
                    count++;
                }
            }
        }
        return count;
    }

    protected static void print() {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    protected static void plotVertical(int x1, int y1, int x2) {
        if (x2 > x1) {
            for (int row = x1; row <= x2; row++) {
                matrix[row][y1]++;
            }
        } else {
            for (int row = x2; row <= x1; row++) {
                matrix[row][y1]++;
            }
        }
    }

    protected static void plotHorizontal(int x1, int y1, int y2) {
        if (y2 > y1) {
            for (int col = y1; col <= y2; col++) {
                matrix[x1][col]++;
            }
        } else {
            for (int col = y2; col <= y1; col++) {
                matrix[x1][col]++;
            }
        }
    }

    protected static int[][] getCoordinates(String input) {
        //x1,y1 -> x2,y2
        String[] coordinatesString = StringUtils.split(input);
        String[] first = coordinatesString[0].split(",");
        String[] second = coordinatesString[2].split(",");

        int coordinates[][] = {{Integer.parseInt(first[0]), Integer.parseInt(first[1])}, {Integer.parseInt(
                second[0]), Integer.parseInt(second[1])}};
        return coordinates;
    }

}
