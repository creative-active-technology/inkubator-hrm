package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author rizkykojek
 */
public class GolonganJabatanModel implements Serializable {

    private Long id;
    private String code;
    private Long pangkatId;
    private Long paySalaryGradeId;
    private Boolean overtime;
    private Map<Long, String> pangkats;
    private Integer pointMin;
    private Integer pointMid;
    private Integer pointMax;
    private BigDecimal ratioCompact;
    private Map<String, Long> dropDownPaySalaryGrade;

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

    public Long getPangkatId() {
        return pangkatId;
    }

    public void setPangkatId(Long pangkatId) {
        this.pangkatId = pangkatId;
    }

    public Boolean getOvertime() {
        return overtime;
    }

    public void setOvertime(Boolean overtime) {
        this.overtime = overtime;
    }

    public Map<Long, String> getPangkats() {
        return pangkats;
    }

    public void setPangkats(Map<Long, String> pangkats) {
        this.pangkats = pangkats;
    }

    public Integer getPointMin() {
        return pointMin;
    }

    public void setPointMin(Integer pointMin) {
        this.pointMin = pointMin;
    }

    public Integer getPointMid() {
        return pointMid;
    }

    public void setPointMid(Integer pointMid) {
        this.pointMid = pointMid;
    }

    public Integer getPointMax() {
        return pointMax;
    }

    public void setPointMax(Integer pointMax) {
        this.pointMax = pointMax;
    }

    public BigDecimal getRatioCompact() {
        return ratioCompact;
    }

    public void setRatioCompact(BigDecimal ratioCompact) {
        this.ratioCompact = ratioCompact;
    }

    public Long getPaySalaryGradeId() {
        return paySalaryGradeId;
    }

    public void setPaySalaryGradeId(Long paySalaryGradeId) {
        this.paySalaryGradeId = paySalaryGradeId;
    }

    public Map<String, Long> getDropDownPaySalaryGrade() {
        return dropDownPaySalaryGrade;
    }

    public void setDropDownPaySalaryGrade(Map<String, Long> dropDownPaySalaryGrade) {
        this.dropDownPaySalaryGrade = dropDownPaySalaryGrade;
    }

    
}
