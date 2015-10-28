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
public class RecruitSelectionApplicantInitialId  implements java.io.Serializable {


     private long applicantId;
     private long hireApplyId;

    public RecruitSelectionApplicantInitialId() {
    }

    public RecruitSelectionApplicantInitialId(long applicantId, long hireApplyId) {
       this.applicantId = applicantId;
       this.hireApplyId = hireApplyId;
    }
   


    @Column(name="applicant_id", nullable=false)
    public long getApplicantId() {
        return this.applicantId;
    }
    
    public void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
    }


    @Column(name="hire_apply_id", nullable=false)
    public long getHireApplyId() {
        return this.hireApplyId;
    }
    
    public void setHireApplyId(long hireApplyId) {
        this.hireApplyId = hireApplyId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof RecruitSelectionApplicantInitialId) ) return false;
		 RecruitSelectionApplicantInitialId castOther = ( RecruitSelectionApplicantInitialId ) other; 
         
		 return (this.getApplicantId()==castOther.getApplicantId())
 && (this.getHireApplyId()==castOther.getHireApplyId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getApplicantId();
         result = 37 * result + (int) this.getHireApplyId();
         return result;
   }   


}
