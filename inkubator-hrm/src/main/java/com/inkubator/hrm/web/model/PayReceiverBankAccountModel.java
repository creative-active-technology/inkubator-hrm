/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author denifahri
 */
public class PayReceiverBankAccountModel implements Serializable{
    private Long id;
    private Long empId;
    private Long totalAccount;
    private String golJab;
    private String firstName;
    private String lastName;
    private Date joinDate;
    private String nik;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Long getTotalAccount() {
        return totalAccount;
    }

    public void setTotalAccount(Long totalAccount) {
        this.totalAccount = totalAccount;
    }

    public String getGolJab() {
        return golJab;
    }

    public void setGolJab(String golJab) {
        this.golJab = golJab;
    }



    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.empId);
        hash = 29 * hash + Objects.hashCode(this.totalAccount);
        hash = 29 * hash + Objects.hashCode(this.golJab);
        hash = 29 * hash + Objects.hashCode(this.firstName);
        hash = 29 * hash + Objects.hashCode(this.lastName);
        hash = 29 * hash + Objects.hashCode(this.joinDate);
        hash = 29 * hash + Objects.hashCode(this.nik);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PayReceiverBankAccountModel other = (PayReceiverBankAccountModel) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.empId, other.empId)) {
            return false;
        }
        if (!Objects.equals(this.totalAccount, other.totalAccount)) {
            return false;
        }
        if (!Objects.equals(this.golJab, other.golJab)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.joinDate, other.joinDate)) {
            return false;
        }
        if (!Objects.equals(this.nik, other.nik)) {
            return false;
        }
        return true;
    }

  

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    @Override
    public String toString() {
        return "PayReceiverBankAccountModel{" + "id=" + id + ", empId=" + empId + ", totalAccount=" + totalAccount + ", golJab=" + golJab + ", firstName=" + firstName + ", lastName=" + lastName + ", joinDate=" + joinDate + ", nik=" + nik + '}';
    }
    
    
}
