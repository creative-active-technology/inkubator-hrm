package com.inkubator.hrm.entity;
// Generated Nov 24, 2014 11:16:03 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PaySalaryEmpTypeId generated by hbm2java
 */
@Embeddable
public class PaySalaryEmpTypeId  implements java.io.Serializable {


     private long paySalaryComId;
     private long empTypeId;

    public PaySalaryEmpTypeId() {
    }

    public PaySalaryEmpTypeId(long paySalaryComId, long empTypeId) {
       this.paySalaryComId = paySalaryComId;
       this.empTypeId = empTypeId;
    }
   


    @Column(name="pay_salary_com_id", nullable=false)
    public long getPaySalaryComId() {
        return this.paySalaryComId;
    }
    
    public void setPaySalaryComId(long paySalaryComId) {
        this.paySalaryComId = paySalaryComId;
    }


    @Column(name="emp_type_id", nullable=false)
    public long getEmpTypeId() {
        return this.empTypeId;
    }
    
    public void setEmpTypeId(long empTypeId) {
        this.empTypeId = empTypeId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PaySalaryEmpTypeId) ) return false;
		 PaySalaryEmpTypeId castOther = ( PaySalaryEmpTypeId ) other; 
         
		 return (this.getPaySalaryComId()==castOther.getPaySalaryComId())
 && (this.getEmpTypeId()==castOther.getEmpTypeId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getPaySalaryComId();
         result = 37 * result + (int) this.getEmpTypeId();
         return result;
   }   


}


