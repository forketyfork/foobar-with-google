package com.google.foobar.fuel;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SolutionTest {

    @ParameterizedTest
    @MethodSource("source")
    void test(int[][] input, int[] output) {
        int[] actual = Solution.solution(input);
        assertArrayEquals(output, actual);
    }

    public static Stream<Object[]> source() {
        return Stream.of(
                new Object[]{
                        new int[][]{},
                        new int[]{}
                },
                new Object[]{
                        new int[][]{
                                {0}
                        },
                        new int[]{1, 1}
                },
                new Object[]{
                        new int[][]{
                                {5}
                        },
                        new int[]{1, 1}
                },
                new Object[]{
                        new int[][]{
                                {0, 1, 0, 0, 0, 1},
                                {4, 0, 0, 3, 2, 0},
                                {0, 0, 1, 0, 0, 0},
                                {0, 0, 0, 1, 0, 0},
                                {0, 0, 0, 0, 1, 0},
                                {0, 0, 0, 0, 0, 1},
                        },
                        new int[]{0, 3, 2, 9, 14}
                },
                new Object[]{
                        new int[][]{
                                {0, 1, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0},
                        },
                        new int[]{1, 0, 0, 0, 0, 1}
                },
                new Object[]{
                        new int[][]{
                                {1, 0, 0, 0, 0, 0},
                                {0, 1, 0, 0, 0, 0},
                                {0, 0, 1, 0, 0, 0},
                                {0, 0, 0, 1, 0, 0},
                                {0, 0, 0, 0, 1, 0},
                                {0, 0, 0, 0, 0, 1},
                        },
                        new int[]{1, 0, 0, 0, 0, 0, 1}
                },
                new Object[]{
                        new int[][]{
                                {0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0},
                        },
                        new int[]{1, 0, 0, 0, 0, 0, 1}
                },
                new Object[]{
                        new int[][]{
                                {0, 1, 0, 0, 0, 1},
                                {4, 0, 0, 3, 2, 0},
                                {0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0},
                        },
                        new int[]{0, 3, 2, 9, 14}
                },
                new Object[]{
                        new int[][]{
                                {0, 1, 0, 0, 0, 1},
                                {4, 0, 0, 5, 0, 0},
                                {0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0},
                        },
                        new int[]{0, 5, 0, 9, 14}
                },
                new Object[]{
                        new int[][]{
                                {0, 0, 0, 0, 1, 1},
                                {0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0},
                                {4, 2, 0, 3, 0, 0},
                                {0, 0, 0, 0, 0, 0},
                        },
                        new int[]{0, 3, 2, 9, 14}
                },
                /* TODO fix this test case
                new Object[] {
                        new int[][] {
                                {0, 2, 1, 0, 0},
                                {0, 0, 0, 3, 4},
                                {0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0}
                        },
                        new int[] {7, 6, 8, 21}

                }, */
                new Object[]{
                        new int[][]{
                                {0, 0, 1, 0, 2},
                                {0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0},
                                {0, 4, 0, 3, 0},
                        },
                        new int[]{7, 6, 8, 21}
                },
                /* TODO fix this test case
                new Object[] {
                        new int[][] {
                                {0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                                {0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0},
                        },
                        new int[] {1, 1, 1, 1, 4}
                }, */
                new Object[]{
                        new int[][]{
                                {0, 1},
                                {0, 0},
                        },
                        new int[]{1, 1}
                },
                new Object[]{
                        new int[][]{
                                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
                        },
                        new int[]{1, 1}
                },
                new Object[]{
                        new int[][]{
                                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
                        },
                        new int[]{1, 1, 2}
                }

        );
    }

}