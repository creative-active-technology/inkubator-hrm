/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.util;

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
                isCondition= Boolean.TRUE;
            } 
        }
        return isCondition;

    }
    
      public static Boolean isHaveLowerCase(String str) {
        Boolean isCondition = Boolean.FALSE;
        for (int i = str.length() - 1; i >= 0; i--) {

            if (Character.isLowerCase(str.charAt(i))) {
                isCondition= Boolean.TRUE;
            } 
        }
        return isCondition;

    }
}
