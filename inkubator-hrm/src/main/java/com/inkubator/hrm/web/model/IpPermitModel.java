/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Deni
 */
public class IpPermitModel implements Serializable {
    private Long id;
    private String fromAddress1;
    private String fromAddress2;
    private Integer untilAddress1;
    private Integer untilAddress2;
    private String lokasi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromAddress1() {
        return fromAddress1;
    }

    public void setFromAddress1(String fromAddress1) {
        this.fromAddress1 = fromAddress1;
    }

    public String getFromAddress2() {
        return fromAddress2;
    }

    public void setFromAddress2(String fromAddress2) {
        this.fromAddress2 = fromAddress2;
    }

    public Integer getUntilAddress1() {
        return untilAddress1;
    }

    public void setUntilAddress1(Integer untilAddress1) {
        this.untilAddress1 = untilAddress1;
    }

    public Integer getUntilAddress2() {
        return untilAddress2;
    }

    public void setUntilAddress2(Integer untilAddress2) {
        this.untilAddress2 = untilAddress2;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    
}
