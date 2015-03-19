package com.inkubator.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

// Generated Mar 13, 2015 9:01:09 AM by Hibernate Tools 4.3.1

/**
 * RmbsSchemaListOfTypeId generated by hbm2java
 */
@Embeddable
public class RmbsSchemaListOfTypeId implements java.io.Serializable {

	private long rmbsTypeId;
	private long rmbsSchemaId;

	public RmbsSchemaListOfTypeId() {
	}

	public RmbsSchemaListOfTypeId(long rmbsTypeId, long rmbsSchemaId) {
		this.rmbsTypeId = rmbsTypeId;
		this.rmbsSchemaId = rmbsSchemaId;
	}

	@Column(name="rmbs_type_id", nullable=false)
	public long getRmbsTypeId() {
		return this.rmbsTypeId;
	}

	public void setRmbsTypeId(long rmbsTypeId) {
		this.rmbsTypeId = rmbsTypeId;
	}

	@Column(name="rmbs_schema_id", nullable=false)
	public long getRmbsSchemaId() {
		return this.rmbsSchemaId;
	}

	public void setRmbsSchemaId(long rmbsSchemaId) {
		this.rmbsSchemaId = rmbsSchemaId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RmbsSchemaListOfTypeId))
			return false;
		RmbsSchemaListOfTypeId castOther = (RmbsSchemaListOfTypeId) other;

		return (this.getRmbsTypeId() == castOther.getRmbsTypeId())
				&& (this.getRmbsSchemaId() == castOther.getRmbsSchemaId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getRmbsTypeId();
		result = 37 * result + (int) this.getRmbsSchemaId();
		return result;
	}

}
