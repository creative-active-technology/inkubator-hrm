package com.inkubator.hrm.web.model;

import java.util.List;

import com.inkubator.hrm.entity.Pangkat;


/**
 *
 * @author rizkykojek
 */
public class GolonganJabatanModel {

	private Long id;
    private String name;
    private String code;
    private Integer level;
    private Long pangkatId;
    private Boolean overtime;
    private List<Pangkat> pangkats;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
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
	public List<Pangkat> getPangkats() {
		return pangkats;
	}
	public void setPangkats(List<Pangkat> pangkats) {
		this.pangkats = pangkats;
	}
	
}
