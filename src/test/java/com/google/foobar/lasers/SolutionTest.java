package com.google.foobar.lasers;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    @ParameterizedTest
    @MethodSource("source")
    void test(String n, String result) {
        assertEquals(result, new Solution().solution(n));
    }

    public static Stream<String[]> source() {
        return Stream.of(
                new String[] {"5", "19"},
                new String[] {"77", "4208"}
        );
    }

}