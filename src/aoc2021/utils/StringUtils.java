package aoc2021.utils;

import java.util.Arrays;

public class StringUtils {

    public static String[] split(String input) {
        return input.split("\\s+");
    }

    public static String sortString(String inputString) {
        char tempArray[] = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }
}
