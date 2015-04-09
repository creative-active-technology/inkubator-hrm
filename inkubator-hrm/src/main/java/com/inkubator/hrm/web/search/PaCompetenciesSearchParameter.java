package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author WebGenX
 */
public class PaCompetenciesSearchParameter extends SearchParameter {

    private String description;
    private String name;
    private String code;

    public String getDescription() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "description")) {
            description = getParameter();
        } else {
            description = null;
        }
        return description;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
