package com.inkubator.hrm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "bio_document", catalog = "hrm")
public class BioDocument implements java.io.Serializable {

    private long id;    
    private Integer version;
    
    private BioData bioData;
    private String documentTitle;
    private String documentNo;
    private String description;
    private String uploadPath;
    
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;

    public BioDocument() {
    }

    public BioDocument(long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Version
    @Column(name = "version")
    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bio_data_id", nullable = false)
    public BioData getBioData() {
		return bioData;
	}

	public void setBioData(BioData bioData) {
		this.bioData = bioData;
	}

	@Column(name = "document_title", length = 60, nullable = false)
	public String getDocumentTitle() {
		return documentTitle;
	}

	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}

	@Column(name = "document_no", length = 60, unique = true, nullable = false)
	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	@Column(name="description", length=65535, columnDefinition="Text")
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
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "updated_by", length = 45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", length = 19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
