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
//        part2();
    }

    private void part1() {
        List<FileBlock> blocks = new ArrayList<>(this.blocks.size());
        this.blocks.forEach(block -> blocks.add(new FileBlock(block.id)));

        int leftCursor = 0;
        int rightCursor = blocks.size() - 1;

        while (true) {
            FileBlock emptySpace = blocks.get(leftCursor );
            while (emptySpace.id != -1) emptySpace = blocks.get(++leftCursor );
            FileBlock fileToMove = blocks.get(rightCursor);
            while (fileToMove.id == -1) fileToMove = blocks.get(--rightCursor);
            if(leftCursor >=rightCursor) break;

            blocks.set(leftCursor , fileToMove);
            blocks.set(rightCursor, emptySpace);
        }
        System.out.println("Part 1 Checksum: "+checksum(blocks));
    }

    private void part2() {
        List<FileBlock> blocks = new ArrayList<>(this.blocks.size());
        this.blocks.forEach(block -> blocks.add(new FileBlock(block.id)));
        System.out.println(blocks);

        int leftCursor = 0;
        int rightCursor = blocks.size() - 1;
        while (true) {
            FileBlock emptySpace = blocks.get(leftCursor);
            while (emptySpace.id != -1) emptySpace = blocks.get(++leftCursor);
            FileBlock fileToMove = blocks.get(rightCursor);
            while (fileToMove.id == -1) fileToMove = blocks.get(--rightCursor);
//            System.out.println(leftCursor +", "+rightCursor);
            if(leftCursor > rightCursor) break;

            int spaceToMove = 0;
            int filesToMove = 0;
            int tempLeftCursor = leftCursor;
            int tempRightCursor = rightCursor;
            while(emptySpace.id==-1){
                spaceToMove++;
                emptySpace = blocks.get(++tempLeftCursor);
            }
            int id = fileToMove.id;
            while(fileToMove.id==id){
                filesToMove++;
                fileToMove = blocks.get(--tempRightCursor);
            }
            tempRightCursor++;
            tempLeftCursor--;

            if(spaceToMove < filesToMove) {
                leftCursor = ++tempLeftCursor;
                continue;
            }
            for(int i=tempRightCursor; i<=rightCursor; i++){
                blocks.set(i, new FileBlock(-1));
            }
            for(int i=leftCursor; i<tempLeftCursor; i++){
                blocks.set(i, new FileBlock(id));
            }
            leftCursor = 0;
        }
        System.out.println(blocks);
        System.out.println("Part 2 Checksum: "+checksum(blocks));
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
