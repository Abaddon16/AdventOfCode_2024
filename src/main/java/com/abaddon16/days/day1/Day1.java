package com.abaddon16.days.day1;

import com.abaddon16.utils.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

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
        int[] lArr = lList.stream().mapToInt(i -> i).toArray();
        int[] rArr = rList.stream().mapToInt(i -> i).toArray();
        quickSort(lArr, 0, lArr.length-1);
        quickSort(rArr, 0, rArr.length-1);

        int distance = 0;
        for (int i=0; i<lArr.length; i++) distance += Math.abs(lArr[i] - rArr[i]);
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

    private static void quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }

    private static int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin-1);
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }
        int swapTemp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swapTemp;
        return i+1;
    }
}
