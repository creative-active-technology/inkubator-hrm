package com.inkubator.hrm.web.report;

import com.inkubator.hrm.HRMConstant;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.LogWtAttendanceRealization;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.LogWtAttendanceRealizationService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.ReportAttendanceLazyDataModel;
import com.inkubator.hrm.web.model.LogWtAttendanceRealizationModel;
import com.inkubator.webcore.controller.BaseController;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@ManagedBean(name = "reportAttendanceViewController")
@ViewScoped
public class ReportAttendanceViewController extends BaseController {

    @ManagedProperty(value = "#{logWtAttendanceRealizationService}")
    private LogWtAttendanceRealizationService logWtAttendanceRealizationService;
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
    private LogWtAttendanceRealizationModel modelSearchParameter;
    private LazyDataModel<LogWtAttendanceRealization> lazyDataModel;
    private List<Department> departmentSource = new ArrayList<>();
    private DualListModel<Department> departmentDualListModel = new DualListModel<>();
    private List<GolonganJabatan> golJabSource = new ArrayList<>();
    private DualListModel<GolonganJabatan> golJabDualListModel = new DualListModel<>();
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    private Map<Integer, Long> wtPeriodeCombo = new TreeMap<Integer, Long>();
    private List<WtPeriode> data = new ArrayList<>();

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        modelSearchParameter = new LogWtAttendanceRealizationModel();

        //get source
        try {
            List<WtPeriode> dataToShow = wtPeriodeService.getAllWithStatusAbsen(HRMConstant.PERIODE_ABSEN_VOID);
            data = dataToShow;
            dataToShow.stream().forEach((wtPeriode) -> {
          
                wtPeriodeCombo.put(wtPeriode.getBulan(), wtPeriode.getId());
            });
            departmentSource = departmentService.getAllData();
            golJabSource = golonganJabatanService.getAllData();
            departmentDualListModel.setSource(departmentSource);
            golJabDualListModel.setSource(golJabSource);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @PreDestroy
    public void cleanAndExit() {
        logWtAttendanceRealizationService = null;
        modelSearchParameter = null;
        lazyDataModel = null;
        departmentSource = null;
        golJabSource = null;
        departmentDualListModel = null;
        golJabDualListModel = null;
        departmentService = null;
        golonganJabatanService = null;
    }

    public void doSearchEmployee() {
//        modelSearchParameter=new LogWtAttendanceRealizationModel();
        modelSearchParameter.setListSelectedDepartment(departmentDualListModel.getTarget());
        modelSearchParameter.setListSelectedGolJab(golJabDualListModel.getTarget());
        List<String> listDeptName = new ArrayList<String>();
        List<String> listGolJabName = new ArrayList<String>();
        for (Department departmentName : modelSearchParameter.getListSelectedDepartment()) {
            listDeptName.add(departmentName.getDepartmentName());
        }
        for (GolonganJabatan golJab : modelSearchParameter.getListSelectedGolJab()) {
            listGolJabName.add(golJab.getCode());
        }
        modelSearchParameter.setListDeptName(listDeptName);
        modelSearchParameter.setListGolJabName(listGolJabName);

        lazyDataModel = null;
    }

    public LogWtAttendanceRealizationService getLogWtAttendanceRealizationService() {
        return logWtAttendanceRealizationService;
    }

    public void setLogWtAttendanceRealizationService(LogWtAttendanceRealizationService logWtAttendanceRealizationService) {
        this.logWtAttendanceRealizationService = logWtAttendanceRealizationService;
    }

    public LogWtAttendanceRealizationModel getModelSearchParameter() {
        return modelSearchParameter;
    }

    public void setModelSearchParameter(LogWtAttendanceRealizationModel modelSearchParameter) {
        this.modelSearchParameter = modelSearchParameter;
    }

    public LazyDataModel<LogWtAttendanceRealization> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new ReportAttendanceLazyDataModel(modelSearchParameter, logWtAttendanceRealizationService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<LogWtAttendanceRealization> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public GolonganJabatanService getGolonganJabatanService() {
        return golonganJabatanService;
    }

    public void setGolonganJabatanService(
            GolonganJabatanService golonganJabatanService) {
        this.golonganJabatanService = golonganJabatanService;
    }

    public List<Department> getDepartmentSource() {
        return departmentSource;
    }

    public void setDepartmentSource(List<Department> departmentSource) {
        this.departmentSource = departmentSource;
    }

    public DualListModel<Department> getDepartmentDualListModel() {
        return departmentDualListModel;
    }

    public void setDepartmentDualListModel(
            DualListModel<Department> departmentDualListModel) {
        this.departmentDualListModel = departmentDualListModel;
    }

    public List<GolonganJabatan> getGolJabSource() {
        return golJabSource;
    }

    public void setGolJabSource(List<GolonganJabatan> golJabSource) {
        this.golJabSource = golJabSource;
    }

    public DualListModel<GolonganJabatan> getGolJabDualListModel() {
        return golJabDualListModel;
    }

    public void setGolJabDualListModel(
            DualListModel<GolonganJabatan> golJabDualListModel) {
        this.golJabDualListModel = golJabDualListModel;
    }

    public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
        this.wtPeriodeService = wtPeriodeService;
    }

    public Map<Integer, Long> getWtPeriodeCombo() {
        return wtPeriodeCombo;
    }

    public void setWtPeriodeCombo(Map<Integer, Long> wtPeriodeCombo) {
        this.wtPeriodeCombo = wtPeriodeCombo;
    }

    public List<WtPeriode> getData() {
        return data;
    }

    public void setData(List<WtPeriode> data) {
        this.data = data;
    }

}
