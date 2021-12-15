package aoc2021.Day07;

import aoc2021.utils.FileUtils;
import aoc2021.utils.NumberUtils;

import java.util.Collections;
import java.util.List;

public class FuelCostFinder {

    protected static String inputPath1 = FileUtils.BASE_PATH + "Day7\\input1";
    protected static String inputPath2 = FileUtils.BASE_PATH + "Day7\\input2";

    public static void main(String[] args) {
        List<String> input1 = FileUtils.readInputFile(inputPath1);
        List<String> input2 = FileUtils.readInputFile(inputPath2);

        System.out.println(getFuelCost(input1));
        System.out.println(getFuelCost(input2));
    }

    private static int getFuelCost(List<String> input) {
        List<Integer> inputList = NumberUtils.convertToInt(input.get(0), ",");
        Collections.sort(inputList);

        int mid = inputList.get(inputList.size() / 2);
        return inputList.stream().map(e -> Math.abs(e - mid)).mapToInt(Integer::intValue).sum();
    }

}
