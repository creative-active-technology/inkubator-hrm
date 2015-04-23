/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.common.util.DateFormatter;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.OrgTypeOfSpec;
import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.entity.RecruitMppPeriod;
import com.inkubator.hrm.service.CurrencyService;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.OrgTypeOfSpecListService;
import com.inkubator.hrm.service.OrgTypeOfSpecService;
import com.inkubator.hrm.service.RecruitMppPeriodService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
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
    private Map<String, Long> mapPeriode = new TreeMap<>();
    private Map<String, Long> mapJabatan = new TreeMap<>();
    private Map<String, Long> mapEmployeeType = new TreeMap<>();
    private Map<String, Long> mapCurrency = new TreeMap<>();
    private List<DualListModel> dataForRenders = new ArrayList<>();
    private List<String> name = new ArrayList<>();

    @PostConstruct
    @Override
    public void initialization() {
        try {
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

}
