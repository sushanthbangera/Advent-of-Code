package aoc2021.Day8;

import aoc2020.utils.FileUtils;
import aoc2021.utils.StringUtils;

import java.util.Arrays;
import java.util.List;

public class SevenSegmentDisplayCounter extends SevenSegmentSearch {

    public static void main(String[] args) {
        List<String> input1 = FileUtils.readInputFile(inputPath1);
        List<String> input2 = FileUtils.readInputFile(inputPath2);

        System.out.println(getOutputDisplaySum(input1));
        System.out.println(getOutputDisplaySum(input2));
    }

    private static int getOutputDisplaySum(List<String> input) {
        int result = 0;
        for (String in : input) {
            String ch[] = in.split("\\s+");
            String[] inputDisplays = Arrays.copyOfRange(ch, 0, 10);
            String[] outputDisplays = Arrays.copyOfRange(ch, 11, 15);

            String[] wires = new String[10];
            // for 1, 4, 7, 8
            updateDigitFromLength(inputDisplays, wires);
            // for 0, 2, 3, 5, 6, 9
            updateDigitFromOverlapping(inputDisplays, wires);

            sortElements(wires);
            sortElements(outputDisplays);

            String displayedDigits = getOutputDisplayDigits(wires, outputDisplays);
            result += Integer.parseInt(displayedDigits);
        }

        return result;
    }


    private static void updateDigitFromLength(String[] inputDisplays, String wires[]) {
        for (String inp : inputDisplays) {
            if (inp.length() == 2) {
                wires[1] = inp;
            } else if (inp.length() == 3) {
                wires[7] = inp;
            } else if (inp.length() == 4) {
                wires[4] = inp;
            } else if (inp.length() == 7) {
                wires[8] = inp;
            }
        }
    }


    private static void updateDigitFromOverlapping(String[] inputDisplays, String wires[]) {

        for (int i = 0; i < 10; i++) { // since input displays are unordered
            for (String display : inputDisplays) {
                if (isDisplayTwo(display, wires)) {
                    wires[2] = display;
                } else if (isDisplayThree(display, wires)) {
                    wires[3] = display;
                } else if (isDisplayNine(display, wires)) {
                    wires[9] = display;
                } else if (isDisplayFive(display, wires)) {
                    wires[5] = display;
                } else if (isDisplaySix(display, wires)) {
                    wires[6] = display;
                } else if (isDisplayZero(display, wires)) {
                    wires[0] = display;
                }
            }
        }
    }



    private static boolean isDisplayZero(String display, String[] wires) {
        int len = display.length();
        return wires[0] == null && len == 6 && getOverlappingCount(display, wires[1]) == 2 &&
                getOverlappingCount(display, wires[2]) == 4 && getOverlappingCount(display, wires[3]) == 4;
    }

    private static boolean isDisplayTwo(String display, String[] wires) {
        int len = display.length();
        return wires[2] == null && len == 5 && getOverlappingCount(display, wires[7]) == 2 &&
                getOverlappingCount(display, wires[1]) == 1 && getOverlappingCount(display, wires[3]) == 4 &&
                getOverlappingCount(display, wires[4]) == 2;
    }

    private static boolean isDisplayThree(String display, String[] wires) {
        int len = display.length();
        return wires[3] == null && len == 5 && getOverlappingCount(display, wires[1]) == 2;
    }

    private static boolean isDisplayFive(String display, String[] wires) {
        int len = display.length();
        return wires[5] == null && len == 5 && getOverlappingCount(display, wires[2]) == 3 &&
                getOverlappingCount(display, wires[4]) == 3;
    }

    private static boolean isDisplaySix(String display, String[] wires) {
        int len = display.length();
        return wires[6] == null && len == 6 && getOverlappingCount(display, wires[1]) == 1 &&
                getOverlappingCount(display, wires[2]) == 4;
    }

    private static boolean isDisplayNine(String display, String[] wires) {
        int len = display.length();
        return wires[9] == null && len == 6 && getOverlappingCount(display, wires[7]) == 3 &&
                getOverlappingCount(display, wires[0]) == 5;
    }

    private static int getOverlappingCount(String input, String reference) {
        int overlapCount = 0;
        if (reference != null) {
            for (int i = 0; i < input.length(); i++) {
                if (reference.contains(input.charAt(i) + "")) {
                    overlapCount++;
                }
            }
        }
        return overlapCount;
    }

    private static void sortElements(String elements[]) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = StringUtils.sortString(elements[i]);
        }
    }

    private static String getOutputDisplayDigits(String wires[], String outputDisplays[]) {
        StringBuilder op = new StringBuilder();
        for (int i = 0; i < outputDisplays.length; i++) {
            for (int j = 0; j < wires.length; j++) {
                if (outputDisplays[i].equals(wires[j])) {
                    op.append(j);
                }
            }
        }
        return op.toString();
    }
}
