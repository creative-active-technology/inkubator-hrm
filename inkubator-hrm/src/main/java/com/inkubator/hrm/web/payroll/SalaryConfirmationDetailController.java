/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.entity.PayTempKalkulasiEmpPajak;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.hrm.service.PayTempKalkulasiEmpPajakService;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import static org.joda.time.DateTime.now;
import org.joda.time.DurationFieldType;
import org.joda.time.Years;

/**
 *
 * @author deni
 */
@ManagedBean(name = "salaryConfirmationDetailController")
@ViewScoped
public class SalaryConfirmationDetailController extends BaseController {

    @ManagedProperty(value = "#{payTempKalkulasiService}")
    private PayTempKalkulasiService payTempKalkulasiService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    @ManagedProperty(value = "#{payTempKalkulasiEmpPajakService}")
    private PayTempKalkulasiEmpPajakService payTempKalkulasiEmpPajakService;
    private PayTempKalkulasi selectedPayTempKalkulasi;
    private String yearMonth;
    private List<PayTempKalkulasi> listPayTempKalkulasi;
    private List<PayTempKalkulasiEmpPajak> listTempKalkulasiPajak;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
            String empDataId = FacesUtil.getRequestParameter("execution");
            selectedPayTempKalkulasi = payTempKalkulasiService.getEntityByEmpIdAndModelTakeHomePayId(Long.parseLong(empDataId.substring(1)));
            WtPeriode wtPeriode = wtPeriodeService.getEntityByPayrollTypeActive();
            DateMidnight date1 = new DateMidnight(selectedPayTempKalkulasi.getEmpData().getJoinDate());
            DateTime now = new DateTime(wtPeriode.getUntilPeriode());
            // mencari total tahun
            Years years = Years.yearsBetween(date1, now);
            //mencari total bulan
            Integer totalMonth = DateTimeUtil.getTotalMonthDifference(selectedPayTempKalkulasi.getEmpData().getJoinDate(), wtPeriode.getUntilPeriode());
            //mencari total sisa bulan
            Integer totalMonthDifference = totalMonth - (12 * years.getYears());
            yearMonth = years.getYears() +" " + messages.getString("global.year") + " " + totalMonthDifference + " " + messages.getString("global.month");
            
            //list pay temp kalkulasi
            listPayTempKalkulasi = payTempKalkulasiService.getAllDataByEmpDataId(Long.valueOf(empDataId.substring(1)));
            listTempKalkulasiPajak = payTempKalkulasiEmpPajakService.getAllDataByEmpDataId(Long.valueOf(empDataId.substring(1)));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    
    @PreDestroy
    public void cleanAndExit() {
        payTempKalkulasiService = null;
        selectedPayTempKalkulasi = null;
        wtPeriodeService = null;
        listPayTempKalkulasi = null;
        listTempKalkulasiPajak = null;
    }
    
    public PayTempKalkulasiService getPayTempKalkulasiService() {
        return payTempKalkulasiService;
    }

    public void setPayTempKalkulasiService(PayTempKalkulasiService payTempKalkulasiService) {
        this.payTempKalkulasiService = payTempKalkulasiService;
    }

    public PayTempKalkulasi getSelectedPayTempKalkulasi() {
        return selectedPayTempKalkulasi;
    }

    public void setSelectedPayTempKalkulasi(PayTempKalkulasi selectedPayTempKalkulasi) {
        this.selectedPayTempKalkulasi = selectedPayTempKalkulasi;
    }

    public WtPeriodeService getWtPeriodeService() {
        return wtPeriodeService;
    }

    public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
        this.wtPeriodeService = wtPeriodeService;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public List<PayTempKalkulasi> getListPayTempKalkulasi() {
        return listPayTempKalkulasi;
    }

    public void setListPayTempKalkulasi(List<PayTempKalkulasi> listPayTempKalkulasi) {
        this.listPayTempKalkulasi = listPayTempKalkulasi;
    }

    public PayTempKalkulasiEmpPajakService getPayTempKalkulasiEmpPajakService() {
        return payTempKalkulasiEmpPajakService;
    }

    public void setPayTempKalkulasiEmpPajakService(PayTempKalkulasiEmpPajakService payTempKalkulasiEmpPajakService) {
        this.payTempKalkulasiEmpPajakService = payTempKalkulasiEmpPajakService;
    }

    public List<PayTempKalkulasiEmpPajak> getListTempKalkulasiPajak() {
        return listTempKalkulasiPajak;
    }

    public void setListTempKalkulasiPajak(List<PayTempKalkulasiEmpPajak> listTempKalkulasiPajak) {
        this.listTempKalkulasiPajak = listTempKalkulasiPajak;
    }
    
    
}
