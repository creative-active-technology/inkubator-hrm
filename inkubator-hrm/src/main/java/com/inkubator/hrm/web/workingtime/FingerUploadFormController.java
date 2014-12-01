/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import ch.lambdaj.Lambda;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.DepartementUploadCapture;
import com.inkubator.hrm.entity.DepartementUploadCaptureId;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.MacineFingerUpload;
import com.inkubator.hrm.entity.MecineFinger;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.MecineFingerService;
import com.inkubator.hrm.web.model.FingerUploadModel;
import com.inkubator.hrm.web.model.SparasiUploadModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
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
    @ManagedProperty(value = "#{mecineFingerService}")
    private MecineFingerService mecineFingerService;
    private MacineFingerUpload selectdMacineFingerUpload;
    private SparasiUploadModel sparasiUploadModel;
    private MecineFinger mecineFinger;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("execution");
            mecineFinger = mecineFingerService.getMecineFingerAndDetaiUploadByFK(Long.parseLong(param.substring(1)));
            List<Department> dataSoucreData = departmentService.getAllData();
            List<Department> target = mecineFinger.getDepartments();
            dataSoucreData.removeAll(target);
            dualListModel.setSource(dataSoucreData);
            dualListModel.setTarget(target);
            fingerUploadModel = new FingerUploadModel();
            fingerUploadModel.setDescription(mecineFinger.getDescription());
//            System.out.println(mecineFinger.getMacineFingerUploads().size());
            if (!mecineFinger.getMacineFingerUploads().isEmpty()) {
                List<MacineFingerUpload> dataMesinFingerUpload = new ArrayList(mecineFinger.getMacineFingerUploads());
                List<MacineFingerUpload> sortBySequence = Lambda.sort(dataMesinFingerUpload, Lambda.on(MacineFingerUpload.class).getFieldNo());
                fingerUploadModel.setDataToSave(sortBySequence);
            } else {
                fingerUploadModel.setDataToSave(getInitialData());
            }

            if (mecineFinger.getBaseOnField() != null) {
                fingerUploadModel.setFieldNumber(mecineFinger.getBaseOnField());
            }

            if (mecineFinger.getFileExtension() != null) {
                fingerUploadModel.setExtensionType(mecineFinger.getFileExtension());
            }

            fingerUploadModel.setId(mecineFinger.getId());
            if (mecineFinger.getInOutStatus() != null) {
                fingerUploadModel.setItialInOut(mecineFinger.getInOutStatus());
            }

            if (mecineFinger.getMecineMethode().equals(HRMConstant.METHOD_UPLOAD_MACINE)) {
                fingerUploadModel.setMecineMethode(mecineFinger.getMecineMethode());
            }
            if (mecineFinger.getFileType() != null) {
                fingerUploadModel.setUploadType(mecineFinger.getFileType());
            }
            fingerUploadModel.setName(mecineFinger.getName());
            fileExtension.put(".xls", 0);
            fileExtension.put(".xlsx", 1);
            fileExtension.put(".csv", 2);

        } catch (Exception ex) {
            LOGGER.error("Errot", ex);
        }

    }

    @PreDestroy
    private void cleanAndExit() {
        fingerUploadModel = null;
        fileExtension = null;
        dualListModel = null;
        departmentService = null;
        mecineFingerService = null;
        selectdMacineFingerUpload = null;
        sparasiUploadModel = null;
        mecineFinger = null;
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
//        fingerUpload.setMecineFinger(mecineFinger);
        fingerUpload.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        fingerUpload.setFieldNo(1);
        fingerUpload.setFieldType("Character");
        fingerUpload.setFieldLabel("Nama");
        fingerUpload.setFieldLen(5);
        data.add(fingerUpload);
        MacineFingerUpload fi = new MacineFingerUpload();
        fi.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
//        fi.setMecineFinger(mecineFinger);
        fi.setFieldNo(2);
        fi.setFieldType("Id");
        fi.setFieldLabel("NIK");
        fi.setFieldLen(3);
        data.add(fi);
        MacineFingerUpload fis = new MacineFingerUpload();
//        fis.setMecineFinger(mecineFinger);
        fis.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
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
        int jumlahData = fingerUploadModel.getDataToSave().size();
        sparasiUploadModel.setFieldNumber(jumlahData + 1);
    }

    public void doEditData() {
        System.out.println(" nilia " + selectdMacineFingerUpload);
        sparasiUploadModel = new SparasiUploadModel();
        sparasiUploadModel.setDescription(selectdMacineFingerUpload.getDescription());
        sparasiUploadModel.setFieldName(selectdMacineFingerUpload.getFieldLabel());
        sparasiUploadModel.setFieldNumber(selectdMacineFingerUpload.getFieldNo());
        sparasiUploadModel.setFieldType(selectdMacineFingerUpload.getFieldType());
        sparasiUploadModel.setId(selectdMacineFingerUpload.getId());
        sparasiUploadModel.setLenght(selectdMacineFingerUpload.getFieldLen());
        sparasiUploadModel.setIsEdit(Boolean.TRUE);

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
        fingerUpload.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        fingerUpload.setDescription(sparasiUploadModel.getDescription());
        fingerUpload.setFieldLabel(sparasiUploadModel.getFieldName());
        fingerUpload.setFieldLen(sparasiUploadModel.getLenght());
        fingerUpload.setFieldNo(sparasiUploadModel.getFieldNumber());
        fingerUpload.setFieldType(sparasiUploadModel.getFieldType());
        List<MacineFingerUpload> dataToShow = fingerUploadModel.getDataToSave();
        dataToShow.remove(selectdMacineFingerUpload);
        dataToShow.add(fingerUpload);
        fingerUploadModel.setDataToSave(dataToShow);
    }

    public void setMecineFingerService(MecineFingerService mecineFingerService) {
        this.mecineFingerService = mecineFingerService;
    }

    public String doSave() {
        try {
            fingerUploadModel.setDataDeptToSave(dualListModel.getTarget());
            Set<DepartementUploadCapture> dataToSave = new HashSet<>();
            List<Department> data = dualListModel.getTarget();
            for (Department department : data) {
                DepartementUploadCapture capture = new DepartementUploadCapture();
                capture.setDepartment(department);
//                capture.setMecineFinger(mecineFinger);
                capture.setId(new DepartementUploadCaptureId(mecineFinger.getId(), department.getId()));
                dataToSave.add(capture);
            }
            System.out.println(" hehehheeh");
            mecineFingerService.saveByModel(fingerUploadModel, dataToSave);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/working_time/mecine_finger_view.htm?faces-redirect=true";
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

        return null;
    }

    public String doBack() {
        return "/protected/working_time/mecine_finger_view.htm?faces-redirect=true";
    }
}
