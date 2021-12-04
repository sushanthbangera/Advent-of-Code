package aoc2021.Day4;

import aoc2020.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/*
You're already almost 1.5km (almost a mile) below the surface of the ocean, already so deep that you can't see any sunlight. What you can see, however, is a giant squid that has attached itself to the outside of your submarine.

Maybe it wants to play bingo?

Bingo is played on a set of boards each consisting of a 5x5 grid of numbers. Numbers are chosen at random, and the chosen number is marked on all boards on which it appears. (Numbers may not appear on all boards.) If all numbers in any row or any column of a board are marked, that board wins. (Diagonals don't count.)

The submarine has a bingo subsystem to help passengers (currently, you and the giant squid) pass the time. It automatically generates a random order in which to draw numbers and a random set of boards (your puzzle input). For example:

7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1

22 13 17 11  0
 8  2 23  4 24
21  9 14 16  7
 6 10  3 18  5
 1 12 20 15 19

 3 15  0  2 22
 9 18 13 17  5
19  8  7 25 23
20 11 10 24  4
14 21 16 12  6

14 21 17 24  4
10 16 15  9 19
18  8 23 26 20
22 11 13  6  5
 2  0 12  3  7
After the first five numbers are drawn (7, 4, 9, 5, and 11), there are no winners, but the boards are marked as follows (shown here adjacent to each other to save space):

22 13 17 11  0         3 15  0  2 22        14 21 17 24  4
 8  2 23  4 24         9 18 13 17  5        10 16 15  9 19
21  9 14 16  7        19  8  7 25 23        18  8 23 26 20
 6 10  3 18  5        20 11 10 24  4        22 11 13  6  5
 1 12 20 15 19        14 21 16 12  6         2  0 12  3  7
After the next six numbers are drawn (17, 23, 2, 0, 14, and 21), there are still no winners:

22 13 17 11  0         3 15  0  2 22        14 21 17 24  4
 8  2 23  4 24         9 18 13 17  5        10 16 15  9 19
21  9 14 16  7        19  8  7 25 23        18  8 23 26 20
 6 10  3 18  5        20 11 10 24  4        22 11 13  6  5
 1 12 20 15 19        14 21 16 12  6         2  0 12  3  7
Finally, 24 is drawn:

22 13 17 11  0         3 15  0  2 22        14 21 17 24  4
 8  2 23  4 24         9 18 13 17  5        10 16 15  9 19
21  9 14 16  7        19  8  7 25 23        18  8 23 26 20
 6 10  3 18  5        20 11 10 24  4        22 11 13  6  5
 1 12 20 15 19        14 21 16 12  6         2  0 12  3  7
At this point, the third board wins because it has at least one complete row or column of marked numbers (in this case, the entire top row is marked: 14 21 17 24 4).

The score of the winning board can now be calculated. Start by finding the sum of all unmarked numbers on that board; in this case, the sum is 188. Then, multiply that sum by the number that was just called when the board won, 24, to get the final score, 188 * 24 = 4512.

To guarantee victory against the giant squid, figure out which board will win first. What will your final score be if you choose that board?

Your puzzle answer was 33348.

 */
public class GiantSquid {

    protected static String inputPath1 = "C:\\MyDrive\\Study\\My_Github_Repo\\Advent-of-Code\\src\\aoc2021\\Day4\\input1";
    protected static String inputPath2 = "C:\\MyDrive\\Study\\My_Github_Repo\\Advent-of-Code\\src\\aoc2021\\Day4\\input2";
    protected static String inputPath3 = "C:\\Users\\suba0521\\Downloads\\input.txt";

    public static void main(String[] args) {
        List<String> input1 = FileUtils.readInputFile(inputPath1);
        List<String> input2 = FileUtils.readInputFile(inputPath2);
        List<String> input3 = FileUtils.readInputFile(inputPath3);

        System.out.println(getSolutionOne(input1));
        System.out.println(getSolutionOne(input2));
        System.out.println(getSolutionOne(input3));
    }

    public static int getSolutionOne(List<String> input) {
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

    protected static  int[][] isWinner(List<int[][]> allBoards) {
        int rowCount = 0;
        int colCount = 0;
        for (int ar[][] : allBoards) {
            for (int row = 0; row < 5; row++) {
                rowCount = 0;
                for (int col = 0; col < 5; col++) {
                    if(ar[row][col] < 0) {
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
                    if(ar[col][row] < 0) {
                        colCount++;
                    }
                }
                if (colCount == 5) {
                    return ar;
                }
            }
        }
        return new int[][] {{1}, {2}};
    }

    protected static int getFinalScore(int arr[][], int lastNum) {
        int totalSum = 0;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if(arr[row][col] > 0) {
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
