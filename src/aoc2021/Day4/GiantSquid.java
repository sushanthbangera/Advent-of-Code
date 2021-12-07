package aoc2021.Day4;

import aoc2020.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class GiantSquid {

    protected static String inputPath1 = "C:\\MyDrive\\Study\\My_Github_Repo\\Advent-of-Code\\src\\aoc2021\\Day4\\input1";
    protected static String inputPath2 = "C:\\MyDrive\\Study\\My_Github_Repo\\Advent-of-Code\\src\\aoc2021\\Day4\\input2";

    public static void main(String[] args) {
        List<String> input1 = FileUtils.readInputFile(inputPath1);
        List<String> input2 = FileUtils.readInputFile(inputPath2);

        System.out.println(getWinnerScore(input1));
        System.out.println(getWinnerScore(input2));
    }

    public static int getWinnerScore(List<String> input) {
        int result = 0;
        List<Integer> randoms = getRandoms(input);
        List<int[][]> allBoards = allBoards(input);

        for (Integer random : randoms) {
            markAndCheckAllBoards(allBoards, random);

            int arr[][] = isWinner(allBoards);
            if (arr.length == 5) {
                result = getFinalScore(arr, random);
                return result;
            }
        }

        return result;
    }

    protected static int[][] readMatrix(List<String> input, int start, int end) {
        int ar[][] = new int[5][5];
        int r = 0;
        for (int i = start; i <= end; i++) {
            String input_row = input.get(i).trim();
            String row[] = input_row.split("\\s+");
            for (int j = 0; j < 5; j++) {
                ar[r][j] = Integer.parseInt(row[j]);
            }
            r++;
        }
        return ar;
    }

    protected static List<int[][]> allBoards(List<String> input) {
        int start = 2, end = 6;
        List<int[][]> allBoards = new ArrayList<>();
        while (end < input.size() + 2) {
            int arr[][] = readMatrix(input, start, end);
            start = start + 6;
            end = end + 6;
            allBoards.add(arr);
        }
        return allBoards;
    }

    protected static List<Integer> getRandoms(List<String> input) {
        String row = input.get(0);
        String r[] = row.split(",");
        List<Integer> randoms = new ArrayList<>();

        for (int i = 0; i < r.length; i++) {
            randoms.add(Integer.parseInt(r[i]));
        }
        return randoms;
    }

    protected static void markAndCheckAllBoards(List<int[][]> allBoards, int input) {
        for (int ar[][] : allBoards) {
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    if (ar[row][col] == input) {
                        ar[row][col] = -1;
                    }
                }
            }
        }
    }

    protected static int[][] isWinner(List<int[][]> allBoards) {
        int rowCount = 0;
        int colCount = 0;
        for (int ar[][] : allBoards) {
            for (int row = 0; row < 5; row++) {
                rowCount = 0;
                for (int col = 0; col < 5; col++) {
                    if (ar[row][col] < 0) {
                        rowCount++;
                    }
                }
                if (rowCount == 5) {
                    return ar;
                }
            }

            for (int row = 0; row < 5; row++) {
                colCount = 0;
                for (int col = 0; col < 5; col++) {
                    if (ar[col][row] < 0) {
                        colCount++;
                    }
                }
                if (colCount == 5) {
                    return ar;
                }
            }
        }
        return new int[][]{{1}, {2}};
    }

    protected static int getFinalScore(int arr[][], int lastNum) {
        int totalSum = 0;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (arr[row][col] > 0) {
                    totalSum += arr[row][col];
                }
            }
        }
        return totalSum * lastNum;
    }

    protected static void print(List<int[][]> allBoards) {
        for (int arr[][] : allBoards) {
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    System.out.print(arr[row][col] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
