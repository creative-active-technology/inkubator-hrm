package com.inkubator.hrm.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "document", catalog = "hrm", uniqueConstraints = 
		@UniqueConstraint(columnNames = "code"))
public class Document implements Serializable{
	
	private long id;
	private Integer version;
	
	private String code;
	private String name;
	private String description;
	private String uploadPath;
	private String documentTitle;
	
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date UpdatedOn;
	
	public Document(){
		
	}
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name = "code", unique = true, nullable = false, length = 12)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", nullable = false, length = 60)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 65535, columnDefinition = "Text")
	public String getDescription() {
		return description;
	}

	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "upload_path", length = 100)
	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	@Column(name = "created_by", length = 45)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on", length = 19)
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "updated_by", length = 45)
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_on", length = 19)
	public Date getUpdatedOn() {
		return UpdatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		UpdatedOn = updatedOn;
	}
	
	@Transient
	public String getUploadFileName(){
		String fileName = StringUtils.EMPTY;
		if(uploadPath!=null){
			fileName = StringUtils.substringAfterLast(uploadPath, "/");
		}
		return fileName;
	}
	
	@Transient
	public String getUploadFileExtension(){
		String fileName = StringUtils.EMPTY;
		if(uploadPath!=null){
			fileName = StringUtils.substringAfterLast(uploadPath, "/");
		}
		return fileName;
	}
	
	@Column(name = "document_title", length = 60, nullable = false)
	public String getDocumentTitle() {
		return documentTitle;
	}

	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}
}
