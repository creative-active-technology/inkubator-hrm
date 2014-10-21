/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.MacineFingerUpload;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.web.model.FingerUploadModel;
import com.inkubator.hrm.web.model.SparasiUploadModel;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "fingerUploadFormController")
@ViewScoped
public class FingerUploadFormController extends BaseController {

    private FingerUploadModel fingerUploadModel;
    private Map<String, Integer> fileExtension = new HashMap<>();
    private DualListModel<Department> dualListModel = new DualListModel<>();
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    private MacineFingerUpload selectdMacineFingerUpload;
    private SparasiUploadModel sparasiUploadModel;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            fingerUploadModel = new FingerUploadModel();
            fileExtension.put(".xls", 0);
            fileExtension.put(".xlsx", 1);
            fileExtension.put(".csv", 2);
            List<Department> dataSoucre = departmentService.getAllData();
            dualListModel.setSource(dataSoucre);
            fingerUploadModel.setDataToSave(getInitialData());
        } catch (Exception ex) {
            LOGGER.error("Errot", ex);
        }

    }

    @PreDestroy
    private void cleanAndExit() {

    }

    public FingerUploadModel getFingerUploadModel() {
        return fingerUploadModel;
    }

    public void setFingerUploadModel(FingerUploadModel fingerUploadModel) {
        this.fingerUploadModel = fingerUploadModel;
    }

    public Map<String, Integer> getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(Map<String, Integer> fileExtension) {
        this.fileExtension = fileExtension;
    }

    public void doChangeUploadType() {
        fileExtension = new HashMap<>();
        if (fingerUploadModel.getUploadType().equals(HRMConstant.UPLOAD_EXCEL_TYPE)) {
            fileExtension.put(".xls", 0);
            fileExtension.put(".xlsx", 1);
        }
        if (fingerUploadModel.getUploadType().equals(HRMConstant.UPLOAD_CSV_TYPE)) {
            fileExtension.put(".csv", 2);
        }
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public DualListModel<Department> getDualListModel() {
        return dualListModel;
    }

    public void setDualListModel(DualListModel<Department> dualListModel) {
        this.dualListModel = dualListModel;
    }

    private List<MacineFingerUpload> getInitialData() {
        List<MacineFingerUpload> data = new ArrayList<>();
        MacineFingerUpload fingerUpload = new MacineFingerUpload();
        fingerUpload.setFieldNo(1);
        fingerUpload.setFieldType("Character");
        fingerUpload.setFieldLabel("Nama");
        fingerUpload.setFieldLen(5);
        data.add(fingerUpload);
        MacineFingerUpload fi = new MacineFingerUpload();
        fi.setFieldNo(2);
        fi.setFieldType("Id");
        fi.setFieldLabel("NIK");
        fi.setFieldLen(3);
        data.add(fi);
        MacineFingerUpload fis = new MacineFingerUpload();
        fis.setFieldNo(3);
        fis.setFieldType("Time");
        fis.setFieldLabel("Swap Time");
        fis.setFieldLen(15);
        data.add(fis);
        return data;
    }

    public void doAddData() {
        System.out.println(" ini di ekseskkeuk");
        sparasiUploadModel = new SparasiUploadModel();
        int jumlahData=fingerUploadModel.getDataToSave().size();
        sparasiUploadModel.setFieldNumber(jumlahData+1);
    }

    public void doEditData() {

    }

    public void doDeleteData() {

    }

    public MacineFingerUpload getSelectdMacineFingerUpload() {
        return selectdMacineFingerUpload;
    }

    public void setSelectdMacineFingerUpload(MacineFingerUpload selectdMacineFingerUpload) {
        this.selectdMacineFingerUpload = selectdMacineFingerUpload;
    }

    public void doDelete() {
        List<MacineFingerUpload> dataToShow = fingerUploadModel.getDataToSave();
        dataToShow.remove(selectdMacineFingerUpload);
        fingerUploadModel.setDataToSave(dataToShow);
        System.out.println(fingerUploadModel.getDataToSave().size());
    }

    public SparasiUploadModel getSparasiUploadModel() {
        return sparasiUploadModel;
    }

    public void setSparasiUploadModel(SparasiUploadModel sparasiUploadModel) {
        this.sparasiUploadModel = sparasiUploadModel;
    }

    public void doSaveMecineFingerUplaod() {

        MacineFingerUpload fingerUpload = new MacineFingerUpload();
        fingerUpload.setDescription(sparasiUploadModel.getDescription());
        fingerUpload.setFieldLabel(sparasiUploadModel.getFieldName());
        fingerUpload.setFieldLen(sparasiUploadModel.getLenght());
        fingerUpload.setFieldNo(sparasiUploadModel.getFieldNumber());
        fingerUpload.setFieldType(sparasiUploadModel.getFieldType());
        List<MacineFingerUpload> dataToShow = fingerUploadModel.getDataToSave();
        dataToShow.add(fingerUpload);
        fingerUploadModel.setDataToSave(dataToShow);
    }
}
