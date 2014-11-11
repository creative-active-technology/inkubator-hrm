/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.Termination;
import com.inkubator.hrm.entity.TerminationType;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.TerminationService;
import com.inkubator.hrm.service.TerminationTypeService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.TerminationModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
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

/**
 *
 * @author Deni
 */
@ManagedBean(name = "terminationFormController")
@ViewScoped
public class TerminationFormController extends BaseController{
    private TerminationModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{terminationService}")
    private TerminationService terminationService;
    @ManagedProperty(value = "#{terminationTypeService}")
    private TerminationTypeService terminationTypeService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    
    //Dropdown
    private Map<String, Long> dropDownTerminationType = new TreeMap<String, Long>();;
    private List<TerminationType> listTerminationType = new ArrayList<>();
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            model = new TerminationModel();
            isUpdate = Boolean.FALSE;
            String terminationId = FacesUtil.getRequestParameter("terminationId");

            if (StringUtils.isNotEmpty(terminationId)) {
                Termination termination = terminationService.getEntityByPkWithAllRelation(Long.parseLong(terminationId));
                if (terminationId != null) {
                    model = getModelFromEntity(termination);
                    isUpdate = Boolean.TRUE;
                }
            }
            listDrowDown();
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        isUpdate = null;
        model = null;
        terminationService = null;
        terminationTypeService = null;
        empDataService = null;
        dropDownTerminationType = null;
        listTerminationType = null;
    }
    
    public void listDrowDown() throws Exception{
        //cost center
        listTerminationType = terminationTypeService.getAllData();
        for (TerminationType terminationType : listTerminationType) {
            dropDownTerminationType.put(terminationType.getName(), terminationType.getId());
        }
        
        
        MapUtil.sortByValue(dropDownTerminationType);
    }
    
    
    
    private TerminationModel getModelFromEntity(Termination entity) {
        TerminationModel terminationModel = new TerminationModel();
        terminationModel.setId(entity.getId());
        terminationModel.setCode(entity.getCode());
        terminationModel.setEffectiveDate(entity.getEffectiveDate());
        terminationModel.setDescription(entity.getDescription());
        terminationModel.setEmpData(entity.getEmpData());
        terminationModel.setTerminationTypeId(entity.getTerminationType().getId());
        return terminationModel;
    }

    
    public void doSave() throws Exception {
        Termination termination = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                terminationService.update(termination);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                terminationService.save(termination);
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

    public List<EmpData> doAutoCompletEmployee(String param){
		List<EmpData> empDatas = new ArrayList<EmpData>();
		try {
			empDatas = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(param));
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
		return empDatas;
	}
    
    public List<String> completeEmployeeString(String query) {
            
        try {
            List<EmpData> allEmployee;
            allEmployee = empDataService.getAllDataWithRelation();
            List<String> queried = new ArrayList<String>();
            
            for (EmpData empData : allEmployee) {
                if (empData.getNik().toLowerCase().startsWith(query)  || empData.getNik().startsWith(query) && empData.getNik() != null ) {
                    queried.add(empData.getNikWithFullName());
                }
            }
            Set<String> setFullName = new HashSet<>(queried);
            List<String> listName = new ArrayList<>(setFullName);
            return listName;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TerminationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
       return null;
    }
    
    private Termination getEntityFromViewModel(TerminationModel model) throws Exception {
        Termination termination = new Termination();
        if (model.getId() != null) {
            termination.setId(model.getId());
        }
        termination.setCode(model.getCode());
        termination.setDescription(model.getDescription());
        termination.setEffectiveDate(model.getEffectiveDate());
        termination.setTerminationType(new TerminationType(model.getTerminationTypeId()));
        termination.setEmpData(model.getEmpData());
//        EmpData selectedEmployee = empDataService.getEntityByNik(StringUtils.substringBefore(model.getEmpDataWithNik(), " - "));
//        termination.setEmpData(new EmpData(selectedEmployee.getId()));
        return termination;
    }

    public TerminationModel getModel() {
        return model;
    }

    public void setModel(TerminationModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public TerminationService getTerminationService() {
        return terminationService;
    }

    public void setTerminationService(TerminationService terminationService) {
        this.terminationService = terminationService;
    }

    public TerminationTypeService getTerminationTypeService() {
        return terminationTypeService;
    }

    public void setTerminationTypeService(TerminationTypeService terminationTypeService) {
        this.terminationTypeService = terminationTypeService;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public Map<String, Long> getDropDownTerminationType() {
        return dropDownTerminationType;
    }

    public void setDropDownTerminationType(Map<String, Long> dropDownTerminationType) {
        this.dropDownTerminationType = dropDownTerminationType;
    }

    public List<TerminationType> getListTerminationType() {
        return listTerminationType;
    }

    public void setListTerminationType(List<TerminationType> listTerminationType) {
        this.listTerminationType = listTerminationType;
    }
    
    
}
