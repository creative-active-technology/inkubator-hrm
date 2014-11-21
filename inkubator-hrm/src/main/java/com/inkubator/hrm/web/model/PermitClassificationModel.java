package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Taufik Hidayat
 */
public class PermitClassificationModel implements Serializable {

    private Long id;
    private String code;
    private String name;
    private String description;
    private Long attendanceStatusId;
    private Boolean status;
    private Integer calculation;
    private Integer basePeriod;
    private Integer availibility;
    private Integer dateIncreased;
    private Integer quantity;
    private Integer limitByDay;
    private Boolean onePerEmployee;
    private Integer maxPerMonth;
    private Double salaryCut;
    private Boolean attachmentRequired;
    private Boolean isActive;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAttendanceStatusId() {
        return attendanceStatusId;
    }

    public void setAttendanceStatusId(Long attendanceStatusId) {
        this.attendanceStatusId = attendanceStatusId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCalculation() {
        return calculation;
    }

    public void setCalculation(Integer calculation) {
        this.calculation = calculation;
    }

    public Integer getBasePeriod() {
        return basePeriod;
    }

    public void setBasePeriod(Integer basePeriod) {
        this.basePeriod = basePeriod;
    }

    public Integer getAvailibility() {
        return availibility;
    }

    public void setAvailibility(Integer availibility) {
        this.availibility = availibility;
    }

    public Integer getDateIncreased() {
        return dateIncreased;
    }

    public void setDateIncreased(Integer dateIncreased) {
        this.dateIncreased = dateIncreased;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getLimitByDay() {
        return limitByDay;
    }

    public void setLimitByDay(Integer limitByDay) {
        this.limitByDay = limitByDay;
    }

    public Boolean getOnePerEmployee() {
        return onePerEmployee;
    }

    public void setOnePerEmployee(Boolean onePerEmployee) {
        this.onePerEmployee = onePerEmployee;
    }

    public Integer getMaxPerMonth() {
        return maxPerMonth;
    }

    public void setMaxPerMonth(Integer maxPerMonth) {
        this.maxPerMonth = maxPerMonth;
    }

    public Double getSalaryCut() {
        return salaryCut;
    }

    public void setSalaryCut(Double salaryCut) {
        this.salaryCut = salaryCut;
    }

    public Boolean getAttachmentRequired() {
        return attachmentRequired;
    }

    public void setAttachmentRequired(Boolean attachmentRequired) {
        this.attachmentRequired = attachmentRequired;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}
