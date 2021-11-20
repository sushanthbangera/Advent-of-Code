package aoc2020.Day3;

import aoc2020.utils.FileUtils;

import java.util.List;

/*
Determine the number of trees you would encounter if, for each of the following slopes, you start at the top-left corner and traverse the map all the way to the bottom:

Right 1, down 1.
Right 3, down 1. (This is the slope you already checked.)
Right 5, down 1.
Right 7, down 1.
Right 1, down 2.
In the above example, these slopes would find 2, 7, 3, 4, and 2 tree(s) respectively; multiplied together, these produce the answer 336.
 */
public class SlopesTreeCounter {

    public static void main(String[] args) {
        int slopes[][] = {{1, 1}, {3, 1}, {5, 1}, {7, 1}, {1, 2}};

        String inputPath = "C:\\MyDrive\\Study\\My_Github_Repo\\Advent-of-Code\\src\\aoc2020\\Day3\\input1";
        List<String> input1 = FileUtils.readInputFile(inputPath);
        System.out.println(getTotalTrees(slopes, input1));

        inputPath = "C:\\MyDrive\\Study\\My_Github_Repo\\Advent-of-Code\\src\\aoc2020\\Day3\\input2";
        List<String> input2 = FileUtils.readInputFile(inputPath);
        System.out.println(getTotalTrees(slopes, input2));
    }

    public static long getTotalTrees(int slopes[][], List<String> inputList) {
        long treeCountProduct = 1;
        for (int i = 0; i < slopes.length; i++) {
            int x = slopes[i][0];
            int y = slopes[i][1];

            treeCountProduct *= TreeCounter.getTotalTrees(inputList, x, y);
        }
        return treeCountProduct;
    }
}
