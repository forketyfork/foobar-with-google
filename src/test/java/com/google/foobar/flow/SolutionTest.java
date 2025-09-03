package com.google.foobar.flow;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    @ParameterizedTest
    @MethodSource("source")
    void test(int[] entrances, int[] exits, int[][] path, int output) {
        int actual = Solution.solution(entrances, exits, path);
        assertEquals(output, actual);
    }

    public static Stream<Object[]> source() {
        return Stream.of(

                new Object[]{
                        new int[]{0, 1},
                        new int[]{4, 5},
                        new int[][]{
                                {0, 0, 4, 6, 0, 0},
                                {0, 0, 5, 2, 0, 0},
                                {0, 0, 0, 0, 4, 4},
                                {0, 0, 0, 0, 6, 6},
                                {0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0}
                        },
                        16
                },

                new Object[]{
                        new int[]{0},
                        new int[]{3},
                        new int[][]{
                                {0, 7, 0, 0},
                                {0, 0, 6, 0},
                                {0, 0, 0, 8},
                                {9, 0, 0, 0}
                        },
                        6
                }

        );
    }

}

