/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Deni
 */
@Entity
@Table(name="approval_definition_permit"
    ,catalog="hrm"
)
public class ApprovalDefinitionPermit implements java.io.Serializable {


     private ApprovalDefinitionPermitId id;
     private ApprovalDefinition approvalDefinition;
     private PermitClassification permitClassification;
     private String description;

    public ApprovalDefinitionPermit() {
    }

	
    public ApprovalDefinitionPermit(ApprovalDefinitionPermitId id, ApprovalDefinition approvalDefinition, PermitClassification permitClassification) {
        this.id = id;
        this.approvalDefinition = approvalDefinition;
        this.permitClassification = permitClassification;
    }
    public ApprovalDefinitionPermit(ApprovalDefinitionPermitId id, ApprovalDefinition approvalDefinition, PermitClassification permitClassification, String description) {
       this.id = id;
       this.approvalDefinition = approvalDefinition;
       this.permitClassification = permitClassification;
       this.description = description;
    }
   
     @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="approvalDefinitionId", column=@Column(name="approval_definition_id", nullable=false) ), 
        @AttributeOverride(name="permitClassificationId", column=@Column(name="permit_classification_id", nullable=false) ) } )
    public ApprovalDefinitionPermitId getId() {
        return this.id;
    }
    
    public void setId(ApprovalDefinitionPermitId id) {
        this.id = id;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="approval_definition_id", nullable=false, insertable=false, updatable=false)
    public ApprovalDefinition getApprovalDefinition() {
        return this.approvalDefinition;
    }
    
    public void setApprovalDefinition(ApprovalDefinition approvalDefinition) {
        this.approvalDefinition = approvalDefinition;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="permit_classification_id", nullable=false, insertable=false, updatable=false)
    public PermitClassification getPermitClassification() {
        return this.permitClassification;
    }
    
    public void setPermitClassification(PermitClassification permitClassification) {
        this.permitClassification = permitClassification;
    }
    
    @Column(name="description", length=45)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }




}