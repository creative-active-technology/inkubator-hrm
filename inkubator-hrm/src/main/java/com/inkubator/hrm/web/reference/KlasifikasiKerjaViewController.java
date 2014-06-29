package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.service.KlasifikasiKerjaService;
import com.inkubator.hrm.web.lazymodel.KlasifikasiKerjaLazyDataModel;
import com.inkubator.hrm.web.search.KlasifikasiKerjaSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "klasifikasiKerjaViewController")
@ViewScoped
public class KlasifikasiKerjaViewController extends BaseController {

    private KlasifikasiKerjaSearchParameter searchParameter;
    private LazyDataModel<KlasifikasiKerja> lazyDataKlasifikasiKerja;
    private KlasifikasiKerja selectedKlasifikasiKerja;
    @ManagedProperty(value = "#{klasifikasiKerjaService}")
    private KlasifikasiKerjaService klasifikasiKerjaService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new KlasifikasiKerjaSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        klasifikasiKerjaService = null;
        searchParameter = null;
        lazyDataKlasifikasiKerja = null;
        selectedKlasifikasiKerja = null;
    }

    public void setKlasifikasiKerjaService(KlasifikasiKerjaService klasifikasiKerjaService) {
        this.klasifikasiKerjaService = klasifikasiKerjaService;
    }

    public KlasifikasiKerjaSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(KlasifikasiKerjaSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    

    public LazyDataModel<KlasifikasiKerja> getLazyDataKlasifikasiKerja() {
        if (lazyDataKlasifikasiKerja == null) {
            lazyDataKlasifikasiKerja = new KlasifikasiKerjaLazyDataModel(searchParameter, klasifikasiKerjaService);
        }
        return lazyDataKlasifikasiKerja;
    }

    public void setLazyDataKlasifikasiKerja(LazyDataModel<KlasifikasiKerja> lazyDataKlasifikasiKerja) {
        this.lazyDataKlasifikasiKerja = lazyDataKlasifikasiKerja;
    }

    public KlasifikasiKerja getSelectedKlasifikasiKerja() {
        return selectedKlasifikasiKerja;
    }

    public void setSelectedKlasifikasiKerja(KlasifikasiKerja selectedKlasifikasiKerja) {
        this.selectedKlasifikasiKerja = selectedKlasifikasiKerja;
    }

    public void doSearch() {
        lazyDataKlasifikasiKerja = null;
    }

    public void doDetail() {
        try {
            selectedKlasifikasiKerja = this.klasifikasiKerjaService.getEntiyByPK(selectedKlasifikasiKerja.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            klasifikasiKerjaService.delete(selectedKlasifikasiKerja);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete klasifikasiKerja ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete klasifikasiKerja", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedKlasifikasiKerja.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 450);
        options.put("contentHeight", 375);
        RequestContext.getCurrentInstance().openDialog("klasifikasi_kerja_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
