/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class RecruitMppApplySearchParameter extends SearchParameter {
    private String recruitMppApplyName;
    private String recruitMppApplyCode;   

    public String getRecruitMppApplyName() {
         if (StringUtils.equalsIgnoreCase(getKeyParam(), "recruitMppApplyName")) {
            recruitMppApplyName = getParameter();
        } else {
            recruitMppApplyName = null;
        }
        return recruitMppApplyName;
    }

    public void setRecruitMppApplyName(String recruitMppApplyName) {
        this.recruitMppApplyName = recruitMppApplyName;
    }

    public String getRecruitMppApplyCode() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "recruitMppApplyCode")) {
            recruitMppApplyCode = getParameter();
        } else {
            recruitMppApplyCode = null;
        }
        return recruitMppApplyCode;
    }

    public void setRecruitMppApplyCode(String recruitMppApplyCode) {
        this.recruitMppApplyCode = recruitMppApplyCode;
    }
    
}
