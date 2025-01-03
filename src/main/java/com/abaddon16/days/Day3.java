package com.abaddon16.days;

import com.abaddon16.utils.Utils;

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
        long sumMultiplications = parseMultiplys(mulPattern.matcher(corruptedMemory));
        System.out.println("[Part 1] All Multiply Sum: "+sumMultiplications);
    }

    private void part2() {
        String dos = String.join("", corruptedMemory.split("don't\\(\\).*?do\\(\\)|don't\\(\\).*?$"));
        long sumMultiplications = parseMultiplys(mulPattern.matcher(dos));
        System.out.println("[Part 2] Flow Control Multiply Sum: "+sumMultiplications);
    }

    private long parseMultiplys(Matcher m){
        long sumMultiplications = 0;
        while(m.find())
        {
            long left = Long.parseLong(m.group(1));
            long right = Long.parseLong(m.group(2));
            sumMultiplications += left *right;
        }
        return sumMultiplications;
    }

    private void readInput() {
        List<String> lines = Utils.readInLines("Day3Input.txt");
        StringBuilder ret = new StringBuilder();
        lines.forEach(ret::append);
        corruptedMemory = ret.toString();
    }
}
