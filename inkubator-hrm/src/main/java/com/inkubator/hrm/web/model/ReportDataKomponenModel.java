package com.inkubator.hrm.web.model;

import java.math.BigDecimal;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public class ReportDataKomponenModel {
	
        private Long id;
	private String namaProsesGaji;
        private String department;
        private String jabatan;
        private String golonganJabatan;
        private String karyawan;
        private String namaKomponen;
        private BigDecimal nominal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
        
    public String getNamaProsesGaji() {
        return namaProsesGaji;
    }

    public void setNamaProsesGaji(String namaProsesGaji) {
        this.namaProsesGaji = namaProsesGaji;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getGolonganJabatan() {
        return golonganJabatan;
    }

    public void setGolonganJabatan(String golonganJabatan) {
        this.golonganJabatan = golonganJabatan;
    }

    public String getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(String karyawan) {
        this.karyawan = karyawan;
    }

    public String getNamaKomponen() {
        return namaKomponen;
    }

    public void setNamaKomponen(String namaKomponen) {
        this.namaKomponen = namaKomponen;
    }

    public BigDecimal getNominal() {
        return nominal;
    }

    public void setNominal(BigDecimal nominal) {
        this.nominal = nominal;
    }
        
        
	
	
}
