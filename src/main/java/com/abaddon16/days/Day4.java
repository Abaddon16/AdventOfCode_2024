package com.abaddon16.days;

import com.abaddon16.utils.Utils;

import java.text.MessageFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

        String forward = "(?=(X.{%d}M.{%d}A.{%d}S))|(?=(S.{%d}A.{%d}M.{%d}X))";
        final Pattern horiz = Pattern.compile(forward.formatted(0, 0, 0, 0, 0, 0));
        final Pattern vert = Pattern.compile(forward.formatted(lineLength, lineLength, lineLength, lineLength, lineLength, lineLength));
        final Pattern diag1 = Pattern.compile(forward.formatted(lineLength+1, lineLength+1, lineLength+1, lineLength+1, lineLength+1, lineLength+1));
        final Pattern diag2 = Pattern.compile(forward.formatted(lineLength-1, lineLength-1, lineLength-1, lineLength-1, lineLength-1, lineLength-1));

        System.out.println("[Part 1] Count: " + getMatches(input, List.of(horiz, vert, diag1, diag2)));
    }

    private void part2() {
        String input = String.join(" ", this.input);
        int lineLength = this.input.getFirst().length()-1; // always one to the left of "just below" because it's easier here

        final Pattern p1 = Pattern.compile("(?=(M.S.{%d}A.{%d}M.S))".formatted(lineLength, lineLength));
        final Pattern p2 = Pattern.compile("(?=(S.M.{%d}A.{%d}S.M))".formatted(lineLength, lineLength));
        final Pattern p3 = Pattern.compile("(?=(M.M.{%d}A.{%d}S.S))".formatted(lineLength, lineLength));
        final Pattern p4 = Pattern.compile("(?=(S.S.{%d}A.{%d}M.M))".formatted(lineLength, lineLength));

        System.out.println("[Part 2] Count: " + getMatches(input, List.of(p1, p2, p3, p4)));
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
