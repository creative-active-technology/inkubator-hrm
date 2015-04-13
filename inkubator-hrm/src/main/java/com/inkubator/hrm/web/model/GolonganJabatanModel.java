package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Map;


/**
 *
 * @author rizkykojek
 */
public class GolonganJabatanModel implements Serializable{

	private Long id;
    private String code;
    private Long pangkatId;
    private Boolean overtime;
    private Map<Long, String> pangkats;
    
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
	
	
}
