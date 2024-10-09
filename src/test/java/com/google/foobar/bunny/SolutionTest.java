package com.google.foobar.bunny;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    @ParameterizedTest
    @MethodSource("source")
    void test(long x, long y, String result) {
        assertEquals(result, Solution.solution(x, y));
    }

    public static Stream<Object[]> source() {
        return Stream.of(
                new Object[] {3, 2, "9"},
                new Object[] {5, 10, "96"},
                new Object[] {1, 1, "1"},
                new Object[] {4, 1, "10"},
                new Object[] {1, 4, "7"}
        );
    }

}