/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

/**
 *
 * @author Deni
 */
public class TravelComponentCostRateModel {
    private Long id;
    private String code;
    private long costCenterId;
    private long golonganJabatanId;
    private long travelComponentId;
    private long travelZoneId;
    private Double defaultRateId;
    private String descriptionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getCostCenterId() {
        return costCenterId;
    }

    public void setCostCenterId(long costCenterId) {
        this.costCenterId = costCenterId;
    }

    public long getGolonganJabatanId() {
        return golonganJabatanId;
    }

    public void setGolonganJabatanId(long golonganJabatanId) {
        this.golonganJabatanId = golonganJabatanId;
    }

    public long getTravelComponentId() {
        return travelComponentId;
    }

    public void setTravelComponentId(long travelComponentId) {
        this.travelComponentId = travelComponentId;
    }

    public long getTravelZoneId() {
        return travelZoneId;
    }

    public void setTravelZoneId(long travelZoneId) {
        this.travelZoneId = travelZoneId;
    }

    public Double getDefaultRateId() {
        return defaultRateId;
    }

    public void setDefaultRateId(Double defaultRateId) {
        this.defaultRateId = defaultRateId;
    }

    public String getDescriptionId() {
        return descriptionId;
    }

    public void setDescriptionId(String descriptionId) {
        this.descriptionId = descriptionId;
    }
    
    
}
