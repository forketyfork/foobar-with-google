package com.google.foobar.locks;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    @ParameterizedTest
    @MethodSource("source")
    void test(int[] l, int result) {
        assertEquals(result, Solution.solution(l));
    }

    public static Stream<Object[]> source() {
        return Stream.of(
                new Object[] {new int[] {1, 1, 1}, 1},
                new Object[] {new int[] {1, 2, 3, 4, 5, 6}, 3}
        );
    }

}