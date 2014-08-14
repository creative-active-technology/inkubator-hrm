/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.PaySalaryGrade;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.PaySalaryGradeService;
import com.inkubator.hrm.web.model.EmpDataModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "empDataFormController")
@ViewScoped
public class EmpDataFormController extends BaseController {

    private EmpDataModel empDataModel;
    private Map<String, Long> mapDepartements = new HashMap<String, Long>();
    private Map<String, Long> mapJabatans = new HashMap<String, Long>();
    private Map<String, Long> mapStatusKaryawan = new HashMap<String, Long>();
    private Map<Integer, Long> mapPaySalary = new HashMap<Integer, Long>();

//    private HrmUserSearchParameter hrmUserSearchParameter;
//    private LazyDataModel<HrmUser> lazyDataHrmUser;
//    private HrmUser selectedHrmUser;
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    @ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
    @ManagedProperty(value = "#{employeeTypeService}")
    private EmployeeTypeService employeeTypeService;
    @ManagedProperty(value = "#{paySalaryGradeService}")
    private PaySalaryGradeService paySalaryGradeService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
//
//    public void setHrmUserService(HrmUserService hrmUserService) {
//        this.hrmUserService = hrmUserService;
//    }
//
    private Boolean isEdit;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            empDataModel = new EmpDataModel();
            List<Department> departements = departmentService.getAllData();
            for (Department department : departements) {
                mapDepartements.put(department.getDepartmentName(), department.getId());

            }

            List<Jabatan> jabatanas = jabatanService.getAllData();
            for (Jabatan jabatan : jabatanas) {
                mapJabatans.put(jabatan.getName(), jabatan.getId());
            }
            List<EmployeeType> empTypeDatas = employeeTypeService.getAllData();
            for (EmployeeType employeeType : empTypeDatas) {
                mapStatusKaryawan.put(employeeType.getName(), employeeType.getId());
            }

            List<PaySalaryGrade> paysSalarys = paySalaryGradeService.getAllData();
            for (PaySalaryGrade paySalaryGrade : paysSalarys) {
                mapPaySalary.put(paySalaryGrade.getGradeSalary(), paySalaryGrade.getId());
            }

            isEdit = Boolean.FALSE;
        } catch (Exception ex) {
            LOGGER.error("error", ex);
        }

    }

//
//    public HrmUserSearchParameter getHrmUserSearchParameter() {
//        return hrmUserSearchParameter;
//    }
//
//    public void setHrmUserSearchParameter(HrmUserSearchParameter hrmUserSearchParameter) {
//        this.hrmUserSearchParameter = hrmUserSearchParameter;
//    }
//
//    public LazyDataModel<HrmUser> getLazyDataHrmUser() {
//        if (lazyDataHrmUser == null) {
//            lazyDataHrmUser = new HrmUserLazyDataModel(hrmUserSearchParameter, hrmUserService);
//        }
//        return lazyDataHrmUser;
//    }
//
//    public void setLazyDataHrmUser(LazyDataModel<HrmUser> lazyDataHrmUser) {
//        this.lazyDataHrmUser = lazyDataHrmUser;
//    }
//
//    public HrmUser getSelectedHrmUser() {
//        return selectedHrmUser;
//    }
//
//    public void setSelectedHrmUser(HrmUser selectedHrmUser) {
//        this.selectedHrmUser = selectedHrmUser;
//    }
//
//    public void doSearch() {
//        lazyDataHrmUser = null;
//    }
//
    public String doAdd() {
        return "/protected/employee/employee_palcement_form.htm?faces-redirect=true";
    }

//
//    public String doDetail() {
//        return "/protected/account/user_detail.htm?faces-redirect=true&execution=e" + selectedHrmUser.getId();
//    }
//
//    public String doEdit() {
//        return "/protected/account/user_form.htm?faces-redirect=true&execution=e" + selectedHrmUser.getId();
//    }
//
//    public void onDelete() {
//        try {
//            selectedHrmUser = this.hrmUserService.getEntiyByPK(selectedHrmUser.getId());
//        } catch (Exception ex) {
//            LOGGER.error("Errpr", ex);
//
//        }
//    }
//
//    public void doDelete() {
//        try {
//            this.hrmUserService.delete(selectedHrmUser);
//            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
//                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//
//        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
//            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
//                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//            LOGGER.error("Error", ex);
//        } catch (Exception ex) {
//            LOGGER.error("Error", ex);
//        }
//    }
//
//    @PreDestroy
//    public void cleanAndExit() {
//        hrmUserSearchParameter = null;
//        lazyDataHrmUser = null;
//        selectedHrmUser = null;
//        hrmUserService = null;
//    }
    public EmpDataModel getEmpDataModel() {
        return empDataModel;
    }

    public void setEmpDataModel(EmpDataModel empDataModel) {
        this.empDataModel = empDataModel;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public Map<String, Long> getMapDepartements() {
        return mapDepartements;
    }

    public void setMapDepartements(Map<String, Long> mapDepartements) {
        this.mapDepartements = mapDepartements;
    }

    public Map<String, Long> getMapJabatans() {
        return mapJabatans;
    }

    public void setMapJabatans(Map<String, Long> mapJabatans) {
        this.mapJabatans = mapJabatans;
    }

    public void setJabatanService(JabatanService jabatanService) {
        this.jabatanService = jabatanService;
    }

    public void setEmployeeTypeService(EmployeeTypeService employeeTypeService) {
        this.employeeTypeService = employeeTypeService;
    }

    public Map<String, Long> getMapStatusKaryawan() {
        return mapStatusKaryawan;
    }

    public void setMapStatusKaryawan(Map<String, Long> mapStatusKaryawan) {
        this.mapStatusKaryawan = mapStatusKaryawan;
    }

    public void setPaySalaryGradeService(PaySalaryGradeService paySalaryGradeService) {
        this.paySalaryGradeService = paySalaryGradeService;
    }

    public Map<Integer, Long> getMapPaySalary() {
        return mapPaySalary;
    }

    public void setMapPaySalary(Map<Integer, Long> mapPaySalary) {
        this.mapPaySalary = mapPaySalary;
    }

    public void doChangeDepartement() {
        try {
            List<Jabatan> jabatanas = jabatanService.getByDepartementId(empDataModel.getDepartementId());
            mapJabatans = new HashMap();
            for (Jabatan jabatan : jabatanas) {
                mapJabatans.put(jabatan.getName(), jabatan.getId());
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }

    public String doSave() {
        EmpData empData = getEntityFromViewModel(empDataModel);
        if (isEdit) {

        } else {
            try {
                empDataService.save(empData);
                 return "/protected/employee/employee_placement_detail.htm?faces-redirect=true&execution=e" + empData.getId();
            } catch (BussinessException ex) {
//                LOGGER.error("Error", ex);
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }

        return null;
    }

    public String doBack() {
        return "/protected/employee/employee_palcement_view.htm?faces-redirect=true";
    }

    public void doSearch() {
        System.out.println("sdfsdhfhdsfhd");
        Map<String, Object> options = new HashMap<>();
        options.put("modal", false);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 700);
        options.put("contentHeight", 350);
//        Map<String, List<String>> dataToSend = new HashMap<>();
//        List<String> dataIsi = new ArrayList<>();
////        dataIsi.add("i" + String.valueOf(selectedJabatan.getId()));
//        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("bio_data_search", options, null);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        BioData bioData = (BioData) event.getObject();
        empDataModel.setBioDataId(bioData.getId());
        empDataModel.setBioDataName(bioData.getFirstName() + " " + bioData.getLastName());
        empDataModel.setBirthDate(bioData.getDateOfBirth());

    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    private EmpData getEntityFromViewModel(EmpDataModel empDataModel) {
        EmpData empData = new EmpData();
        if (empDataModel.getId() != null) {
            empData.setId(empDataModel.getId());
        }
        empData.setBasicSalary(empDataModel.getBasicSalary());
        empData.setBioData(new BioData(empDataModel.getBioDataId()));
        empData.setEmployeeType(new EmployeeType(empDataModel.getEmployeeTypeId()));
        empData.setHeatlyPremi(Boolean.TRUE);
        empData.setInsentifStatus(Boolean.TRUE);
        empData.setIsFinger(Boolean.TRUE);
        empData.setJabatanByJabatanGajiId(new Jabatan(empDataModel.getJabatanByJabatanId()));
        empData.setJabatanByJabatanId(new Jabatan(empDataModel.getJabatanByJabatanId()));
        empData.setJoinDate(empDataModel.getJoinDate());
        empData.setNik(empDataModel.getNik());
        empData.setPaySalaryGrade(new PaySalaryGrade(empDataModel.getPaySalaryGradeId()));
        empData.setPtkpStatus(Boolean.FALSE);
        empData.setPtkpNumber(0);
//        empData.setWtGroupWorking(new WtGroupWorking());
        return empData;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

}
