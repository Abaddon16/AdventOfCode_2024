package com.abaddon16.days;

import com.abaddon16.utils.Utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {
    List<String> rawInput;

    private final Pattern pattern = Pattern.compile("XMAS|SMAX");

    public static void main(String[] args) {
        new Day4().solve();
    }

    private void solve() {
        readInput();
        part1();
        part2();
    }

    private void part1() {
        long count = 0;

        for (String input : rawInput) count += pattern.matcher(input).results().count();
        System.out.println(count);
    }

    private void part2() {

    }

    private void readInput() {
        rawInput = Utils.readInLines("Day4Input.txt");
    }
}
