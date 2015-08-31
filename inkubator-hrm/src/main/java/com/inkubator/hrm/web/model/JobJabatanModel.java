/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.Faculty;
import com.inkubator.hrm.entity.JabatanDeskripsi;
import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.entity.Major;
import com.inkubator.hrm.entity.OccupationType;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class JobJabatanModel implements Serializable {

    private Long id;
    private Long jabatanAtasanId;
    private String kodeJabatan;
    private String namaJabatan;
    private Long golonganJabatanId;
    private Long unitKerjaId;
    private Long departementId;
    private Long posBiayaId;
    private String tujuanJabatan;
    private Long salaryGradeId;
    private String NIK;
    private List<EducationLevel> listEducationLevel = new ArrayList<EducationLevel>();
    private List<KlasifikasiKerja> listKlasifikasiKerja = new ArrayList<KlasifikasiKerja>();
    private List<OccupationType> listOccupationType = new ArrayList<OccupationType>();
    private List<Major> listMajor = new ArrayList<Major>();
    private List<Faculty> listFaculties = new ArrayList<Faculty>();
    private List<JabatanDeskripsi> listJabatanDeskripsi = new ArrayList<JabatanDeskripsi>();
    private List<JabatanDeskripsiModel> listJabatanDeskripsiModel = new ArrayList<JabatanDeskripsiModel>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJabatanAtasanId() {
        return jabatanAtasanId;
    }

    public void setJabatanAtasanId(Long jabatanAtasanId) {
        this.jabatanAtasanId = jabatanAtasanId;
    }

    public String getKodeJabatan() {
        return kodeJabatan;
    }

    public void setKodeJabatan(String kodeJabatan) {
        this.kodeJabatan = kodeJabatan;
    }

    public String getNamaJabatan() {
        return namaJabatan;
    }

    public void setNamaJabatan(String namaJabatan) {
        this.namaJabatan = namaJabatan;
    }

    public Long getGolonganJabatanId() {
        return golonganJabatanId;
    }

    public void setGolonganJabatanId(Long golonganJabatanId) {
        this.golonganJabatanId = golonganJabatanId;
    }

    public Long getUnitKerjaId() {
        return unitKerjaId;
    }

    public void setUnitKerjaId(Long unitKerjaId) {
        this.unitKerjaId = unitKerjaId;
    }

    public Long getDepartementId() {
        return departementId;
    }

    public void setDepartementId(Long departementId) {
        this.departementId = departementId;
    }

    public Long getPosBiayaId() {
        return posBiayaId;
    }

    public void setPosBiayaId(Long posBiayaId) {
        this.posBiayaId = posBiayaId;
    }

    public String getTujuanJabatan() {
        return tujuanJabatan;
    }

    public void setTujuanJabatan(String tujuanJabatan) {
        this.tujuanJabatan = tujuanJabatan;
    }

    //    public Integer getLevelJabatan() {
    //        return levelJabatan;
    //    }
    //
    //    public void setLevelJabatan(Integer levelJabatan) {
    //        this.levelJabatan = levelJabatan;
    //    }
    public Long getSalaryGradeId() {
        return salaryGradeId;
    }

    public void setSalaryGradeId(Long salaryGradeId) {
        this.salaryGradeId = salaryGradeId;
    }

    public String getNIK() {
        return NIK;
    }

    public void setNIK(String NIK) {
        this.NIK = NIK;
    }

	public List<EducationLevel> getListEducationLevel() {
		return listEducationLevel;
	}

	public void setListEducationLevel(List<EducationLevel> listEducationLevel) {
		this.listEducationLevel = listEducationLevel;
	}

	public List<KlasifikasiKerja> getListKlasifikasiKerja() {
		return listKlasifikasiKerja;
	}

	public void setListKlasifikasiKerja(List<KlasifikasiKerja> listKlasifikasiKerja) {
		this.listKlasifikasiKerja = listKlasifikasiKerja;
	}

	public List<OccupationType> getListOccupationType() {
		return listOccupationType;
	}

	public void setListOccupationType(List<OccupationType> listOccupationType) {
		this.listOccupationType = listOccupationType;
	}

	public List<Major> getListMajor() {
		return listMajor;
	}

	public void setListMajor(List<Major> listMajor) {
		this.listMajor = listMajor;
	}

	public List<Faculty> getListFaculties() {
		return listFaculties;
	}

	public void setListFaculties(List<Faculty> listFaculties) {
		this.listFaculties = listFaculties;
	}
	
	public List<JabatanDeskripsi> getListJabatanDeskripsi() {
		return listJabatanDeskripsi;
	}

	public void setListJabatanDeskripsi(List<JabatanDeskripsi> listJabatanDeskripsi) {
		this.listJabatanDeskripsi = listJabatanDeskripsi;
	}

	public List<JabatanDeskripsiModel> getListJabatanDeskripsiModel() {
		return listJabatanDeskripsiModel;
	}

	public void setListJabatanDeskripsiModel(
			List<JabatanDeskripsiModel> listJabatanDeskripsiModel) {
		this.listJabatanDeskripsiModel = listJabatanDeskripsiModel;
	}

	
    
}
