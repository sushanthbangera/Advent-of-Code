package aoc2021.Day9;

import aoc2020.utils.FileUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LargestBasinSizeFinder extends BasinLowPointFinder {

    public static void main(String[] args) {
        List<String> input1 = FileUtils.readInputFile(inputPath1);
        List<String> input2 = FileUtils.readInputFile(inputPath2);

        System.out.println(getLargestBasinsSum(input1));
        System.out.println(getLargestBasinsSum(input2));
    }

    private static int getLargestBasinsSum(List<String> input) {
        int result = 1;
        int heightMap[][] = readInput(input);

        List<Integer> list = connectedCell(heightMap);
        Collections.sort(list);

        for (int i = list.size() - 1; i > list.size() - 4; i--) {
            result *= list.get(i);
        }
        return result;
    }

    public static List<Integer> connectedCell(int matrix[][]) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        Result result = new Result();
        List<Integer> finalResult = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                bfs(matrix, i, j, result);
                finalResult.add(result.value);
                result.value = 0;
            }
        }
        return finalResult;
    }

    private static void bfs(int matrix[][], int row, int col, Result result) {
        if (row >= 0 && col >= 0 && row < matrix.length && col < matrix[0].length && matrix[row][col] != 9) {
            matrix[row][col] = 9;
            result.value++;
            int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            for (int i = 0; i < moves.length; i++) {
                int r = row + moves[i][0];
                int c = col + moves[i][1];
                bfs(matrix, r, c, result);
            }
        }
        return;
    }

   static class Result {
        int value;
    }
}
