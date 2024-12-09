package com.abaddon16.days;

import com.abaddon16.utils.Utils;

import java.util.List;
import java.util.regex.Pattern;

public class Day4 {
    List<String> input;

    public static void main(String[] args) {
        new Day4().solve();
    }

    private void solve() {
        readInput();
        part1();
        part2();
    }

    private void part1() {
        String input = String.join(" ", this.input);
        int lineLength = this.input.getFirst().length();

        String xmas = "(?=(X.{0}M.{0}A.{0}S))|(?=(S.{0}A.{0}M.{0}X))";
        String horizPattern = xmas.replaceAll(".\\{0}", "");
        String vertPattern =  xmas.replaceAll("\\{0}", "{"+lineLength+"}");
        String diagPattern1 = xmas.replaceAll("\\{0}", "{"+(lineLength+1)+"}");
        String diagPattern2 = xmas.replaceAll("\\{0}", "{"+(lineLength-1)+"}");

        System.out.println("[Part 1] Count: " + getMatches(input, List.of(
                Pattern.compile(horizPattern),
                Pattern.compile(vertPattern),
                Pattern.compile(diagPattern1),
                Pattern.compile(diagPattern2)
        )));
    }

    private void part2() {
        String input = String.join(" ", this.input);
        int lineLength = this.input.getFirst().length()-1; // always one to the left of "just below" because it's easier here

        final Pattern x_mas = Pattern.compile("(?=M.S.{0}A.{0}M.S)|(?=S.M.{0}A.{0}S.M)|(?=M.M.{0}A.{0}S.S)|(?=S.S.{0}A.{0}M.M)"
                .replaceAll("\\{0}","{"+lineLength+"}"));

        System.out.println("[Part 2] Count: " + getMatches(input, List.of(x_mas)));
    }

    private long getMatches(String input, List<Pattern> patterns)
    {
        long count = 0;
        for (Pattern pattern : patterns) count += pattern.matcher(input).results().count();
        return count;
    }

    private void readInput() {
        input = Utils.readInLines("Day4Input.txt");
    }
}
