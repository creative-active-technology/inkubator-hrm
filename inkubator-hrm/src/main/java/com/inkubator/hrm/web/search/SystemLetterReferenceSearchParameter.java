package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author WebGenX
 */
public class SystemLetterReferenceSearchParameter extends SearchParameter {

    private String letterSumary;
    private String name;
    private String code;

    public String getLetterSumary() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "letterSumary")) {
            letterSumary = getParameter();
        } else {
            letterSumary = null;
        }
        return letterSumary;
    }

    public String getName() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "name")) {
            name = getParameter();
        } else {
            name = null;
        }
        return name;
    }

    public String getCode() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "code")) {
            code = getParameter();
        } else {
            code = null;
        }
        return code;
    }

    public void setLetterSumary(String letterSumary) {
        this.letterSumary = letterSumary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
