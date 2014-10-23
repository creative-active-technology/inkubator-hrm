package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.MacineFingerUpload;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Deni Husni FR
 */
public class FingerUploadModel implements Serializable {

    private Long id;
    private Integer mecineMethode;
    private String name;
    private String description;
    private Integer uploadType;
    private Integer extensionType;
    private Boolean itialInOut;
    private Integer fieldNumber = 1;
    private List<MacineFingerUpload> dataToSave;
    private List<Department>dataDeptToSave;

    public Integer getUploadType() {
        return uploadType;
    }

    public void setUploadType(Integer uploadType) {
        this.uploadType = uploadType;
    }

    public Integer getExtensionType() {
        return extensionType;
    }

    public void setExtensionType(Integer extensionType) {
        this.extensionType = extensionType;
    }

    public Boolean getItialInOut() {
        return itialInOut;
    }

    public void setItialInOut(Boolean itialInOut) {
        this.itialInOut = itialInOut;
    }

    public Integer getFieldNumber() {
        return fieldNumber;
    }

    public void setFieldNumber(Integer fieldNumber) {
        this.fieldNumber = fieldNumber;
    }

    public List<MacineFingerUpload> getDataToSave() {
        return dataToSave;
    }

    public void setDataToSave(List<MacineFingerUpload> dataToSave) {
        this.dataToSave = dataToSave;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMecineMethode() {
        return mecineMethode;
    }

    public void setMecineMethode(Integer mecineMethode) {
        this.mecineMethode = mecineMethode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Department> getDataDeptToSave() {
        return dataDeptToSave;
    }

    public void setDataDeptToSave(List<Department> dataDeptToSave) {
        this.dataDeptToSave = dataDeptToSave;
    }

    
    
    
}
