package aoc2021.Day11;

import aoc2020.utils.FileUtils;

import java.util.List;

public class AllFlashCounter extends FlashCounter {

    public static void main(String[] args) {
        List<String> input1 = FileUtils.readInputFile(inputPath1);
        List<String> input2 = FileUtils.readInputFile(inputPath2);

        System.out.println(getAllFlashedStep(input1));
        flashCount = 0;
        System.out.println(getAllFlashedStep(input2));
    }

    private static int getAllFlashedStep(List<String> input) {
        readInput(input);
        int step = 0;
        while(step++ < Integer.MAX_VALUE) {
            int previousCount = flashCount;
            increasePower();
            startFlash();
            if (isAllFlashed(previousCount)) {
                return step;
            }
        }
        return -1;
    }

    private static boolean isAllFlashed(int previousCount) {
        return flashCount - previousCount == (matrix.length * matrix.length);
    }
}
