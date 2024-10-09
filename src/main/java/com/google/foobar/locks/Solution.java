package com.google.foobar.locks;

/**
 * Level 3.
 */
public class Solution {

    public static int solution(int[] l) {

        int count = 0;
        for (int i = 0; i < l.length - 2; i++) {
            for (int j = i + 1; j < l.length - 1; j++) {
                if (l[j] % l[i] == 0) {
                    for (int k = j + 1; k < l.length; k++) {
                        if (l[k] % l[j] == 0) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

}
