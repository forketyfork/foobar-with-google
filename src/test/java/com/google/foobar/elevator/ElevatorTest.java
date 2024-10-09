package com.google.foobar.elevator;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ElevatorTest {

    @ParameterizedTest
    @MethodSource("source")
    void test(String[] input, String[] output) {
        assertArrayEquals(output, Elevator.solution(input));
    }

    public static Stream<Object[]> source() {
        return Stream.of(
                new Object[] {
                        new String[] {},
                        new String[] {}
                },
                new Object[] {
                        new String[] {"1"},
                        new String[] {"1"}
                },
                new Object[] {
                        new String[] {"1.0.0", "1.0", "1"},
                        new String[] {"1", "1.0", "1.0.0"}
                },
                new Object[] {
                        new String[] {"1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"},
                        new String[] {"0.1", "1.1.1", "1.2", "1.2.1", "1.11", "2", "2.0", "2.0.0"}
                },
                new Object[] {
                        new String[] {"1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"},
                        new String[] {"1.0", "1.0.2", "1.0.12", "1.1.2", "1.3.3"}
                }
        );
    }

}