package com.abaddon16.days;

import com.abaddon16.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Day9 {
    List<FileBlock> blocks = new ArrayList<>();
    public static void main(String[] args) {
        new Day9().solve();
    }

    private void solve() {
        readInput();
        part1();
        part2();
    }

    private void part1() {
        List<FileBlock> blocks = new ArrayList<>(this.blocks.size());
        this.blocks.forEach(block -> {
//            List<Integer> temp = new ArrayList<>(block.data);
            blocks.add(new FileBlock(block.id));
        });

        int start= 0;
        int end = blocks.size() - 1;

        while (true) {
            FileBlock emptySpace = blocks.get(start);
            while (emptySpace.id != -1) emptySpace = blocks.get(++start);
            FileBlock fileToMove = blocks.get(end);
            while (fileToMove.id == -1) fileToMove = blocks.get(--end);
            if(start>=end) break;

            blocks.set(start, fileToMove);
            blocks.set(end, emptySpace);
        }
        System.out.println(this.blocks);
        System.out.println(blocks);
        System.out.println(checksum(blocks));
    }

    private void part2() {
    }

    private String blocksToString(List<FileBlock> blocks) {
        return String.join("", blocks.stream().map(String::valueOf).toList());
    }

    private long checksum(List<FileBlock> blocks) {
//        long sum = 0;
//        long pos = 0;
//        for (FileBlock block : blocks) {
//            if (block.id == -1) break;
//            for (int j = 0; j < block.data.size(); j++) {
//                sum += block.id * (pos++);
//            }
//        }
//        return sum;
        long sum = 0;
        for(int i = 0; i < blocks.size(); i++) {
            if(blocks.get(i).id != -1) sum += (long) blocks.get(i).id *i;
        }
        return sum;
    }

    private void readInput() {
        String compressedDrive = Utils.readInLines("Day9Input.txt").getFirst();
        String[] splitDrive = compressedDrive.split("");
        List<FileBlock> blocks = new ArrayList<>();
        int fileId = 0;
        for(int i = 0; i<splitDrive.length; i++) {
            if(splitDrive[i].isEmpty() || Integer.parseInt(splitDrive[i])==0) continue;
            int dataId = i%2==0 ? fileId++ : -1;
            for(int j = 0; j<Integer.parseInt(splitDrive[i]); j++) blocks.add(new FileBlock(dataId));
        }
        this.blocks = blocks;
    }

    private record FileBlock(int id){
        @Override
        public String toString() {
            return id==-1?".":String.valueOf(id);
        }
    }
}
