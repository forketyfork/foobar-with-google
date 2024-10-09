package com.google.foobar.elevator;

import java.util.Arrays;

/**
 * Level 2.
 */
public class Elevator {

    public static String[] solution(String[] l) {

        String[] result = new String[l.length];

        System.arraycopy(l, 0, result, 0, l.length);

        Arrays.sort(result, (v1, v2) -> {
            Integer[] v1array = parse(v1);
            Integer[] v2array = parse(v2);

            for (int i = 0; i < 3; i++) {
                Integer d1 = v1array[i];
                Integer d2 = v2array[i];
                if (d1 == null && d2 == null) {
                    return 0;
                }
                if (d1 == null) {
                    return -1;
                }
                if (d2 == null) {
                    return 1;
                }
                if (d1.equals(d2)) {
                    continue;
                }
                return d1.compareTo(d2);
            }

            return 0;
        });

        return result;
    }

    private static Integer[] parse(String version) {
        Integer[] array = new Integer[3];
        int dotIndex = version.indexOf('.');
        if (dotIndex == -1) {
            array[0] = Integer.parseInt(version);
        }
        else {
            array[0] = Integer.parseInt(version.substring(0, dotIndex));
            int secondDotIndex = version.indexOf('.', dotIndex + 1);
            if (secondDotIndex == -1) {
                array[1] = Integer.parseInt(version.substring(dotIndex + 1));
            }
            else {
                array[1] = Integer.parseInt(version.substring(dotIndex + 1, secondDotIndex));
                array[2] = Integer.parseInt(version.substring(secondDotIndex + 1));
            }
        }
        return array;
    }

}
