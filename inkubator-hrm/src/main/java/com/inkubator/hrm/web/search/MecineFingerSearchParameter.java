package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Taufik Hidayat
 */
public class MecineFingerSearchParameter extends SearchParameter {

    private String code;
    private String name;
    private String methodType;

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

    public String getMethodType() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "methodType")) {
            methodType = getParameter();
        } else {
            methodType = null;
        }
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

}
