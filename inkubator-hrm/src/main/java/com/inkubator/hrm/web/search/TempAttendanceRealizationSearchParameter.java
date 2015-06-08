package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author WebGenX
 */
public class TempAttendanceRealizationSearchParameter extends SearchParameter {

    private String nik;
    private String name;
    private String noJab;
    private String jabatan;

    public String getNik() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "nik")) {
            nik = getParameter();
        } else {
            nik = null;
        }
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getName() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "name")) {
            name = getParameter();
        } else {
            name = null;
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNoJab() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "noJab")) {
            noJab = getParameter();
        } else {
            noJab = null;
        }
        return noJab;
    }

    public void setNoJab(String noJab) {
        this.noJab = noJab;
    }

    public String getJabatan() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "jabatan")) {
            jabatan = getParameter();
        } else {
            jabatan = null;
        }
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

}
