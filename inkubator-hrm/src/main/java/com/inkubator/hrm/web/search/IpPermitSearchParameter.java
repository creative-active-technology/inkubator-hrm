package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Deni Husni FR
 */
public class IpPermitSearchParameter extends SearchParameter {

    private String lokasi;
    private String from;
    private String until;

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
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "location")) {
            lokasi = getParameter();
        } else {
            lokasi = null;
        }
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getFrom() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "from")) {
            from = getParameter();
        } else {
            from = null;
        }
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getUntil() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "until")) {
            until = getParameter();
        } else {
            until = null;
        }
        return until;
    }

    public void setUntil(String until) {
        this.until = until;
    }

    

    
}
