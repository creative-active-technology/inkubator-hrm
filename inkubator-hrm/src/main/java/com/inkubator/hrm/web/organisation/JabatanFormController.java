/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.CostCenter;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.service.CostCenterService;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.UnitKerjaService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.JabatanModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "jabatanFormController")
@ViewScoped
public class JabatanFormController extends BaseController {

    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    @ManagedProperty(value = "#{unitKerjaService}")
    private UnitKerjaService unitKerjaService;
    @ManagedProperty(value = "#{costCenterService}")
    private CostCenterService costCenterService;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
    @ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
    private Boolean isDisable;
    private Boolean isEdit;
    private JabatanModel jabatanModel;
    private Map<String, Long> untiKerjas = new TreeMap<>();
    private Map<String, Long> golJabatans = new TreeMap<>();
    private Map<String, Long> departments = new TreeMap<>();
    private Map<String, Long> posBiayas = new TreeMap<>();
    private Map<String, Long> jabatanAtasans = new TreeMap<>();

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution");
            if (id != null) {
                Jabatan jabatan = jabatanService.getEntiyByPK(Long.parseLong(id.substring(1)));
                isEdit = Boolean.TRUE;
                jabatanModel = getJabatanModelFromEntity(jabatan);
                doChangeLevel();
                System.out.println(" hahahah");
            } else {
                jabatanModel = new JabatanModel();
                isEdit = Boolean.FALSE;
                jabatanModel.setLevelJabatan(1);
//                List<Jabatan> listJabatans = jabatanService.getAllData();
//                for (Jabatan jabatan : listJabatans) {
//                    jabatanAtasans.put(jabatan.getName(), jabatan.getId());
//                }
                doChangeLevel();
            }

//            if (jabatanModel.getLevelJabatan() == 1) {
//                isDisable = Boolean.TRUE;
//            } else {
//                isDisable = Boolean.FALSE;
//            }
            List<Department> listDepartemens = departmentService.getAllData();
            List<CostCenter> listCostCenters = costCenterService.getAllData();
            List<UnitKerja> listUnitKerjas = unitKerjaService.getAllData();
            List<GolonganJabatan> listGolonganJabatans = golonganJabatanService.getAllWithDetail();

            for (GolonganJabatan golonganJabatan : listGolonganJabatans) {
                golJabatans.put(golonganJabatan.getCode()+" - "+golonganJabatan.getPangkat().getPangkatName(), golonganJabatan.getId());
            }
            for (UnitKerja unitKerja : listUnitKerjas) {
                untiKerjas.put(unitKerja.getName(), unitKerja.getId());
            }

            for (CostCenter costCenter : listCostCenters) {
                posBiayas.put(costCenter.getName(), costCenter.getId());
            }
            for (Department department : listDepartemens) {
                departments.put(department.getDepartmentName(), department.getId());
            }
            MapUtil.sortByValue(jabatanAtasans);
            MapUtil.sortByValue(golJabatans);
            MapUtil.sortByValue(untiKerjas);
            MapUtil.sortByValue(posBiayas);
            MapUtil.sortByValue(departments);

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }

    public String doBack() {
        return "/protected/organisation/job_title_view.htm?faces-redirect=true";
    }

    public String doSave() {
        Jabatan jabatan = getEntityFromView(jabatanModel);
        try {
            if (isEdit) {

                jabatanService.update(jabatan);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            } else {
                jabatanService.save(jabatan);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            return "/protected/organisation/job_title_detil.htm?faces-redirect=true&execution=e" + jabatan.getId();
        } catch (BussinessException ex) { //data already exist(duplicate)
            LOGGER.error("Error", ex);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    public void doReset() {
        isDisable = Boolean.TRUE;
        jabatanModel.setLevelJabatan(1);

    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    private Jabatan getEntityFromView(JabatanModel jabatanModel) {
        Jabatan jabatan = new Jabatan();
        if (jabatanModel.getId() != null) {
            jabatan.setId(jabatanModel.getId());
        }
        jabatan.setCode(jabatanModel.getKodeJabatan());
        jabatan.setCostCenter(new CostCenter(jabatanModel.getPosBiayaId()));
        jabatan.setDepartment(new Department(jabatanModel.getDepartementId()));
        jabatan.setGolonganJabatan(new GolonganJabatan(jabatanModel.getGolonganJabatanId()));
        if (jabatanModel.getJabatanAtasanId() != null) {
            jabatan.setJabatan(new Jabatan(jabatanModel.getJabatanAtasanId()));
        }
        jabatan.setLevelJabatan(jabatanModel.getLevelJabatan());
        jabatan.setName(jabatanModel.getNamaJabatan());
        jabatan.setTujuanJabatan(jabatanModel.getTujuanJabatan());
        jabatan.setUnitKerja(new UnitKerja(jabatanModel.getUnitKerjaId()));
        return jabatan;
    }

    public JabatanModel getJabatanModelFromEntity(Jabatan jabatan) {
        JabatanModel jbm = new JabatanModel();
        jbm.setDepartementId(jabatan.getDepartment().getId());
        jbm.setGolonganJabatanId(jabatan.getGolonganJabatan().getId());
        jbm.setId(jabatan.getId());
        if (jabatan.getJabatan() != null) {
            jbm.setJabatanAtasanId(jabatan.getJabatan().getId());
        }
        jbm.setKodeJabatan(jabatan.getCode());
        jbm.setLevelJabatan(jabatan.getLevelJabatan());
        jbm.setNamaJabatan(jabatan.getName());
        jbm.setPosBiayaId(jabatan.getCostCenter().getId());
        jbm.setTujuanJabatan(jabatan.getTujuanJabatan());
        jbm.setUnitKerjaId(jabatan.getUnitKerja().getId());
        return jbm;
    }

    @PreDestroy
    public void cleanAndExit() {

    }

    public JabatanModel getJabatanModel() {
        return jabatanModel;
    }

    public void setJabatanModel(JabatanModel jabatanModel) {
        this.jabatanModel = jabatanModel;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public void setUnitKerjaService(UnitKerjaService unitKerjaService) {
        this.unitKerjaService = unitKerjaService;
    }

    public Map<String, Long> getUntiKerjas() {
        return untiKerjas;
    }

    public void setUntiKerjas(Map<String, Long> untiKerjas) {
        this.untiKerjas = untiKerjas;
    }

    public Map<String, Long> getGolJabatans() {
        return golJabatans;
    }

    public void setGolJabatans(Map<String, Long> golJabatans) {
        this.golJabatans = golJabatans;
    }

    public Map<String, Long> getDepartments() {
        System.out.println("hahah");
        return departments;
    }

    public void setDepartments(Map<String, Long> departments) {
        this.departments = departments;
    }

    public Map<String, Long> getPosBiayas() {
        return posBiayas;
    }

    public void setPosBiayas(Map<String, Long> posBiayas) {
        this.posBiayas = posBiayas;
    }

    public void setCostCenterService(CostCenterService costCenterService) {
        this.costCenterService = costCenterService;
    }

    public void setGolonganJabatanService(GolonganJabatanService golonganJabatanService) {
        this.golonganJabatanService = golonganJabatanService;
    }

    public Map<String, Long> getJabatanAtasans() {
        return jabatanAtasans;
    }

    public void setJabatanAtasans(Map<String, Long> jabatanAtasans) {
        this.jabatanAtasans = jabatanAtasans;
    }

    public void setJabatanService(JabatanService jabatanService) {
        this.jabatanService = jabatanService;
    }

    public void doChangeLevel() {
        try {
            if (jabatanModel.getLevelJabatan() > 1) {
                isDisable = Boolean.FALSE;
            } else {
                isDisable = Boolean.TRUE;
            }
            List<Jabatan> listJabatans = jabatanService.getJabatansByLevel(jabatanModel.getLevelJabatan() - 1);
            jabatanAtasans = new TreeMap<>();
            for (Jabatan jabatan : listJabatans) {

                jabatanAtasans.put(jabatan.getName(), jabatan.getId());
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }

    public Boolean getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(Boolean isDisable) {
        this.isDisable = isDisable;
    }

}
