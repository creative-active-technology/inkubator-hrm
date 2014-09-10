/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Deni
 */
public class ReimbursmentSchemaSearchParameter extends SearchParameter{
    private String code;
    private String name;
    private BigDecimal nominalUnit;

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

    public BigDecimal getNominalUnit() {
        if (getKeyParam() != null) {
            if (getKeyParam().equalsIgnoreCase("nominalUnit")&& getParameter()!=null) {
                nominalUnit = new BigDecimal(getParameter());
            }
        }
        return nominalUnit;
    }

    public void setNominalUnit(BigDecimal nominalUnit) {
        this.nominalUnit = nominalUnit;
    }
    
    
}
