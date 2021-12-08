package aoc2021.Day1;

import aoc2020.utils.FileUtils;
import aoc2021.utils.NumberUtils;

import java.util.List;

public class DepthIncreaseFinder {

    private static String inputPath1 = "C:\\MyDrive\\Study\\My_Github_Repo\\Advent-of-Code\\src\\aoc2021\\Day1\\input1";
    private static String inputPath2 = "C:\\MyDrive\\Study\\My_Github_Repo\\Advent-of-Code\\src\\aoc2021\\Day1\\input2";

    public static void main(String[] args) {
        List<String> input1 = FileUtils.readInputFile(inputPath1);
        System.out.println(getIncreasedWindowDepthCount(input1));

        List<String> input2 = FileUtils.readInputFile(inputPath2);
        System.out.println(getIncreasedDepthCount(input2));
        System.out.println(getIncreasedWindowDepthCount(input2));
    }

    public static int getIncreasedDepthCount(List<String> input) {
        List<Integer> depths = NumberUtils.convertToInt(input);
        int result = 0;

        for (int i = 1; i < depths.size(); i++) {
            if (depths.get(i) > depths.get(i - 1)) {
                result++;
            }
        }
        return result;
    }

    public static int getIncreasedWindowDepthCount(List<String> input) {
        List<Integer> depths = NumberUtils.convertToInt(input);
        int result = 0;
        int prev = -1;
        int currentSum;
        for (int i = 0; i < depths.size() - 3; i++) {
            currentSum = 0;
            for (int j = i; j < i + 3; j++) {
                currentSum += depths.get(j);
            }
            if (currentSum > prev) {
                result++;
            }
            prev = currentSum;
        }
        return result;
    }

}
