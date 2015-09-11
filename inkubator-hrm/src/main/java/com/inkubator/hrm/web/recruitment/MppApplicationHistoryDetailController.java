/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BenefitGroup;
import com.inkubator.hrm.entity.BenefitGroupRate;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.service.BenefitGroupRateService;
import com.inkubator.hrm.service.BenefitGroupService;
import com.inkubator.hrm.service.RecruitMppApplyDetailService;
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
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "mppApplicationHistoryDetailController")
@ViewScoped
public class MppApplicationHistoryDetailController extends BaseController {

    private RecruitMppApplyDetail selected;
    @ManagedProperty(value = "#{recruitMppApplyDetailService}")
    private RecruitMppApplyDetailService recruitMppApplyDetailService;
    private List<RecruitMppApplyDetail> listMppApplyDetailInSelectedPeriod;
    private RecruitMppApplyDetail selectedDataList;
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String recruitMppApplyDetailId = FacesUtil.getRequestParameter("execution");
            selected = recruitMppApplyDetailService.getEntityWithDetail(Long.parseLong(recruitMppApplyDetailId.substring(1)));
            listMppApplyDetailInSelectedPeriod = new ArrayList<RecruitMppApplyDetail>();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    public String doBack() {
        return "/protected/recruitment/mpp_application_history_view.htm?faces-redirect=true";
    }

   public void doSelectDataList() {
        /*try {
            selectedDataList = benefitGroupRateService.getEntityByPKWithDetail(selectedBenefitGroupRate.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }*/
    }

    public void doUpdateRecruitMppApplyDetail() {

        List<String> benefitGroupRateId = new ArrayList<>();
        benefitGroupRateId.add(String.valueOf(selectedDataList.getId()));

        List<String> benefitGroupId = new ArrayList<>();
        benefitGroupId.add(String.valueOf(selectedDataList.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("benefitGroupRateId", benefitGroupRateId);
        dataToSend.put("benefitGroupId", benefitGroupId);
        showDialogBenefitGroupRate(dataToSend);

    }

   

    

    private void showDialogBenefitGroupRate(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 550);
        options.put("contentHeight", 330);
        RequestContext.getCurrentInstance().openDialog("benefit_group_rate", options, params);
    }

    public void onDialogReturnDataList(SelectEvent event) {
       /* try {
            benefitGroupRates = benefitGroupRateService.getAllDataByBenefitGroupId(selectedBenefitGroup.getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }*/
    }


    @PreDestroy
    public void cleanAndExit() {
        selected = null;
        recruitMppApplyDetailService = null;
    }
    
    
	public RecruitMppApplyDetail getSelectedDataList() {
		return selectedDataList;
	}

	public void setSelectedDataList(RecruitMppApplyDetail selectedDataList) {
		this.selectedDataList = selectedDataList;
	}

	public RecruitMppApplyDetail getSelected() {
		return selected;
	}

	public void setSelected(RecruitMppApplyDetail selected) {
		this.selected = selected;
	}

	public void setRecruitMppApplyDetailService(
			RecruitMppApplyDetailService recruitMppApplyDetailService) {
		this.recruitMppApplyDetailService = recruitMppApplyDetailService;
	}

	public List<RecruitMppApplyDetail> getListMppApplyDetailInSelectedPeriod() {
		return listMppApplyDetailInSelectedPeriod;
	}

	public void setListMppApplyDetailInSelectedPeriod(
			List<RecruitMppApplyDetail> listMppApplyDetailInSelectedPeriod) {
		this.listMppApplyDetailInSelectedPeriod = listMppApplyDetailInSelectedPeriod;
	}
    
    
}
