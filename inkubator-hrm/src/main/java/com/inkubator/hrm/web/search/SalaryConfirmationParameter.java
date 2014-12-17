/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author rizkykojek
 */
public class SalaryConfirmationParameter extends SearchParameter{
    
	private String name;
    private String nik;
    private Long golonganJabatanId;
	
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNik() {
		return nik;
	}
	public void setNik(String nik) {
		this.nik = nik;
	}
	public Long getGolonganJabatanId() {
		return golonganJabatanId;
	}
	public void setGolonganJabatanId(Long golonganJabatanId) {
		this.golonganJabatanId = golonganJabatanId;
	}
    
	
            
}
