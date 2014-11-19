package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.web.reference.*;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.AttendanceStatus;
import com.inkubator.hrm.entity.TravelType;
import com.inkubator.hrm.service.AttendanceStatusService;
import com.inkubator.hrm.service.TravelTypeService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.TravelTypeModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "travelTypeFormController")
@ViewScoped
public class TravelTypeFormController extends BaseController {

    private TravelTypeModel travelTypeModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{travelTypeService}")
    private TravelTypeService travelTypeService;
    @ManagedProperty(value = "#{attendanceStatusService}")
    private AttendanceStatusService attendanceStatusService;
    private Map<String, Long> attendances = new TreeMap<>();

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("param");
            travelTypeModel = new TravelTypeModel();
            isUpdate = Boolean.FALSE;

            List<AttendanceStatus> listCurrency = attendanceStatusService.getAllData();

            for (AttendanceStatus currency : listCurrency) {
                attendances.put(currency.getStatusKehadrian(), currency.getId());
            }

            MapUtil.sortByValue(attendances);

            if (StringUtils.isNumeric(param)) {
                TravelType travelType = travelTypeService.getEntityByPKWithDetail(Long.parseLong(param));
                if (travelType != null) {
                    travelTypeModel.setId(travelType.getId());
                    travelTypeModel.setCode(travelType.getCode());
                    travelTypeModel.setName(travelType.getName());
                    travelTypeModel.setDescription(travelType.getDescription());
                    travelTypeModel.setAttendanceStatusId(travelType.getAttendanceStatus().getId());
                    isUpdate = Boolean.TRUE;
                }

            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        travelTypeService = null;
//        travelTypeModel = null;
        isUpdate = null;
    }

    public TravelTypeModel getTravelTypeModel() {
        return travelTypeModel;
    }

    public void setTravelTypeModel(TravelTypeModel travelTypeModel) {
        this.travelTypeModel = travelTypeModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setTravelTypeService(TravelTypeService travelTypeService) {
        this.travelTypeService = travelTypeService;
    }

    public Map<String, Long> getAttendances() {
        return attendances;
    }

    public void setAttendances(Map<String, Long> attendances) {
        this.attendances = attendances;
    }

    public void setAttendanceStatusService(AttendanceStatusService attendanceStatusService) {
        this.attendanceStatusService = attendanceStatusService;
    }
    
    

    public void doSave() {
        TravelType travelType = getEntityFromViewModel(travelTypeModel);
        try {
            if (isUpdate) {
                travelTypeService.update(travelType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                travelTypeService.save(travelType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private TravelType getEntityFromViewModel(TravelTypeModel travelTypeModel) {
        TravelType travelType = new TravelType();
        if (travelTypeModel.getId() != null) {
            travelType.setId(travelTypeModel.getId());
        }
        travelType.setCode(travelTypeModel.getCode());
        travelType.setName(travelTypeModel.getName());
        travelType.setDescription(travelTypeModel.getDescription());
        travelType.setAttendanceStatus(new AttendanceStatus(travelTypeModel.getAttendanceStatusId()));
        return travelType;
    }
}
