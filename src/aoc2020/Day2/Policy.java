package aoc2020.Day2;

public class Policy {

    private int startRange;

    private int endRange;

    private char aplhabet;

    public Policy(String[] input) {
        String range[] = input[0].split("-");
        this.startRange = Integer.valueOf(range[0]);;
        this.endRange = Integer.valueOf(range[1]);;
        this.aplhabet = input[1].charAt(0);;
    }

    public int getStartRange() {
        return startRange;
    }

    public void setStartRange(int startRange) {
        this.startRange = startRange;
    }

    public int getEndRange() {
        return endRange;
    }

    public void setEndRange(int endRange) {
        this.endRange = endRange;
    }

    public char getAplhabet() {
        return aplhabet;
    }

    public void setAplhabet(char aplhabet) {
        this.aplhabet = aplhabet;
    }
}
