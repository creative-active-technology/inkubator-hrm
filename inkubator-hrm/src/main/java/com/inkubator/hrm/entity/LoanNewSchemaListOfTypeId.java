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
public class LoanNewSchemaListOfTypeId  implements java.io.Serializable {


     private long loanNewTypeId;
     private long loanNewSchemaId;

    public LoanNewSchemaListOfTypeId() {
    }

    public LoanNewSchemaListOfTypeId(long loanNewTypeId, long loanNewSchemaId) {
       this.loanNewTypeId = loanNewTypeId;
       this.loanNewSchemaId = loanNewSchemaId;
    }
   


    @Column(name="loan_new_type_id", nullable=false)
    public long getLoanNewTypeId() {
        return this.loanNewTypeId;
    }
    
    public void setLoanNewTypeId(long loanNewTypeId) {
        this.loanNewTypeId = loanNewTypeId;
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
		 if ( !(other instanceof LoanNewSchemaListOfTypeId) ) return false;
		 LoanNewSchemaListOfTypeId castOther = ( LoanNewSchemaListOfTypeId ) other; 
         
		 return (this.getLoanNewTypeId()==castOther.getLoanNewTypeId())
 && (this.getLoanNewSchemaId()==castOther.getLoanNewSchemaId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getLoanNewTypeId();
         result = 37 * result + (int) this.getLoanNewSchemaId();
         return result;
   }   


}
