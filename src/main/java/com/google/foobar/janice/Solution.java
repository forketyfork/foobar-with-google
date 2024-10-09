package com.google.foobar.janice;

/**
 * Level 1.
 */
public class Solution {

    public static String solution(String x) {
        if (x == null) {
            return null;
        }
        char[] result = new char[x.length()];
        for (int i = 0; i < x.length(); i++) {
            char c = x.charAt(i);
            if (c >= 'a' && c <= 'z') {
                c = (char) ('z' - (c - 'a'));
            }
            result[i] = c;
        }
        return new String(result);
    }

}
