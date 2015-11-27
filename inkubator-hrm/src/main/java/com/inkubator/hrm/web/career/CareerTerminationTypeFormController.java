package com.inkubator.hrm.web.career;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.CareerTerminationType;
import com.inkubator.hrm.entity.SystemCareerConst;
import com.inkubator.hrm.entity.SystemLetterReference;
import com.inkubator.hrm.service.CareerTerminationTypeService;
import com.inkubator.hrm.service.SystemCareerConstService;
import com.inkubator.hrm.service.SystemLetterReferenceService;
import com.inkubator.hrm.web.model.CareerTerminationTypeModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
@ManagedBean(name = "careerTerminationTypeFormController")
@ViewScoped
public class CareerTerminationTypeFormController extends BaseController {

    private CareerTerminationTypeModel careerTerminationTypeModel;
    private Boolean isUpdate = Boolean.FALSE;
    @ManagedProperty(value = "#{careerTerminationTypeService}")
    private CareerTerminationTypeService careerTerminationTypeService;
    private Map<String, Long> letterRefferencesMap = new TreeMap<>();
    private Map<String, Long> systemCareerConstantsMap = new TreeMap<>();
    @ManagedProperty(value = "#{systemLetterReferenceService}")
    private SystemLetterReferenceService systemLetterReferenceService;
    @ManagedProperty(value = "#{systemCareerConstService}")
    private SystemCareerConstService systemCareerConstService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            careerTerminationTypeModel = new CareerTerminationTypeModel();
            
            //Inisialisasi Map SystemLetterReference
            List<SystemLetterReference> listSystemReferense = systemLetterReferenceService.getAllData();
            listSystemReferense.stream().forEach(systemReference -> letterRefferencesMap.put(systemReference.getName(), systemReference.getId()));
            
            //Inisialisasi Map SystemCareerConst, Filter hanya yang isWork = 0
            List<SystemCareerConst> listCareerConst = systemCareerConstService.getAllData();
            List<SystemCareerConst> listCareerConstWithIsWorkIsFalse = listCareerConst.stream().filter(carrerConst -> carrerConst.getIsWork() == Boolean.FALSE).collect(Collectors.toList());
            listCareerConstWithIsWorkIsFalse.stream().forEach(careerConst -> systemCareerConstantsMap.put(careerConst.getName(), careerConst.getId()));
            
            String param = FacesUtil.getRequestParameter("param");
            if (StringUtils.isNotBlank(param) && StringUtils.isNumeric(param)) {
                CareerTerminationType careerTerminationType = careerTerminationTypeService.getEntityWithDetailById(Long.parseLong(param));
                if (careerTerminationType != null) {
                    careerTerminationTypeModel = getModelFromEntity(careerTerminationType);
                    isUpdate = Boolean.TRUE;
                }

            }
        } catch (Exception ex) {
            Logger.getLogger(CareerTerminationTypeFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        careerTerminationTypeService = null;
        isUpdate = null;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void doSave() {
    	CareerTerminationType careerTerminationType = getEntityFromViewModel(careerTerminationTypeModel);
        try {
            if (isUpdate) {
                careerTerminationTypeService.update(careerTerminationType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                careerTerminationTypeService.save(careerTerminationType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private CareerTerminationType getEntityFromViewModel(CareerTerminationTypeModel careerTerminationTypeModel) {
    	CareerTerminationType careerTerminationType = new CareerTerminationType();
        if (careerTerminationTypeModel.getId() != null) {
            careerTerminationType.setId(careerTerminationTypeModel.getId());
        }
        careerTerminationType.setCode(careerTerminationTypeModel.getCode());
        careerTerminationType.setName(careerTerminationTypeModel.getName());
        careerTerminationType.setDescription(careerTerminationTypeModel.getDescription());
        careerTerminationType.setSystemLetterReference(new SystemLetterReference(careerTerminationTypeModel.getReffLetterId()));
        careerTerminationType.setSystemCareerConst(new SystemCareerConst(careerTerminationTypeModel.getSystemCareerConstId()));
        return careerTerminationType;
    }
    
    private CareerTerminationTypeModel getModelFromEntity(CareerTerminationType careerTerminationType){
    	CareerTerminationTypeModel careerTerminationTypeModel = new CareerTerminationTypeModel();
    	careerTerminationTypeModel.setId(careerTerminationType.getId());
        careerTerminationTypeModel.setCode(careerTerminationType.getCode());
        careerTerminationTypeModel.setName(careerTerminationType.getName());
        careerTerminationTypeModel.setDescription(careerTerminationType.getDescription());
        careerTerminationTypeModel.setReffLetterId(careerTerminationType.getSystemLetterReference().getId());
        careerTerminationTypeModel.setSystemCareerConstId(careerTerminationType.getSystemCareerConst().getId());
    	return careerTerminationTypeModel;
    }

    public Map<String, Long> getLetterRefferences() {
        return letterRefferencesMap;
    }

    public void setLetterRefferences(Map<String, Long> letterRefferences) {
        this.letterRefferencesMap = letterRefferences;
    }

    public void setSystemLetterReferenceService(SystemLetterReferenceService systemLetterReferenceService) {
        this.systemLetterReferenceService = systemLetterReferenceService;
    }

	public CareerTerminationTypeModel getCareerTerminationTypeModel() {
		return careerTerminationTypeModel;
	}

	public void setCareerTerminationTypeModel(CareerTerminationTypeModel careerTerminationTypeModel) {
		this.careerTerminationTypeModel = careerTerminationTypeModel;
	}

	public Map<String, Long> getLetterRefferencesMap() {
		return letterRefferencesMap;
	}

	public void setLetterRefferencesMap(Map<String, Long> letterRefferencesMap) {
		this.letterRefferencesMap = letterRefferencesMap;
	}

	public Map<String, Long> getSystemCareerConstantsMap() {
		return systemCareerConstantsMap;
	}

	public void setSystemCareerConstantsMap(Map<String, Long> systemCareerConstantsMap) {
		this.systemCareerConstantsMap = systemCareerConstantsMap;
	}

	public SystemLetterReferenceService getSystemLetterReferenceService() {
		return systemLetterReferenceService;
	}

	public void setCareerTerminationTypeService(CareerTerminationTypeService careerTerminationTypeService) {
		this.careerTerminationTypeService = careerTerminationTypeService;
	}

	public void setSystemCareerConstService(SystemCareerConstService systemCareerConstService) {
		this.systemCareerConstService = systemCareerConstService;
	}
    
    

}
