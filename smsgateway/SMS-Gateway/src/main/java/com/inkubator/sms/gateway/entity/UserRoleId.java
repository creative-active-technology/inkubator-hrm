/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author deni
 */
@Embeddable
public class UserRoleId  implements java.io.Serializable {


     private long userId;
     private long roleId;

    public UserRoleId() {
    }

    public UserRoleId(long userId, long roleId) {
       this.userId = userId;
       this.roleId = roleId;
    }
   


    @Column(name="user_id", nullable=false)
    public long getUserId() {
        return this.userId;
    }
    
    public void setUserId(long userId) {
        this.userId = userId;
    }


    @Column(name="role_id", nullable=false)
    public long getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UserRoleId) ) return false;
		 UserRoleId castOther = ( UserRoleId ) other; 
         
		 return (this.getUserId()==castOther.getUserId())
 && (this.getRoleId()==castOther.getRoleId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getUserId();
         result = 37 * result + (int) this.getRoleId();
         return result;
   }   


}
