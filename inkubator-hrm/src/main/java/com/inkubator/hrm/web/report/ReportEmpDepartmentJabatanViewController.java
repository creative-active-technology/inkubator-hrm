package com.inkubator.hrm.web.report;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.web.lazymodel.ReportEmpDepartmentJabatanLazyDataModel;
import com.inkubator.hrm.web.search.ReportEmpDepartmentJabatanParameter;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import org.primefaces.model.DualListModel;

/**
 *
 * @author taufik
 */
@ManagedBean(name = "reportEmpDepartmentJabatanViewController")
@ViewScoped
public class ReportEmpDepartmentJabatanViewController extends BaseController {

    private ReportEmpDepartmentJabatanParameter searchParameter;
    private LazyDataModel<EmpData> lazyDataModel;
    private DualListModel<GolonganJabatan> dualListModel = new DualListModel<>();
    private List<Department> listDepartment;
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    private List<GolonganJabatan> listGoljab;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new ReportEmpDepartmentJabatanParameter();
        try {
            listDepartment = departmentService.getAllData();
            listGoljab = golonganJabatanService.getAllWithDetail();
            dualListModel.setSource(listGoljab);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        empDataService = null;
        searchParameter = null;
        lazyDataModel = null;
        listDepartment = null;
        dualListModel = null;
        listGoljab = null;
        departmentService = null;
        golonganJabatanService = null;
    }

    public DualListModel<GolonganJabatan> getDualListModel() {
        return dualListModel;
    }

    public void setDualListModel(DualListModel<GolonganJabatan> dualListModel) {
        this.dualListModel = dualListModel;
    }

    public ReportEmpDepartmentJabatanParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(ReportEmpDepartmentJabatanParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<EmpData> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new ReportEmpDepartmentJabatanLazyDataModel(searchParameter, empDataService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<EmpData> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public List<Department> getListDepartment() {
        return listDepartment;
    }

    public void setListDepartment(List<Department> listDepartment) {
        this.listDepartment = listDepartment;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public List<GolonganJabatan> getListGoljab() {
        return listGoljab;
    }

    public void setListGoljab(List<GolonganJabatan> listGoljab) {
        this.listGoljab = listGoljab;
    }

    public GolonganJabatanService getGolonganJabatanService() {
        return golonganJabatanService;
    }

    public void setGolonganJabatanService(GolonganJabatanService golonganJabatanService) {
        this.golonganJabatanService = golonganJabatanService;
    }

    public void doSearch() {
        if (!dualListModel.getTarget().isEmpty()) {
            List<Long> ids = new ArrayList<>();
            List<GolonganJabatan> golJabs = new ArrayList<>();
            golJabs.addAll(dualListModel.getTarget());
            for(GolonganJabatan golonganJabatan : golJabs){
                ids.add(golonganJabatan.getId());
            }
            searchParameter.setGolonganJabatanId(ids);
        }else{
            searchParameter.setGolonganJabatanId(null);
        }
        lazyDataModel = null;
    }
}
