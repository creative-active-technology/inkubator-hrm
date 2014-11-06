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
    private Integer fromAddress11;
    private Integer fromAddress12;
    private Integer fromAddress13;
    private Integer fromAddress21;
    private Integer fromAddress22;
    private Integer fromAddress23;
    private Integer untilAddress1;
    private Integer untilAddress2;
    private String ipAddressFromView;
    private String ipAddressUntilView;
    private String lokasi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFromAddress11() {
        return fromAddress11;
    }

    public void setFromAddress11(Integer fromAddress11) {
        this.fromAddress11 = fromAddress11;
    }

    public Integer getFromAddress12() {
        return fromAddress12;
    }

    public void setFromAddress12(Integer fromAddress12) {
        this.fromAddress12 = fromAddress12;
    }

    public Integer getFromAddress13() {
        return fromAddress13;
    }

    public void setFromAddress13(Integer fromAddress13) {
        this.fromAddress13 = fromAddress13;
    }

    public Integer getFromAddress21() {
        return fromAddress21;
    }

    public void setFromAddress21(Integer fromAddress21) {
        this.fromAddress21 = fromAddress21;
    }

    public Integer getFromAddress22() {
        return fromAddress22;
    }

    public void setFromAddress22(Integer fromAddress22) {
        this.fromAddress22 = fromAddress22;
    }

    public Integer getFromAddress23() {
        return fromAddress23;
    }

    public void setFromAddress23(Integer fromAddress23) {
        this.fromAddress23 = fromAddress23;
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

    public String getIpAddressFromView() {
        return ipAddressFromView;
    }

    public void setIpAddressFromView(String ipAddressFromView) {
        this.ipAddressFromView = ipAddressFromView;
    }

    public String getIpAddressUntilView() {
        return ipAddressUntilView;
    }

    public void setIpAddressUntilView(String ipAddressUntilView) {
        this.ipAddressUntilView = ipAddressUntilView;
    }
}
