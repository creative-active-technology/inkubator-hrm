/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Deni Husni FR
 */
public class WtGroupWorkingSearchParameter extends SearchParameter{
    private String kode;
    private String name;

    public String getKode() {
         if (getKeyParam() != null) {
            if (getKeyParam().equalsIgnoreCase("kode")) {
                kode = getParameter();
            }
        }
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getName() {
        if (getKeyParam() != null) {
            if (getKeyParam().equalsIgnoreCase("name")) {
                name = getParameter();
            }
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
            
            
}
