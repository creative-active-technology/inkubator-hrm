/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.DateTime;

/**
 *
 * @author Deni Husni FR
 */
public class KodefikasiUtil {

    public static String getKodefikasi(int maxNumerData, String pattern) {
        String afterString = StringsUtils.substringAfter(pattern, "[");
        String beforeString = StringsUtils.substringBefore(pattern, "[");
        String removeParenties1 = StringsUtils.remove(afterString, "[");
        String removeParenties2 = StringsUtils.remove(removeParenties1, "]");
        int numberOfYear = StringsUtils.countMatches(removeParenties2, "Y");
        int numberofN = StringsUtils.countMatches(removeParenties2, "N");
        
        int currentNumber = maxNumerData + 1;
        int numberofNEx = String.valueOf(currentNumber).length();
        
        int selisih = numberofN - numberofNEx;
        
        String montInString = null;
        String yearInString = null;
        String dayInString = null;
        String numberInString = null;
        if (selisih == 0) {
            numberInString = String.valueOf(currentNumber);
        } else {
            numberInString = StringsUtils.leftPad(String.valueOf(currentNumber), numberofN, "0");
        }
        DateTime jodaTime = new DateTime();
        int year = jodaTime.getYear();
        int month = jodaTime.getMonthOfYear();
        int day = jodaTime.getDayOfMonth();

        if (month < 10) {
            montInString = "0" + month;
        } else {
            montInString = String.valueOf(month);
        }
        if (numberOfYear == 2) {
            year = Integer.parseInt(new SimpleDateFormat("YY").format(new Date()));
            if (year < 10) {
                yearInString = "0" + year;
            } else {
                yearInString = String.valueOf(year);
            }
        } else {
            yearInString = String.valueOf(year);
        }

        if (day < 10) {
            dayInString = "0" + day;
        } else {
            dayInString = String.valueOf(day);
        }
        String output1 = StringsUtils.replaceOnce(removeParenties2, "Y", yearInString);
        String output2 = StringsUtils.remove(output1, "Y");
        String output3 = StringsUtils.replaceOnce(output2, "M", montInString);
        String output4 = StringsUtils.remove(output3, "M");
        String output5 = StringsUtils.replaceOnce(output4, "D", dayInString);
        String output6 = StringsUtils.remove(output5, "D");
        String output7 = StringsUtils.replaceOnce(output6, "N", numberInString);
        String output8 = StringsUtils.remove(output7, "N");
        return beforeString+output8;

    }
}
