/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.waktukerja;

import com.inkubator.hrm.entity.WtHoliday;
import com.inkubator.hrm.service.WtHolidayService;
import com.inkubator.hrm.web.lazymodel.WtHolidayLazyModel;
import com.inkubator.hrm.web.search.HolidaySearchParameter;
import com.inkubator.webcore.controller.BaseController;
import java.util.HashMap;
import java.util.Map;
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
@ManagedBean(name = "holidayViewController")
@ViewScoped
public class HolidayViewController extends BaseController {

    private HolidaySearchParameter holidaySearchParameter;
    private LazyDataModel<WtHoliday> wtHolidayLazyDataModel;
    @ManagedProperty(value = "#{wtHolidayService}")
    private WtHolidayService wtHolidayService;
    private WtHoliday selecWtHoliday;

    public HolidaySearchParameter getHolidaySearchParameter() {
        return holidaySearchParameter;
    }

    public void setHolidaySearchParameter(HolidaySearchParameter holidaySearchParameter) {
        this.holidaySearchParameter = holidaySearchParameter;
    }

    public LazyDataModel<WtHoliday> getWtPeriodelazyDataModel() {
        if(wtHolidayLazyDataModel==null){
            wtHolidayLazyDataModel=new WtHolidayLazyModel(holidaySearchParameter, wtHolidayService);
        }
        return wtHolidayLazyDataModel;
    }

    public void setWtPeriodelazyDataModel(LazyDataModel<WtHoliday> wtPeriodelazyDataModel) {
        this.wtHolidayLazyDataModel = wtPeriodelazyDataModel;
    }

    public WtHoliday getSelecWtHoliday() {
        return selecWtHoliday;
    }

    public void setSelecWtHoliday(WtHoliday selecWtHoliday) {
        this.selecWtHoliday = selecWtHoliday;
    }

    public void setWtHolidayService(WtHolidayService wtHolidayService) {
        this.wtHolidayService = wtHolidayService;
    }
  

   

   

//    public LazyDataModel<WtPeriode> getWtPeriodelazyDataModel() {
//        if (wtHolidayLazyDataModel == null) {
//            wtHolidayLazyDataModel = new WtPeriodLazyModel(wtPeriodeSearchParameter, wtPeriodeService);
//        }
//        return wtHolidayLazyDataModel;
//    }
//
//    public void setWtPeriodelazyDataModel(LazyDataModel<WtPeriode> wtHolidayLazyDataModel) {
//        this.wtHolidayLazyDataModel = wtHolidayLazyDataModel;
//    }

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
      holidaySearchParameter=new HolidaySearchParameter();
      

    }

//    public List<Integer> getListTahun() {
//        return listTahun;
//    }
//
//    public void setListTahun(List<Integer> listTahun) {
//        this.listTahun = listTahun;
//    }

    public void doSearch() {
        wtHolidayLazyDataModel = null;
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
    
    public void doChangeYear(){
        wtHolidayLazyDataModel=null;
    }
    
    public void doChangeMonth(){
          wtHolidayLazyDataModel=null;
    }
}
