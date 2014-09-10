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
public class LoanSchemaSearchParameter extends SearchParameter{
    private String name;
    private String code;
    private BigDecimal maxNominal;
    
    public String getName() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "name")){
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
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "code")){
            code = getParameter();
        } else {
            code = null;
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getMaxNominal() {
        if (getKeyParam() != null) {
            if (getKeyParam().equalsIgnoreCase("maxNominal")&& getParameter()!=null) {
                maxNominal = new BigDecimal(getParameter());
            }
        }
        return maxNominal;
    }

    public void setMaxNominal(BigDecimal maxNominal) {
        this.maxNominal = maxNominal;
    }
    
    
}
