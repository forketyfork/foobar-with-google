package com.google.foobar.lasers;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Level 5.
 */
public class Solution {

    private static final BigDecimal TWO = BigDecimal.valueOf(2);

    // value of sqrt(2)-1 with good enough precision (100 digits)
    private static final BigDecimal SQRT_2_1 = new BigDecimal(
            "0.4142135623730950488016887242096980785696718753769480731766797379907324784621070388503875343276415727");

    public static String solution(String s) {
        return solution(new BigDecimal(s)).toString();
    }

    private static BigDecimal solution(BigDecimal n) {
        // using a recursive algorithm based on an idea from https://math.stackexchange.com/questions/2052179/
        if (n.equals(BigDecimal.ZERO)) {
            return BigDecimal.ZERO;
        }
        // this value is always less than n, this ensures that the recursion will terminate
        BigDecimal n1 = n.multiply(SQRT_2_1).setScale(0, RoundingMode.FLOOR);
        return n.multiply(n1)
                // n * (n + 1) / 2 is always exact, so no need to round
                .add(n.multiply(n.add(BigDecimal.ONE)).divide(TWO, RoundingMode.UNNECESSARY))
                .subtract(n1.multiply(n1.add(BigDecimal.ONE)).divide(TWO, RoundingMode.UNNECESSARY))
                .subtract(solution(n1));
    }
}
