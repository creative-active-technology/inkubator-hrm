package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.entity.PayTempAttendanceStatus;
import com.inkubator.hrm.entity.Reimbursment;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.InclusionReimbursmentService;
import com.inkubator.hrm.service.PayTempAttendanceStatusService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.InclusionReimbursmentLazyDataModel;
import com.inkubator.hrm.web.lazymodel.PayTempAttendanceStatusLazyDataModel;
import com.inkubator.hrm.web.model.InclusionReimbursmentModel;
import com.inkubator.hrm.web.model.PayTempAttendanceStatusModel;
import com.inkubator.webcore.controller.BaseController;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "payTempAttendanceStatusViewController")
@ViewScoped
public class PayTempAttendanceStatusViewController extends BaseController{
    @ManagedProperty(value = "#{payTempAttendanceStatusService}")
    private PayTempAttendanceStatusService payTempAttendanceStatusService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    private String parameter;
    private LazyDataModel<PayTempAttendanceStatus> lazy;
    private PayTempAttendanceStatus selected;
    private PayTempAttendanceStatusModel payTempAttendanceStatusModel;
    private Integer jumlahKaryawan;
    private Integer jumlahHariKerja;
    //private BigDecimal jmlNominalReimbursment;
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            payTempAttendanceStatusModel = new PayTempAttendanceStatusModel();
            WtPeriode wtPeriode = wtPeriodeService.getEntityByPayrollTypeActive();
            if(wtPeriode != null){
            	payTempAttendanceStatusModel.setStartDate(wtPeriode.getFromPeriode());
            	payTempAttendanceStatusModel.setEndDate(wtPeriode.getUntilPeriode());
            }
            List<PayTempAttendanceStatus> listPayTempAttendanceStatus = payTempAttendanceStatusService.getByWtPeriodeWhereComponentPayrollIsActive(payTempAttendanceStatusModel);
            jumlahKaryawan = listPayTempAttendanceStatus.size();
            /*jmlNominalReimbursment = new BigDecimal(0);
            for (Reimbursment listReimbursement : listReimbursment) {
                jmlNominalReimbursment = jmlNominalReimbursment.add(listReimbursement.getNominal());
            }*/
        } catch (Exception ex) {
            Logger.getLogger(PayTempAttendanceStatusViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @PreDestroy
    private void cleanAndExit() {
        jumlahKaryawan = null;
        lazy = null;
        parameter = null;
        payTempAttendanceStatusService = null;
        wtPeriodeService = null;
        selected = null;
        payTempAttendanceStatusModel = null;
        jumlahHariKerja = null;
    }
    
    public void doSearch() {
        lazy = null;
    }

    
    public PayTempAttendanceStatusService getPayTempAttendanceStatusService() {
		return payTempAttendanceStatusService;
	}

	public void setPayTempAttendanceStatusService(
			PayTempAttendanceStatusService payTempAttendanceStatusService) {
		this.payTempAttendanceStatusService = payTempAttendanceStatusService;
	}

	public WtPeriodeService getWtPeriodeService() {
        return wtPeriodeService;
    }

    public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
        this.wtPeriodeService = wtPeriodeService;
    }

    
    public PayTempAttendanceStatusModel getPayTempAttendanceStatusModel() {
		return payTempAttendanceStatusModel;
	}

	public void setPayTempAttendanceStatusModel(
			PayTempAttendanceStatusModel payTempAttendanceStatusModel) {
		this.payTempAttendanceStatusModel = payTempAttendanceStatusModel;
	}

	public LazyDataModel<PayTempAttendanceStatus> getLazy() {
        if(lazy == null){
            lazy = new PayTempAttendanceStatusLazyDataModel(payTempAttendanceStatusService, payTempAttendanceStatusModel, parameter);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<PayTempAttendanceStatus> lazy) {
        this.lazy = lazy;
    }

    public PayTempAttendanceStatus getSelected() {
        return selected;
    }

    public void setSelected(PayTempAttendanceStatus selected) {
        this.selected = selected;
    }

    public Integer getJumlahKaryawan() {
        return jumlahKaryawan;
    }

    public void setJumlahKaryawan(Integer jumlahKaryawan) {
        this.jumlahKaryawan = jumlahKaryawan;
    }

   

    public Integer getJumlahHariKerja() {
		return jumlahHariKerja;
	}

	public void setJumlahHariKerja(Integer jumlahHariKerja) {
		this.jumlahHariKerja = jumlahHariKerja;
	}

	public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
    
    
}
