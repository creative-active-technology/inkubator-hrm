/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.util;

/**
 *
 * @author Deni Husni FR
 */
public class RomanovUtil {

    final static char symbol[] = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
    final static int value[] = {1000, 500, 100, 50, 10, 5, 1};

    public static int valueOf(String roman) {
        roman = roman.toUpperCase();
        if (roman.length() == 0) {
            return 0;
        }
        for (int i = 0; i < symbol.length; i++) {
            int pos = roman.indexOf(symbol[i]);
            if (pos >= 0) {
                return value[i] - valueOf(roman.substring(0, pos)) + valueOf(roman.substring(pos + 1));
            }
        }
        throw new IllegalArgumentException("Invalid Roman Symbol.");
    }

    private static final int[] numbers = {1000, 900, 500, 400, 100, 90,
        50, 40, 10, 9, 5, 4, 1};

    private static final String[] letters = {"M", "CM", "D", "CD", "C", "XC",
        "L", "XL", "X", "IX", "V", "IV", "I"};

    public static String convertToRoman(int N) {
        String roman = "";
        for (int i = 0; i < numbers.length; i++) {
            while (N >= numbers[i]) {
                roman += letters[i];
                N -= numbers[i];
            }
        }
        return roman;
    }
}
