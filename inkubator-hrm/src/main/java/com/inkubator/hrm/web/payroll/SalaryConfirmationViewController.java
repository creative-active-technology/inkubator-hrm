package com.inkubator.hrm.web.payroll;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.web.lazymodel.SalaryConfirmationLazyDataModel;
import com.inkubator.hrm.web.search.SalaryConfirmationParameter;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "salaryConfirmationViewController")
@ViewScoped
public class SalaryConfirmationViewController extends BaseController {

    private SalaryConfirmationParameter searchParameter;
    private LazyDataModel<EmpData> lazyDataModel;
    private List<GolonganJabatan> listGolJab;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private EmpData selectedEmpData;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new SalaryConfirmationParameter();
        try {
            listGolJab = golonganJabatanService.getAllData();
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        empDataService = null;
        searchParameter = null;
        lazyDataModel = null;
        listGolJab = null;
        golonganJabatanService = null;
        selectedEmpData = null;
    }

    public String doDetail() {
        return "/protected/payroll/salary_confirmation_detail.htm?faces-redirect=true&execution=e" + selectedEmpData.getId();
    }
    
    public LazyDataModel<EmpData> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new SalaryConfirmationLazyDataModel(searchParameter, empDataService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<EmpData> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public List<GolonganJabatan> getListGolJab() {
        return listGolJab;
    }

    public void setListGolJab(List<GolonganJabatan> listGolJab) {
        this.listGolJab = listGolJab;
    }

    public void setGolonganJabatanService(GolonganJabatanService golonganJabatanService) {
        this.golonganJabatanService = golonganJabatanService;
    }

    public SalaryConfirmationParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(SalaryConfirmationParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public void doSearch() {
        lazyDataModel = null;
    }

    public EmpData getSelectedEmpData() {
        return selectedEmpData;
    }

    public void setSelectedEmpData(EmpData selectedEmpData) {
        this.selectedEmpData = selectedEmpData;
    }

}
