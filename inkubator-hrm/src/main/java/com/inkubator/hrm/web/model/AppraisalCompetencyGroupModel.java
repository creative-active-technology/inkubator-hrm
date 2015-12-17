package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.DualListModel;

import com.inkubator.hrm.entity.AppraisalCompetencyType;
import com.inkubator.hrm.entity.KlasifikasiKerja;

/**
 *
 * @author rizkykojek
 */
public class AppraisalCompetencyGroupModel implements Serializable {

	private Long id;
	private String code;
    private String name;
    private String description;
    private Long competencyTypeId;
    private DualListModel<KlasifikasiKerja> dualListModelKlasifikasiKerja = new DualListModel<>();
    
    //not persisted
    private List<AppraisalCompetencyType> listCompetencyType = new ArrayList<>();
    
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
	public Long getCompetencyTypeId() {
		return competencyTypeId;
	}
	public void setCompetencyTypeId(Long competencyTypeId) {
		this.competencyTypeId = competencyTypeId;
	}
	public DualListModel<KlasifikasiKerja> getDualListModelKlasifikasiKerja() {
		return dualListModelKlasifikasiKerja;
	}
	public void setDualListModelKlasifikasiKerja(DualListModel<KlasifikasiKerja> dualListModelKlasifikasiKerja) {
		this.dualListModelKlasifikasiKerja = dualListModelKlasifikasiKerja;
	}
	public List<AppraisalCompetencyType> getListCompetencyType() {
		return listCompetencyType;
	}
	public void setListCompetencyType(List<AppraisalCompetencyType> listCompetencyType) {
		this.listCompetencyType = listCompetencyType;
	}
    
}
