/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Deni Husni FR
 */
public class StringsUtils extends StringUtils {

    public static Boolean isHaveUpperCase(String str) {
        Boolean isCondition = Boolean.FALSE;
        for (int i = str.length() - 1; i >= 0; i--) {

            if (Character.isUpperCase(str.charAt(i))) {
                isCondition = Boolean.TRUE;
            }
        }
        return isCondition;

    }

    public static Boolean isHaveLowerCase(String str) {
        Boolean isCondition = Boolean.FALSE;
        for (int i = str.length() - 1; i >= 0; i--) {

            if (Character.isLowerCase(str.charAt(i))) {
                isCondition = Boolean.TRUE;
            }
        }
        return isCondition;

    }

    public static boolean isContainsSpecialChar(String toCheck) {
        boolean isContainsSC = false;
        if (toCheck != null && !toCheck.equals("")) {
            Matcher m = Pattern.compile("[\\W]").matcher(toCheck);
            while (m.find()) {
                isContainsSC = true;

            }
        }
        return isContainsSC;
    }

    public static boolean isHaveNumber(String toCheck) {
        Boolean isCondition = Boolean.FALSE;
        for (int i = toCheck.length() - 1; i >= 0; i--) {

            if (Character.isDigit(toCheck.charAt(i))) {
                isCondition = Boolean.TRUE;
            }
        }
        return isCondition;
    }

    /**
     * <p>
     * Potong kalimat berdasarkan inputan panjang karakter, tetapi dipotong
     * per-kata</p>
     *
     * <pre>
     * StringUtils.slicePerWord("rizky kojek cute", 13)   = "rizky kojek"
     * StringUtils.slicePerWord("rizky kojek cute", 12)   = "rizky kojek"
     * StringUtils.slicePerWord("rizky kojek cute", 11)   = "rizky kojek"
     * StringUtils.slicePerWord("rizky kojek cute", 10)   = "rizky"
     * StringUtils.slicePerWord("rizky kojek cute", 5)   = "rizky"
     * StringUtils.slicePerWord("rizky kojek cute", 3)   = "riz"
     * </pre>
     *
     * @param str the String to get the substring from, may be null
     * @param len the maximum character
     * @return string which already slice
     */
    public static String slicePerWord(String str, int len) {

        String result = EMPTY;
        if (str == null || str.length() == 0) {
            return result;
        }

        String chr = substring(str, len - 1, len);
        result = substring(str, 0, len);
        if (isAlphanumeric(chr) && !equals(substring(str, len, len + 1), " ")) {
            result = substringBeforeLast(result, " ");
        }

        return result;
    }

    public static boolean isContain(String source, String subItem) {
        String pattern = "\\b" + subItem + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        return m.find();
    }
}
