/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Deni
 */
public class EmpPersonAchievementSearchParameter extends SearchParameter{
    private String empData;
    private String achievementName;

    public String getEmpData() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "empData")){
			empData = getParameter();
        } else {
                empData = null;
        }
        return empData;
    }

    public void setEmpData(String empData) {
        this.empData = empData;
    }

    public String getAchievementName() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "achievementName")){
			achievementName = getParameter();
        } else {
                achievementName = null;
        }
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }
    
    
}
