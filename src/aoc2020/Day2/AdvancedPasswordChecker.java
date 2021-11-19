package aoc2020.Day2;

import aoc2020.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/*

Given the same example list from above:

1-3 a: abcde is valid: position 1 contains a and position 3 does not.
1-3 b: cdefg is invalid: neither position 1 nor position 3 contains b.
2-9 c: ccccccccc is invalid: both position 2 and position 9 contain c.
How many passwords are valid according to the new interpretation of the policies?

 */
public class AdvancedPasswordChecker {

    public static int getValidPasswordCount(List<String> inputList) {
        int result = 0;
        for (String input : inputList) {
            String inArr[] = input.split("\\s+");
            Policy policy = new Policy(inArr);
            String password = inArr[2];

            char aplha = inArr[1].charAt(0);

            if ((password.charAt(policy.getStartRange() - 1) == aplha &&
                    password.charAt(policy.getEndRange() - 1) != aplha) ||
                    password.charAt(policy.getStartRange() - 1) != aplha &&
                            password.charAt(policy.getEndRange() - 1) == aplha) {
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        List<String> inputList = new ArrayList<>();
        inputList.add("1-3 a: abcde");
        inputList.add("1-3 b: cdefg");
        inputList.add("2-9 c: ccccccccc");

        System.out.println(getValidPasswordCount(inputList));

        String inputPath = "C://MyDrive//Study/day2.txt";
        List<String> fileInput = FileUtils.readInputFile(inputPath);
        System.out.println(getValidPasswordCount(fileInput));
    }
}
