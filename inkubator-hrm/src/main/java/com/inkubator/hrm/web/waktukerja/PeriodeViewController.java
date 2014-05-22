/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.waktukerja;

import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.WtPeriodLazyModel;
import com.inkubator.hrm.web.search.WtPeriodeSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import java.text.DateFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
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
        String[] month = DateFormatSymbols.getInstance(Locale.FRENCH).getMonths();
        for (String string : month) {
            System.out.println(" nilai bulan" + string);
        }

    }

    public void doSearch() {
        wtPeriodelazyDataModel = null;
    }

    @PreDestroy
    public void onPostClose() {
        System.out.println("Bersih -berisesfsdhfkh");
    }

    public void doDetail() {
//        try {
//            selectedHrmRole = this.hrmRoleService.getEntiyByPK(selectedHrmRole.getId());
//        } catch (Exception ex) {
//            LOGGER.error("Error", ex);
//        }
    }

    public void doDelete() {
//        try {
//            this.hrmRoleService.delete(selectedHrmRole);
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
    }

    public void doAdd() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 340);
//        options.put("closable", false);
//        options.put("height", "auto");

//        options.put("contentHeight", 340);
        RequestContext.getCurrentInstance().openDialog("role_form", options, null);
    }

    public void doEdit() {
//        Map<String, Object> options = new HashMap<>();
//        options.put("modal", true);
//        options.put("draggable", true);
//        options.put("resizable", false);
//        options.put("contentWidth", 400);
//        options.put("contentHeight", 340);
//        Map<String, List<String>> dataToSend = new HashMap<>();
//        List<String> dataIsi = new ArrayList<>();
//        dataIsi.add(String.valueOf(selectedHrmRole.getId()));
//        dataToSend.put("param", dataIsi);
//        RequestContext.getCurrentInstance().openDialog("role_form", options, dataToSend);
    }

    public void onDialogReturn(SelectEvent event) {
//        lazyDataHrmRole = null;
//        System.out.println(" shhsdfhsdhdsfhdsfhdh");
//        String condition = (String) event.getObject();
//        System.out.println(" kodisi " + condition);
//        if (condition.equalsIgnoreCase(HRMConstant.SAVE_CONDITION)) {
//            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
//                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//        }
//        if (condition.equalsIgnoreCase(HRMConstant.UPDATE_CONDITION)) {
//            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
//                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//        }

    }

    public void onDelete() {
//        try {
//            selectedHrmRole = this.hrmRoleService.getEntiyByPK(selectedHrmRole.getId());
//        } catch (Exception ex) {
//            LOGGER.error("Error", ex);
//        }
    }
}
