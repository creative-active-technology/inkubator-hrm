package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class MppApplicationHistoryFormModel implements Serializable {
	private Long id;
    private Long mppApplyDetailId;
    private Long jabatanId;
    private Long actual;
    private Long mpp;
    private Long difference;
    private Date periodeStart;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMppApplyDetailId() {
		return mppApplyDetailId;
	}
	public void setMppApplyDetailId(Long mppApplyDetailId) {
		this.mppApplyDetailId = mppApplyDetailId;
	}
	
	public Long getJabatanId() {
		return jabatanId;
	}
	public void setJabatanId(Long jabatanId) {
		this.jabatanId = jabatanId;
	}
	public Long getActual() {
		return actual;
	}
	public void setActual(Long actual) {
		this.actual = actual;
	}
	public Long getMpp() {
		return mpp;
	}
	public void setMpp(Long mpp) {
		this.mpp = mpp;
	}
	public Long getDifference() {
		return difference;
	}
	public void setDifference(Long difference) {
		this.difference = difference;
	}
	public Date getPeriodeStart() {
		return periodeStart;
	}
	public void setPeriodeStart(Date periodeStart) {
		this.periodeStart = periodeStart;
	}
   
}
