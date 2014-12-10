/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.webcore.controller.BaseController;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author deni
 */
@ManagedBean(name = "paySalaryExecuteDetailController")
@ViewScoped
public class PaySalaryExecuteDetailController extends BaseController {

    @ManagedProperty(value = "#{payTempKalkulasiService}")
    private PayTempKalkulasiService payTempKalkulasiService;
}
