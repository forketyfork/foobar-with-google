package com.google.foobar.escape;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Level 3.
 */
public class Solution {

    private static final int[][] directions = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public static int solution(int[][] map) {
        int n = map.length;
        int m = map[0].length;
        int n_1 = n - 1;
        int m_1 = m - 1;

        boolean[][][] visited = new boolean[n][m][2];

        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{0, 0, 0});

        int shortestPath = 1;

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int q = 0; q < queueSize; q++) {
                int[] cell = queue.poll();
                int i = cell[0];
                int j = cell[1];
                int brokeAWall = cell[2];

                if (i == n_1 && j == m_1) {
                    return shortestPath;
                }

                for (int[] direction : directions) {
                    int nextI = i + direction[0];
                    int nextJ = j + direction[1];
                    if (nextI >= 0 && nextI < n && nextJ >= 0 && nextJ < m
                            && !visited[nextI][nextJ][brokeAWall]) {
                        visited[nextI][nextJ][brokeAWall] = true;
                        if (map[nextI][nextJ] == 0) {
                            queue.offer(new int[]{nextI, nextJ, brokeAWall});
                        } else if (brokeAWall == 0) {
                            queue.offer(new int[]{nextI, nextJ, 1});
                        }
                    }
                }
            }
            shortestPath++;
        }

        throw new RuntimeException("Shouldn't happen");

    }

}
