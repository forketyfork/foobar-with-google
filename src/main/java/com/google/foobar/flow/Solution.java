package com.google.foobar.flow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * Level 4.
 */
public class Solution {

    private static int N;

    public static int solution(int[] entrances, int[] exits, int[][] path) {

        // Solution uses Ford-Fulkerson algorithm to find the maximum flow in the network.
        // Since there are multiple sources and sinks, the graph is enriched
        // with a "super-source" and a "super-sink"

        int n = path.length;
        N = n + 2;

        // creating a single source and a single sink
        int[][] matrix = new int[N][N];
        for (int i = 0; i < n; i++) {
            System.arraycopy(path[i], 0, matrix[i + 1], 1, n);
        }

        for (int entrance : entrances) {
            for (int j = 0; j < n; j++) {
                matrix[0][entrance + 1] += path[entrance][j];
            }
        }

        for (int exit : exits) {
            for (int[] row : path) {
                matrix[exit + 1][N - 1] += row[exit];
            }
        }

        int maxFlow = 0;

        int[] parent = new int[N];
        Arrays.fill(parent, -1);

        int source = 0;
        int sink = N - 1;

        while (hasPathBfs(matrix, source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            int s = sink;
            while (s != source) {
                pathFlow = Math.min(pathFlow, matrix[parent[s]][s]);
                s = parent[s];
            }
            maxFlow += pathFlow;

            int v = sink;
            while (v != source) {
                int u = parent[v];
                matrix[u][v] -= pathFlow;
                matrix[v][u] += pathFlow;
                v = parent[v];
            }
        }

        return maxFlow;
    }

    public static boolean hasPathBfs(int[][] matrix, int s, int t, int[] parent) {
        boolean[] visited = new boolean[N];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(s);
        visited[s] = true;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int i = 0; i < N; i++) {
                if (!visited[i] && matrix[u][i] > 0) {
                    queue.offer(i);
                    visited[i] = true;
                    parent[i] = u;
                }
            }
        }
        return visited[t];
    }

}
