package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.MacineFingerUpload;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Deni Husni FR
 */
public class FingerUploadModel implements Serializable {

   private Integer uploadType;
   private Integer extensionType;
   private Boolean itialInOut;
   private Integer fieldNumber=1;
   List<MacineFingerUpload> dataToSave;

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
   
   
   
    

}
