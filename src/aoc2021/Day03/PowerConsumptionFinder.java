package aoc2021.Day03;

import aoc2021.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PowerConsumptionFinder {

    private static String inputPath1 = FileUtils.BASE_PATH + "Day3\\input1";
    private static String inputPath2 = FileUtils.BASE_PATH + "Day3\\input2";

    public static void main(String[] args) {
        List<String> input = FileUtils.readInputFile(inputPath1);
        System.out.println(getPowerConsumption(input));
        System.out.println(getLifeSupportRating(input));

        input = FileUtils.readInputFile(inputPath2);
        System.out.println(getPowerConsumption(input));
        System.out.println(getLifeSupportRating(input));
    }

    public static int getPowerConsumption(List<String> input) {
        int len = input.get(0).length();

        int zero[] = new int[len];
        int one[] = new int[len];

        for (String in : input) {
            char c[] = in.toCharArray();
            for (int i = 0; i < len; i++) {
                if (c[i] == '0') {
                    zero[i]++;
                } else {
                    one[i]++;
                }
            }
        }

        StringBuilder s = new StringBuilder();
        StringBuilder t = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (zero[i] > one[i]) {
                s.append('0');
                t.append('1');
            } else {
                s.append('1');
                t.append('0');
            }
        }

        int gammaRate = Integer.valueOf(s.toString(), 2); // ie base 2
        int epsilonRate = Integer.valueOf(t.toString(), 2); // ie base 2
        return gammaRate * epsilonRate;
    }

    public static int getLifeSupportRating(List<String> input) {
        int len = input.get(0).length();
        List<String> rem;
        List<String> cur = new ArrayList<>(input);

        int oxygenGeneratorRating, co2ScrubberRating;

        for (int i = 0; i < len && cur.size() > 1; i++) {
            char mostSigBit = getMostRepeatingBit(cur, i);
            final int pos = i;
            rem = cur.stream().filter(e -> e.charAt(pos) == mostSigBit).collect(Collectors.toList());
            cur.clear();
            cur.addAll(rem);
        }
        oxygenGeneratorRating = Integer.valueOf(cur.get(0).toString(), 2);

        cur = new ArrayList<>(input);

        for (int i = 0; i < len && cur.size() > 1; i++) {
            char mostSigBit = getMostRepeatingBit(cur, i);
            char leastSigBit = mostSigBit == '1' ? '0' : '1';
            final int pos = i;
            rem = cur.stream().filter(e -> e.charAt(pos) == leastSigBit).collect(Collectors.toList());
            cur.clear();
            cur.addAll(rem);
        }
        co2ScrubberRating = Integer.valueOf(cur.get(0).toString(), 2);
        return oxygenGeneratorRating * co2ScrubberRating;
    }

    private static char getMostRepeatingBit(List<String> input, int position) {
        int oneCount;
        int zerCount = (int) input.stream().filter(in -> in.charAt(position) == '0').count();

        oneCount = input.size() - zerCount;
        return oneCount >= zerCount ? '1' : '0';
    }

}
