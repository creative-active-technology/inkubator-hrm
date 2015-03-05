/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Deni
 */
public class BioPotensiSwotSearchParameter extends SearchParameter {

    private String name;
    private String classification;

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

    public String getClassification() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "classification")) {
            classification = getParameter();
        } else {
            classification = null;
        }
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

}
