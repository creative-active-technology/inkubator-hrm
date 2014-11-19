/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Religion;
import com.inkubator.hrm.entity.WtHoliday;
import com.inkubator.hrm.service.ReligionService;
import com.inkubator.hrm.service.WtHolidayService;
import com.inkubator.hrm.web.model.HolidayModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
@ManagedBean(name = "holidayFormController")
@ViewScoped
public class HolidayFormController extends BaseController {

    private HolidayModel holidayModel;
    @ManagedProperty(value = "#{wtHolidayService}")
    private WtHolidayService wtHolidayService;
    @ManagedProperty(value = "#{religionService}")
    private ReligionService religionService;
    private Map<String, Long> mapReligions = new TreeMap<>();
    private Boolean isEdit;

    public HolidayModel getHolidayModel() {
        return holidayModel;
    }

    public void setHolidayModel(HolidayModel holidayModel) {
        this.holidayModel = holidayModel;
    }

    public void setWtHolidayService(WtHolidayService wtHolidayService) {
        this.wtHolidayService = wtHolidayService;
    }

    public void setReligionService(ReligionService religionService) {
        this.religionService = religionService;
    }

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("param");
            holidayModel = new HolidayModel();
            List<Religion> religions = religionService.getAllData();
            for (Religion religion : religions) {
                mapReligions.put(religion.getName(), religion.getId());
            }
            if (param != null) {
                System.out.println(" nilai param" + param);
                WtHoliday wtHoliday = wtHolidayService.getEntiyByPK(Long.parseLong(param));
                holidayModel.setId(wtHoliday.getId());
                holidayModel.setHolidayDate(wtHoliday.getHolidayDate());
                holidayModel.setHolidayName(wtHoliday.getHolidayName());
                if (wtHoliday.getIsColectiveLeave() == 1) {
                    holidayModel.setIsCollective(Boolean.TRUE);
                }
                if (wtHoliday.getIsColectiveLeave() == 0) {
                    holidayModel.setIsCollective(Boolean.FALSE);
                }

                if (wtHoliday.getIsEveryYear() == 1) {
                    holidayModel.setIsEveryYear(Boolean.TRUE);
                }
                if (wtHoliday.getIsEveryYear() == 0) {
                    holidayModel.setIsEveryYear(Boolean.FALSE);
                }
                if (wtHoliday.getReligion() != null) {
                    holidayModel.setReligionId(wtHoliday.getReligion().getId());
                }
                isEdit = Boolean.TRUE;
            } else {
                isEdit = Boolean.FALSE;
            }

        } catch (Exception ex) {
            LOGGER.error("Errot", ex);
        }

    }

    public Map<String, Long> getMapReligions() {
        return mapReligions;
    }

    public void setMapReligions(Map<String, Long> mapReligions) {
        this.mapReligions = mapReligions;
    }

    public void doSave() {
        WtHoliday wtHoliday = getEntityFromViewModel(holidayModel);
        try {
            if (isEdit) {
                wtHolidayService.update(wtHoliday);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                wtHolidayService.save(wtHoliday);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        cleanAndExit();
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    private WtHoliday getEntityFromViewModel(HolidayModel holidayModel) {
        WtHoliday wtHoliday = new WtHoliday();
        if (holidayModel.getId() != null) {
            wtHoliday.setId(holidayModel.getId());
        }
        wtHoliday.setHolidayDate(holidayModel.getHolidayDate());
        wtHoliday.setHolidayName(holidayModel.getHolidayName());
        if (holidayModel.getIsCollective()) {
            wtHoliday.setIsColectiveLeave(1);
        } else {
            wtHoliday.setIsColectiveLeave(0);
        }
        if (holidayModel.getIsEveryYear()) {
            wtHoliday.setIsEveryYear(1);
        } else {
            wtHoliday.setIsEveryYear(0);
        }
        wtHoliday.setReligion(new Religion(holidayModel.getReligionId()));
        return wtHoliday;
    }

    @PreDestroy
    private void cleanAndExit() {
        holidayModel = null;
        wtHolidayService=null;
        religionService=null;
        mapReligions=null;
        isEdit=null;

    }
}
