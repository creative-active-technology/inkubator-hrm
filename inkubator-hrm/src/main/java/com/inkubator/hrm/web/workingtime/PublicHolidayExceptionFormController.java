package com.inkubator.hrm.web.workingtime;

import com.inkubator.hrm.web.reference.*;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.PublicHoliday;
import com.inkubator.hrm.entity.PublicHolidayException;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.PublicHolidayService;
import com.inkubator.hrm.service.PublicHolidayExceptionService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.PublicHolidayExceptionModel;
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
@ManagedBean(name = "publicHolidayExceptionFormController")
@ViewScoped
public class PublicHolidayExceptionFormController extends BaseController {

    private PublicHolidayExceptionModel publicHolidayExceptionModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{publicHolidayExceptionService}")
    private PublicHolidayExceptionService publicHolidayExceptionService;
    @ManagedProperty(value = "#{publicHolidayService}")
    private PublicHolidayService publicHolidayService;
    private Map<String, Long> publicHolidays = new TreeMap<>();
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("param");
            publicHolidayExceptionModel = new PublicHolidayExceptionModel();
            isUpdate = Boolean.FALSE;
            List<PublicHoliday> listPublicHolidays = publicHolidayService.getAllWithDetail();

            for (PublicHoliday publicHoliday : listPublicHolidays) {
                publicHolidays.put(publicHoliday.getLeaveScheme().getName(), publicHoliday.getId());
            }

            MapUtil.sortByValue(publicHolidays);
            
            if (StringUtils.isNumeric(param)) {
                PublicHolidayException publicHolidayException = publicHolidayExceptionService.getEntityByPKWithDetail(Long.parseLong(param));
                if (publicHolidayException != null) {
                    publicHolidayExceptionModel.setId(publicHolidayException.getId());
                    publicHolidayExceptionModel.setPublicHolidayId(publicHolidayException.getPublicHoliday().getId());
                    publicHolidayExceptionModel.setEmpData(publicHolidayException.getEmpData());
                    publicHolidayExceptionModel.setDescription(publicHolidayException.getDescription());
                    isUpdate = Boolean.TRUE;
                }

            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        publicHolidayExceptionService = null;
//        publicHolidayExceptionModel = null;
        isUpdate = null;
    }

    public PublicHolidayExceptionModel getPublicHolidayExceptionModel() {
        return publicHolidayExceptionModel;
    }

    public void setPublicHolidayExceptionModel(PublicHolidayExceptionModel publicHolidayExceptionModel) {
        this.publicHolidayExceptionModel = publicHolidayExceptionModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setPublicHolidayExceptionService(PublicHolidayExceptionService publicHolidayExceptionService) {
        this.publicHolidayExceptionService = publicHolidayExceptionService;
    }

    public Map<String, Long> getPublicHolidays() {
        return publicHolidays;
    }

    public void setPublicHolidays(Map<String, Long> publicHolidays) {
        this.publicHolidays = publicHolidays;
    }

    public void setPublicHolidayService(PublicHolidayService publicHolidayService) {
        this.publicHolidayService = publicHolidayService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }
    
    
    

    public void doSave() {
        PublicHolidayException publicHolidayException = getEntityFromViewModel(publicHolidayExceptionModel);
        try {
            if (isUpdate) {
                publicHolidayExceptionService.update(publicHolidayException);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                publicHolidayExceptionService.save(publicHolidayException);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private PublicHolidayException getEntityFromViewModel(PublicHolidayExceptionModel publicHolidayExceptionModel) {
        PublicHolidayException publicHolidayException = new PublicHolidayException();
        if (publicHolidayExceptionModel.getId() != null) {
            publicHolidayException.setId(publicHolidayExceptionModel.getId());
        }
        publicHolidayException.setPublicHoliday(new PublicHoliday(publicHolidayExceptionModel.getPublicHolidayId()));
        publicHolidayException.setEmpData(publicHolidayExceptionModel.getEmpData());
        publicHolidayException.setDescription(publicHolidayExceptionModel.getDescription());
        return publicHolidayException;
    }
    
    public List<EmpData> completeEmpData(String query) {
        try {
            List<EmpData> allEmpData = empDataService.getAllDataByNameOrNik(query);
            
            return allEmpData;
        } catch (Exception ex) {
            Logger.getLogger(PublicHolidayExceptionFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
