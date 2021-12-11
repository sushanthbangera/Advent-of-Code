package aoc2021.Day11;

import aoc2020.utils.FileUtils;

import java.util.List;

public class FlashCounter {

    protected static String inputPath1 = "C:\\MyDrive\\Study\\My_Github_Repo\\Advent-of-Code\\src\\aoc2021\\Day11\\input1";
    protected static String inputPath2 = "C:\\MyDrive\\Study\\My_Github_Repo\\Advent-of-Code\\src\\aoc2021\\Day11\\input2";

    private static int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    protected static int matrix[][] = new int[10][10];
    protected static int flashCount = 0;

    public static void main(String[] args) {
        List<String> input1 = FileUtils.readInputFile(inputPath1);
        List<String> input2 = FileUtils.readInputFile(inputPath2);

        System.out.println(getFlashCount(input1, 100));
        flashCount = 0;
        System.out.println(getFlashCount(input2, 100));
    }

    private static int getFlashCount(List<String> input, int steps) {
        readInput(input);
        for (int i = 0; i < steps; i++) {
            increasePower();
            startFlash();
        }
        return flashCount;
    }

    protected static void readInput(List<String> input) {
        int row = 0;
        for (String data: input) {
            char arr[] = data.toCharArray();
            for (int i = 0 ; i < data.length(); i++) {
                matrix[row][i] = Integer.parseInt(arr[i] + "");
            }
            row++;
        }
    }

    protected static void increasePower() {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col]++;
            }
        }
    }

    protected static void startFlash() {
        boolean flashed[][] = new boolean[10][10];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] > 9) {
                    flash(row, col, flashed);
                }
            }
        }
    }


    protected static void flash(int row, int col, boolean flashed[][]) {
        if (row >= 0 && col >= 0 && row < matrix.length && col < matrix[0].length && !flashed[row][col]) {
            matrix[row][col]++;
            if (matrix[row][col] > 9) {
                flashed[row][col] = true;
                flashCount++;
                matrix[row][col] = 0;

                for (int i = 0; i < moves.length; i++) {
                    int r = row + moves[i][0];
                    int c = col + moves[i][1];
                    flash(r, c, flashed);
                }
            }
        }
    }

    protected static void print() {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
