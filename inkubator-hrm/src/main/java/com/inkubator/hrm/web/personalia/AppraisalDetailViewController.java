package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.AppraisalDetail;
import com.inkubator.hrm.service.AppraisalDetailService;
import com.inkubator.hrm.web.lazymodel.AppraisalDetailLazyDataModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "appraisalDetailViewController")
@ViewScoped
public class AppraisalDetailViewController extends BaseController {

    private String searchParameter;
    private LazyDataModel<AppraisalDetail> lazyDataAppraisalDetail;
    private AppraisalDetail selectedAppraisalDetail;
    @ManagedProperty(value = "#{appraisalDetailService}")
    private AppraisalDetailService appraisalDetailService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
    }

    @PreDestroy
    public void cleanAndExit() {
        appraisalDetailService = null;
        searchParameter = null;
        lazyDataAppraisalDetail = null;
        selectedAppraisalDetail = null;
    }

    public void setAppraisalDetailService(AppraisalDetailService appraisalDetailService) {
        this.appraisalDetailService = appraisalDetailService;
    }

    public String getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(String searchParameter) {
        this.searchParameter = searchParameter;
    }

    
    

    public LazyDataModel<AppraisalDetail> getLazyDataAppraisalDetail() {
        if (lazyDataAppraisalDetail == null) {
            lazyDataAppraisalDetail = new AppraisalDetailLazyDataModel(searchParameter, appraisalDetailService);
        }
        return lazyDataAppraisalDetail;
    }

    public void setLazyDataAppraisalDetail(LazyDataModel<AppraisalDetail> lazyDataAppraisalDetail) {
        this.lazyDataAppraisalDetail = lazyDataAppraisalDetail;
    }

    public AppraisalDetail getSelectedAppraisalDetail() {
        return selectedAppraisalDetail;
    }

    public void setSelectedAppraisalDetail(AppraisalDetail selectedAppraisalDetail) {
        this.selectedAppraisalDetail = selectedAppraisalDetail;
    }

    public void doSearch() {
        lazyDataAppraisalDetail = null;
    }

    public void doDetail() {
        try {
            selectedAppraisalDetail = this.appraisalDetailService.getEntityByPKWithDetail(selectedAppraisalDetail.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            appraisalDetailService.delete(selectedAppraisalDetail);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete appraisalDetail ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete appraisalDetail", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedAppraisalDetail.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 400);
        RequestContext.getCurrentInstance().openDialog("appraisal_detail_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
