package aoc2021.Day8;

import aoc2021.utils.FileUtils;

import java.util.List;

public class SevenSegmentSearch {

    protected static String inputPath1 = FileUtils.BASE_PATH + "Day8\\input1";
    protected static String inputPath2 = FileUtils.BASE_PATH + "Day8\\input2";

    public static void main(String[] args) {
        List<String> input1 = FileUtils.readInputFile(inputPath1);
        List<String> input2 = FileUtils.readInputFile(inputPath2);

        System.out.println(getSolution(input1));
        System.out.println(getSolution(input2));
    }

    private static int getSolution(List<String> input) {
        int result = 0;
        for (String in : input) {
            System.out.println(in);
            String ch[] = in.split("\\s+");

            for (int i = 11; i < ch.length; i++) {
                if (ch[i].length() == 2 || ch[i].length() == 3 || ch[i].length() == 4 || ch[i].length() == 7) {
                    result++;
                }
            }
        }
        return result;
    }


}
