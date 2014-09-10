package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.AppraisalElement;
import com.inkubator.hrm.entity.AppraisalDetail;
import com.inkubator.hrm.service.AppraisalElementService;
import com.inkubator.hrm.service.AppraisalDetailService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.AppraisalDetailModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
@ManagedBean(name = "appraisalDetailFormController")
@ViewScoped
public class AppraisalDetailFormController extends BaseController {

    private AppraisalDetailModel appraisalDetailModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{appraisalDetailService}")
    private AppraisalDetailService appraisalDetailService;
    @ManagedProperty(value = "#{appraisalElementService}")
    private AppraisalElementService appraisalElementService;
    private Map<String, Long> elements = new TreeMap<>();

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("param");
            appraisalDetailModel = new AppraisalDetailModel();
            isUpdate = Boolean.FALSE;

            List<AppraisalElement> listElement = appraisalElementService.getAllData();

            for (AppraisalElement element : listElement) {
                elements.put(element.getName(), element.getId());
            }

            MapUtil.sortByValue(elements);

            if (StringUtils.isNumeric(param)) {
                AppraisalDetail appraisalDetail = appraisalDetailService.getEntityByPKWithDetail(Long.parseLong(param));
                if (appraisalDetail != null) {
                    appraisalDetailModel.setId(appraisalDetail.getId());
                    appraisalDetailModel.setName(appraisalDetail.getName());
                    appraisalDetailModel.setDescription(appraisalDetail.getDescription());
                    appraisalDetailModel.setAppraisalElementId(appraisalDetail.getAppraisalElement().getId());
                    isUpdate = Boolean.TRUE;
                }

            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        appraisalDetailService = null;
//        appraisalDetailModel = null;
        isUpdate = null;
    }

    public AppraisalDetailModel getAppraisalDetailModel() {
        return appraisalDetailModel;
    }

    public void setAppraisalDetailModel(AppraisalDetailModel appraisalDetailModel) {
        this.appraisalDetailModel = appraisalDetailModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setAppraisalDetailService(AppraisalDetailService appraisalDetailService) {
        this.appraisalDetailService = appraisalDetailService;
    }

    public Map<String, Long> getElements() {
        return elements;
    }

    public void setElements(Map<String, Long> elements) {
        this.elements = elements;
    }

    public void setAppraisalElementService(AppraisalElementService appraisalElementService) {
        this.appraisalElementService = appraisalElementService;
    }
    
    

    public void doSave() {
        AppraisalDetail appraisalDetail = getEntityFromViewModel(appraisalDetailModel);
        try {
            if (isUpdate) {
                appraisalDetailService.update(appraisalDetail);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                appraisalDetailService.save(appraisalDetail);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { //data already exist(duplicate)
            LOGGER.error("Error", ex);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private AppraisalDetail getEntityFromViewModel(AppraisalDetailModel appraisalDetailModel) {
        AppraisalDetail appraisalDetail = new AppraisalDetail();
        if (appraisalDetailModel.getId() != null) {
            appraisalDetail.setId(appraisalDetailModel.getId());
        }
        appraisalDetail.setName(appraisalDetailModel.getName());
        appraisalDetail.setDescription(appraisalDetailModel.getDescription());
        appraisalDetail.setAppraisalElement(new AppraisalElement(appraisalDetailModel.getAppraisalElementId()));
        return appraisalDetail;
    }
}
