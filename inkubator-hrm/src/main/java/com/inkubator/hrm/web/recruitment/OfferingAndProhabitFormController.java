/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RecruitCommChannels;
import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.RecruitCommChannelsService;
import com.inkubator.hrm.service.RecruitLetterComChannelService;
import com.inkubator.hrm.service.RecruitSelectionTypeService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.loan.LoanApplicationFormController;
import com.inkubator.hrm.web.model.OfferingAndProhabitModel;
import com.inkubator.webcore.controller.BaseController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DualListModel;

/**
 *
 * @author denifahri
 */
@ManagedBean(name = "offeringAndProhabitFormController")
@ViewScoped
public class OfferingAndProhabitFormController extends BaseController {

    private OfferingAndProhabitModel offeringAndProhabitModel;
    private DualListModel<RecruitSelectionType> dualListModel = new DualListModel<>();
    @ManagedProperty(value = "#{recruitSelectionTypeService}")
    private RecruitSelectionTypeService recruitSelectionTypeService;
    @ManagedProperty(value = "#{recruitCommChannelsService}")
    private RecruitCommChannelsService recruitCommChannelsService;
    private DualListModel<RecruitCommChannels> dualListModelCom = new DualListModel<>();
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            offeringAndProhabitModel = new OfferingAndProhabitModel();
            offeringAndProhabitModel.setExpiredTime(1);
            List<RecruitSelectionType> sourceDualLis = recruitSelectionTypeService.getAllData();
            List<RecruitCommChannels> sourceDualLisCom = recruitCommChannelsService.getAllData();
            dualListModel.setSource(sourceDualLis);
            dualListModelCom.setSource(sourceDualLisCom);
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    @PreDestroy
    private void cleanAndExit() {
        dualListModel = null;
        offeringAndProhabitModel = null;
    }

    public OfferingAndProhabitModel getOfferingAndProhabitModel() {
        return offeringAndProhabitModel;
    }

    public void setOfferingAndProhabitModel(OfferingAndProhabitModel offeringAndProhabitModel) {
        this.offeringAndProhabitModel = offeringAndProhabitModel;
    }

    public DualListModel<RecruitSelectionType> getDualListModel() {
        return dualListModel;
    }

    public void setDualListModel(DualListModel<RecruitSelectionType> dualListModel) {
        this.dualListModel = dualListModel;
    }

    public void setRecruitSelectionTypeService(RecruitSelectionTypeService recruitSelectionTypeService) {
        this.recruitSelectionTypeService = recruitSelectionTypeService;
    }

    public DualListModel<RecruitCommChannels> getDualListModelCom() {
        return dualListModelCom;
    }

    public void setDualListModelCom(DualListModel<RecruitCommChannels> dualListModelCom) {
        this.dualListModelCom = dualListModelCom;
    }

    public void setRecruitCommChannelsService(RecruitCommChannelsService recruitCommChannelsService) {
        this.recruitCommChannelsService = recruitCommChannelsService;
    }

    public List<EmpData> completeEmpData(String query) {
        try {
            List<EmpData> allEmpData = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(query), HrmUserInfoUtil.getCompanyId());
            return allEmpData;
        } catch (Exception ex) {
          LOGGER.error(ex, ex);
        }
        return null;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public void doSave(){
        
    }
}
