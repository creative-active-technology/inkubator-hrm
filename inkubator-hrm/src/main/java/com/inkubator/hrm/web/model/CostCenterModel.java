/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.CostCenter;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author deniarianto
 */
public class CostCenterModel implements Serializable{
    private Long id;
    private String code;
    private String name;
    private String description;
//    private Integer level;
    private BigDecimal balance;
    private Long parentId;
    private Boolean isManageParentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Integer getLevel() {
//        return level;
//    }
//
//    public void setLevel(Integer level) {
//        this.level = level;
//    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getParentId() {
        System.out.println("masuk parent id");
        return parentId;
    }

    public void setParentId(Long parent) {
        this.parentId = parent;
    }

    public Boolean getIsManageParentId() {
        return isManageParentId;
    }

    public void setIsManageParentId(Boolean isManageParentId) {
        this.isManageParentId = isManageParentId;
    }
    
    
}
