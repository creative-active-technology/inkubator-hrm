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
public class RecruitVacancySelectionDetailPicId  implements java.io.Serializable {


     private long recruitVacancySelectionDetailId;
     private long empDataId;

    public RecruitVacancySelectionDetailPicId() {
    }

    public RecruitVacancySelectionDetailPicId(long recruitVacancySelectionDetailId, long empDataId) {
       this.recruitVacancySelectionDetailId = recruitVacancySelectionDetailId;
       this.empDataId = empDataId;
    }
   


    @Column(name="recruit_vacancy_selection_detail_id", nullable=false)
    public long getRecruitVacancySelectionDetailId() {
        return this.recruitVacancySelectionDetailId;
    }
    
    public void setRecruitVacancySelectionDetailId(long recruitVacancySelectionDetailId) {
        this.recruitVacancySelectionDetailId = recruitVacancySelectionDetailId;
    }


    @Column(name="emp_data_id", nullable=false)
    public long getEmpDataId() {
        return this.empDataId;
    }
    
    public void setEmpDataId(long empDataId) {
        this.empDataId = empDataId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof RecruitVacancySelectionDetailPicId) ) return false;
		 RecruitVacancySelectionDetailPicId castOther = ( RecruitVacancySelectionDetailPicId ) other; 
         
		 return (this.getRecruitVacancySelectionDetailId()==castOther.getRecruitVacancySelectionDetailId())
 && (this.getEmpDataId()==castOther.getEmpDataId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getRecruitVacancySelectionDetailId();
         result = 37 * result + (int) this.getEmpDataId();
         return result;
   }   


}