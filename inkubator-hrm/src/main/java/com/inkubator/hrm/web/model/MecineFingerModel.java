/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

/**
 *
 * @author Deni
 */
public class MecineFingerModel {
    private Long id;
    private Integer mecineMethode;
    private String name;
    private String code;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getMecineMethode() {
        return mecineMethode;
    }

    public void setMecineMethode(Integer mecineMethode) {
        this.mecineMethode = mecineMethode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
