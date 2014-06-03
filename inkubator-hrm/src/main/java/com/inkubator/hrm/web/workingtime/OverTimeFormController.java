/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.hrm.service.WtOverTimeService;
import com.inkubator.hrm.web.model.OverTimeModel;
import com.inkubator.webcore.controller.BaseController;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "overTimeFormController")
@ViewScoped
public class OverTimeFormController extends BaseController {

    private OverTimeModel overTimeModel;
    @ManagedProperty(value = "#{wtOverTimeService}")
    private WtOverTimeService wtOverTimeService;

    public void setWtOverTimeService(WtOverTimeService wtOverTimeService) {
        this.wtOverTimeService = wtOverTimeService;
    }
    private Boolean isEdit;

//    private HolidayModel holidayModel;
//    @ManagedProperty(value = "#{wtHolidayService}")
//    private WtHolidayService wtHolidayService;
//    @ManagedProperty(value = "#{religionService}")
//    private ReligionService religionService;
//    private Map<String, Long> mapReligions = new TreeMap<>();
//    private Boolean isEdit;
//
//    public HolidayModel getHolidayModel() {
//        return holidayModel;
//    }
//
//    public void setHolidayModel(HolidayModel holidayModel) {
//        this.holidayModel = holidayModel;
//    }
//
//    public void setWtHolidayService(WtHolidayService wtHolidayService) {
//        this.wtHolidayService = wtHolidayService;
//    }
//
//    public void setReligionService(ReligionService religionService) {
//        this.religionService = religionService;
//    }
//
    @PostConstruct
    @Override
    public void initialization() {
          super.initialization();
          overTimeModel=new OverTimeModel();
//        try {
          
//            String param = FacesUtil.getRequestParameter("param");
//            holidayModel = new HolidayModel();
//            List<Religion> religions = religionService.getAllData();
//            for (Religion religion : religions) {
//                mapReligions.put(religion.getName(), religion.getId());
//            }
//            if (param != null) {
//                System.out.println(" nilai param" + param);
//                WtHoliday wtHoliday = wtHolidayService.getEntiyByPK(Long.parseLong(param));
//                holidayModel.setId(wtHoliday.getId());
//                holidayModel.setHolidayDate(wtHoliday.getHolidayDate());
//                holidayModel.setHolidayName(wtHoliday.getHolidayName());
//                if (wtHoliday.getIsColectiveLeave() == 1) {
//                    holidayModel.setIsCollective(Boolean.TRUE);
//                }
//                if (wtHoliday.getIsColectiveLeave() == 0) {
//                    holidayModel.setIsCollective(Boolean.FALSE);
//                }
//
//                if (wtHoliday.getIsEveryYear() == 1) {
//                    holidayModel.setIsEveryYear(Boolean.TRUE);
//                }
//                if (wtHoliday.getIsEveryYear() == 0) {
//                    holidayModel.setIsEveryYear(Boolean.FALSE);
//                }
//                if (wtHoliday.getReligion() != null) {
//                    holidayModel.setReligionId(wtHoliday.getReligion().getId());
//                }
//                isEdit = Boolean.TRUE;
//            } else {
//                isEdit = Boolean.FALSE;
//            }
//
//        } catch (Exception ex) {
//            LOGGER.error("Errot", ex);
//        }

    }
//
//    public Map<String, Long> getMapReligions() {
//        return mapReligions;
//    }
//
//    public void setMapReligions(Map<String, Long> mapReligions) {
//        this.mapReligions = mapReligions;
//    }
//
    public void doSave() {
//        WtHoliday wtHoliday = getEntityFromViewModel(holidayModel);
//        try {
//            if (isEdit) {
//                wtHolidayService.update(wtHoliday);
//                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
//            } else {
//                wtHolidayService.save(wtHoliday);
//                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
//            }
//            cleanAndExit();
//        } catch (BussinessException ex) { //data already exist(duplicate)
//            LOGGER.error("Error", ex);
//            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//        } catch (Exception ex) {
//            LOGGER.error("Error", ex);
//        }
//        cleanAndExit();
    }
//
//    public Boolean getIsEdit() {
//        return isEdit;
//    }
//
//    public void setIsEdit(Boolean isEdit) {
//        this.isEdit = isEdit;
//    }
//
//    private WtHoliday getEntityFromViewModel(HolidayModel holidayModel) {
//        WtHoliday wtHoliday = new WtHoliday();
//        if (holidayModel.getId() != null) {
//            wtHoliday.setId(holidayModel.getId());
//        }
//        wtHoliday.setHolidayDate(holidayModel.getHolidayDate());
//        wtHoliday.setHolidayName(holidayModel.getHolidayName());
//        if (holidayModel.getIsCollective()) {
//            wtHoliday.setIsColectiveLeave(1);
//        } else {
//            wtHoliday.setIsColectiveLeave(0);
//        }
//        if (holidayModel.getIsEveryYear()) {
//            wtHoliday.setIsEveryYear(1);
//        } else {
//            wtHoliday.setIsEveryYear(0);
//        }
//        wtHoliday.setReligion(new Religion(holidayModel.getReligionId()));
//        return wtHoliday;
//    }
//
//    @PreDestroy
//    private void cleanAndExit() {
//
//        isEdit = null;
//        System.out.println(" ahhahaha");
//    }
    public OverTimeModel getOverTimeModel() {
        return overTimeModel;
    }

    public void setOverTimeModel(OverTimeModel overTimeModel) {
        this.overTimeModel = overTimeModel;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }
    
    
    
}
