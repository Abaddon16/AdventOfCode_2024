package com.abaddon16.days;

import com.abaddon16.utils.Utils;

import java.util.List;

public class Day6 {
    int[][] grid;
    int[] curPos;
    int rotateCount = 0;


    public static void main(String[] args) {
        new Day6().solve();
    }

    private void solve() {
        readInput();
        part1();
        part2();
    }

    private void part1() {
        rotate(true);
        while(!isFinished()){
            if(canMove()) moveGuard();
            else rotate(false);
        }
        int spotsOccupied = 0;
        for (int[] row : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                if (row[j] != -1 && row[j] != 0) spotsOccupied++;
            }
        }
        for (int i=0; i<rotateCount%4; i++) rotate(false);
        printGrid(grid);
        System.out.println("[Part 1] Spots Occupied: "+spotsOccupied);
    }

    private void part2() {

    }

    private void readInput() {
        List<String> lines = Utils.readInLines("Day6Input.txt");
        int rows = lines.size();
        int cols = lines.getFirst().length();
        grid = new int[rows][cols];
        // -1 = can't visit
        //  0 = haven't visited
        //  1 = have visited
        //  2 = self
        for(int i=0; i<lines.size(); i++){
            for(int j=0; j<lines.getFirst().length(); j++){
                char c = lines.get(i).charAt(j);
                int marker;
                if(c == '^')
                {
                    curPos = new int[]{i, j};
                    marker = 2;
                } else if (c == '.') {
                    marker = 0;
                } else {
                    marker = -1;
                }

                grid[i][j] = marker;
            }
        }
    }

    private void printGrid(int[][] grid){
        for (int[] row : grid) {
            for (int j = 0; j < grid[0].length; j++){
                if(row[j]==0) System.out.print('.');
                else if(row[j]==-1) System.out.print('#');
                else if(row[j]==-2) System.out.print('^');
                else System.out.print('X');
            };
            System.out.println();
        }
        System.out.println("===========");
    }

    private void rotate(boolean clockWise) {
        rotateCount = rotateCount + (clockWise?-1:1);
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] ret = new int[rows][cols];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                ret[i][j] = clockWise ? grid[cols - j - 1][i] : grid[j][cols - i - 1];
            }
        }
        curPos = clockWise ? new int[]{curPos[1], grid.length - 1 - curPos[0]} : new int[]{grid.length - 1 - curPos[1], curPos[0]};
        grid = ret;
    }

    private void moveGuard()
    {
        int curPosRow = curPos[0];
        int curPosCol = curPos[1];
        int[] row = grid[curPosRow];
        for(int i=curPosCol; i<row.length; i++){
            if (!canMove()) break;
            curPos = new int[]{curPosRow, i};
            row[i]= (row[i]==-2)?1:row[i]+1;
        }
        grid[curPos[0]][curPos[1]] = -2;
    }

    private boolean canMove(){
        int row = curPos[0];
        int col = curPos[1];
        return col + 1 < grid[0].length && grid[row][col+1] != -1;
    }

    private boolean isFinished(){
        int row = curPos[0];
        int col = curPos[1];
        return row + 1 > grid.length || col + 1 > grid[0].length || row - 1 < 0 || col - 1 < 0;
    }
}
