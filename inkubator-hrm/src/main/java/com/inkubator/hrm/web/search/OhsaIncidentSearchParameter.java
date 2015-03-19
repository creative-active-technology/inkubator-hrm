package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class OhsaIncidentSearchParameter extends SearchParameter {
	
	private String kategori;	
	private String subjek;
        private String lokasi;
       
    public String getKategori() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "kategori")) {
            kategori = getParameter();
        } else {
            kategori = null;
        }
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getSubjek() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "subjek")) {
            subjek = getParameter();
        } else {
            subjek = null;
        }
        return subjek;
    }

    public void setSubjek(String subjek) {
        this.subjek = subjek;
    }

    public String getLokasi() {
         if (StringUtils.equalsIgnoreCase(getKeyParam(), "lokasi")) {
            lokasi = getParameter();
        } else {
            lokasi = null;
        }
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }
                
	

}
