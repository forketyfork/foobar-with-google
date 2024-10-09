package com.google.foobar.janice;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    @ParameterizedTest
    @MethodSource("source")
    void test(String from, String to) {
        assertEquals(to, Solution.solution(from));
    }

    public static Stream<String[]> source() {
        return Stream.of(
                new String[] {null, null},
                new String[] {"a", "z"},
                new String[] {"z", "a"},
                new String[] {"A", "A"},
                new String[] {"", ""},
                new String[] {
                        "Yvzs! I xzm'g yvorvev Lzmxv olhg srh qly zg gsv xlolmb!!",
                        "Yeah! I can't believe Lance lost his job at the colony!!"
                },
                new String[] {
                        "wrw blf hvv ozhg mrtsg'h vkrhlwv?",
                        "did you see last night's episode?"
                }
        );
    }

}