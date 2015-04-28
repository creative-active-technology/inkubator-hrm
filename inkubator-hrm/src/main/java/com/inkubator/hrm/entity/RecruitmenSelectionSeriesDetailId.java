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
public class RecruitmenSelectionSeriesDetailId  implements java.io.Serializable {


     private long recruitmenSelectionSeriesId;
     private long selectionTypeId;

    public RecruitmenSelectionSeriesDetailId() {
    }

    public RecruitmenSelectionSeriesDetailId(long recruitmenSelectionSeriesId, long selectionTypeId) {
       this.recruitmenSelectionSeriesId = recruitmenSelectionSeriesId;
       this.selectionTypeId = selectionTypeId;
    }
   


    @Column(name="recruitmen_selection_series_id", nullable=false)
    public long getRecruitmenSelectionSeriesId() {
        return this.recruitmenSelectionSeriesId;
    }
    
    public void setRecruitmenSelectionSeriesId(long recruitmenSelectionSeriesId) {
        this.recruitmenSelectionSeriesId = recruitmenSelectionSeriesId;
    }


    @Column(name="selection_type_id", nullable=false)
    public long getSelectionTypeId() {
        return this.selectionTypeId;
    }
    
    public void setSelectionTypeId(long selectionTypeId) {
        this.selectionTypeId = selectionTypeId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof RecruitmenSelectionSeriesDetailId) ) return false;
		 RecruitmenSelectionSeriesDetailId castOther = ( RecruitmenSelectionSeriesDetailId ) other; 
         
		 return (this.getRecruitmenSelectionSeriesId()==castOther.getRecruitmenSelectionSeriesId())
 && (this.getSelectionTypeId()==castOther.getSelectionTypeId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getRecruitmenSelectionSeriesId();
         result = 37 * result + (int) this.getSelectionTypeId();
         return result;
   }   


}
