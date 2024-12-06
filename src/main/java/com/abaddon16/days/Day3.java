package com.abaddon16.days;

import com.abaddon16.utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    String corruptedMemory;
    Pattern mulPattern = Pattern.compile("mul\\((-?\\d{1,3}),(-?\\d{1,3})\\)");

    public static void main(String[] args) {
        new Day3().solve();
    }

    private void solve() {
        readInput();
        part1();
        part2();
    }

    private void part1() {

        Matcher matches = mulPattern.matcher(corruptedMemory);
        long sumMultiplications = 0;
        while(matches.find())
        {
            long left = Long.parseLong(matches.group(1));
            long right = Long.parseLong(matches.group(2));
            sumMultiplications += left *right;
        }
        System.out.println("[Part 1] All Multiply Sum: "+sumMultiplications);
    }

    private void part2() {
        List<String> dos = Arrays.stream(corruptedMemory.split("don't\\(\\).*?do\\(\\)|don't\\(\\).*?$")).toList();

        long sumMultiplications = 0;
        for(String s:dos) {
            Matcher mulMatches = mulPattern.matcher(s);
            while(mulMatches.find())
            {
                long left = Long.parseLong(mulMatches.group(1));
                long right = Long.parseLong(mulMatches.group(2));
                sumMultiplications += left *right;
            }
        }

        System.out.println("[Part 2] Flow Control Multiply Sum: "+sumMultiplications);
    }

    private void readInput() {
        List<String> lines = Utils.readInLines("Day3Input.txt");
        StringBuilder ret = new StringBuilder();
        lines.forEach(ret::append);
        corruptedMemory = ret.toString();
    }
}
