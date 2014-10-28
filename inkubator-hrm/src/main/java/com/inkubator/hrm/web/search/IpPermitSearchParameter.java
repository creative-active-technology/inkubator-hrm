package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Deni Husni FR
 */
public class IpPermitSearchParameter extends SearchParameter {

    private String lokasi;
    private Integer ipAddress;

//    public String getName() {
//        if (StringUtils.equalsIgnoreCase(getKeyParam(), "name")) {
//            name = getParameter();
//        } else {
//            name = null;
//        }
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getCode() {
//        if (StringUtils.equalsIgnoreCase(getKeyParam(), "code")) {
//            code = getParameter();
//        } else {
//            code = null;
//        }
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }

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

    public Integer getIpAddress() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "ipAddress")) {
            ipAddress = Integer.parseInt(getParameter());
        } else {
            ipAddress = null;
        }
        return ipAddress;
    }

    public void setIpAddress(Integer ipAddress) {
        this.ipAddress = ipAddress;
    }

    
}
