package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.BioEmploymentHistory;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.OccupationType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class MppApplyDetailModel implements Serializable {

    private Long id;
    private Long mppPeriodeId;
    private String mppPeriodeName;
    private String mppCode;
    private String mppName;
    private Date recruitMppMoth;
    private Long numberOfActualPosition;
    private Long numberOfPlanningPosition;
    private Long departemenId;
    private Long jabatanId;

    public MppApplyDetailModel() {
        this.numberOfActualPosition = 0l;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMppPeriodeId() {
        return mppPeriodeId;
    }

    public void setMppPeriodeId(Long mppPeriodeId) {
        this.mppPeriodeId = mppPeriodeId;
    }

    public String getMppPeriodeName() {
        return mppPeriodeName;
    }

    public void setMppPeriodeName(String mppPeriodeName) {
        this.mppPeriodeName = mppPeriodeName;
    }

    public String getMppCode() {
        return mppCode;
    }

    public void setMppCode(String mppCode) {
        this.mppCode = mppCode;
    }

    public String getMppName() {
        return mppName;
    }

    public void setMppName(String mppName) {
        this.mppName = mppName;
    }

    public Date getRecruitMppMoth() {
        return recruitMppMoth;
    }

    public void setRecruitMppMoth(Date recruitMppMoth) {
        this.recruitMppMoth = recruitMppMoth;
    }

    public Long getNumberOfActualPosition() {
        return numberOfActualPosition;
    }

    public void setNumberOfActualPosition(Long numberOfActualPosition) {
        this.numberOfActualPosition = numberOfActualPosition;
    }

    public Long getNumberOfPlanningPosition() {
        return numberOfPlanningPosition;
    }

    public void setNumberOfPlanningPosition(Long numberOfPlanningPosition) {
        this.numberOfPlanningPosition = numberOfPlanningPosition;
    }

    public Long getDepartemenId() {
        return departemenId;
    }

    public void setDepartemenId(Long departemenId) {
        this.departemenId = departemenId;
    }

    public Long getJabatanId() {
        return jabatanId;
    }

    public void setJabatanId(Long jabatanId) {
        this.jabatanId = jabatanId;
    }
    
    
}
