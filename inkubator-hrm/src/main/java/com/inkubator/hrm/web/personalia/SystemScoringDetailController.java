/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.entity.SystemScoring;
import com.inkubator.hrm.entity.SystemScoringIndex;
import com.inkubator.hrm.service.SystemScoringIndexService;
import com.inkubator.hrm.service.SystemScoringService;
import com.inkubator.hrm.web.lazymodel.SystemScoringIndexLazyDataModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Deni
 */
@ManagedBean(name = "systemScoringDetailController")
@ViewScoped
public class SystemScoringDetailController extends BaseController {

    @ManagedProperty(value = "#{systemScoringService}")
    private SystemScoringService systemScoringService;
    @ManagedProperty(value = "#{systemScoringIndexService}")
    private SystemScoringIndexService systemScoringIndexService;
    private SystemScoring selectedSystemScoring;
    private SystemScoringIndex selectedSystemScoringIndex;
    private LazyDataModel<SystemScoringIndex> lazyDataModel;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String systemScoringId = FacesUtil.getRequestParameter("execution");
        try {
            selectedSystemScoring = systemScoringService.getEntiyByPK(Long.parseLong(systemScoringId.substring(1)));
        } catch (Exception ex) {
            Logger.getLogger(SystemScoringDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    private void cleanAndExit() {
        systemScoringService = null;
        selectedSystemScoring = null;
        selectedSystemScoringIndex = null;
        lazyDataModel = null;
        systemScoringIndexService = null;
    }

    public SystemScoringService getSystemScoringService() {
        return systemScoringService;
    }

    public void setSystemScoringService(SystemScoringService systemScoringService) {
        this.systemScoringService = systemScoringService;
    }

    public SystemScoringIndexService getSystemScoringIndexService() {
        return systemScoringIndexService;
    }

    public void setSystemScoringIndexService(SystemScoringIndexService systemScoringIndexService) {
        this.systemScoringIndexService = systemScoringIndexService;
    }

    public SystemScoring getSelectedSystemScoring() {
        return selectedSystemScoring;
    }

    public void setSelectedSystemScoring(SystemScoring selectedSystemScoring) {
        this.selectedSystemScoring = selectedSystemScoring;
    }

    public SystemScoringIndex getSelectedSystemScoringIndex() {
        return selectedSystemScoringIndex;
    }

    public void setSelectedSystemScoringIndex(SystemScoringIndex selectedSystemScoringIndex) {
        this.selectedSystemScoringIndex = selectedSystemScoringIndex;
    }

    public LazyDataModel<SystemScoringIndex> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new SystemScoringIndexLazyDataModel(systemScoringIndexService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<SystemScoringIndex> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public void doAdd() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> systemScoringId = new ArrayList<>();
        systemScoringId.add(String.valueOf(selectedSystemScoring.getId()));
        dataToSend.put("systemScoringId", systemScoringId);
        showDialog(dataToSend);
    }

    public void doEdit() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> systemScoringId = new ArrayList<>();
        systemScoringId.add(String.valueOf(selectedSystemScoring.getId()));

        List<String> systemScoringIndexId = new ArrayList<>();
        systemScoringIndexId.add(String.valueOf(selectedSystemScoringIndex.getId()));
        dataToSend.put("systemScoringId", systemScoringId);
        dataToSend.put("systemScoringIndexId", systemScoringIndexId);
        showDialog(dataToSend);
    }

    public void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 370);
        RequestContext.getCurrentInstance().openDialog("system_scoring_index_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        resetLazy();
        super.onDialogReturn(event);

    }

    public void resetLazy() {
        lazyDataModel = null;
    }

}
