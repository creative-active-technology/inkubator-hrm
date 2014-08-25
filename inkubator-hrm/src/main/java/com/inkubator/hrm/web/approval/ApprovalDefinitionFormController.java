/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.approval;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.service.ApprovalDefinitionService;
import com.inkubator.hrm.web.model.ApprovalDefinitionModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "approvalDefinitionFormController")
@ViewScoped
public class ApprovalDefinitionFormController extends BaseController {

    private ApprovalDefinitionModel approvalDefinitionModel;
    private Boolean onBehalf;
    private Boolean onProcess;
    private Boolean approverTypeIndividual;
    private Boolean approverTypePosition;
    private Boolean approverTypeDepartment;
    private Boolean onBehalfApproverTypeIndividual;
    private Boolean onBehalfApproverTypePosition;
    @ManagedProperty(value = "#{approvalDefinitionService}")
    private ApprovalDefinitionService approvalDefinitionService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        approvalDefinitionModel = new ApprovalDefinitionModel();
        onBehalf = Boolean.TRUE;
        onProcess = Boolean.TRUE;
        approverTypeIndividual = Boolean.TRUE;
        approverTypePosition = Boolean.TRUE;
        approverTypeDepartment = Boolean.TRUE;
        onBehalfApproverTypeIndividual = Boolean.TRUE;
        onBehalfApproverTypePosition = Boolean.TRUE;
//        onBehalfApproverTypeDepartment = Boolean.TRUE;

    }

    public ApprovalDefinitionModel getApprovalDefinitionModel() {
        return approvalDefinitionModel;
    }

    public void setApprovalDefinitionModel(ApprovalDefinitionModel approvalDefinitionModel) {
        this.approvalDefinitionModel = approvalDefinitionModel;
    }

    public Boolean getOnBehalf() {
        return onBehalf;
    }

    public void setOnBehalf(Boolean onBehalf) {
        this.onBehalf = onBehalf;
    }

    public void onChange() {
        onBehalf = approvalDefinitionModel.getAllowOnBehalf();
    }

    public void onProcessCange() {
        String approvalProcess = approvalDefinitionModel.getProcessType();
        if (StringUtils.equalsIgnoreCase(approvalProcess, HRMConstant.ON_APPROVE_INFO)
                || StringUtils.equalsIgnoreCase(approvalProcess, HRMConstant.ON_REJECT_INFO)) {
            onProcess = Boolean.TRUE;
        }
        if (StringUtils.equalsIgnoreCase(approvalProcess, HRMConstant.APPROVAL_PROCESS) || approvalProcess == null || approvalProcess.isEmpty()) {
            onProcess = Boolean.FALSE;
        }
    }

    public void onAppoverChange() {
        String apporverType = approvalDefinitionModel.getApproverType();
        if (StringUtils.equalsIgnoreCase(apporverType, HRMConstant.APPROVAL_TYPE_INDIVIDUAL)) {
            approverTypeIndividual = Boolean.FALSE;
            approverTypePosition = Boolean.TRUE;
            approverTypeDepartment = Boolean.TRUE;

        }
        if (StringUtils.equalsIgnoreCase(apporverType, HRMConstant.APPROVAL_TYPE_DEPARTMENT)) {
            approverTypeIndividual = Boolean.TRUE;
            approverTypePosition = Boolean.TRUE;
            approverTypeDepartment = Boolean.FALSE;
        }

        if (StringUtils.equalsIgnoreCase(apporverType, HRMConstant.APPROVAL_TYPE_POSITION)) {
            approverTypeIndividual = Boolean.TRUE;
            approverTypePosition = Boolean.FALSE;
            approverTypeDepartment = Boolean.TRUE;
        }

        approvalDefinitionModel.setHrmUserByApproverIndividualId(null);
        approvalDefinitionModel.setHrmUserByApproverIndividualName("");
        approvalDefinitionModel.setJabatanByApproverPositionId(null);
        approvalDefinitionModel.setJabatanByApproverPositionName("");
    }

    public void onBehalfAppoverChange() {
        String apporverType = approvalDefinitionModel.getOnBehalfType();
        if (StringUtils.equalsIgnoreCase(apporverType, HRMConstant.APPROVAL_TYPE_INDIVIDUAL)) {
            onBehalfApproverTypeIndividual = Boolean.FALSE;
            onBehalfApproverTypePosition = Boolean.TRUE;
//            onBehalfApproverTypeDepartment = Boolean.TRUE;
        }
        if (StringUtils.equalsIgnoreCase(apporverType, HRMConstant.APPROVAL_TYPE_DEPARTMENT)) {
            onBehalfApproverTypeIndividual = Boolean.TRUE;
            onBehalfApproverTypePosition = Boolean.TRUE;
//            onBehalfApproverTypeDepartment = Boolean.FALSE;
        }

        if (StringUtils.equalsIgnoreCase(apporverType, HRMConstant.APPROVAL_TYPE_POSITION)) {
            onBehalfApproverTypeIndividual = Boolean.TRUE;
            onBehalfApproverTypePosition = Boolean.FALSE;
//            onBehalfApproverTypeDepartment = Boolean.TRUE;
        }
        approvalDefinitionModel.setHrmUserByOnBehalfIndividualId(null);
        approvalDefinitionModel.setHrmUserByOnBehalfIndividualName("");
        approvalDefinitionModel.setJabatanByOnBehalfPositionId(null);
        approvalDefinitionModel.setJabatanByOnBehalfPositionName("");

    }

    public Boolean getOnProcess() {
        return onProcess;
    }

    public void setOnProcess(Boolean onProcess) {
        this.onProcess = onProcess;
    }

    public Boolean getApproverTypeIndividual() {
        return approverTypeIndividual;
    }

    public void setApproverTypeIndividual(Boolean approverTypeIndividual) {
        this.approverTypeIndividual = approverTypeIndividual;
    }

    public Boolean getApproverTypePosition() {
        return approverTypePosition;
    }

    public void setApproverTypePosition(Boolean approverTypePosition) {
        this.approverTypePosition = approverTypePosition;
    }

    public Boolean getApproverTypeDepartment() {
        return approverTypeDepartment;
    }

    public void setApproverTypeDepartment(Boolean approverTypeDepartment) {
        this.approverTypeDepartment = approverTypeDepartment;
    }

    public String doSave() {
        ApprovalDefinition approvalDefinition = getEntityFromView(approvalDefinitionModel);
        try {
            approvalDefinitionService.save(approvalDefinition);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/approval/approval_definition_detail.htm?faces-redirect=true&execution=e" + approvalDefinition.getId();
        } catch (BussinessException ex) { //data already exist(duplicate)
//            LOGGER.error("Error", ex);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    public ApprovalDefinition getEntityFromView(ApprovalDefinitionModel approvalDefinitionModel) {
        System.out.println(approvalDefinitionModel);
        ApprovalDefinition approvalDefinition = new ApprovalDefinition();
        if (approvalDefinitionModel.getId() != null) {
            approvalDefinition.setId(approvalDefinitionModel.getId());
        }
        approvalDefinition.setAllowOnBehalf(approvalDefinitionModel.getAllowOnBehalf());
        approvalDefinition.setApproverType(approvalDefinitionModel.getApproverType());
        if (approvalDefinitionModel.getHrmUserByApproverIndividualId() != null) {
            approvalDefinition.setHrmUserByApproverIndividual(new HrmUser(approvalDefinitionModel.getHrmUserByApproverIndividualId()));
        }
        if (approvalDefinitionModel.getHrmUserByOnBehalfIndividualId() != null) {
            approvalDefinition.setHrmUserByOnBehalfIndividual(new HrmUser(approvalDefinitionModel.getHrmUserByOnBehalfIndividualId()));
        }

        if (approvalDefinitionModel.getJabatanByApproverPositionId() != null) {
            approvalDefinition.setJabatanByApproverPosition(new Jabatan(approvalDefinitionModel.getJabatanByApproverPositionId()));
        }

        if (approvalDefinitionModel.getJabatanByOnBehalfPositionId() != null) {
            approvalDefinition.setJabatanByOnBehalfPosition(new Jabatan(approvalDefinitionModel.getJabatanByOnBehalfPositionId()));
        }

        if (approvalDefinitionModel.getMinApprover() != null) {
            approvalDefinition.setMinApprover(approvalDefinitionModel.getMinApprover());
        }

        if (approvalDefinitionModel.getMinRejector() != null) {
            approvalDefinition.setMinRejector(approvalDefinitionModel.getMinRejector());
        }
        approvalDefinition.setName(approvalDefinitionModel.getName());
        if (approvalDefinitionModel.getOnBehalfType() != null) {
            approvalDefinition.setOnBehalfType(approvalDefinitionModel.getOnBehalfType());
        }
        approvalDefinition.setProcessType(approvalDefinitionModel.getProcessType());
        approvalDefinition.setSequence(approvalDefinitionModel.getSequence());
        return approvalDefinition;
    }

    public void doSearchJabatan() {
        System.out.println("sdfsdhfhdsfhd");
        Map<String, Object> options = new HashMap<>();
        options.put("modal", false);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 700);
        options.put("contentHeight", 350);
//        Map<String, List<String>> dataToSend = new HashMap<>();
//        List<String> dataIsi = new ArrayList<>();
////        dataIsi.add("i" + String.valueOf(selectedJabatan.getId()));
//        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("jabatan_data_search", options, null);
    }

    public void onDialogReturnJabatan(SelectEvent event) {
        Jabatan jabatan = (Jabatan) event.getObject();
        approvalDefinitionModel.setJabatanByApproverPositionId(jabatan.getId());
        approvalDefinitionModel.setJabatanByApproverPositionName(jabatan.getName());

    }

    public void onDialogReturnJabatanBehalf(SelectEvent event) {
        Jabatan jabatan = (Jabatan) event.getObject();
        approvalDefinitionModel.setJabatanByOnBehalfPositionId(jabatan.getId());
        approvalDefinitionModel.setJabatanByOnBehalfPositionName(jabatan.getName());

    }

    public Boolean getOnBehalfApproverTypeIndividual() {
        return onBehalfApproverTypeIndividual;
    }

    public void setOnBehalfApproverTypeIndividual(Boolean onBehalfApproverTypeIndividual) {
        this.onBehalfApproverTypeIndividual = onBehalfApproverTypeIndividual;
    }

    public Boolean getOnBehalfApproverTypePosition() {
        return onBehalfApproverTypePosition;
    }

    public void setOnBehalfApproverTypePosition(Boolean onBehalfApproverTypePosition) {
        this.onBehalfApproverTypePosition = onBehalfApproverTypePosition;
    }

//    public Boolean getOnBehalfApproverTypeDepartment() {
//        return onBehalfApproverTypeDepartment;
//    }
//
//    public void setOnBehalfApproverTypeDepartment(Boolean onBehalfApproverTypeDepartment) {
//        this.onBehalfApproverTypeDepartment = onBehalfApproverTypeDepartment;
//    }
    public void setApprovalDefinitionService(ApprovalDefinitionService approvalDefinitionService) {
        this.approvalDefinitionService = approvalDefinitionService;
    }
}
