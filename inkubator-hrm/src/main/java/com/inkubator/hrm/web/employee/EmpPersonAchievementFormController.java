/*
\ * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;

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
import com.inkubator.hrm.entity.CareerAwardType;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.EmpPersonAchievement;
import com.inkubator.hrm.service.CareerAwardTypeService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.EmpPersonAchievementService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.EmpPersonAchievementModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "empPersonAchievementFormController")
@ViewScoped
public class EmpPersonAchievementFormController extends BaseController {

    private EmpPersonAchievementModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{empPersonAchievementService}")
    private EmpPersonAchievementService empPersonAchievementService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{careerAwardTypeService}")
    private CareerAwardTypeService careerAwardTypeService;
    
    private List<CareerAwardType> awardList = new ArrayList<CareerAwardType>();
    private Map<String, Long> dropDownAwardList = new TreeMap<String, Long>();
    

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            model = new EmpPersonAchievementModel();
            isUpdate = Boolean.FALSE;
            String empPersonId = FacesUtil.getRequestParameter("empPersonId");

            if (StringUtils.isNotEmpty(empPersonId)) {
                EmpPersonAchievement empPersonAchievement = empPersonAchievementService.getEntityByPkWithEmployee(Long.parseLong(empPersonId));
                if (empPersonId != null) {
                    model = getModelFromEntity(empPersonAchievement);
                    isUpdate = Boolean.TRUE;
                }
            }
            doSelectOneMenuAwardList();
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        empPersonAchievementService = null;
        isUpdate = null;
        model = new EmpPersonAchievementModel();
        empDataService = null;
    }

    private EmpPersonAchievementModel getModelFromEntity(EmpPersonAchievement entity) {
        EmpPersonAchievementModel achievementModel = new EmpPersonAchievementModel();
        achievementModel.setId(entity.getId());
        achievementModel.setNikWithFullName(entity.getEmpData().getNikWithFullName());
        achievementModel.setDescription(entity.getDescription());
        achievementModel.setDateAchievement(entity.getDateAchievement());
        achievementModel.setEmpData(entity.getEmpData());
        achievementModel.setCareerAwardTypeId(entity.getCareerAwardType().getId());
        return achievementModel;
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
        EmpPersonAchievement empPersonAchievement = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                empPersonAchievementService.update(empPersonAchievement);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                empPersonAchievementService.save(empPersonAchievement);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private EmpPersonAchievement getEntityFromViewModel(EmpPersonAchievementModel model) throws Exception {
        EmpPersonAchievement personAchievement = new EmpPersonAchievement();
        if (model.getId() != null) {
            personAchievement.setId(model.getId());
        }
        personAchievement.setEmpData(model.getEmpData());
        personAchievement.setDateAchievement(model.getDateAchievement());
        personAchievement.setDescription(model.getDescription());
        personAchievement.setCareerAwardType(new CareerAwardType(model.getCareerAwardTypeId()));
//        EmpData selectedEmployee = empDataService.getEntityByNik(StringUtils.substringBefore(model.getNikWithFullName(), " - "));
//        personAchievement.setEmpData(new EmpData(selectedEmployee.getId()));
        return personAchievement;
    }
    
    public void doSelectOneMenuAwardList() throws Exception{
        awardList = careerAwardTypeService.getAllData();
    
        for(CareerAwardType careerAwardType : awardList){
            dropDownAwardList.put(careerAwardType.getName(), careerAwardType.getId());
       
        }
    }

    public EmpPersonAchievementModel getModel() {
        return model;
    }

    public void setModel(EmpPersonAchievementModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public EmpPersonAchievementService getEmpPersonAchievementService() {
        return empPersonAchievementService;
    }

    public void setEmpPersonAchievementService(EmpPersonAchievementService empPersonAchievementService) {
        this.empPersonAchievementService = empPersonAchievementService;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

	public List<CareerAwardType> getAwardList() {
		return awardList;
	}

	public void setAwardList(List<CareerAwardType> awardList) {
		this.awardList = awardList;
	}

	public Map<String, Long> getDropDownAwardList() {
		return dropDownAwardList;
	}

	public void setDropDownAwardList(Map<String, Long> dropDownAwardList) {
		this.dropDownAwardList = dropDownAwardList;
	}

	public CareerAwardTypeService getCareerAwardTypeService() {
		return careerAwardTypeService;
	}

	public void setCareerAwardTypeService(CareerAwardTypeService careerAwardTypeService) {
		this.careerAwardTypeService = careerAwardTypeService;
	}

	
}
