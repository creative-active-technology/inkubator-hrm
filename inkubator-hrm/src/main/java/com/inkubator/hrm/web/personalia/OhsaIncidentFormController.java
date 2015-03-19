package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.web.reference.*;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.OhsaCategory;
import com.inkubator.hrm.entity.OhsaIncident;
import com.inkubator.hrm.service.EducationLevelService;
import com.inkubator.hrm.service.OhsaCategoryService;
import com.inkubator.hrm.service.OhsaIncidentService;
import com.inkubator.hrm.web.model.EducationLevelModel;
import com.inkubator.hrm.web.model.OhsaIncidentModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "ohsaIncidentFormController")
@ViewScoped
public class OhsaIncidentFormController extends BaseController {

    private OhsaIncidentModel ohsaIncidentModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{ohsaIncidentService}")
    private OhsaIncidentService ohsaIncidentService;
    @ManagedProperty(value = "#{ohsaCategoryService}")
    private OhsaCategoryService ohsaCategoryService;
    private Long ohsaCategoryId;
    private Map<String, Long> mapOhsaCategory = new HashMap<String, Long>();
    private Integer severityLevel;
    private Map<String, Integer> mapSeverityLevel = new HashMap<String, Integer>();

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            String param = FacesUtil.getRequestParameter("param");
            ohsaIncidentModel = new OhsaIncidentModel();
            isUpdate = Boolean.FALSE;

            //Inisialisasi Tingkat kerusakan
            mapSeverityLevel.put("Rendah", 1);
            mapSeverityLevel.put("Sedang", 2);
            mapSeverityLevel.put("Tinggi", 3);
            
            //Inisialisasi Kategori K3
            List<OhsaCategory> listOhsaCategorys = ohsaCategoryService.getAllData();
            for (OhsaCategory ohsaCategory : listOhsaCategorys) {
                mapOhsaCategory.put(ohsaCategory.getName(), ohsaCategory.getId());
            }
            
            if (StringUtils.isNumeric(param)) {

                OhsaIncident ohsaIncident = ohsaIncidentService.getEntityByPKWithDetail(Long.parseLong(param));
                if (ohsaIncident != null) {
                    ohsaIncidentModel.setId(ohsaIncident.getId());
                    ohsaIncidentModel.setIncidentTime(ohsaIncident.getIncidentTime());
                    ohsaIncidentModel.setLevel(ohsaIncident.getSeverityLevel());
                    ohsaIncidentModel.setLocation(ohsaIncident.getLocation());
                    ohsaIncidentModel.setOhsaCategoryId(ohsaIncident.getOhsaCategory().getId());
                    ohsaIncidentModel.setSubject(ohsaIncident.getSubject());
                    ohsaIncidentModel.setDescription(ohsaIncident.getDescription());

                    severityLevel = ohsaIncident.getSeverityLevel();
                    ohsaCategoryId = ohsaIncident.getOhsaCategory().getId();

                    isUpdate = Boolean.TRUE;
                }

            }

        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        ohsaCategoryId = null;
        ohsaIncidentService = null;
        mapOhsaCategory = null;
        mapSeverityLevel = null;
        ohsaIncidentModel = null;
        isUpdate = null;
    }

    public OhsaIncidentService getOhsaIncidentService() {
        return ohsaIncidentService;
    }

    public void setOhsaIncidentService(OhsaIncidentService ohsaIncidentService) {
        this.ohsaIncidentService = ohsaIncidentService;
    }

    public Long getOhsaCategoryId() {
        return ohsaCategoryId;
    }

    public void setOhsaCategoryId(Long ohsaCategoryId) {
        this.ohsaCategoryId = ohsaCategoryId;
    }

    public Map<String, Long> getMapOhsaCategory() {
        return mapOhsaCategory;
    }

    public void setMapOhsaCategory(Map<String, Long> mapOhsaCategory) {
        this.mapOhsaCategory = mapOhsaCategory;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public OhsaIncidentModel getOhsaIncidentModel() {
        return ohsaIncidentModel;
    }

    public void setOhsaIncidentModel(OhsaIncidentModel ohsaIncidentModel) {
        this.ohsaIncidentModel = ohsaIncidentModel;
    }

    public Integer getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(Integer severityLevel) {
        this.severityLevel = severityLevel;
    }

    public Map<String, Integer> getMapSeverityLevel() {
        return mapSeverityLevel;
    }

    public void setMapSeverityLevel(Map<String, Integer> mapSeverityLevel) {
        this.mapSeverityLevel = mapSeverityLevel;
    }

    public OhsaCategoryService getOhsaCategoryService() {
        return ohsaCategoryService;
    }

    public void setOhsaCategoryService(OhsaCategoryService ohsaCategoryService) {
        this.ohsaCategoryService = ohsaCategoryService;
    }

    public void doSave() {
        try {
            OhsaIncident ohsaIncident = getEntityFromViewModel(ohsaIncidentModel);
            if (isUpdate) {
                ohsaIncidentService.update(ohsaIncident);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                ohsaIncidentService.save(ohsaIncident);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private OhsaIncident getEntityFromViewModel(OhsaIncidentModel model) throws Exception {
        OhsaIncident ohsaIncident = new OhsaIncident();

        if (model.getId() != null) {
            ohsaIncident.setId(model.getId());
        }

        OhsaCategory ohsaCategory = ohsaCategoryService.getEntiyByPK(ohsaCategoryId);
        ohsaIncident.setOhsaCategory(ohsaCategory);
        ohsaIncident.setIncidentTime(model.getIncidentTime());
        ohsaIncident.setSubject(model.getSubject());
        ohsaIncident.setDescription(model.getDescription());
        ohsaIncident.setLocation(model.getLocation());
        ohsaIncident.setSeverityLevel(severityLevel);

        return ohsaIncident;
    }
}
