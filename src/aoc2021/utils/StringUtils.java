package aoc2021.utils;

import java.util.Arrays;
import java.util.List;

public class StringUtils {

    public static String[] split(String input) {
        return input.split("\\s+");
    }

    public static String sortString(String inputString) {
        char tempArray[] = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    public static int[][] readInput(List<String> input) {
        int rowsize = input.size();
        int colsize = input.get(0).length();
        int matrix[][] = new int[rowsize][colsize];

        for (int row = 0; row < rowsize; row++) {
            for (int col = 0; col < colsize; col++) {
                String rowData = input.get(row);
                char data[] = rowData.toCharArray();
                matrix[row][col] = Character.getNumericValue(data[col]);
            }
        }
        return matrix;
    }
}
