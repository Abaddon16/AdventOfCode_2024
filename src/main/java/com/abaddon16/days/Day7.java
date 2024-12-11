package com.abaddon16.days;

import com.abaddon16.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day7 {
    List<Calibration> calibrations = new ArrayList<>();
    public static void main(String[] args) {
        new Day7().solve();
    }

    private void solve() {
        readInput();
        part1();
        part2();
    }

    private void part1() {
        long sum = 0;
        for(Calibration c:calibrations){
            if(isValidP1(c, 0, 0)) sum+=c.solution;
        }
        System.out.println("[Part 1] Sum of Valid Calibrations: "+sum);
    }

    private boolean isValidP1(Calibration calibration, int position, long runningValue){
        if (position >= calibration.elements.size()) return runningValue == calibration.solution;
        if (runningValue > calibration.solution) return false;
        long curValue = calibration.elements.get(position);
        return isValidP1(calibration, position+1, runningValue*curValue) ||
                isValidP1(calibration, position+1, runningValue+curValue);
    }

    private void part2() {
        long sum = 0;
        for(Calibration c:calibrations){
            if(isValidP2(c, 0, 0)) sum+=c.solution;
        }
        System.out.println("[Part 2] Sum of Valid Calibrations: "+sum);
    }

    private boolean isValidP2(Calibration calibration, int position, long runningValue){
        if (position >= calibration.elements.size()) return runningValue == calibration.solution;
        if (runningValue > calibration.solution) return false;
        return isValidP2(calibration, position+1, runningValue*calibration.elements.get(position)) ||
                isValidP2(calibration, position+1, runningValue+calibration.elements.get(position)) ||
                isValidP2(calibration, position+1, Long.parseLong(runningValue+""+calibration.elements.get(position)));
    }

    private void readInput() {
        List<String> lines = Utils.readInLines("Day7Input.txt");
        lines.forEach(line->{
            String[] split1 = line.split(": ");
            long solution = Long.parseLong(split1[0]);
            String[] split2 = split1[1].split(" ");
            List<Long> elements = Arrays.stream(split2).map(Long::parseLong).toList();
            calibrations.add(new Calibration(solution, elements));
        });
    }

    private record Calibration(long solution, List<Long> elements){
        @Override
        public String toString(){
            return solution+": "+elements;
        }
    }
}
