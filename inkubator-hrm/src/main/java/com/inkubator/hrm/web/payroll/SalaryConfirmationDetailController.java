/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author deni
 */
@ManagedBean(name = "salaryConfirmationDetailController")
@ViewScoped
public class SalaryConfirmationDetailController extends BaseController {

    @ManagedProperty(value = "#{payTempKalkulasiService}")
    private PayTempKalkulasiService PayTempKalkulasiService;
    private PayTempKalkulasi selectedPayTempKalkulasi;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String payTempKalkulasi = FacesUtil.getRequestParameter("execution");
            selectedPayTempKalkulasi = PayTempKalkulasiService.getEntityByEmpIdAndModelTakeHomePayId(Long.parseLong(payTempKalkulasi.substring(1)));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
        PayTempKalkulasiService = null;
        selectedPayTempKalkulasi = null;
    }
    
    public PayTempKalkulasiService getPayTempKalkulasiService() {
        return PayTempKalkulasiService;
    }

    public void setPayTempKalkulasiService(PayTempKalkulasiService PayTempKalkulasiService) {
        this.PayTempKalkulasiService = PayTempKalkulasiService;
    }

    public PayTempKalkulasi getSelectedPayTempKalkulasi() {
        return selectedPayTempKalkulasi;
    }

    public void setSelectedPayTempKalkulasi(PayTempKalkulasi selectedPayTempKalkulasi) {
        this.selectedPayTempKalkulasi = selectedPayTempKalkulasi;
    }
    
    
}
