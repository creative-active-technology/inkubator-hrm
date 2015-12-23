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
    private String careerAwardTypeName;

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

	public String getCareerAwardTypeName() {
		if(StringUtils.equalsIgnoreCase(getKeyParam(), "careerAwardTypeName")){
			careerAwardTypeName = getParameter();
        } else {
        	careerAwardTypeName = null;
        }
        return careerAwardTypeName;
	}

	public void setCareerAwardTypeName(String careerAwardTypeName) {
		this.careerAwardTypeName = careerAwardTypeName;
	}

}
