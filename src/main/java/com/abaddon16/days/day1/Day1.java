package com.abaddon16.days.day1;

import com.abaddon16.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 {

    private final List<Integer> lList = new ArrayList<>();
    private final List<Integer> rList = new ArrayList<>();

    public static void main(String[] args) {
        var day1 = new Day1();
        day1.solve();
    }

    private void solve()
    {
        readInput();
        part1();
        part2();
    }

    private void part1()
    {
        List<Integer> left = Utils.quickSort(lList);
        List<Integer> right = Utils.quickSort(rList);

        int distance = 0;
        for (int i=0; i<left.size(); i++) distance += Math.abs(left.get(i) - right.get(i));
        System.out.println("[Part1] Distance: "+distance);
    }

    private void part2()
    {
        int similarityScore = 0;
        for(Integer i: lList)
        {
            int occurR = Collections.frequency(rList, i);
            similarityScore += i*occurR;
        }

        System.out.println("[Part2] Similarity Score: "+similarityScore);
    }

    private void readInput(){
        List<String> lines = Utils.readInLines("Day1Input.txt");
        lines.forEach(
                line->
                {
                    String[] elements = line.split(" {3}");
                    lList.add(Integer.valueOf(elements[0]));
                    rList.add(Integer.valueOf(elements[1]));
                }
        );
    }
}
