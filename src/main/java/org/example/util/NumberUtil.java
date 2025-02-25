package org.example.util;

public class NumberUtil {

    public static String stripTrailingZeros(Number number) {
        return String.valueOf(number)
                .replaceAll("\\.0+$", "")
                .replaceAll("(\\.\\d*?)0+$", "$1");
    }
}
