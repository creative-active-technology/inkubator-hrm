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

@Entity
@Table(name = "approval_definition_rmbs_schema", catalog = "hrm"
)
public class ApprovalDefinitionRmbsSchema implements java.io.Serializable {

    private ApprovalDefinitionRmbsSchemaId id;
    private RmbsSchema rmbsSchema;
    private ApprovalDefinition approvalDefinition;
    private String description;

    public ApprovalDefinitionRmbsSchema() {
    }

    public ApprovalDefinitionRmbsSchema(ApprovalDefinitionRmbsSchemaId id, ApprovalDefinition approvalDefinition, RmbsSchema rmbsSchema) {
        this.id = id;
        this.rmbsSchema = rmbsSchema;
        this.approvalDefinition = approvalDefinition;
    }

    public ApprovalDefinitionRmbsSchema(ApprovalDefinitionRmbsSchemaId id, ApprovalDefinition approvalDefinition, RmbsSchema rmbsSchema, String description) {
    	this.id = id;
        this.rmbsSchema = rmbsSchema;
        this.approvalDefinition = approvalDefinition;
        this.description = description;
    }

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "rmbsSchemaId", column = @Column(name = "rmbs_schema_id", nullable = false)),
        @AttributeOverride(name = "approvalDefinitionId", column = @Column(name = "approval_definition_id", nullable = false))})
    public ApprovalDefinitionRmbsSchemaId getId() {
		return id;
	}

	public void setId(ApprovalDefinitionRmbsSchemaId id) {
		this.id = id;
	}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rmbs_schema_id", nullable = false, insertable = false, updatable = false)
    public RmbsSchema getRmbsSchema() {
		return rmbsSchema;
	}

	public void setRmbsSchema(RmbsSchema rmbsSchema) {
		this.rmbsSchema = rmbsSchema;
	}	

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approval_definition_id", nullable = false, insertable = false, updatable = false)
	public ApprovalDefinition getApprovalDefinition() {
		return approvalDefinition;
	}

	public void setApprovalDefinition(ApprovalDefinition approvalDefinition) {
		this.approvalDefinition = approvalDefinition;
	}

    @Column(name = "description", length = 45)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
