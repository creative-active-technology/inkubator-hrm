/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Deni
 */
public class TravelComponentCostRateSearchParameter extends SearchParameter {

    private String code;
    private String golonganJabatan;
    private String travelComponent;
    private String travelZone;

    public String getCode() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "code")) {
            code = getParameter();
        } else {
            code = null;
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGolonganJabatan() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "golonganJabatan")) {
            golonganJabatan = getParameter();
        } else {
            golonganJabatan = null;
        }
        return golonganJabatan;
    }

    public void setGolonganJabatan(String golonganJabatan) {
        this.golonganJabatan = golonganJabatan;
    }

    public String getTravelComponent() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "travelComponent")) {
            travelComponent = getParameter();
        } else {
            travelComponent = null;
        }
        return travelComponent;
    }

    public void setTravelComponent(String travelComponent) {
        this.travelComponent = travelComponent;
    }

    public String getTravelZone() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "travelZone")) {
            travelZone = getParameter();
        } else {
            travelZone = null;
        }
        return travelZone;
    }

    public void setTravelZone(String travelZone) {
        this.travelZone = travelZone;
    }
    
    
}
