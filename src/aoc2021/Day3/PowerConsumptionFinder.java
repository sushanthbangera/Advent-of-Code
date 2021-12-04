package aoc2021.Day3;

import aoc2020.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PowerConsumptionFinder {

    public static void main(String[] args) {
        String inputPath = "C:\\MyDrive\\Study\\My_Github_Repo\\Advent-of-Code\\src\\aoc2021\\Day3\\input1";
        List<String> input = FileUtils.readInputFile(inputPath);
        System.out.println(getPowerConsumption(input));
        System.out.println(getLifeSupportRating(input));

        inputPath = "C:\\MyDrive\\Study\\My_Github_Repo\\Advent-of-Code\\src\\aoc2021\\Day3\\input2";
        input = FileUtils.readInputFile(inputPath);
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

        int gammarate = Integer.valueOf(s.toString(), 2); // ie base 2
        int epsilonRate = Integer.valueOf(t.toString(), 2); // ie base 2
        return gammarate * epsilonRate;
    }

    public static int getLifeSupportRating(List<String> input) {
        int len = input.get(0).length();

        List<String> rem;
        List<String> cur = new ArrayList<>();
        cur.addAll(input);

        int oxygenGeneratorRating, co2ScrubberRating;

        for (int i = 0; i < len && cur.size() > 1; i++) {
            char mostSigBit = getMostRepeatingBit(cur, i);
            final int pos = i;
            rem = cur.stream().filter(e -> e.charAt(pos) == mostSigBit).collect(Collectors.toList());
            cur.clear();
            cur.addAll(rem);
        }
        oxygenGeneratorRating = Integer.valueOf(cur.get(0).toString(), 2);

        cur = new ArrayList<>();
        cur.addAll(input);

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
        int zero = 0;
        int one;

        for (String in : input) {
            if (in.charAt(position) == '0') {
                zero++;
            }
        }
        one = input.size() - zero;
        return one >= zero ? '1' : '0';
    }
}
