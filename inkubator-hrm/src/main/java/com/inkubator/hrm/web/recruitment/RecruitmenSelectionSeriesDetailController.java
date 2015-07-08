/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.PaySalaryGrade;
import com.inkubator.hrm.entity.RecruitmenSelectionSeries;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetail;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetailId;
import com.inkubator.hrm.service.RecruitmenSelectionSeriesDetailService;
import com.inkubator.hrm.service.RecruitmenSelectionSeriesService;
import com.inkubator.hrm.web.lazymodel.RecruitmenSelectionSeriesDetailLazyDataModel;
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
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "recruitmenSelectionSeriesDetailController")
@ViewScoped
public class RecruitmenSelectionSeriesDetailController extends BaseController {

    @ManagedProperty(value = "#{recruitmenSelectionSeriesService}")
    private RecruitmenSelectionSeriesService recruitmenSelectionSeriesService;
    @ManagedProperty(value = "#{recruitmenSelectionSeriesDetailService}")
    private RecruitmenSelectionSeriesDetailService recruitmenSelectionSeriesDetailService;
    private RecruitmenSelectionSeries selected;
    private RecruitmenSelectionSeriesDetail selectedRecruitmenSelectionSeriesDetail;
    private LazyDataModel<RecruitmenSelectionSeriesDetail> lazyDataModel;
    private List<Integer> listOrder;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            String recruitSelectionSeriesId = FacesUtil.getRequestParameter("execution");
            selected = recruitmenSelectionSeriesService.getEntiyByPK(Long.valueOf(recruitSelectionSeriesId));
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        selected = null;
        recruitmenSelectionSeriesService = null;
        recruitmenSelectionSeriesDetailService = null;
        lazyDataModel = null;
        listOrder = null;
        selectedRecruitmenSelectionSeriesDetail = null;
    }

    public void doAdd() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> recruitSelectionSeriesId = new ArrayList<>();
        recruitSelectionSeriesId.add(String.valueOf(selected.getId()));
        dataToSend.put("recruitSelectionSeriesId", recruitSelectionSeriesId);
        showDialog(dataToSend);
    }

    public void doEdit(RecruitmenSelectionSeriesDetail recruitmenSelectionSeriesDetail) {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> recruitSelectionSeriesId = new ArrayList<>();
        List<String> recruitSelectedTypeId = new ArrayList<>();
        recruitSelectionSeriesId.add(String.valueOf(selectedRecruitmenSelectionSeriesDetail.getRecruitmenSelectionSeries().getId()));
        recruitSelectedTypeId.add(String.valueOf(selectedRecruitmenSelectionSeriesDetail.getRecruitSelectionType().getId()));
        dataToSend.put("recruitSelectionSeriesId", recruitSelectionSeriesId);
        dataToSend.put("recruitSelectedTypeId", recruitSelectedTypeId);
        showDialog(dataToSend);
    }

    public void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 360);
        RequestContext.getCurrentInstance().openDialog("recruit_selection_series_detail_form", options, params);
    }

    public void doDelete() {
        try {
            this.recruitmenSelectionSeriesDetailService.delete(selectedRecruitmenSelectionSeriesDetail);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doSelectEntity() throws Exception{
        selectedRecruitmenSelectionSeriesDetail = recruitmenSelectionSeriesDetailService.getEntityByPk(new RecruitmenSelectionSeriesDetailId(selectedRecruitmenSelectionSeriesDetail.getRecruitmenSelectionSeries().getId(), selectedRecruitmenSelectionSeriesDetail.getRecruitSelectionType().getId()));
    }
    
    public RecruitmenSelectionSeriesService getRecruitmenSelectionSeriesService() {
        return recruitmenSelectionSeriesService;
    }

    public void setRecruitmenSelectionSeriesService(RecruitmenSelectionSeriesService recruitmenSelectionSeriesService) {
        this.recruitmenSelectionSeriesService = recruitmenSelectionSeriesService;
    }

    public RecruitmenSelectionSeries getSelected() {
        return selected;
    }

    public void setSelected(RecruitmenSelectionSeries selected) {
        this.selected = selected;
    }

    public RecruitmenSelectionSeriesDetailService getRecruitmenSelectionSeriesDetailService() {
        return recruitmenSelectionSeriesDetailService;
    }

    public void setRecruitmenSelectionSeriesDetailService(RecruitmenSelectionSeriesDetailService recruitmenSelectionSeriesDetailService) {
        this.recruitmenSelectionSeriesDetailService = recruitmenSelectionSeriesDetailService;
    }

    public LazyDataModel<RecruitmenSelectionSeriesDetail> getLazyDataModel() {
        try {
            listOrder = new ArrayList<>();
            Long totalData = recruitmenSelectionSeriesDetailService.getTotalBySelectionSeriesId(selected.getId());
            for (int i = 1; i < totalData + 1; i++) {
                listOrder.add(i);
            }
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
        if (lazyDataModel == null) {
            lazyDataModel = new RecruitmenSelectionSeriesDetailLazyDataModel(recruitmenSelectionSeriesDetailService, selected.getId());
        }
        return lazyDataModel;
    }

    public void doChangeLevel(RecruitmenSelectionSeriesDetail recruitmenSelectionSeriesDetail) {

        try {
            int newGrade = recruitmenSelectionSeriesDetail.getListOrder();
//            Long idOldData = recruitmenSelectionSeriesDetail.getId();
            recruitmenSelectionSeriesDetailService.doChangerListOrder(newGrade, recruitmenSelectionSeriesDetail.getRecruitSelectionType().getId(), recruitmenSelectionSeriesDetail.getRecruitmenSelectionSeries().getId(), recruitmenSelectionSeriesDetail.getRecruitmenSelectionSeries().getId()); 
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }

    }
    
    public void setLazyDataModel(LazyDataModel<RecruitmenSelectionSeriesDetail> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public List<Integer> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<Integer> listOrder) {
        this.listOrder = listOrder;
    }

    public RecruitmenSelectionSeriesDetail getSelectedRecruitmenSelectionSeriesDetail() {
        return selectedRecruitmenSelectionSeriesDetail;
    }

    public void setSelectedRecruitmenSelectionSeriesDetail(RecruitmenSelectionSeriesDetail selectedRecruitmenSelectionSeriesDetail) {
        this.selectedRecruitmenSelectionSeriesDetail = selectedRecruitmenSelectionSeriesDetail;
    }

    
}
