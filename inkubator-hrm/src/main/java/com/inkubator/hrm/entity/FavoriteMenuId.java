package com.inkubator.hrm.entity;
// Generated Jul 14, 2014 2:09:36 PM by Hibernate Tools 3.6.0


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FavoriteMenuId generated by hbm2java
 */
@Embeddable
public class FavoriteMenuId  implements java.io.Serializable {


     private long userId;
     private long menuId;

    public FavoriteMenuId() {
    }

    public FavoriteMenuId(long userId, long menuId) {
       this.userId = userId;
       this.menuId = menuId;
    }
   


    @Column(name="user_id", nullable=false)
    public long getUserId() {
        return this.userId;
    }
    
    public void setUserId(long userId) {
        this.userId = userId;
    }


    @Column(name="menu_id", nullable=false)
    public long getMenuId() {
        return this.menuId;
    }
    
    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FavoriteMenuId) ) return false;
		 FavoriteMenuId castOther = ( FavoriteMenuId ) other; 
         
		 return (this.getUserId()==castOther.getUserId())
 && (this.getMenuId()==castOther.getMenuId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getUserId();
         result = 37 * result + (int) this.getMenuId();
         return result;
   }   


}


