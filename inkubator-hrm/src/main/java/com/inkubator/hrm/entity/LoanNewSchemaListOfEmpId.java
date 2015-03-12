/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Deni
 */
@Embeddable
public class LoanNewSchemaListOfEmpId  implements java.io.Serializable {


     private long empId;
     private long loanNewSchemaId;

    public LoanNewSchemaListOfEmpId() {
    }

    public LoanNewSchemaListOfEmpId(long empId, long loanNewSchemaId) {
       this.empId = empId;
       this.loanNewSchemaId = loanNewSchemaId;
    }
   


    @Column(name="emp_id", nullable=false)
    public long getEmpId() {
        return this.empId;
    }
    
    public void setEmpId(long empId) {
        this.empId = empId;
    }


    @Column(name="loan_new_schema_id", nullable=false)
    public long getLoanNewSchemaId() {
        return this.loanNewSchemaId;
    }
    
    public void setLoanNewSchemaId(long loanNewSchemaId) {
        this.loanNewSchemaId = loanNewSchemaId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof LoanNewSchemaListOfEmpId) ) return false;
		 LoanNewSchemaListOfEmpId castOther = ( LoanNewSchemaListOfEmpId ) other; 
         
		 return (this.getEmpId()==castOther.getEmpId())
 && (this.getLoanNewSchemaId()==castOther.getLoanNewSchemaId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getEmpId();
         result = 37 * result + (int) this.getLoanNewSchemaId();
         return result;
   }   


}


