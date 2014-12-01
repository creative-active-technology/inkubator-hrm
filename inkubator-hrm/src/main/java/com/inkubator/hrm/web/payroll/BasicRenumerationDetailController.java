/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.common.util.AESUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.BenefitGroupRate;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.BenefitGroupRateService;
import com.inkubator.hrm.web.model.BenefitGroupRenumerationModel;
import com.inkubator.hrm.web.model.SubsidiModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "basicRenumerationDetailController")
@ViewScoped
public class BasicRenumerationDetailController extends BaseController {

    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private EmpData selectedEmpData;
    private String id;
    private String total;

    //personal discipline
    @ManagedProperty(value = "#{benefitGroupRateService}")
    private BenefitGroupRateService benefitGroupRateService;
    private List<BenefitGroupRate> listBenefitGroupRate;

    private List<SubsidiModel> listSubsidi;
    private List<BenefitGroupRenumerationModel> listBenefit;
    private String subsidiTotal;
    DecimalFormat f = new DecimalFormat("###,###.###");

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            DecimalFormatSymbols custom = new DecimalFormatSymbols();
            custom.setDecimalSeparator(',');
            custom.setGroupingSeparator('.');            
            f.setDecimalFormatSymbols(custom);
            String empId = FacesUtil.getRequestParameter("execution");
            selectedEmpData = empDataService.getByEmpIdWithDetail(Long.parseLong(empId.substring(1)));
            listBenefitGroupRate = benefitGroupRateService.getByGolonganJabatan(selectedEmpData.getGolonganJabatan().getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EmpData getSelectedEmpData() {
        return selectedEmpData;
    }

    public void setSelectedEmpData(EmpData selectedEmpData) {
        this.selectedEmpData = selectedEmpData;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public String doBack() {
        return "/protected/payroll/basic_renumeration_view.htm?faces-redirect=true";
    }

    public void doSelectEmpCardName() {
        try {
            selectedEmpData = empDataService.getByEmpIdWithDetail(selectedEmpData.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public BenefitGroupRateService getBenefitGroupRateService() {
        return benefitGroupRateService;
    }

    public void setBenefitGroupRateService(BenefitGroupRateService benefitGroupRateService) {
        this.benefitGroupRateService = benefitGroupRateService;
    }

    public List<BenefitGroupRate> getListBenefitGroupRate() {
        return listBenefitGroupRate;
    }

    public void setListBenefitGroupRate(List<BenefitGroupRate> listBenefitGroupRate) {
        this.listBenefitGroupRate = listBenefitGroupRate;
    }

    @PreDestroy
    public void cleanAndExit() {
        empDataService = null;
        selectedEmpData = null;
        id = null;
        benefitGroupRateService = null;
        listBenefitGroupRate = null;
        total = null;
        listBenefit = null;
        listSubsidi = null;
        subsidiTotal = null;
    }

    public String getTotal() {
        Integer totalDouble = 0;
        for (BenefitGroupRate b : listBenefitGroupRate) {
            totalDouble += b.getNominal().intValue();
        }
        String dataDecripted = selectedEmpData.getBasicSalaryDecrypted();
        totalDouble += Integer.parseInt(dataDecripted);
        total = String.valueOf(totalDouble);
        return String.valueOf(f.format(Integer.parseInt(total)));
    }

    public String getSubsidiTotal() {
        Integer subTotal = 0;
        Integer ppip = selectedEmpData.getPpip() != null ? Integer.parseInt(selectedEmpData.getPpip()) : 0;
        Integer ppmp = selectedEmpData.getPpmp() != null ? Integer.parseInt(selectedEmpData.getPpmp()) : 0;

        subTotal = ppip + ppmp;
        
        return String.valueOf(f.format(subTotal));
    }

    public List<SubsidiModel> getListSubsidi() {
        listSubsidi = new ArrayList<>();

        Integer ppip = selectedEmpData.getPpip() != null ? Integer.parseInt(selectedEmpData.getPpip()) : 0;
        Integer ppmp = selectedEmpData.getPpmp() != null ? Integer.parseInt(selectedEmpData.getPpmp()) : 0;

        SubsidiModel ppipObj = new SubsidiModel();
        ppipObj.setSubsidiName("Premi PPIP");
        ppipObj.setNominal(String.valueOf(f.format(ppip)));

        SubsidiModel ppmpObj = new SubsidiModel();
        ppmpObj.setSubsidiName("Premi PPMP");
        ppmpObj.setNominal(String.valueOf(f.format(ppmp)));

        listSubsidi.add(ppipObj);
        listSubsidi.add(ppmpObj);

        return listSubsidi;
    }

    public List<BenefitGroupRenumerationModel> getListBenefit() {
        listBenefit = new ArrayList<>();
        BenefitGroupRenumerationModel bgrm = new BenefitGroupRenumerationModel();
        bgrm.setBenefitGroup("Basic Salary");
        bgrm.setNominal(selectedEmpData.getBasicSalary());
        listBenefit.add(bgrm);

        for (BenefitGroupRate benefitGroupRate : listBenefitGroupRate) {
            BenefitGroupRenumerationModel benefitGroupRenumerationModel = new BenefitGroupRenumerationModel();
            benefitGroupRenumerationModel.setBenefitGroup(benefitGroupRate.getBenefitGroup().getName());
            benefitGroupRenumerationModel.setNominal(String.valueOf(f.format(benefitGroupRate.getNominal())));

            listBenefit.add(benefitGroupRenumerationModel);
        }
        return listBenefit;
    }

}
