/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

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

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.AdmonitionType;
import com.inkubator.hrm.entity.CareerDisciplineType;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.PersonalDiscipline;
import com.inkubator.hrm.service.AdmonitionTypeService;
import com.inkubator.hrm.service.CareerDisciplineTypeService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.PersonalDisciplineService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.PersonalDisciplineModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "personalDisciplineFormController")
@ViewScoped
public class PersonalDisciplineFormController extends BaseController{
    private PersonalDisciplineModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{personalDisciplineService}")
    private PersonalDisciplineService personalDisciplineService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{admonitionTypeService}")
    private AdmonitionTypeService admonitionTypeService;
    @ManagedProperty(value = "#{careerDisciplineTypeService}")
    private CareerDisciplineTypeService careerDisciplineTypeService;
    
    //Dropdown
    private Map<String, Long> dropDownAdmonitionType = new TreeMap<String, Long>();;
    private List<AdmonitionType> listAdmonitionType = new ArrayList<>();
    
    //dropdown discipline type
    private List<CareerDisciplineType> disciplineList = new ArrayList<CareerDisciplineType>();
    private Map<String, Long> dropDownDisciplineType = new TreeMap<String, Long>();
    
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            model = new PersonalDisciplineModel();
            isUpdate = Boolean.FALSE;
            String personalDisciplineId = FacesUtil.getRequestParameter("personalDisciplineId");

            if (StringUtils.isNotEmpty(personalDisciplineId)) {
                PersonalDiscipline personalDiscipline = personalDisciplineService.getEntityByPkWithAllRelation(Long.parseLong(personalDisciplineId));
                if (personalDisciplineId != null) {
                    model = getModelFromEntity(personalDiscipline);
                    isUpdate = Boolean.TRUE;
                }
            }
            doSelectOneMenuCareerDisciplineType();
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        personalDisciplineService = null;
        isUpdate = null;
        model = new PersonalDisciplineModel();
        admonitionTypeService = null;
        empDataService = null;  
        dropDownAdmonitionType = null;
        listAdmonitionType = null;
        dropDownDisciplineType = null;
    }
    
    public void listDrowDown() throws Exception{
        //admonition type 
        listAdmonitionType = admonitionTypeService.getAllData();
        for (AdmonitionType admonitionType : listAdmonitionType) {
            dropDownAdmonitionType.put(admonitionType.getName(), admonitionType.getId());
        }


        MapUtil.sortByValue(dropDownAdmonitionType);
    }
    
    public void doSelectOneMenuCareerDisciplineType() throws Exception{
    	disciplineList = careerDisciplineTypeService.getAllData();
    	for(CareerDisciplineType careerDiscipline : disciplineList){
    		dropDownDisciplineType.put(careerDiscipline.getName(), careerDiscipline.getId());
    	}
    }
    
    
    
    private PersonalDisciplineModel getModelFromEntity(PersonalDiscipline entity) {
        PersonalDisciplineModel model = new PersonalDisciplineModel();
        model.setId(entity.getId());
        if(entity.getCareerDisciplineType()!= null){
            model.setCareerDisciplineTypeId(entity.getCareerDisciplineType().getId());
        }
        model.setDescription(entity.getDescription());
        model.setExpireDate(entity.getExpiredDate());
        model.setStartDate(entity.getStartDate());
        model.setCareerDisciplineTypeId(entity.getCareerDisciplineType().getId());
//        model.setNikWithFullName(entity.getEmpData().getNikWithFullName());
        model.setEmpData(entity.getEmpData());
        return model;
    }
    
    public List<EmpData> doAutoCompletEmployee(String param){
		List<EmpData> empDatas = new ArrayList<EmpData>();
		try {
			empDatas = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(param), HrmUserInfoUtil.getCompanyId());
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
		return empDatas;
	}
    
    public void doSave() throws Exception {
        PersonalDiscipline  personalDiscipline = getEntityFromViewModel(model);
    
        try {
            if (isUpdate) {
                personalDisciplineService.update(personalDiscipline);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            
            } else {
                personalDisciplineService.save(personalDiscipline);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
           
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private PersonalDiscipline getEntityFromViewModel(PersonalDisciplineModel model) throws Exception {
    	
        PersonalDiscipline personalDiscipline = new PersonalDiscipline();
        if (model.getId() != null) {
            personalDiscipline.setId(model.getId());
        }
//        EmpData selectedEmployee = empDataService.getEntityByNik(StringUtils.substringBefore(model.getNikWithFullName(), " - "));
        personalDiscipline.setEmpData(model.getEmpData());
        personalDiscipline.setDescription(model.getDescription());
        personalDiscipline.setStartDate(model.getStartDate());
        personalDiscipline.setExpiredDate(model.getExpireDate());
//        personalDiscipline.setAdmonitionType(new AdmonitionType(model.getAdmonitionType()));
        personalDiscipline.setCareerDisciplineType(new CareerDisciplineType(model.getCareerDisciplineTypeId()));
       
        return personalDiscipline;
    }

    public PersonalDisciplineModel getModel() {
        return model;
    }

    public void setModel(PersonalDisciplineModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public PersonalDisciplineService getPersonalDisciplineService() {
        return personalDisciplineService;
    }

    public void setPersonalDisciplineService(PersonalDisciplineService personalDisciplineService) {
        this.personalDisciplineService = personalDisciplineService;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public AdmonitionTypeService getAdmonitionTypeService() {
        return admonitionTypeService;
    }

    public void setAdmonitionTypeService(AdmonitionTypeService admonitionTypeService) {
        this.admonitionTypeService = admonitionTypeService;
    }

    public Map<String, Long> getDropDownAdmonitionType() {
        return dropDownAdmonitionType;
    }

    public void setDropDownAdmonitionType(Map<String, Long> dropDownAdmonitionType) {
        this.dropDownAdmonitionType = dropDownAdmonitionType;
    }

    public List<AdmonitionType> getListAdmonitionType() {
        return listAdmonitionType;
    }

    public void setListAdmonitionType(List<AdmonitionType> listAdmonitionType) {
        this.listAdmonitionType = listAdmonitionType;
    }

	public CareerDisciplineTypeService getCareerDisciplineTypeService() {
		return careerDisciplineTypeService;
	}

	public void setCareerDisciplineTypeService(CareerDisciplineTypeService careerDisciplineTypeService) {
		this.careerDisciplineTypeService = careerDisciplineTypeService;
	}

	public List<CareerDisciplineType> getDisciplineList() {
		return disciplineList;
	}

	public void setDisciplineList(List<CareerDisciplineType> disciplineList) {
		this.disciplineList = disciplineList;
	}

	public Map<String, Long> getDropDownDisciplineType() {
		return dropDownDisciplineType;
	}

	public void setDropDownDisciplineType(Map<String, Long> dropDownDisciplineType) {
		this.dropDownDisciplineType = dropDownDisciplineType;
	}
    
    
}