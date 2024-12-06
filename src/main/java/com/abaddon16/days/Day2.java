package com.abaddon16.days;

import com.abaddon16.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2 {
    List<List<Integer>> reports = new ArrayList<>();

    public static void main(String[] args) {
        new Day2().solve();
    }

    private void solve() {
        readInput();
        part1();
        part2();
    }

    private void part1() {
        int safeReports = 0;

        for (List<Integer> report : reports) {
            boolean isIncreasing = report.get(1) - report.get(0) > 0;
            for (int i = 0; i < report.size()-1; i++) {
                int diff = report.get(i + 1) - report.get(i);
                int absDiff = Math.abs(diff);
                if ((diff < 0 && isIncreasing) || (diff>0 && !isIncreasing) || absDiff > 3 || absDiff < 1) break;
                if (i == report.size()-2) safeReports += 1;
            }
        }
        System.out.println("[Part 1] Safe Reports: "+ safeReports);
    }

    private void part2() {
        int safeReports = 0;

        for (List<Integer> report : reports) {
            final boolean isIncreasing = report.get(1) - report.get(0) > 0;
            boolean badReportFound = false;
            for (int i = 0; i < report.size()-1; i++) {
                int diff = report.get(i + 1) - report.get(i);
                int absDiff = Math.abs(diff);
                if ((diff < 0 && isIncreasing) || (diff>0 && !isIncreasing) || absDiff > 3 || absDiff < 1)
                {
                    if (badReportFound) break;
                    else badReportFound = true;
                }
                if (i == report.size()-2) safeReports += 1;
            }
        }
        System.out.println("[Part 2] Safe Reports: "+ safeReports);
    }

    private void readInput() {
        List<String> lines = Utils.readInLines("Day2Input.txt");

        lines.forEach(line->{
            List<Integer> singleLevel = Arrays.stream(line.split(" ")).mapToInt(Integer::valueOf).boxed().toList();
            reports.add(singleLevel);
        });
    }
}
