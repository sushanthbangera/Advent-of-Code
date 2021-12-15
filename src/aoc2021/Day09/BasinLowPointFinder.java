package aoc2021.Day09;

import aoc2021.utils.FileUtils;
import aoc2021.utils.StringUtils;

import java.util.List;

public class BasinLowPointFinder {

    protected static String inputPath1 = FileUtils.BASE_PATH + "Day9\\input1";
    protected static String inputPath2 = FileUtils.BASE_PATH + "Day9\\input2";

    public static void main(String[] args) {
        List<String> input1 = FileUtils.readInputFile(inputPath1);
        List<String> input2 = FileUtils.readInputFile(inputPath2);

        System.out.println(getLowerBasinSums(input1));
        System.out.println(getLowerBasinSums(input2));
    }

    private static int getLowerBasinSums(List<String> input) {
        int result = 0;
        int heightMap[][] = StringUtils.readInput(input);

        for (int row = 0; row < heightMap.length; row++) {
            for (int col = 0; col < heightMap[row].length; col++) {
                boolean isLow = checkLowLevel(heightMap, row, col);
                if (isLow) {
                    result += (1 + heightMap[row][col]);
                }
            }
        }
        return result;
    }

    private static boolean checkLowLevel(int matrix[][], int row, int col) {
        int moves[][] = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        for (int i = 0; i < moves.length; i++) {
            int x = row + moves[i][0];
            int y = col + moves[i][1];
            if (x >= 0 && y >= 0 && x < matrix.length && y < matrix[0].length && matrix[row][col] >= matrix[x][y]) {
                return false;
            }
        }
        return true;
    }



}
