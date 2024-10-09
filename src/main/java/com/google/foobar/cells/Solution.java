package com.google.foobar.cells;

import java.util.ArrayList;
import java.util.List;

/**
 * Level 4.
 */
public class Solution {

    public static int[][] solution(int num_buns, int num_required) {
        List<List<Integer>> bunnyKeyLists = new ArrayList<>();
        for (int i = 0; i < num_buns; i++) {
            bunnyKeyLists.add(new ArrayList<>());
        }

        // the idea is to generate all possible allocations of each key across the bunnies
        // the number of repetitions of a single key across all bunnies can be calculated as follows:
        int numReps = num_buns - num_required + 1;

        // to simulate an arbitrary amount of nested loops, we create a list of loop conditions
        // list of nested loops to generate possible allocations of a key across bunnies
        // arrays consist of 3 values: start value, limit value and current value
        int[][] bunnyLoops = new int[numReps][3];
        for (int i = 0; i < bunnyLoops.length; i++) {
            bunnyLoops[i][0] = i;                                   // start
            bunnyLoops[i][1] = num_buns - bunnyLoops.length + i + 1;// limit
            bunnyLoops[i][2] = bunnyLoops[i][0];                    // current
        }

        // current key number
        int key = 0;

        outer:
        while (true) {

            // allocating keys
            for (int[] bunnyLoop : bunnyLoops) {
                bunnyKeyLists.get(bunnyLoop[2]).add(key);
            }

            // updating loop conditions - finding the left-most unfinished loop
            int loopIndex = bunnyLoops.length - 1;
            while (true) {
                bunnyLoops[loopIndex][2]++;
                if (bunnyLoops[loopIndex][2] >= bunnyLoops[loopIndex][1]) {
                    loopIndex--;
                    if (loopIndex < 0) {
                        // finished all loops
                        break outer;
                    }
                    else {
                        continue;
                    }
                }
                break;
            }

            // updating loop conditions - resetting all loops to the right
            int outerLoopCurrent = bunnyLoops[loopIndex][2];
            for (int i = loopIndex + 1; i < bunnyLoops.length; i++) {
                bunnyLoops[i][0] = outerLoopCurrent + 1;                 // start
                bunnyLoops[i][1] = num_buns - bunnyLoops.length + i + 1; // limit
                bunnyLoops[i][2] = bunnyLoops[i][0];                     // current
                outerLoopCurrent = bunnyLoops[i][2];
            }

            key++;
        }

        // converting list of lists to 2D array
        int[][] result = new int[num_buns][bunnyKeyLists.get(0).size()];
        for (int i = 0; i < bunnyKeyLists.size(); i++) {
            List<Integer> bunnyKeyList = bunnyKeyLists.get(i);
            for (int j = 0; j < bunnyKeyList.size(); j++) {
                result[i][j] = bunnyKeyList.get(j);
            }
        }
        return result;
    }
}