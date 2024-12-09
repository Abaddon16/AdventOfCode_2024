package com.abaddon16.days;

import com.abaddon16.utils.Utils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day5 {
    Map<Integer, List<Integer>> rules = new HashMap<>();
    List<String> updates;

    public static void main(String[] args) {
        new Day5().solve();
    }

    private void solve() {
        readInput();
        part1();
        part2();
    }

    private void part1() {
        int sum = 0;
        for(String update: updates) {
            List<Integer> pages = Arrays.stream(update.split(",")).map(Integer::parseInt).toList();
            boolean isGood = true;
            for(Integer page: pages) {
                List<Integer> ruleChecks = rules.get(page);
                if (ruleChecks == null) continue;
                for(Integer second: ruleChecks) {
                    if (pages.contains(second) && pages.indexOf(page) > pages.indexOf(second)) {
                        isGood = false;
                        break;
                    }
                }
                if (!isGood) break;
            }
            if (isGood) sum+=pages.get(pages.size()/2);
        }
        System.out.println("[Part1] Middle Pages Sum: "+sum);
    }

    private void part2() {
        AtomicInteger sum = new AtomicInteger();
        List<List<Integer>> badUpdates = new ArrayList<>();
        for(String update: updates) {
            List<Integer> pages = Arrays.stream(update.split(",")).map(Integer::parseInt).toList();
            boolean isGood = true;
            for(Integer page: pages) {
                List<Integer> ruleChecks = rules.get(page);
                if (ruleChecks == null) continue;
                for(Integer second: ruleChecks) {
                    if (pages.contains(second) && pages.indexOf(page) > pages.indexOf(second)) {
                        isGood = false;
                        badUpdates.add(pages);
                        break;
                    }
                }
                if (!isGood) break;
            }
        }
        Comparator<Integer> x = (o1, o2) -> {
            if (!rules.containsKey(o1)) return 0;
            List<Integer> checks = rules.get(o1);
            if (!checks.contains(o2)) return 0;
            return checks.contains(o2) ? -1 : 1;
        };
        badUpdates.forEach(update->{
            List<Integer> newOrder = update.stream().sorted(x).toList();
            sum.addAndGet(newOrder.get(newOrder.size() / 2));
        });

        System.out.println("[Part2] Middle Pages Sum: "+sum);
    }

    private void readInput() {
        List<String> input = Utils.readInLines("Day5Input.txt");
        int rulesEnd = input.indexOf("");
        input.subList(0, rulesEnd).forEach(string -> {
            String[] pair = string.split("\\|");
            Integer ruleFirst = Integer.parseInt(pair[0]);
            Integer ruleSecond = Integer.parseInt(pair[1]);
            if(!rules.containsKey(ruleFirst)) rules.put(ruleFirst, new ArrayList<>());
            rules.get(ruleFirst).add(ruleSecond);
        });
        updates = input.subList(rulesEnd+1, input.size());
        System.out.println(rules);
        System.out.println(updates);
    }
}

class Pair<T>{
    T first;
    T second;
    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
