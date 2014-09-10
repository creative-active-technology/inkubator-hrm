package com.inkubator.hrm.web.model;

import java.io.Serializable;
/**
 *
 * @author Taufik Hidayat
 */
public class BenefitGroupRenumerationModel implements Serializable {

    private String benefitGroup;
    private String nominal;

    public String getBenefitGroup() {
        return benefitGroup;
    }

    public void setBenefitGroup(String benefitGroup) {
        this.benefitGroup = benefitGroup;
    }

    
    
    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    

    
}
