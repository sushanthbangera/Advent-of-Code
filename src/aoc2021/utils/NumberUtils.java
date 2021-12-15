package aoc2021.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NumberUtils {

    public static List<Integer> convertToInt(List<String> input) {
        return input.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    public static List<Integer> convertToInt(String input, String splitRegex) {
        String[] inputArray = input.split(splitRegex);
        return Arrays.stream(inputArray).map(Integer::parseInt).collect(Collectors.toList());
    }

    public static void print(int matrix[][]) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

}
