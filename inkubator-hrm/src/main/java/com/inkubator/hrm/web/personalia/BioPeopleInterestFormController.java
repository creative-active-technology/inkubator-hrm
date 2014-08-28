/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioPeopleInterest;
import com.inkubator.hrm.entity.InterestType;
import com.inkubator.hrm.service.BioPeopleInterestService;
import com.inkubator.hrm.service.InterestTypeService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.BioPeopleInterestModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
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
 * @author Deni
 */
@ManagedBean(name = "peopleInterestFormController")
@ViewScoped
public class BioPeopleInterestFormController extends BaseController{
    @ManagedProperty(value = "#{bioPeopleInterestService}")
    private BioPeopleInterestService peopleInterestService;
    @ManagedProperty(value = "#{interestTypeService}")
    private InterestTypeService interestTypeService;
    private Long bioDataId;
    private BioPeopleInterest selected;
    private BioPeopleInterestModel model;
    private Boolean isEdit;
    
    //List Dropdown
    private Map<String, Long> listInterestTypes = new TreeMap<String, Long>();;
    private List<InterestType> listInterestType = new ArrayList<>();

    @PreDestroy
    private void cleanAndExit() {
        model = null;
        selected = null;
        isEdit = null;
        bioDataId = null;
        listInterestType = null;
        listInterestTypes = null;
        peopleInterestService = null;
    }
    
    @PostConstruct
    @Override
    public void initialization() {
     
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        model = new BioPeopleInterestModel();
        
        try {
            if(param.contains("i")){
                bioDataId = Long.parseLong(param.substring(1));
                isEdit = Boolean.FALSE;
            }
            if (param.contains("e")) {
                isEdit = Boolean.TRUE;
                long educationId = Long.parseLong(param.substring(1));
                BioPeopleInterest peopleInterest = peopleInterestService.getAllDataByPK(educationId);
                model.setId(peopleInterest.getId());
                model.setInterestId(peopleInterest.getInterestType().getId());
                model.setName(peopleInterest.getName());
                bioDataId = peopleInterest.getBiodata().getId();
            } else {
                isEdit = Boolean.FALSE;
            }
            listDrowDown();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void listDrowDown() throws Exception{
        //Interest Type
        listInterestType = interestTypeService.getAllData();
        for (InterestType interestType : listInterestType) {
            if(interestType.getName() != null){
                listInterestTypes.put(interestType.getName(), interestType.getId());
            }
        }
        MapUtil.sortByValue(listInterestTypes);
    }

    public void doSave() {
     
        BioPeopleInterest peopleInterest = getEntityFromViewModel(model);
        try {
            if (isEdit) {
                peopleInterestService.update(peopleInterest);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                peopleInterestService.save(peopleInterest);
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
    
    private BioPeopleInterest getEntityFromViewModel(BioPeopleInterestModel model) {
        BioPeopleInterest peopleInterest = new BioPeopleInterest();
        if (model.getId() != null) {
            peopleInterest.setId(model.getId());
        }
        peopleInterest.setBiodata(new BioData(bioDataId));
        peopleInterest.setInterestType(new InterestType(model.getInterestId()));
        peopleInterest.setName(model.getName());
        return peopleInterest;
    }
    
    public InterestTypeService getInterestTypeService() {
        return interestTypeService;
    }

    public void setInterestTypeService(InterestTypeService interestTypeService) {
        this.interestTypeService = interestTypeService;
    }
    
    
    public BioPeopleInterestService getPeopleInterestService() {
        return peopleInterestService;
    }

    public void setPeopleInterestService(BioPeopleInterestService peopleInterestService) {
        this.peopleInterestService = peopleInterestService;
    }

    public Long getBioDataId() {
        return bioDataId;
    }

    public void setBioDataId(Long bioDataId) {
        this.bioDataId = bioDataId;
    }

    public BioPeopleInterest getSelected() {
        return selected;
    }

    public void setSelected(BioPeopleInterest selected) {
        this.selected = selected;
    }

    public BioPeopleInterestModel getModel() {
        return model;
    }

    public void setModel(BioPeopleInterestModel model) {
        this.model = model;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    public Map<String, Long> getListInterestTypes() {
        return listInterestTypes;
    }

    public void setListInterestTypes(Map<String, Long> listInterestTypes) {
        this.listInterestTypes = listInterestTypes;
    }

    public List<InterestType> getListInterestType() {
        return listInterestType;
    }

    public void setListInterestType(List<InterestType> listInterestType) {
        this.listInterestType = listInterestType;
    }
    
    
}
