package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class BioSertifikasiModel implements Serializable{

    private Long id;
    private Long bioDataId;
    private String documentTitle;
    private String documentNo;
    private String namaSertifikasi;
    private String uploadFileName;
    private Long occupationTypeId;
    private Long eduNonFormalId;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBioDataId() {
		return bioDataId;
	}
	public void setBioDataId(Long bioDataId) {
		this.bioDataId = bioDataId;
	}
	public String getDocumentTitle() {
		return documentTitle;
	}
	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}
	public String getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

    public String getNamaSertifikasi() {
        return namaSertifikasi;
    }

    public void setNamaSertifikasi(String namaSertifikasi) {
        this.namaSertifikasi = namaSertifikasi;
    }
	
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}   

    public Long getOccupationTypeId() {
        return occupationTypeId;
    }

    public void setOccupationTypeId(Long occupationTypeId) {
        this.occupationTypeId = occupationTypeId;
    }

    public Long getEduNonFormalId() {
        return eduNonFormalId;
    }

    public void setEduNonFormalId(Long eduNonFormalId) {
        this.eduNonFormalId = eduNonFormalId;
    }
	
        
}
