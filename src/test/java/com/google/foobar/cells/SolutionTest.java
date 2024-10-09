package com.google.foobar.cells;

import com.google.foobar.cells.Solution;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

//    @ParameterizedTest
//    @MethodSource("source")
//    void test(int[] entrances, int[] exits, int[][] path, int output) {
//        int actual = Solution.solution(entrances, exits, path);
//        assertEquals(output, actual);
//    }

    @Test
    void test() {
        System.out.println(Arrays.deepToString(Solution.solution(5, 3)));
    }
}