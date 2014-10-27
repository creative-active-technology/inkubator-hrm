/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

/**
 *
 * @author Deni Husni FR
 */
public class MecineFingerQueryModel {
    private Long id;
    private Integer mecineMethode;
    private String name;
    private String code;
    private String description;
    private String dbHost;
    private String dbUserName;
    private String dbType;
    private String dbQuery;
    private String dbPassword;
    private String employeeIdFieldName;
    private String swapTimeFieldName;

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

    public String getDbHost() {
        return dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDbQuery() {
        return dbQuery;
    }

    public void setDbQuery(String dbQuery) {
        this.dbQuery = dbQuery;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getEmployeeIdFieldName() {
        return employeeIdFieldName;
    }

    public void setEmployeeIdFieldName(String employeeIdFieldName) {
        this.employeeIdFieldName = employeeIdFieldName;
    }

    public String getSwapTimeFieldName() {
        return swapTimeFieldName;
    }

    public void setSwapTimeFieldName(String swapTimeFieldName) {
        this.swapTimeFieldName = swapTimeFieldName;
    }
    
    

    
    
}
