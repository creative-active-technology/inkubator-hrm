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
//                System.out.println(m.start());
//                System.out.println(m.group());
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
}
