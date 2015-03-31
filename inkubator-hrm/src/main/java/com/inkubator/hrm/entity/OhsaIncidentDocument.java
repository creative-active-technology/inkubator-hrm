package com.inkubator.hrm.entity;
// Generated Mar 12, 2015 4:24:01 PM by Hibernate Tools 4.3.1

import com.inkubator.common.util.FilesUtil;
import java.io.File;
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
import javax.persistence.Transient;
import org.apache.commons.lang3.StringUtils;

/**
 * OhsaIncidentDocument generated by hbm2java
 */
@Entity
@Table(name = "ohsa_incident_document", catalog = "hrm_payroll"
)
public class OhsaIncidentDocument implements java.io.Serializable {

    private int id;
    private OhsaIncident ohsaIncident;
    private String documentLabel;
    private String documentName;
    private String uploadedPath;
    private String description;
    private String createdBy;
    private Date createdOn;

    public OhsaIncidentDocument() {
    }

    public OhsaIncidentDocument(int id) {
        this.id = id;
    }

    public OhsaIncidentDocument(int id, OhsaIncident ohsaIncident, String documentLabel, String uploadedPath, String description) {
        this.id = id;
        this.ohsaIncident = ohsaIncident;
        this.documentLabel = documentLabel;
        this.uploadedPath = uploadedPath;
        this.description = description;
    }

    @Id

    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ohsa_incident_id")
    public OhsaIncident getOhsaIncident() {
        return this.ohsaIncident;
    }

    public void setOhsaIncident(OhsaIncident ohsaIncident) {
        this.ohsaIncident = ohsaIncident;
    }

    @Column(name = "document_label", length = 100)
    public String getDocumentLabel() {
        return this.documentLabel;
    }

    public void setDocumentLabel(String documentLabel) {
        this.documentLabel = documentLabel;
    }

    @Column(name = "document_name", length = 60)
    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    @Column(name = "uploaded_path", length = 120)
    public String getUploadedPath() {
        return this.uploadedPath;
    }

    public void setUploadedPath(String uploadedPath) {
        this.uploadedPath = uploadedPath;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Transient
    public String getUploadFileExtension() {
        String fileName = StringUtils.EMPTY;
        if (uploadedPath != null) {
            fileName = StringUtils.substringAfterLast(uploadedPath, ".");
        }
        return fileName;
    }
    
    @Transient
    public Double getUploadFileSize() {
        Double fileSize = 0d;
        if (uploadedPath != null) {
            Long sizeInBytes = FilesUtil.getFileSize(new File(uploadedPath));
            //convert to Kilobyte
            fileSize = Double.parseDouble(String.valueOf(sizeInBytes)) / 1024;
        }
        return fileSize;
    }
}