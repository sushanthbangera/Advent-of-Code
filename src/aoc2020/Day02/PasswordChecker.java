package aoc2020.Day02;

import aoc2020.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/*
1-3 a: abcde
1-3 b: cdefg
2-9 c: ccccccccc
Each line gives the password policy and then the password. The password policy indicates the lowest and highest number of times a given letter must appear for the password to be valid. For example, 1-3 a means that the password must contain a at least 1 time and at most 3 times.

In the above example, 2 passwords are valid. The middle password, cdefg, is not; it contains no instances of b, but needs at least 1. The first and third passwords are valid: they contain one a or nine c, both within the limits of their respective policies.

How many passwords are valid according to their policies?
 */
public class PasswordChecker {

    public static int getValidPasswordCount(List<String> inputList) {
        int result = 0;
        for (String input : inputList) {
            String inArr[] = input.split("\\s+");
            Policy policy = new Policy(inArr);
            String password = inArr[2];

            int count = 0;
            for (int i = 0; i < password.length(); i++) {
                if (policy.getAplhabet() == password.charAt(i)) {
                    count++;
                    if (count > policy.getEndRange()) {
                        break;
                    }
                }
            }
            if (count >= policy.getStartRange() && count <= policy.getEndRange()) {
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
