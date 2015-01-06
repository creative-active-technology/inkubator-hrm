/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.Religion;
import com.inkubator.hrm.entity.UnregSalary;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.ReligionService;
import com.inkubator.hrm.service.UnregSalaryService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.model.UnregSalaryModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author deni
 */
@ManagedBean(name = "UnregSalaryEmpSettingFormController")
@ViewScoped
public class UnregSalaryEmpSettingFormController extends BaseController {

    @ManagedProperty(value = "#{unregSalaryService}")
    private UnregSalaryService unregSalaryService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    @ManagedProperty(value = "#{religionService}")
    private ReligionService religionService;
    @ManagedProperty(value = "#{employeeTypeService}")
    private EmployeeTypeService employeeTypeService;
    private DualListModel<GolonganJabatan> goljabDualListModel = new DualListModel<>();
    private List<GolonganJabatan> golonganJabatanSource = new ArrayList<>();
    private DualListModel<Department> departmentDualListModel = new DualListModel<>();
    private List<Department> departmentSource = new ArrayList<>();
    private DualListModel<Religion> religionDualListModel = new DualListModel<>();
    private List<Religion> religionSource = new ArrayList<>();
    private DualListModel<EmployeeType> empTypeDualListModel = new DualListModel<>();
    private List<EmployeeType> empTypeSource = new ArrayList<>();
    private UnregSalaryModel model;
    private String unregSalaryId;
    private Boolean isDataEmpty;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            unregSalaryId = FacesUtil.getRequestParameter("execution");
            model = new UnregSalaryModel();
            if (StringUtils.isNotEmpty(unregSalaryId)) {
                UnregSalary unregSalary = unregSalaryService.getEntiyByPK(Long.parseLong(unregSalaryId.substring(1)));
                if (unregSalaryId != null) {
                    WtPeriode wtPeriode = wtPeriodeService.getEntiyByPK(unregSalary.getWtPeriode().getId());
                    model = getModelFromEntity(unregSalary, wtPeriode);
                }
            }
            //source picklist
            golonganJabatanSource = golonganJabatanService.getAllData();
            departmentSource = departmentService.getAllData();
            religionSource = religionService.getAllData();
            empTypeSource = employeeTypeService.getAllData();

            /* 
             * update or view
             */
            GolonganJabatan golonganJabatan = golonganJabatanService.getEntityByUnregSalaryIdWithDetail(Long.parseLong(unregSalaryId.substring(1)));
            List<GolonganJabatan> golonganJabatans = golonganJabatan.getListGolonganJabatans();
            if (golonganJabatans.isEmpty()) {
                goljabDualListModel.setSource(golonganJabatanSource);
            } else {
                golonganJabatanSource.removeAll(golonganJabatans);
                goljabDualListModel = new DualListModel<>(golonganJabatanSource, golonganJabatans);
            }
            Department department = departmentService.getEntityByUnregSalaryIdWithDetail(Long.parseLong(unregSalaryId.substring(1)));
            List<Department> departements = department.getListDepartments();
            if (departements.isEmpty()) {
                departmentDualListModel.setSource(departmentSource);
            } else {
                departmentSource.removeAll(departements);
                departmentDualListModel = new DualListModel<>(departmentSource, departements);
            }

            Religion religion = religionService.getEntityByUnregSalaryIdWithDetail(Long.parseLong(unregSalaryId.substring(1)));
            List<Religion> religions = religion.getListReligion();
            if (religions.isEmpty()) {
                religionDualListModel.setSource(religionSource);
            } else {
                religionSource.removeAll(religions);
                religionDualListModel = new DualListModel<>(religionSource, religions);
            }
            EmployeeType employeeType = employeeTypeService.getEntityByUnregSalaryIdWithDetail(Long.parseLong(unregSalaryId.substring(1)));
            List<EmployeeType> employeeTypes = employeeType.getListEmployeeTypes();
            if(employeeTypes.isEmpty()){
                empTypeDualListModel.setSource(empTypeSource);
            }else{
                empTypeSource.removeAll(employeeTypes);
                empTypeDualListModel = new DualListModel<>(empTypeSource, employeeTypes);
            }
            System.out.println(unregSalaryId + "hohoho");
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        unregSalaryService = null;
        isDataEmpty = null;
        model = null;
        unregSalaryId = null;
        wtPeriodeService = null;
        golonganJabatanSource = null;
        goljabDualListModel = null;
        golonganJabatanService = null;
        departmentService = null;
        departmentDualListModel = null;
        departmentSource = null;
        religionService = null;
    }

    public UnregSalaryModel getModelFromEntity(UnregSalary entity, WtPeriode wtPeriode) {
        UnregSalaryModel model = new UnregSalaryModel();
        model.setName(entity.getName());
        model.setYear(wtPeriode.getTahun());
        model.setMonth(wtPeriode.getBulan());
        model.setStartPeriodDate(entity.getStartPeriodDate());
        model.setEndPeriodDate(entity.getEndPeriodDate());
        return model;
    }

    public void doSave() throws Exception {
        System.out.println("hohohaosdf");
        List<GolonganJabatan> listGolonganJabatan = goljabDualListModel.getTarget();
        List<Department> listDepartement = departmentDualListModel.getTarget();
        List<Religion> listReligion = religionDualListModel.getTarget();
        List<EmployeeType> listEmployeeType = empTypeDualListModel.getTarget();
        this.unregSalaryService.save(Long.parseLong(unregSalaryId.substring(1)), model.getStartPeriodDate(), model.getEndPeriodDate(), listGolonganJabatan, listDepartement, listReligion, listEmployeeType);
        MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        super.onDialogReturn(event);
    }

    public UnregSalaryService getUnregSalaryService() {
        return unregSalaryService;
    }

    public void setUnregSalaryService(UnregSalaryService unregSalaryService) {
        this.unregSalaryService = unregSalaryService;
    }

    public UnregSalaryModel getModel() {
        return model;
    }

    public void setModel(UnregSalaryModel model) {
        this.model = model;
    }

    public WtPeriodeService getWtPeriodeService() {
        return wtPeriodeService;
    }

    public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
        this.wtPeriodeService = wtPeriodeService;
    }

    public GolonganJabatanService getGolonganJabatanService() {
        return golonganJabatanService;
    }

    public void setGolonganJabatanService(GolonganJabatanService golonganJabatanService) {
        this.golonganJabatanService = golonganJabatanService;
    }

    public DualListModel<GolonganJabatan> getGoljabDualListModel() {
        return goljabDualListModel;
    }

    public void setGoljabDualListModel(DualListModel<GolonganJabatan> goljabDualListModel) {
        this.goljabDualListModel = goljabDualListModel;
    }

    public List<GolonganJabatan> getGolonganJabatanSource() {
        return golonganJabatanSource;
    }

    public void setGolonganJabatanSource(List<GolonganJabatan> golonganJabatanSource) {
        this.golonganJabatanSource = golonganJabatanSource;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public ReligionService getReligionService() {
        return religionService;
    }

    public void setReligionService(ReligionService religionService) {
        this.religionService = religionService;
    }

    public EmployeeTypeService getEmployeeTypeService() {
        return employeeTypeService;
    }

    public void setEmployeeTypeService(EmployeeTypeService employeeTypeService) {
        this.employeeTypeService = employeeTypeService;
    }

    public DualListModel<Department> getDepartmentDualListModel() {
        return departmentDualListModel;
    }

    public void setDepartmentDualListModel(DualListModel<Department> departmentDualListModel) {
        this.departmentDualListModel = departmentDualListModel;
    }

    public List<Department> getDepartmentSource() {
        return departmentSource;
    }

    public void setDepartmentSource(List<Department> departmentSource) {
        this.departmentSource = departmentSource;
    }

    public DualListModel<Religion> getReligionDualListModel() {
        return religionDualListModel;
    }

    public void setReligionDualListModel(DualListModel<Religion> religionDualListModel) {
        this.religionDualListModel = religionDualListModel;
    }

    public List<Religion> getReligionSource() {
        return religionSource;
    }

    public void setReligionSource(List<Religion> religionSource) {
        this.religionSource = religionSource;
    }

    public DualListModel<EmployeeType> getEmpTypeDualListModel() {
        return empTypeDualListModel;
    }

    public void setEmpTypeDualListModel(DualListModel<EmployeeType> empTypeDualListModel) {
        this.empTypeDualListModel = empTypeDualListModel;
    }

    public List<EmployeeType> getEmpTypeSource() {
        return empTypeSource;
    }

    public void setEmpTypeSource(List<EmployeeType> empTypeSource) {
        this.empTypeSource = empTypeSource;
    }

    public String getUnregSalaryId() {
        return unregSalaryId;
    }

    public void setUnregSalaryId(String unregSalaryId) {
        this.unregSalaryId = unregSalaryId;
    }

    public Boolean getIsDataEmpty() {
        return isDataEmpty;
    }

    public void setIsDataEmpty(Boolean isDataEmpty) {
        this.isDataEmpty = isDataEmpty;
    }

}
