package aoc2021.Day7;

import aoc2020.utils.FileUtils;
import aoc2021.utils.NumberUtils;

import java.util.Collections;
import java.util.List;

public class FuelCostFinderPart2 extends FuelCostFinder {

    public static void main(String[] args) {
        List<String> input1 = FileUtils.readInputFile(inputPath1);
        List<String> input2 = FileUtils.readInputFile(inputPath2);

        System.out.println(getFuelCost(input1));
        System.out.println(getFuelCost(input2));
    }

    private static int getFuelCost(List<String> input) {
        List<Integer> inputList = NumberUtils.convertToInt(input.get(0), ",");
        Collections.sort(inputList);

        int startPosition = inputList.get(0);
        int endPosition = inputList.get(inputList.size() - 1);
        int minFuelCost = Integer.MAX_VALUE;

        for (int pos = startPosition; pos <= endPosition; pos++) {
            int fuelCost = 0;
            for (Integer crab : inputList) {
                fuelCost += totalCost(crab, pos);
            }
            minFuelCost = Math.min(minFuelCost, fuelCost);
        }
        return minFuelCost;
    }

    private static int totalCost(int a, int b) {
        int lastValue = Math.abs(a - b);
        return lastValue * (1 + lastValue) / 2;
    }
}
