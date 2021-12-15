package aoc2021.Day02;

import aoc2021.utils.FileUtils;
import aoc2021.utils.StringUtils;

import java.util.List;

public class SubmarinePositionFinder {

    protected static String inputPath1 = FileUtils.BASE_PATH + "Day2\\input1";
    protected static String inputPath2 = FileUtils.BASE_PATH + "Day2\\input2";

    public static void main(String[] args) {
        List<String> input = FileUtils.readInputFile(inputPath1);
        System.out.println(findSubmarinePosition2(input));

        input = FileUtils.readInputFile(inputPath2);
        System.out.println(findSubmarinePosition(input));
        System.out.println(findSubmarinePosition2(input));
    }

    public static int findSubmarinePosition(List<String> input) {
        int hp = 0, depth = 0;

        for (String in : input) {
            String[] inArr = StringUtils.split(in);
            Integer movement = Integer.parseInt(inArr[1]);

            switch (inArr[0]) {
                case "forward":
                    hp += movement;
                    break;
                case "up":
                    depth -= movement;
                    break;
                case "down":
                    depth += movement;
                    break;
            }
        }
        return hp * depth;
    }

    public static int findSubmarinePosition2(List<String> input) {
        int hp = 0, depth = 0, aim = 0;

        for (String in : input) {
            String[] inArr = StringUtils.split(in);
            Integer movement = Integer.parseInt(inArr[1]);
            switch (inArr[0]) {
                case "forward":
                    hp += movement;
                    depth += (aim * movement);
                    break;
                case "up":
                    aim -= movement;
                    break;
                case "down":
                    aim += movement;
                    break;
            }
        }
        return hp * depth;
    }
}
