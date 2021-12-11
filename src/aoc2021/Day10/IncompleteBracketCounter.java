package aoc2021.Day10;

import aoc2020.utils.FileUtils;

import java.util.*;

public class IncompleteBracketCounter extends CorruptedBracketCounter {

    protected static Map<Character, Integer> pointMap = new HashMap<Character, Integer>()
    {{
        put('(', 1); put('[', 2); put('{', 3); put('<', 4);
    }};

    public static void main(String[] args) {
        List<String> input1 = FileUtils.readInputFile(inputPath1);
        List<String> input2 = FileUtils.readInputFile(inputPath2);

        System.out.println(getSolution(input1));
        System.out.println(getSolution(input2));
    }

    private static Long getSolution(List<String> input) {
        List<Long> pointsList = new ArrayList<>();
        for (String in : input) {
            String cor = isBalanced(in);
            if (cor != null && !cor.equals("-1")) {
                pointsList.add(calculateResult(cor));
            }
        }
        Collections.sort(pointsList);
        return pointsList.get(pointsList.size() / 2);
    }

    static String isBalanced(String value) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            if (openBrackets.contains(ch)) {
                stack.push(ch);
            } else if (!stack.isEmpty() && closedBrackets.get(stack.peek()) == ch) {
                stack.pop();
            } else {
                return "-1";
            }
        }
        return getIncompleteString(stack);
    }

    private static String getIncompleteString(Stack<Character> stack) {
        String corruptString = null;
        if (!stack.isEmpty()) {
            StringBuilder str = new StringBuilder();
            while (!stack.isEmpty()) {
                str.append(stack.pop());
            }
            corruptString = str.toString();
        }
        return corruptString;
    }

    private static Long calculateResult(String incompleteBrackets) {
        Long result = 0l;
        for (int i = 0; i < incompleteBrackets.length(); i++) {
            result = result * 5 + pointMap.get(incompleteBrackets.charAt(i));
        }
        return result;
    }
}
