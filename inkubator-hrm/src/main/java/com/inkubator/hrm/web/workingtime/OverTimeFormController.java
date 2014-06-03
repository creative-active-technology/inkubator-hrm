/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.WtOverTime;
import com.inkubator.hrm.service.WtOverTimeService;
import com.inkubator.hrm.web.model.OverTimeModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

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
    WtOverTime selectedWtOverTime;
    private Boolean isEdit;

    public WtOverTime getSelectedWtOverTime() {
        return selectedWtOverTime;
    }

    public void setSelectedWtOverTime(WtOverTime selectedWtOverTime) {
        this.selectedWtOverTime = selectedWtOverTime;
    }

    public void setWtOverTimeService(WtOverTimeService wtOverTimeService) {
        this.wtOverTimeService = wtOverTimeService;
    }

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
        String param = FacesUtil.getRequestParameter("param");
        overTimeModel = new OverTimeModel();
        try {
            if (param != null) {

                isEdit = Boolean.TRUE;
                WtOverTime wtOverTime = wtOverTimeService.getEntiyByPK(Long.parseLong(param));
                overTimeModel.setId(wtOverTime.getId());
                overTimeModel.setCode(wtOverTime.getCode());
                overTimeModel.setDescription(wtOverTime.getDescription());
                overTimeModel.setFinishTimeFactor(wtOverTime.getFinishTimeFactor());
                overTimeModel.setMaximumTime(wtOverTime.getMaximumTime());
                overTimeModel.setMinimumTime(wtOverTime.getMinimumTime());
                overTimeModel.setName(wtOverTime.getName());
                overTimeModel.setOtRounding(wtOverTime.getOtRounding());
                overTimeModel.setOverTimeCalculation(wtOverTime.getOverTimeCalculation());
                overTimeModel.setStartTimeFactor(wtOverTime.getStartTimeFactor());
                overTimeModel.setValuePrice(wtOverTime.getValuePrice());

            } else {
                isEdit = Boolean.FALSE;
            }
        } catch (Exception ex) {
          LOGGER.error("Error", ex);
        }
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
        WtOverTime wtOverTime = getEntityFromViewModel(overTimeModel);
        try {
            if (isEdit) {
                wtOverTimeService.update(wtOverTime);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                wtOverTimeService.save(wtOverTime);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { //data already exist(duplicate)
            LOGGER.error("Error", ex);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        cleanAndExit();
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

    private WtOverTime getEntityFromViewModel(OverTimeModel overTimeModel) {
        WtOverTime overTime = new WtOverTime();
        if (overTimeModel.getId() != null) {
            overTime.setId(overTimeModel.getId());
        }
        overTime.setCode(overTimeModel.getCode());
        overTime.setDescription(overTimeModel.getDescription());
        overTime.setFinishTimeFactor(overTimeModel.getFinishTimeFactor());
        overTime.setMaximumTime(overTimeModel.getMaximumTime());
        overTime.setMinimumTime(overTimeModel.getMinimumTime());
        overTime.setName(overTimeModel.getName());
        overTime.setOtRounding(overTimeModel.getOtRounding());
        overTime.setOverTimeCalculation(overTimeModel.getOverTimeCalculation());
        overTime.setStartTimeFactor(overTimeModel.getStartTimeFactor());
        overTime.setValuePrice(overTimeModel.getValuePrice());
        return overTime;
    }

    @PreDestroy
    private void cleanAndExit() {

        isEdit = null;
        System.out.println(" ahhahaha");
    }

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
