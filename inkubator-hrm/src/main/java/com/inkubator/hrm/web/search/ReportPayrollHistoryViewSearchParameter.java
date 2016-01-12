package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public class ReportPayrollHistoryViewSearchParameter extends SearchParameter {
	
	 private String tahun;
	 private Integer bulan;

    public String getTahun() {
        if (getKeyParam() != null) {
            if (getKeyParam().equalsIgnoreCase("tahun")) {
                tahun = getParameter();
            }else{
                tahun=null;
            }
        }
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public Integer getBulan() {
        if (getKeyParam() != null) {
            if (getKeyParam().equalsIgnoreCase("bulan")) {
                bulan = Integer.parseInt(getParameter());
            }else{
                bulan=null;
            }
        }

        return bulan;
    }

    public void setBulan(Integer bulan) {
        this.bulan = bulan;
    }
    
}
