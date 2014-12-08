/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.webcore.controller.BaseController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "paySalaryExecuteController")
@ViewScoped
public class PaySalaryExecuteController extends BaseController {

    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
     @ManagedProperty(value = "#{payTempKalkulasiService}")
    private PayTempKalkulasiService payTempKalkulasiService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();

    }

    @PreDestroy
    public void cleanAndExit() {

    }

    public void calculatePayRoll() {
        try {
            payTempKalkulasiService.calculatePayRoll();
        } catch (Exception ex) {
          LOGGER.error(ex, ex);
        }
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public void setPayTempKalkulasiService(PayTempKalkulasiService payTempKalkulasiService) {
        this.payTempKalkulasiService = payTempKalkulasiService;
    }

    
}
