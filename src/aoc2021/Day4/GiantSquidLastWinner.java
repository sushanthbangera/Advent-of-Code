package aoc2021.Day4;

import aoc2020.utils.FileUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GiantSquidLastWinner extends GiantSquid {

    public static void main(String[] args) {
        List<String> input1 = FileUtils.readInputFile(inputPath1);
        List<String> input2 = FileUtils.readInputFile(inputPath2);

        System.out.println(getFinalWinnerScore(input1));
        System.out.println(getFinalWinnerScore(input2));
    }

    public static int getFinalWinnerScore(List<String> input) {
        int result = 0;
        List<Integer> randoms = getRandoms(input);
        List<int[][]> allBoards = allBoards(input);

        for (Integer random : randoms) {
            markAndCheckAllBoards(allBoards, random);

            Set<Integer> winners = getWinnerList(allBoards);
            List<Integer> winnerBoards = new ArrayList<>(winners);

            if (winners.size() > 0) {
                if (allBoards.size() == 1) {
                    result = getFinalScore(allBoards.get(0), random);
                    return result;
                }
                int winsRemoved = 0;
                for (int winBoard : winnerBoards) {
                    allBoards.remove(winBoard - winsRemoved);
                    winsRemoved++;
                }
            }
        }
        return result;
    }

    private static Set<Integer> getWinnerList(List<int[][]> allBoards) {
        int rowCount, colCount, boardNum = 0;
        Set<Integer> winnerBoards = new HashSet<>();

        for (int ar[][] : allBoards) {
            for (int row = 0; row < 5; row++) {
                rowCount = 0;
                for (int col = 0; col < 5; col++) {
                    if(ar[row][col] < 0) {
                        rowCount++;
                    }
                }
                if (rowCount == 5) {
                    winnerBoards.add(boardNum);
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
                    winnerBoards.add(boardNum);
                }
            }
            boardNum++;
        }
        return winnerBoards;
    }

}
