/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.lazymodel.WtPeriodLazyModel;
import com.inkubator.hrm.web.search.WtPeriodeSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "periodeViewController")
@ViewScoped
public class PeriodeViewController extends BaseController {

    private WtPeriodeSearchParameter wtPeriodeSearchParameter;
    private LazyDataModel<WtPeriode> wtPeriodelazyDataModel;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    private WtPeriode selectedWtPeriode;
    private Map<String, Integer> mapMonths = new TreeMap<>();
    private List<Integer> listTahun = new ArrayList<>();
    private Integer selectedTahun;
    private Integer selectedBulan;

    public Integer getSelectedTahun() {
        return selectedTahun;
    }

    public void setSelectedTahun(Integer selectedTahun) {
        this.selectedTahun = selectedTahun;
    }

    public Integer getSelectedBulan() {
        return selectedBulan;
    }

    public void setSelectedBulan(Integer selectedBulan) {
        this.selectedBulan = selectedBulan;
    }

    public Map<String, Integer> getMapMonths() {
        return mapMonths;
    }

    public void setMapMonths(Map<String, Integer> mapMonths) {
        this.mapMonths = mapMonths;
    }

    public WtPeriode getSelectedWtPeriode() {
        return selectedWtPeriode;
    }

    public void setSelectedWtPeriode(WtPeriode selectedWtPeriode) {
        this.selectedWtPeriode = selectedWtPeriode;
    }

    public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
        this.wtPeriodeService = wtPeriodeService;
    }

    public WtPeriodeSearchParameter getWtPeriodeSearchParameter() {
        return wtPeriodeSearchParameter;
    }

    public void setWtPeriodeSearchParameter(WtPeriodeSearchParameter wtPeriodeSearchParameter) {
        this.wtPeriodeSearchParameter = wtPeriodeSearchParameter;
    }

    public LazyDataModel<WtPeriode> getWtPeriodelazyDataModel() {
        if (wtPeriodelazyDataModel == null) {
            wtPeriodelazyDataModel = new WtPeriodLazyModel(wtPeriodeSearchParameter, wtPeriodeService);
        }
        return wtPeriodelazyDataModel;
    }

    public void setWtPeriodelazyDataModel(LazyDataModel<WtPeriode> wtPeriodelazyDataModel) {
        this.wtPeriodelazyDataModel = wtPeriodelazyDataModel;
    }

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        wtPeriodeSearchParameter = new WtPeriodeSearchParameter();
        String[] month = DateFormatSymbols.getInstance(Locale.forLanguageTag((String) FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE))).getMonths();

        for (int i = 0; i < month.length - 1; i++) {
            mapMonths.put(month[i], i + 1);
            listTahun.add(2013 + i);
        }
        mapMonths = MapUtil.sortByValue(mapMonths);

    }

    public List<Integer> getListTahun() {
        return listTahun;
    }

    public void setListTahun(List<Integer> listTahun) {
        this.listTahun = listTahun;
    }

    public void doSearch() {
        wtPeriodelazyDataModel = null;
    }

    @PreDestroy
    public void onPostClose() {
        wtPeriodeSearchParameter = null;
        wtPeriodelazyDataModel = null;
        wtPeriodeService = null;
        selectedWtPeriode = null;
        mapMonths = null;
        listTahun = null;
        selectedTahun = null;
        selectedBulan = null;
    }
    public void doChangeYear() {
        wtPeriodelazyDataModel = null;
    }

    public void doChangeMonth() {
        wtPeriodelazyDataModel = null;
    }
}
