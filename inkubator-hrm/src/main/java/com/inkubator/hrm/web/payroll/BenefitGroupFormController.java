package com.inkubator.hrm.web.payroll;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BenefitGroup;
import com.inkubator.hrm.service.BenefitGroupService;
import com.inkubator.hrm.web.model.BenefitGroupModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "benefitGroupFormController")
@ViewScoped
public class BenefitGroupFormController extends BaseController {

    private BenefitGroupModel benefitGroupModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{benefitGroupService}")
    private BenefitGroupService benefitGroupService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        benefitGroupModel = new BenefitGroupModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                BenefitGroup benefitGroup = benefitGroupService.getEntiyByPK(Long.parseLong(param));
                if (benefitGroup != null) {
                    benefitGroupModel.setId(benefitGroup.getId());
                    benefitGroupModel.setCode(benefitGroup.getCode());
                    benefitGroupModel.setName(benefitGroup.getName());
                    benefitGroupModel.setValidDate(benefitGroup.getValidDate());
                    benefitGroupModel.setDescription(benefitGroup.getDescription());
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        benefitGroupService = null;
//        benefitGroupModel = null;
        isUpdate = null;
    }

    public BenefitGroupModel getBenefitGroupModel() {
        return benefitGroupModel;
    }

    public void setBenefitGroupModel(BenefitGroupModel benefitGroupModel) {
        this.benefitGroupModel = benefitGroupModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setBenefitGroupService(BenefitGroupService benefitGroupService) {
        this.benefitGroupService = benefitGroupService;
    }

    public void doSave() {
        BenefitGroup benefitGroup = getEntityFromViewModel(benefitGroupModel);
        try {
            if (isUpdate) {
                benefitGroupService.update(benefitGroup);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                benefitGroupService.save(benefitGroup);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private BenefitGroup getEntityFromViewModel(BenefitGroupModel benefitGroupModel) {
        BenefitGroup benefitGroup = new BenefitGroup();
        if (benefitGroupModel.getId() != null) {
            benefitGroup.setId(benefitGroupModel.getId());
        }
        benefitGroup.setCode(benefitGroupModel.getCode());
        benefitGroup.setName(benefitGroupModel.getName());
        benefitGroup.setValidDate(benefitGroupModel.getValidDate());
        benefitGroup.setDescription(benefitGroupModel.getDescription());
        return benefitGroup;
    }
}
