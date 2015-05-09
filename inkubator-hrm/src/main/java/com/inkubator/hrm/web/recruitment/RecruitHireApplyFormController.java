/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.common.util.DateFormatter;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.hrm.entity.RecruitHireApplyDetail;
import com.inkubator.hrm.entity.RecruitMppPeriod;
import com.inkubator.hrm.service.CurrencyService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.OrgTypeOfSpecListService;
import com.inkubator.hrm.service.RecruitHireApplyService;
import com.inkubator.hrm.service.RecruitMppApplyDetailService;
import com.inkubator.hrm.service.RecruitMppPeriodService;
import com.inkubator.hrm.web.model.RecruitHireApplyModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;

/**
 *
 * @author deni.fahri
 */
@ManagedBean(name = "recruitHireApplyFormController")
@ViewScoped
public class RecruitHireApplyFormController extends BaseController {

    @ManagedProperty(value = "#{recruitMppPeriodService}")
    private RecruitMppPeriodService recruitMppPeriodService;
    @ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
    @ManagedProperty(value = "#{employeeTypeService}")
    private EmployeeTypeService employeeTypeService;
    @ManagedProperty(value = "#{currencyService}")
    private CurrencyService currencyService;
    @ManagedProperty(value = "#{orgTypeOfSpecListService}")
    private OrgTypeOfSpecListService orgTypeOfSpecListService;
    @ManagedProperty(value = "#{recruitHireApplyService}")
    private RecruitHireApplyService recruitHireApplyService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{recruitMppApplyDetailService}")
    private RecruitMppApplyDetailService recruitMppApplyDetailService;
    
    private Map<String, Long> mapPeriode = new TreeMap<>();
    private Map<String, Long> mapJabatan = new TreeMap<>();
    private Map<String, Long> mapEmployeeType = new TreeMap<>();
    private Map<String, Long> mapCurrency = new TreeMap<>();
    private List<DualListModel> dataForRenders = new ArrayList<>();
    private RecruitHireApplyModel model;
    private List<String> name = new ArrayList<>();
    private Boolean isEdit = Boolean.FALSE;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            model = new RecruitHireApplyModel();
            List<RecruitMppPeriod> dataToshow = recruitMppPeriodService.getAllData();
            for (RecruitMppPeriod period : dataToshow) {
                System.out.println(" nilia " + period.getPeriodeStart());
                String periodeStart = DateFormatter.getDateAsStringActiveLocale(period.getPeriodeStart(), "dd MMMM yyyy", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
                String periodeEnd = DateFormatter.getDateAsStringActiveLocale(period.getPeriodeEnd(), "dd MMMM yyyy", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
                mapPeriode.put(periodeStart + " - " + periodeEnd + "  |  " + period.getName(), period.getId());
            }
            List<Jabatan> jabatanToShow = jabatanService.getAllData();
            for (Jabatan jabatan : jabatanToShow) {
                mapJabatan.put(jabatan.getName(), jabatan.getId());
            }

            List<EmployeeType> typeToShow = employeeTypeService.getAllData();
            for (EmployeeType employeeType : typeToShow) {
                mapEmployeeType.put(employeeType.getName(), employeeType.getId());
            }
            List<Currency> curencLiss = currencyService.getAllData();
            for (Currency currency : curencLiss) {
                mapCurrency.put(currency.getCode() + " - " + currency.getName(), currency.getId());
            }
            dataForRenders = orgTypeOfSpecListService.getAllBySpectJabatan();

            name = orgTypeOfSpecListService.getOrgTypeSpecName();
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    public String doSave() {
        String redirect = null;
        if (isEdit) {
            System.out.println(" Edit mode");
        } else {
            try {
                System.out.println(" Save mode");
                List<OrgTypeOfSpecList> listSelectedSpec = new ArrayList<>();
                
                for (DualListModel<OrgTypeOfSpecList> dual : dataForRenders) {
                    listSelectedSpec.addAll(dual.getTarget());
                }
                
                Set<RecruitHireApplyDetail> setRecruitHireApplyDetails = new HashSet<>();                
                RecruitHireApply recruitHireApply = getEntityFromModel(model);
                recruitHireApply.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
                
                for (OrgTypeOfSpecList typeSpecList : listSelectedSpec) {
                    RecruitHireApplyDetail recruitHireApplyDetail = new RecruitHireApplyDetail();
                    recruitHireApplyDetail.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
                    recruitHireApplyDetail.setOrgTypeOfSpecList(typeSpecList);
                    recruitHireApplyDetail.setRecruitHireApply(recruitHireApply);
                    setRecruitHireApplyDetails.add(recruitHireApplyDetail);
                }
                
                recruitHireApply.setRecruitHireApplyDetails(setRecruitHireApplyDetails);
                recruitHireApplyService.saveRecruitHireWithApproval(recruitHireApply);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());               
                redirect = "/protected/recruitment/recruitment_req_history_view.htm?faces-redirect=true";
            } catch (BussinessException ex) {
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } catch (Exception ex) {
                LOGGER.error(ex, ex);
            }
        }
        return redirect;
    }
    
    public void onChangeJabatan() {
        try {
            Long totalActual = empDataService.getTotalKaryawanByJabatanId(model.getJabatanId());
            model.setActual(totalActual);
            if(model.getRecruitMppId() != null){
                Long totalMpp = recruitMppApplyDetailService.getRecruitPlanByJabatanIdAndMppPeriodId(model.getJabatanId(), model.getRecruitMppId());
                model.setMpp(totalMpp);
            }
        } catch (Exception ex) {
            Logger.getLogger(RecruitHireApplyFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private RecruitHireApply getEntityFromModel(RecruitHireApplyModel model) throws Exception {
        RecruitHireApply recruitHireApply = new RecruitHireApply();
        
        if (model.getRecruitHireApplyId() != null) {
            recruitHireApply.setId(model.getRecruitHireApplyId());
        }
        
        recruitHireApply.setAgeMax(model.getAgeMax());
        recruitHireApply.setAgeMin(model.getAgeMin());
        recruitHireApply.setCandidateCountRequest(model.getCandidateCountRequest());
        recruitHireApply.setCurrency(new Currency(model.getCurrencyId()));
        recruitHireApply.setEfectiveDate(model.getEfectiveDate());
        recruitHireApply.setEmployeeType(new EmployeeType(model.getEmpStatus()));
        recruitHireApply.setGender(model.getGender());
        recruitHireApply.setGpaMax(model.getGpaMax());
        recruitHireApply.setGpaMin(model.getGpaMin());
        recruitHireApply.setJabatan(new Jabatan(model.getJabatanId()));
        recruitHireApply.setMaritalStatus(model.getMaritalStatus());
        recruitHireApply.setProposeDate(model.getProposeDate());
        recruitHireApply.setReason(model.getReason());
        recruitHireApply.setReqHireCode(model.getReqHireCode());
        recruitHireApply.setSalaryMax(model.getSalaryMax());
        recruitHireApply.setSalaryMin(model.getSalaryMin());
        recruitHireApply.setYearExperience(model.getYearExperience());
        
        return recruitHireApply;
    }

    public void setRecruitMppPeriodService(RecruitMppPeriodService recruitMppPeriodService) {
        this.recruitMppPeriodService = recruitMppPeriodService;
    }

    public Map<String, Long> getMapPeriode() {
        return mapPeriode;
    }

    public void setMapPeriode(Map<String, Long> mapPeriode) {
        this.mapPeriode = mapPeriode;
    }

    public void setJabatanService(JabatanService jabatanService) {
        this.jabatanService = jabatanService;
    }

    public Map<String, Long> getMapJabatan() {
        return mapJabatan;
    }

    public void setMapJabatan(Map<String, Long> mapJabatan) {
        this.mapJabatan = mapJabatan;
    }

    public void setEmployeeTypeService(EmployeeTypeService employeeTypeService) {
        this.employeeTypeService = employeeTypeService;
    }

    public Map<String, Long> getMapEmployeeType() {
        return mapEmployeeType;
    }

    public void setMapEmployeeType(Map<String, Long> mapEmployeeType) {
        this.mapEmployeeType = mapEmployeeType;
    }

    public void setCurrencyService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public Map<String, Long> getMapCurrency() {
        return mapCurrency;
    }

    public void setMapCurrency(Map<String, Long> mapCurrency) {
        this.mapCurrency = mapCurrency;
    }

    public List<DualListModel> getDataForRenders() {
        return dataForRenders;
    }

    public void setDataForRenders(List<DualListModel> dataForRenders) {
        this.dataForRenders = dataForRenders;
    }

    public void setOrgTypeOfSpecListService(OrgTypeOfSpecListService orgTypeOfSpecListService) {
        this.orgTypeOfSpecListService = orgTypeOfSpecListService;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public RecruitHireApplyModel getModel() {
        return model;
    }

    public void setModel(RecruitHireApplyModel model) {
        this.model = model;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    public void setRecruitHireApplyService(RecruitHireApplyService recruitHireApplyService) {
        this.recruitHireApplyService = recruitHireApplyService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public void setRecruitMppApplyDetailService(RecruitMppApplyDetailService recruitMppApplyDetailService) {
        this.recruitMppApplyDetailService = recruitMppApplyDetailService;
    }
    
    

}
