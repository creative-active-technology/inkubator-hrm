package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Province;
import com.inkubator.hrm.service.ProvinceService;
import com.inkubator.hrm.web.lazymodel.ProvinceLazyDataModel;
import com.inkubator.hrm.web.search.ProvinceSearchParameter;
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
@ManagedBean(name = "provinceViewController")
@ViewScoped
public class ProvinceViewController extends BaseController {

    private ProvinceSearchParameter searchParameter;
    private LazyDataModel<Province> lazyDataProvince;
    private Province selectedProvince;
    @ManagedProperty(value = "#{provinceService}")
    private ProvinceService provinceService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new ProvinceSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        provinceService = null;
        searchParameter = null;
        lazyDataProvince = null;
        selectedProvince = null;
    }

    public void setProvinceService(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    public ProvinceSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(ProvinceSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    

    public LazyDataModel<Province> getLazyDataProvince() {
        if (lazyDataProvince == null) {
            lazyDataProvince = new ProvinceLazyDataModel(searchParameter, provinceService);
        }
        return lazyDataProvince;
    }

    public void setLazyDataProvince(LazyDataModel<Province> lazyDataProvince) {
        this.lazyDataProvince = lazyDataProvince;
    }

    public Province getSelectedProvince() {
        return selectedProvince;
    }

    public void setSelectedProvince(Province selectedProvince) {
        this.selectedProvince = selectedProvince;
    }

    public void doSearch() {
        lazyDataProvince = null;
    }

    public void doDetail() {
        try {
            selectedProvince = this.provinceService.getProvinceByIdWithDetail(selectedProvince.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            provinceService.delete(selectedProvince);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete province ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete province", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedProvince.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 480);
        options.put("contentHeight", 475);
        RequestContext.getCurrentInstance().openDialog("province_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
