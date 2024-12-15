package com.abaddon16.days;

import com.abaddon16.utils.Utils;

import java.util.*;

public class Day8 {
    Map<Character, List<Pair>> map = new HashMap<>();
    int width;
    int height;
    public static void main(String[] args) {
        new Day8().solve();
    }

    private void solve() {
        readInput();
        part1();
        part2();
    }

    private void part1() {
        Set<Pair> antinodes = new HashSet<>();
        for(List<Pair> antennaLocations : map.values()) {
            for(int i = 0; i < antennaLocations.size(); i++) {
                Pair a1 = antennaLocations.get(i);
                for(int j = i+1; j < antennaLocations.size(); j++) {
                    Pair a2 = antennaLocations.get(j);
                    Pair d = getDistance(a1, a2);
                    Pair anti1 = a1.addInvertedOffset(d);
                    Pair anti2 = a2.addOffset(d);
                    if(anti1.first<height && anti1.first>=0 && anti1.second<width && anti1.second>=0) antinodes.add(anti1);
                    if(anti2.first<height && anti2.first>=0 && anti2.second<width && anti2.second>=0) antinodes.add(anti2);
                }
            }
        }

        System.out.println("[Part 1] Antinode count: "+antinodes.size());
    }

    private void part2() {
        Set<Pair> antinodes = new HashSet<>();
        for(List<Pair> antennaLocations : map.values()) {
            for(int i = 0; i < antennaLocations.size(); i++) {
                Pair a1 = antennaLocations.get(i);
                for(int j = i+1; j < antennaLocations.size(); j++) {
                    Pair a2 = antennaLocations.get(j);
                    antinodes.add(a1);
                    antinodes.add(a2);
                    Pair d = getDistance(a1, a2);
                    Pair anti1 = a1.addInvertedOffset(d);
                    Pair anti2 = a2.addOffset(d);
                    while(anti1.first<height && anti1.first>=0 && anti1.second<width && anti1.second>=0){
                        antinodes.add(anti1);
                        anti1 = anti1.addInvertedOffset(d);
                    }
                    while(anti2.first<height && anti2.first>=0 && anti2.second<width && anti2.second>=0){
                        antinodes.add(anti2);
                        anti2 = anti2.addOffset(d);
                    }
                }
            }
        }

        System.out.println("[Part 2] Antinode count: "+antinodes.size());
    }

    private Pair getDistance(Pair p1, Pair p2) {
        return new Pair(p2.first-p1.first, p2.second-p1.second);
    }

    private void readInput() {
        List<String> lines = Utils.readInLines("Day8Input.txt");
        height = lines.size();
        width = lines.getFirst().length();
        for(int i = 0; i < lines.size(); i++){
            String line = lines.get(i);
            for(int j = 0; j < line.length(); j++){
                char c = line.charAt(j);
                if (c == '.') continue;
                if (!map.containsKey(c)) map.put(c, new ArrayList<>());
                map.get(c).add(new Pair(i, j));
            }
        }
    }

    private record Pair(int first, int second) implements Comparable<Pair> {
        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }

        public Pair addOffset(Pair offset){
            return new Pair(first + offset.first, second + offset.second);
        }

        public Pair addInvertedOffset(Pair offset){
            return new Pair(first+offset.first*-1, second+offset.second*-1);
        }

        @Override
        public int compareTo(Pair o) {
            if (first > o.first) return 1;
            if (first < o.first) return -1;
            return Integer.compare(second, o.second);
        }
    }
}

