package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.web.reference.*;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioMedicalHistory;
import com.inkubator.hrm.entity.Disease;
import com.inkubator.hrm.service.BioMedicalHistoryService;
import com.inkubator.hrm.service.DiseaseService;
import com.inkubator.hrm.web.model.BioMedicalHistoryModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
@ManagedBean(name = "bioMedicalHistoryFormController")
@ViewScoped
public class BioMedicalHistoryFormController extends BaseController {

    private BioMedicalHistoryModel bioMedicalHistoryModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{bioMedicalHistoryService}")
    private BioMedicalHistoryService bioMedicalHistoryService;
    @ManagedProperty(value= "#{diseaseService}")
    private DiseaseService diseaseService;
    private Map<Integer, Integer> listYears = new TreeMap<>(Collections.reverseOrder());
    private Map<String, Long> dropDownDiseases = new TreeMap<String, Long>();;
    private List<Disease> listDiseases = new ArrayList<>();

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
            for (int i = year-54; i <= year; i++) {
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
            
          //get dropdown
            listDiseases = diseaseService.getAllData();
            for(Disease disease : listDiseases){
        		dropDownDiseases.put(disease.getName(), disease.getId());
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        bioMedicalHistoryService = null;
        diseaseService = null;
//        bioMedicalHistoryModel = null;
        isUpdate = null;
        listYears = null;
        listDiseases = null;
        dropDownDiseases = null;
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
        } catch (BussinessException ex) { 
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
//        bioMedicalHistory.setDisease(bioMedicalHistoryModel.getDisease());
        bioMedicalHistory.setDisease(new Disease(bioMedicalHistoryModel.getDiseasesId()));
        bioMedicalHistory.setStatus(bioMedicalHistoryModel.getStatus());
        bioMedicalHistory.setDescription(bioMedicalHistoryModel.getDescription());
        return bioMedicalHistory;
    }

    private BioMedicalHistoryModel getModelFromEntity(BioMedicalHistory entity) {
        BioMedicalHistoryModel bioMedicalHistoryModel = new BioMedicalHistoryModel();
        bioMedicalHistoryModel.setId(entity.getId());
        bioMedicalHistoryModel.setBioDataId(entity.getBioData().getId());
        bioMedicalHistoryModel.setYear(entity.getYear());
//        bioMedicalHistoryModel.setDisease(entity.getDisease());
        if(entity.getDisease() != null){
        	bioMedicalHistoryModel.setDiseasesId(entity.getDisease().getId());
        }
        bioMedicalHistoryModel.setStatus(entity.getStatus());
        bioMedicalHistoryModel.setDescription(entity.getDescription());
        return bioMedicalHistoryModel;
    }

	public DiseaseService getDiseaseService() {
		return diseaseService;
	}

	public void setDiseaseService(DiseaseService diseaseService) {
		this.diseaseService = diseaseService;
	}

	public Map<String, Long> getDropDownDiseases() {
		return dropDownDiseases;
	}

	public void setDropDownDiseases(Map<String, Long> dropDownDiseases) {
		this.dropDownDiseases = dropDownDiseases;
	}

	public List<Disease> getListDiseases() {
		return listDiseases;
	}

	public void setListDiseases(List<Disease> listDiseases) {
		this.listDiseases = listDiseases;
	}

	public BioMedicalHistoryService getBioMedicalHistoryService() {
		return bioMedicalHistoryService;
	}
    
//    public List<String> completeDisease(String query) {
//        try {
//            List<BioMedicalHistory> allBioMedicalHistory = bioMedicalHistoryService.getAllData();
//            List<String> queried = new ArrayList<>();
//            
//            for (BioMedicalHistory bioMedicalHistory : allBioMedicalHistory) {
//                if (bioMedicalHistory.getDisease().toLowerCase().startsWith(query)  || bioMedicalHistory.getDisease().startsWith(query)) {
//                    queried.add(bioMedicalHistory.getDisease());
//                }
//            }
//            Set<String> setCompany = new HashSet<>(queried);
//            List<String> listCompany = new ArrayList<>(setCompany);
//            return listCompany;
//        } catch (Exception ex) {
//            Logger.getLogger(BioMedicalHistoryFormController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
    
    
}
