package aoc2021.Day05;

import aoc2020.utils.FileUtils;

import java.util.List;

public class IntersectionAllPointsFinder extends IntersectionPointFinder {

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
            } else {
                plotDiagonal(x1, y1, x2, y2);
            }
        }
        return findOverlappingCount();
    }

    private static void plotDiagonal(int x1, int y1, int x2, int y2) {
        // check in 4 directions
        if (x1 > x2 && y1 > y2) {
            int row = x2;
            for (int col = y2; col <= y1; col++, row++) {
                matrix[row][col] += 1;
            }
        } else if (x1 > x2 && y1 < y2) {
            int row = x2;
            for (int col = y2; col >= y1; col--, row++) {
                matrix[row][col] += 1;
            }
        } else if (x1 < x2 && y1 > y2) {
            int row = x2;
            for (int col = y2; col <= y1; col++, row--) {
                matrix[row][col] += 1;
            }
        } else if (x1 < x2 && y1 < y2) {
            int row = x2;
            for (int col = y2; col >= y1; col--, row--) {
                matrix[row][col] += 1;
            }
        }
    }

}
