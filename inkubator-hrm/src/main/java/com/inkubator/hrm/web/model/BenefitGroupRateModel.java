package com.inkubator.hrm.web.model;

import java.io.Serializable;
/**
 *
 * @author Taufik Hidayat
 */
public class BenefitGroupRateModel implements Serializable {

    private Long id;
    private Long benefitGroupId;
    private Long golonganJabatanId;
    private Double nominal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBenefitGroupId() {
        return benefitGroupId;
    }

    public void setBenefitGroupId(Long benefitGroupId) {
        this.benefitGroupId = benefitGroupId;
    }

    public Long getGolonganJabatanId() {
        return golonganJabatanId;
    }

    public void setGolonganJabatanId(Long golonganJabatanId) {
        this.golonganJabatanId = golonganJabatanId;
    }

    public Double getNominal() {
        return nominal;
    }

    public void setNominal(Double nominal) {
        this.nominal = nominal;
    }

    
}
