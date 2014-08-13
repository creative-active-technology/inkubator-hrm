package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.web.reference.*;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioMedicalHistory;
import com.inkubator.hrm.service.BioMedicalHistoryService;
import com.inkubator.hrm.web.model.BioMedicalHistoryModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.Calendar;
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
@ManagedBean(name = "bioMedicalHistoryFormController")
@ViewScoped
public class BioMedicalHistoryFormController extends BaseController {

    private BioMedicalHistoryModel bioMedicalHistoryModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{bioMedicalHistoryService}")
    private BioMedicalHistoryService bioMedicalHistoryService;
    private Map<Integer, Integer> listYears = new TreeMap<Integer, Integer>();

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            bioMedicalHistoryModel = new BioMedicalHistoryModel();
            isUpdate = Boolean.FALSE;
            String bioDataId = FacesUtil.getRequestParameter("bioDataId");
            bioMedicalHistoryModel.setBioDataId(Long.parseLong(bioDataId));

            int year = Calendar.getInstance().get(Calendar.YEAR);
            for (int i = year-100; i <= year; i++) {
                listYears.put(i, i);
            }

            String bioMedicalHistoryId = FacesUtil.getRequestParameter("bioMedicalHistoryId");
            if (StringUtils.isNotEmpty(bioMedicalHistoryId)) {
                BioMedicalHistory bioMedicalHistory = bioMedicalHistoryService.getEntiyByPK(Long.parseLong(bioMedicalHistoryId));
                if (bioMedicalHistoryId != null) {
                    bioMedicalHistoryModel = getModelFromEntity(bioMedicalHistory);
                    isUpdate = Boolean.TRUE;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        bioMedicalHistoryService = null;
//        bioMedicalHistoryModel = null;
        isUpdate = null;
    }

    public BioMedicalHistoryModel getBioMedicalHistoryModel() {
        return bioMedicalHistoryModel;
    }

    public void setBioMedicalHistoryModel(BioMedicalHistoryModel bioMedicalHistoryModel) {
        this.bioMedicalHistoryModel = bioMedicalHistoryModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setBioMedicalHistoryService(BioMedicalHistoryService bioMedicalHistoryService) {
        this.bioMedicalHistoryService = bioMedicalHistoryService;
    }

    public Map<Integer, Integer> getListYears() {
        return listYears;
    }

    public void setListYears(Map<Integer, Integer> listYears) {
        this.listYears = listYears;
    }

    public void doSave() {
        BioMedicalHistory bioMedicalHistory = getEntityFromViewModel(bioMedicalHistoryModel);
        try {
            if (isUpdate) {
                bioMedicalHistoryService.update(bioMedicalHistory);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                bioMedicalHistoryService.save(bioMedicalHistory);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { //data already exist(duplicate)
            LOGGER.error("Error", ex);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private BioMedicalHistory getEntityFromViewModel(BioMedicalHistoryModel bioMedicalHistoryModel) {
        BioMedicalHistory bioMedicalHistory = new BioMedicalHistory();
        if (bioMedicalHistoryModel.getId() != null) {
            bioMedicalHistory.setId(bioMedicalHistoryModel.getId());
        }
        bioMedicalHistory.setBioData(new BioData(bioMedicalHistoryModel.getBioDataId()));
        bioMedicalHistory.setYear(bioMedicalHistoryModel.getYear());
        bioMedicalHistory.setDisease(bioMedicalHistoryModel.getDisease());
        bioMedicalHistory.setStatus(bioMedicalHistoryModel.getStatus());
        bioMedicalHistory.setDescription(bioMedicalHistoryModel.getDescription());
        return bioMedicalHistory;
    }

    private BioMedicalHistoryModel getModelFromEntity(BioMedicalHistory entity) {
        BioMedicalHistoryModel bioMedicalHistoryModel = new BioMedicalHistoryModel();
        bioMedicalHistoryModel.setId(entity.getId());
        bioMedicalHistoryModel.setBioDataId(entity.getBioData().getId());
        bioMedicalHistoryModel.setYear(entity.getYear());
        bioMedicalHistoryModel.setDisease(entity.getDisease());
        bioMedicalHistoryModel.setStatus(entity.getStatus());
        bioMedicalHistoryModel.setDescription(entity.getDescription());
        return bioMedicalHistoryModel;
    }
}
