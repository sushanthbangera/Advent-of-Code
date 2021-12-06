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

}
