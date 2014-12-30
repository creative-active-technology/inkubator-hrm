/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.util;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;
import java.text.DateFormatSymbols;
import java.util.Locale;

/**
 *
 * @author deni
 */
public class MonthAsStringUtil {

    public static String getMonth(Integer month) {
        DateFormatSymbols dfs = new DateFormatSymbols(new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        return dfs.getMonths()[month - 1];
    }
    
}
