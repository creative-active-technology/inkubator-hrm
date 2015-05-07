/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.approval;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import com.inkubator.common.util.JsonConverter;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.service.ApprovalDefinitionService;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.LoanNewSchemaService;
import com.inkubator.hrm.service.RmbsSchemaService;
import com.inkubator.hrm.web.model.ApprovalDefinitionModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "approvalDefinitionPopupFormController")
@ViewScoped
public class ApprovalDefinitionPopupFormController extends BaseController {

    private ApprovalDefinitionModel approvalDefinitionModel;
    private Boolean onBehalf;
    private Boolean onProcess;
    private Boolean approverTypeIndividual;
    private Boolean approverTypePosition;
    private Boolean onBehalfApproverTypeIndividual;
    private Boolean onBehalfApproverTypePosition;
    private Boolean onAutoApprove;
    private Boolean isEdit;
    private Boolean isPersistedToDB;
    private String entityId;
    @ManagedProperty(value = "#{approvalDefinitionService}")
    private ApprovalDefinitionService approvalDefinitionService;
    @ManagedProperty(value = "#{hrmUserService}")
    private HrmUserService hrmUserService;
    @ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;

    /**
     * Optional Service, only when it's needed (usually for many to many
     * approval definition)
     */
    @ManagedProperty(value = "#{rmbsSchemaService}")
    private RmbsSchemaService rmbsSchemaService;
    @ManagedProperty(value = "#{loanNewSchemaService}")
    private LoanNewSchemaService loanNewSchemaService;

    /**
     * END Optional Service
     */
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        approvalDefinitionModel = new ApprovalDefinitionModel();
        isPersistedToDB = StringUtils.equals(FacesUtil.getRequestParameter("isPersistedToDB"), "true");
        entityId = FacesUtil.getRequestParameter("entityId");

        String jsonAppDef = FacesUtil.getRequestParameter("jsonAppDef");
        if (StringUtils.isNotEmpty(jsonAppDef)) {
            try {
                isEdit = Boolean.TRUE;
                ApprovalDefinition ad = (ApprovalDefinition) JsonConverter.getClassFromJson(jsonAppDef, ApprovalDefinition.class, "dd MMMM yyyy hh:mm");
                approvalDefinitionModel.setId(ad.getId());
                approvalDefinitionModel.setMinApprover(ad.getMinApprover());
                approvalDefinitionModel.setMinRejector(ad.getMinRejector());
                approvalDefinitionModel.setAllowOnBehalf(ad.getAllowOnBehalf());
                approvalDefinitionModel.setApproverType(ad.getApproverType());
                approvalDefinitionModel.setAutoApproveOnDelay(ad.getAutoApproveOnDelay());
                approvalDefinitionModel.setDelayTime(ad.getDelayTime());
                approvalDefinitionModel.setEscalateOnDelay(ad.getEscalateOnDelay());
                approvalDefinitionModel.setName(ad.getName());
                approvalDefinitionModel.setProcessType(ad.getProcessType());
                approvalDefinitionModel.setSequence(ad.getSequence());
                approvalDefinitionModel.setSmsNotification(ad.getSmsNotification());
                approvalDefinitionModel.setSpecificName(ad.getSpecificName());

                onBehalf = !ad.getAllowOnBehalf();
                onBehalfApproverTypeIndividual = onBehalf;
                onBehalfApproverTypePosition = onBehalf;

                if (ad.getApproverType().equalsIgnoreCase(HRMConstant.APPROVAL_TYPE_INDIVIDUAL)) {
                    HrmUser user = hrmUserService.getEntiyByPkWithDetail(ad.getHrmUserByApproverIndividual().getId());
                    approvalDefinitionModel.setHrmUserByApproverIndividual(user);
                    approverTypeIndividual = Boolean.FALSE;
                    approverTypePosition = Boolean.TRUE;
                } else if (ad.getApproverType().equalsIgnoreCase(HRMConstant.APPROVAL_TYPE_POSITION)) {
                    Jabatan jabatan = jabatanService.getEntiyByPK(ad.getJabatanByApproverPosition().getId());
                    approvalDefinitionModel.setJabatanByApproverPosition(jabatan);
                    approverTypeIndividual = Boolean.TRUE;
                    approverTypePosition = Boolean.FALSE;
                } else if (ad.getApproverType().equalsIgnoreCase(HRMConstant.APPROVAL_TYPE_DEPARTMENT)) {
                    approverTypeIndividual = Boolean.TRUE;
                    approverTypePosition = Boolean.TRUE;
                }

                if (ad.getOnBehalfType() != null) {
                    approvalDefinitionModel.setOnBehalfType(ad.getOnBehalfType());
                    if (ad.getHrmUserByOnBehalfIndividual() != null) {
                        HrmUser user = hrmUserService.getEntiyByPkWithDetail(ad.getHrmUserByOnBehalfIndividual().getId());
                        approvalDefinitionModel.setHrmUserByOnBehalfIndividual(user);
                        onBehalfApproverTypeIndividual = Boolean.FALSE;
                        onBehalfApproverTypePosition = Boolean.TRUE;
                    } else if (ad.getJabatanByOnBehalfPosition() != null) {
                        Jabatan jabatan = jabatanService.getEntiyByPK(ad.getJabatanByOnBehalfPosition().getId());
                        approvalDefinitionModel.setJabatanByOnBehalfPosition(jabatan);
                        onBehalfApproverTypeIndividual = Boolean.TRUE;
                        onBehalfApproverTypePosition = Boolean.FALSE;
                    }
                }

            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }

        } else {
            String appDefName = FacesUtil.getRequestParameter("appDefName");
            String specificName = FacesUtil.getRequestParameter("specificName");

            isEdit = Boolean.FALSE;
            onBehalf = Boolean.TRUE;
            onProcess = Boolean.TRUE;
            approverTypeIndividual = Boolean.TRUE;
            approverTypePosition = Boolean.TRUE;
            onBehalfApproverTypeIndividual = Boolean.TRUE;
            onBehalfApproverTypePosition = Boolean.TRUE;
            onAutoApprove = Boolean.TRUE;
            approvalDefinitionModel.setAutoApproveOnDelay(onAutoApprove);
            approvalDefinitionModel.setEscalateOnDelay(!onAutoApprove);
            approvalDefinitionModel.setMinApprover(1);
            approvalDefinitionModel.setMinRejector(1);
            approvalDefinitionModel.setName(appDefName);
            approvalDefinitionModel.setSmsNotification(Boolean.FALSE);
            if (StringUtils.isNotEmpty(specificName)) {
                approvalDefinitionModel.setSpecificName(specificName);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        approvalDefinitionService = null;
        approvalDefinitionModel = null;
        onBehalf = null;
        onProcess = null;
        approverTypeIndividual = null;
        approverTypePosition = null;
        onBehalfApproverTypeIndividual = null;
        onBehalfApproverTypePosition = null;
        onAutoApprove = null;
        approvalDefinitionService = null;
        hrmUserService = null;
        jabatanService = null;
        isEdit = null;
        isPersistedToDB = null;
        entityId = null;
        rmbsSchemaService = null;
    }

    public void doReset(){
    	approvalDefinitionModel.setAutoApproveOnDelay(Boolean.TRUE);
        approvalDefinitionModel.setEscalateOnDelay(Boolean.FALSE);
        approvalDefinitionModel.setSmsNotification(Boolean.FALSE);
        approvalDefinitionModel.setSequence(1);
        approvalDefinitionModel.setMinApprover(1);
        approvalDefinitionModel.setMinRejector(1);
        approvalDefinitionModel.setDelayTime(1);
        approvalDefinitionModel.setProcessType(null);
        approvalDefinitionModel.setApproverType(null);
        approvalDefinitionModel.setHrmUserByApproverIndividual(null);
        approvalDefinitionModel.setJabatanByApproverPosition(null);
        approvalDefinitionModel.setOnBehalfType(null);
        approvalDefinitionModel.setHrmUserByOnBehalfIndividual(null);
        approvalDefinitionModel.setJabatanByOnBehalfPosition(null);
        approvalDefinitionModel.setAllowOnBehalf(Boolean.FALSE);
        onBehalf = Boolean.TRUE;
        onProcess = Boolean.TRUE;
        approverTypeIndividual = Boolean.TRUE;
        approverTypePosition = Boolean.TRUE;
        onBehalfApproverTypeIndividual = Boolean.TRUE;
        onBehalfApproverTypePosition = Boolean.TRUE;
        onAutoApprove = Boolean.TRUE;
        
    }
    public void onProcessChange() {
        String approvalProcess = approvalDefinitionModel.getProcessType();
        if (StringUtils.equalsIgnoreCase(approvalProcess, HRMConstant.ON_APPROVE_INFO)
                || StringUtils.equalsIgnoreCase(approvalProcess, HRMConstant.ON_REJECT_INFO)) {
            onProcess = Boolean.TRUE;
        } else if (StringUtils.equalsIgnoreCase(approvalProcess, HRMConstant.APPROVAL_PROCESS) || approvalProcess == null || approvalProcess.isEmpty()) {
            onProcess = Boolean.FALSE;
        }
    }

    public void onAppoverChange() {
        String apporverType = approvalDefinitionModel.getApproverType();
        if (StringUtils.equalsIgnoreCase(apporverType, HRMConstant.APPROVAL_TYPE_INDIVIDUAL)) {
            approverTypeIndividual = Boolean.FALSE;
            approverTypePosition = Boolean.TRUE;
        } else if (StringUtils.equalsIgnoreCase(apporverType, HRMConstant.APPROVAL_TYPE_DEPARTMENT)) {
            approverTypeIndividual = Boolean.TRUE;
            approverTypePosition = Boolean.TRUE;
        } else if (StringUtils.equalsIgnoreCase(apporverType, HRMConstant.APPROVAL_TYPE_POSITION)) {
            approverTypeIndividual = Boolean.TRUE;
            approverTypePosition = Boolean.FALSE;
        }

        approvalDefinitionModel.setHrmUserByApproverIndividual(null);
        approvalDefinitionModel.setJabatanByApproverPosition(null);
    }

    public void onBehalfAppoverChange() {
        String apporverType = approvalDefinitionModel.getOnBehalfType();
        if (StringUtils.equalsIgnoreCase(apporverType, HRMConstant.APPROVAL_TYPE_INDIVIDUAL)) {
            onBehalfApproverTypeIndividual = Boolean.FALSE;
            onBehalfApproverTypePosition = Boolean.TRUE;
        } else if (StringUtils.equalsIgnoreCase(apporverType, HRMConstant.APPROVAL_TYPE_DEPARTMENT)) {
            onBehalfApproverTypeIndividual = Boolean.TRUE;
            onBehalfApproverTypePosition = Boolean.TRUE;
        } else if (StringUtils.equalsIgnoreCase(apporverType, HRMConstant.APPROVAL_TYPE_POSITION)) {
            onBehalfApproverTypeIndividual = Boolean.TRUE;
            onBehalfApproverTypePosition = Boolean.FALSE;
        }
        approvalDefinitionModel.setHrmUserByOnBehalfIndividual(null);
        approvalDefinitionModel.setJabatanByOnBehalfPosition(null);

    }

    public void onAllowOnBehalfChange() {
        onBehalf = approvalDefinitionModel.getAllowOnBehalf();
    }

    public void autoApproveOnDelay() {
        approvalDefinitionModel.setEscalateOnDelay(approvalDefinitionModel.getAutoApproveOnDelay());
    }

    public void escalateOnDelay() {
        approvalDefinitionModel.setAutoApproveOnDelay(approvalDefinitionModel.getEscalateOnDelay());
    }

    public List<HrmUser> doAutoCompleteUser(String param) {
        List<HrmUser> users = new ArrayList<HrmUser>();
        try {
            users = hrmUserService.getAllDataByNameOrNik(StringUtils.stripToEmpty(param));
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
        return users;
    }

    public List<Jabatan> doAutoCompleteJabatan(String param) {
        List<Jabatan> jabatans = new ArrayList<Jabatan>();
        try {
            jabatans = jabatanService.getAllDataByCodeOrName(StringUtils.stripToEmpty(param));
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
        return jabatans;
    }

    public void doSaveWithPersistedToDB() {
        ApprovalDefinition approvalDefinition = this.getEntityFromViewModel(approvalDefinitionModel);
        try {
            switch (approvalDefinition.getName()) {
                case HRMConstant.REIMBURSEMENT:
                    if (isEdit) {
                        rmbsSchemaService.updateApprovalConf(approvalDefinition, Long.parseLong(entityId));
                    } else {
                        rmbsSchemaService.saveApprovalConf(approvalDefinition, Long.parseLong(entityId));
                    }
                    break;
                case HRMConstant.LOAN:
                    if (isEdit) {
                        loanNewSchemaService.updateApprovalConf(approvalDefinition, Long.parseLong(entityId));
                    } else {
                        loanNewSchemaService.saveApprovalConf(approvalDefinition, Long.parseLong(entityId));
                    }
                    break;
                default:
                    break;
            }

            RequestContext.getCurrentInstance().closeDialog(isEdit ? HRMConstant.UPDATE_CONDITION : HRMConstant.SAVE_CONDITION);
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doSaveWithoutPersistedToDB() {
        ApprovalDefinition approvalDefinition = this.getEntityFromViewModel(approvalDefinitionModel);
        RequestContext.getCurrentInstance().closeDialog(approvalDefinition);
        cleanAndExit();
    }

    private ApprovalDefinition getEntityFromViewModel(ApprovalDefinitionModel approvalDefinitionModel) {

        ApprovalDefinition approvalDefinition = new ApprovalDefinition();
        if (approvalDefinitionModel.getId() != null) {
            approvalDefinition.setId(approvalDefinitionModel.getId());
        }
        approvalDefinition.setAllowOnBehalf(approvalDefinitionModel.getAllowOnBehalf());
        approvalDefinition.setApproverType(approvalDefinitionModel.getApproverType());
        approvalDefinition.setHrmUserByApproverIndividual(approvalDefinitionModel.getHrmUserByApproverIndividual());
        approvalDefinition.setHrmUserByOnBehalfIndividual(approvalDefinitionModel.getHrmUserByOnBehalfIndividual());
        approvalDefinition.setJabatanByApproverPosition(approvalDefinitionModel.getJabatanByApproverPosition());
        approvalDefinition.setJabatanByOnBehalfPosition(approvalDefinitionModel.getJabatanByOnBehalfPosition());
        approvalDefinition.setMinApprover(approvalDefinitionModel.getMinApprover());
        approvalDefinition.setMinRejector(approvalDefinitionModel.getMinRejector());
        approvalDefinition.setName(approvalDefinitionModel.getName());
        approvalDefinition.setOnBehalfType(approvalDefinitionModel.getOnBehalfType());
        approvalDefinition.setProcessType(approvalDefinitionModel.getProcessType());
        approvalDefinition.setSequence(approvalDefinitionModel.getSequence());
        approvalDefinition.setAutoApproveOnDelay(approvalDefinitionModel.getAutoApproveOnDelay());
        approvalDefinition.setEscalateOnDelay(approvalDefinitionModel.getEscalateOnDelay());
        approvalDefinition.setDelayTime(approvalDefinitionModel.getDelayTime());
        approvalDefinition.setSmsNotification(approvalDefinitionModel.getSmsNotification());
        approvalDefinition.setSpecificName(approvalDefinitionModel.getSpecificName());
        return approvalDefinition;
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

    public void setApprovalDefinitionService(ApprovalDefinitionService approvalDefinitionService) {
        this.approvalDefinitionService = approvalDefinitionService;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    public Boolean getOnAutoApprove() {
        return onAutoApprove;
    }

    public void setOnAutoApprove(Boolean onAutoApprove) {
        this.onAutoApprove = onAutoApprove;
    }

    public Boolean getIsPersistedToDB() {
        return isPersistedToDB;
    }

    public void setIsPersistedToDB(Boolean isPersistedToDB) {
        this.isPersistedToDB = isPersistedToDB;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public void setHrmUserService(HrmUserService hrmUserService) {
        this.hrmUserService = hrmUserService;
    }

    public void setJabatanService(JabatanService jabatanService) {
        this.jabatanService = jabatanService;
    }

    public RmbsSchemaService getRmbsSchemaService() {
        return rmbsSchemaService;
    }

    public void setRmbsSchemaService(RmbsSchemaService rmbsSchemaService) {
        this.rmbsSchemaService = rmbsSchemaService;
    }

    public ApprovalDefinitionService getApprovalDefinitionService() {
        return approvalDefinitionService;
    }

    public HrmUserService getHrmUserService() {
        return hrmUserService;
    }

    public JabatanService getJabatanService() {
        return jabatanService;
    }

    public LoanNewSchemaService getLoanNewSchemaService() {
        return loanNewSchemaService;
    }

    public void setLoanNewSchemaService(LoanNewSchemaService loanNewSchemaService) {
        this.loanNewSchemaService = loanNewSchemaService;
    }

    
}
