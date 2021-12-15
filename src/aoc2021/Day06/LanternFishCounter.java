package aoc2021.Day06;

import aoc2021.utils.FileUtils;
import aoc2021.utils.NumberUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanternFishCounter {

    protected static String inputPath1 = FileUtils.BASE_PATH + "Day6\\input1";
    protected static String inputPath2 = FileUtils.BASE_PATH + "Day6\\input2";

    public static void main(String[] args) {
        List<String> input1 = FileUtils.readInputFile(inputPath1);
        List<String> input2 = FileUtils.readInputFile(inputPath2);

        System.out.println(getTotalFisheAfterDays(input1, 80));
        System.out.println(getTotalFisheAfterDays(input2, 80));

        //Part 2
        System.out.println(getTotalFisheAfterDays(input1, 256));
        System.out.println(getTotalFisheAfterDays(input2, 256));
    }

    protected static Long getTotalFisheAfterDays(List<String> input, int days) {
        List<Integer> fishList = NumberUtils.convertToInt(input.get(0), ",");
        // map for birthTimer and No of fishes having that birth time
        Map<Integer, Long> birthMap = new HashMap<>();
        // add number of fishes as per their new birth time
        for (Integer fish : fishList) {
            birthMap.put(fish, birthMap.getOrDefault(fish, 0l) + 1);
        }

        for (int day = 1; day <= days; day++) {
            long fishCount = birthMap.getOrDefault(0, 0l);

            //Add all fishes to one lower birthTime
            for(int i = 0; i <= 8; i++) {
                birthMap.put(i, birthMap.getOrDefault(i + 1, 0l));
            }

            birthMap.put(8, fishCount);
            birthMap.put(6, birthMap.getOrDefault(6, 0l) + fishCount);
        }
        return birthMap.entrySet().stream().mapToLong(birthMapEntry -> birthMapEntry.getValue()).sum();
    }
}