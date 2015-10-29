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
 * @author denifahri
 */
public class RecrutimentLetterSearchParameter extends SearchParameter{
    private String letterType;
    private String selectionType;
    private String senderBy;

    public String getLetterType() {
          if (StringUtils.equalsIgnoreCase(getKeyParam(), "letterType")) {
            letterType = getParameter();
        } else {
            letterType = null;
        }
        return letterType;
    }

    public void setLetterType(String letterType) {
        this.letterType = letterType;
    }

    public String getSelectionType() {
         if (StringUtils.equalsIgnoreCase(getKeyParam(), "selectionType")) {
            selectionType = getParameter();
        } else {
            selectionType = null;
        }
        return selectionType;
    }

    public void setSelectionType(String selectionType) {
        this.selectionType = selectionType;
    }

    public String getSenderBy() {
          if (StringUtils.equalsIgnoreCase(getKeyParam(), "senderBy")) {
            senderBy = getParameter();
        } else {
            senderBy = null;
        }
        return senderBy;
    }

    public void setSenderBy(String senderBy) {
        this.senderBy = senderBy;
    }
    
    
    
}
