package com.inkubator.hrm.web.workingtime;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.entity.LeaveScheme;
import com.inkubator.hrm.entity.PublicHoliday;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.service.LeaveSchemeService;
import com.inkubator.hrm.service.LeaveService;
import com.inkubator.hrm.service.PublicHolidayService;
import com.inkubator.hrm.service.TransactionCodeficationService;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.PublicHolidayModel;
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
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "publicHolidayFormController")
@ViewScoped
public class PublicHolidayFormController extends BaseController {

    private PublicHolidayModel publicHolidayModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{publicHolidayService}")
    private PublicHolidayService publicHolidayService;
    @ManagedProperty(value = "#{leaveService}")
    private LeaveService leaveService;
    private Map<String, Long> leaveSchemes = new TreeMap<>();
    @ManagedProperty(value = "#{transactionCodeficationService}")
    private TransactionCodeficationService transactionCodeficationService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("param");
            publicHolidayModel = new PublicHolidayModel();
            isUpdate = Boolean.FALSE;
            TransactionCodefication transactionCodefication = transactionCodeficationService.getEntityByModulCode(HRMConstant.PUBCIL_HOLIDAY_KODE);
           
            publicHolidayModel.setCode(KodefikasiUtil.getKodefikasiOnlyPattern(transactionCodefication.getCode()));
            List<Leave> listLeaveSchemes = leaveService.getAllData();

            for (Leave leaveScheme : listLeaveSchemes) {
                leaveSchemes.put(leaveScheme.getName(), leaveScheme.getId());
            }

            MapUtil.sortByValue(leaveSchemes);

            if (StringUtils.isNumeric(param)) {
                PublicHoliday publicHoliday = publicHolidayService.getEntiyByPK(Long.parseLong(param));
                if (publicHoliday != null) {
                    publicHolidayModel.setId(publicHoliday.getId());
                    publicHolidayModel.setLeaveSchemeId(publicHoliday.getLeave().getId());
                    publicHolidayModel.setStartDate(publicHoliday.getStartDate());
                    publicHolidayModel.setEndDate(publicHoliday.getEndDate());
                    publicHolidayModel.setDescription(publicHoliday.getDescription());
                    publicHolidayModel.setCode(publicHoliday.getCode());
                    isUpdate = Boolean.TRUE;
                }

            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        publicHolidayService = null;
//        publicHolidayModel = null;
        isUpdate = null;
        leaveService = null;
        leaveSchemes = null;
    }

    public PublicHolidayModel getPublicHolidayModel() {
        return publicHolidayModel;
    }

    public void setPublicHolidayModel(PublicHolidayModel publicHolidayModel) {
        this.publicHolidayModel = publicHolidayModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setPublicHolidayService(PublicHolidayService publicHolidayService) {
        this.publicHolidayService = publicHolidayService;
    }

    public Map<String, Long> getLeaveSchemes() {
        return leaveSchemes;
    }

    public void setLeaveSchemes(Map<String, Long> leaveSchemes) {
        this.leaveSchemes = leaveSchemes;
    }

    public void setLeaveService(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

   
    
    

    public void doSave() {
        PublicHoliday publicHoliday = getEntityFromViewModel(publicHolidayModel);
        try {
            if (isUpdate) {
                publicHolidayService.update(publicHoliday);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                publicHolidayService.save(publicHoliday);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private PublicHoliday getEntityFromViewModel(PublicHolidayModel publicHolidayModel) {
        PublicHoliday publicHoliday = new PublicHoliday();
        if (publicHolidayModel.getId() != null) {
            publicHoliday.setId(publicHolidayModel.getId());
        }
        publicHoliday.setLeave(new Leave(publicHolidayModel.getLeaveSchemeId()));
        publicHoliday.setStartDate(publicHolidayModel.getStartDate());
        publicHoliday.setEndDate(publicHolidayModel.getEndDate());
        publicHoliday.setDescription(publicHolidayModel.getDescription());
        publicHoliday.setCode(publicHolidayModel.getCode());
        return publicHoliday;
    }

    public void setTransactionCodeficationService(TransactionCodeficationService transactionCodeficationService) {
        this.transactionCodeficationService = transactionCodeficationService;
    }
    
    
}
