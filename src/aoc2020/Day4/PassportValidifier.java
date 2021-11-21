package aoc2020.Day4;

import aoc2020.utils.FileUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PassportValidifier {

    private static  Set<String> validKeys = new HashSet<String>(Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid"));
    private static String optionalKey = "cid";

    public static void main(String[] args) {
        List<String> validKeys = Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid");
        String inputPath = "C:\\MyDrive\\Study\\My_Github_Repo\\Advent-of-Code\\src\\aoc2020\\Day4\\input1";
        List<String> input1 = FileUtils.readInputFile(inputPath);
        System.out.println(getValidPassportCount(input1));

        inputPath = "C:\\MyDrive\\Study\\My_Github_Repo\\Advent-of-Code\\src\\aoc2020\\Day4\\input2";
        List<String> input2 = FileUtils.readInputFile(inputPath);
        System.out.println(getValidPassportCount(input2));
    }

    public static int getValidPassportCount(List<String> inputList) {
        int validCount = 0;
        StringBuilder passportDetails = new StringBuilder();
        boolean isValid = false;
        for (String line : inputList) {
            if (line.equals("")) {
                isValid = checkValid(passportDetails.toString());
                passportDetails = new StringBuilder();
                validCount = isValid ? validCount + 1 : validCount;
            } else {
                passportDetails.append(line + " ");
            }
        }

        isValid = checkValid(passportDetails.toString());
        validCount = isValid ? validCount + 1 : validCount;
        return validCount;
    }

    private static boolean checkValid(String passportDetails) {
        String[] keyValues = passportDetails.split("\\s+");
        System.out.println(passportDetails);
        int count = 0;
        boolean isCID = false;
        for (String keyPair : keyValues) {
            String key = keyPair.substring(0, keyPair.indexOf(":"));
            if (validKeys.contains(key)) {
                count++;
                if (key.equals("cid")) {
                    isCID = true;
                }
            }
        }
        return (count == 8 || (count == 7 && !isCID));
    }
}
