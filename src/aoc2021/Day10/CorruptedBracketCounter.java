package aoc2021.Day10;

import aoc2021.utils.FileUtils;

import java.util.*;

public class CorruptedBracketCounter {

    protected static String inputPath1 = FileUtils.BASE_PATH + "Day10\\input1";
    protected static String inputPath2 = FileUtils.BASE_PATH + "Day10\\input2";

    protected static Set<Character> openBrackets = new HashSet<>(Arrays.asList(new Character[]{'{', '(', '[', '<'}));
    protected static Map<Character, Character> closedBrackets = new HashMap<Character, Character>() {{
        put('(', ')');
        put('{', '}');
        put('[', ']');
        put('<', '>');
    }};
    protected static Map<Character, Integer> pointMap = new HashMap<Character, Integer>() {{
        put(')', 3);
        put(']', 57);
        put('}', 1197);
        put('>', 25137);
    }};

    public static void main(String[] args) {
        List<String> input1 = FileUtils.readInputFile(inputPath1);
        List<String> input2 = FileUtils.readInputFile(inputPath2);

        System.out.println(getCorruptedBracketsValue(input1));
        System.out.println(getCorruptedBracketsValue(input2));
    }

    private static int getCorruptedBracketsValue(List<String> input) {
        int result = 0;
        for (String in : input) {
            Character cor = getFirstCorruptedCharacter(in);
            if (cor != null) {
                result += pointMap.get(cor);
            }
        }
        return result;
    }

    private static Character getFirstCorruptedCharacter(String value) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            if (openBrackets.contains(ch)) {
                stack.push(ch);
            } else if (!stack.isEmpty() && closedBrackets.get(stack.peek()) == ch) {
                stack.pop();
            } else {
                return ch;
            }
        }
        return null;
    }
}
