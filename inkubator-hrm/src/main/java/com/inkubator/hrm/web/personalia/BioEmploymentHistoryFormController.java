package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.web.reference.*;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioEmploymentHistory;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.OccupationType;
import com.inkubator.hrm.service.BioEmploymentHistoryService;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.service.OccupationTypeService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.BioEmploymentHistoryModel;
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
@ManagedBean(name = "bioEmploymentHistoryFormController")
@ViewScoped
public class BioEmploymentHistoryFormController extends BaseController {

    private BioEmploymentHistoryModel bioEmploymentHistoryModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{bioEmploymentHistoryService}")
    private BioEmploymentHistoryService bioEmploymentHistoryService;
    @ManagedProperty(value = "#{cityService}")
    private CityService cityService;
    @ManagedProperty(value = "#{occupationTypeService}")
    private OccupationTypeService occupationTypeService;
    private Map<Integer, Integer> listYears = new TreeMap<>(Collections.reverseOrder());

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            bioEmploymentHistoryModel = new BioEmploymentHistoryModel();
            isUpdate = Boolean.FALSE;
            String bioDataId = FacesUtil.getRequestParameter("bioDataId");
            bioEmploymentHistoryModel.setBioDataId(Long.parseLong(bioDataId));

            int year = Calendar.getInstance().get(Calendar.YEAR);
            for (int i = year; i >= year - 54; i--) {
                listYears.put(i, i);
            }
            

            String bioEmploymentHistoryId = FacesUtil.getRequestParameter("bioEmploymentHistoryId");
            if (StringUtils.isNotEmpty(bioEmploymentHistoryId)) {
                BioEmploymentHistory bioEmploymentHistory = bioEmploymentHistoryService.getEntityByPKWithDetail(Long.parseLong(bioEmploymentHistoryId));
                if (bioEmploymentHistoryId != null) {
                    bioEmploymentHistoryModel = getModelFromEntity(bioEmploymentHistory);
                    isUpdate = Boolean.TRUE;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        bioEmploymentHistoryService = null;
//        bioEmploymentHistoryModel = null;
        isUpdate = null;
    }

    public BioEmploymentHistoryModel getBioEmploymentHistoryModel() {
        return bioEmploymentHistoryModel;
    }

    public void setBioEmploymentHistoryModel(BioEmploymentHistoryModel bioEmploymentHistoryModel) {
        this.bioEmploymentHistoryModel = bioEmploymentHistoryModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setBioEmploymentHistoryService(BioEmploymentHistoryService bioEmploymentHistoryService) {
        this.bioEmploymentHistoryService = bioEmploymentHistoryService;
    }

    public Map<Integer, Integer> getListYears() {
        return listYears;
    }

    public void setListYears(Map<Integer, Integer> listYears) {
        this.listYears = listYears;
    }

    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    public void setOccupationTypeService(OccupationTypeService occupationTypeService) {
        this.occupationTypeService = occupationTypeService;
    }

    
    
    public void doSave() {
        BioEmploymentHistory bioEmploymentHistory = getEntityFromViewModel(bioEmploymentHistoryModel);
        try {
            if (isUpdate) {
                bioEmploymentHistoryService.update(bioEmploymentHistory);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                bioEmploymentHistoryService.save(bioEmploymentHistory);
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

    private BioEmploymentHistory getEntityFromViewModel(BioEmploymentHistoryModel bioEmploymentHistoryModel) {
        try {
            BioEmploymentHistory bioEmploymentHistory = new BioEmploymentHistory();
            if (bioEmploymentHistoryModel.getId() != null) {
                bioEmploymentHistory.setId(bioEmploymentHistoryModel.getId());
            }
            bioEmploymentHistory.setBioData(new BioData(bioEmploymentHistoryModel.getBioDataId()));
            bioEmploymentHistory.setCity(bioEmploymentHistoryModel.getCity());
            bioEmploymentHistory.setYearIn(bioEmploymentHistoryModel.getYearIn());
            bioEmploymentHistory.setYearOut(bioEmploymentHistoryModel.getYearOut());
            bioEmploymentHistory.setCompanyName(bioEmploymentHistoryModel.getCompanyName());
            bioEmploymentHistory.setLastOccupation(bioEmploymentHistoryModel.getLastOccupation());
            bioEmploymentHistory.setSalary(bioEmploymentHistoryModel.getSalary());
            bioEmploymentHistory.setJobSector(bioEmploymentHistoryModel.getJobSector());
            return bioEmploymentHistory;
        } catch (Exception ex) {
            Logger.getLogger(BioEmploymentHistoryFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private BioEmploymentHistoryModel getModelFromEntity(BioEmploymentHistory entity) {
        BioEmploymentHistoryModel bioEmploymentHistoryModel = new BioEmploymentHistoryModel();
        bioEmploymentHistoryModel.setId(entity.getId());
        bioEmploymentHistoryModel.setBioDataId(entity.getBioData().getId());
        bioEmploymentHistoryModel.setCity(entity.getCity());
        bioEmploymentHistoryModel.setYearIn(entity.getYearIn());
        bioEmploymentHistoryModel.setYearOut(entity.getYearOut());
        bioEmploymentHistoryModel.setCompanyName(entity.getCompanyName());
        bioEmploymentHistoryModel.setLastOccupation(entity.getLastOccupation());
        bioEmploymentHistoryModel.setSalary(entity.getSalary());
        bioEmploymentHistoryModel.setJobSector(entity.getJobSector());
        return bioEmploymentHistoryModel;
    }

    public List<City> completeCity(String query) {
        try {
            List<City> allCity = cityService.getAllData();
            List<City> queried = new ArrayList<City>();
            for (City city : allCity) {
                if (city.getCityName().toLowerCase().contains(query)  || city.getCityName().contains(query)) {
                    queried.add(city);
                }
            }

            return queried;
        } catch (Exception ex) {
            Logger.getLogger(BioEmploymentHistoryFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<String> completeCompany(String query) {
        try {
            List<BioEmploymentHistory> allBioEmploymentHistory = bioEmploymentHistoryService.getAllData();
            List<String> queried = new ArrayList<>();
            
            for (BioEmploymentHistory bioEmploymentHistory : allBioEmploymentHistory) {
                if (bioEmploymentHistory.getCompanyName().toLowerCase().startsWith(query)  || bioEmploymentHistory.getCompanyName().startsWith(query)) {
                    queried.add(bioEmploymentHistory.getCompanyName());
                }
            }
            Set<String> setCompany = new HashSet<>(queried);
            List<String> listCompany = new ArrayList<>(setCompany);
            return listCompany;
        } catch (Exception ex) {
            Logger.getLogger(BioEmploymentHistoryFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<String> completeOccupation(String query) {
        try {
            List<OccupationType> allOccupationType = occupationTypeService.getAllData();
            List<String> queried = new ArrayList<>();
            for (OccupationType occupationType : allOccupationType) {
                if (occupationType.getOccupationTypeName().toLowerCase().startsWith(query)  || occupationType.getOccupationTypeName().startsWith(query)) {
                    queried.add(occupationType.getOccupationTypeName());
                }
            }

            Set<String> setOccupation = new HashSet<>(queried);
            List<String> listOccupation = new ArrayList<>(setOccupation);
            return listOccupation;
        } catch (Exception ex) {
            Logger.getLogger(OccupationTypeFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
