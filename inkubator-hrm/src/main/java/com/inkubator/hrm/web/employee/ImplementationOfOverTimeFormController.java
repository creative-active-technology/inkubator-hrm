/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.ImplementationOfOverTime;
import com.inkubator.hrm.entity.WtOverTime;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.ImplementationOfOverTimeService;
import com.inkubator.hrm.service.WtOverTimeService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.ImplementationOfOverTimeModel;
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
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "implementationOfOverTimeFormController")
@ViewScoped
public class ImplementationOfOverTimeFormController extends BaseController {
    @ManagedProperty(value = "#{wtOverTimeService}")
    private WtOverTimeService wtOverTimeService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{implementationOfOverTimeService}")
    private ImplementationOfOverTimeService implementationOfOverTimeService;
    private Boolean isUpdate;
    private Map<String, Long> wtOverTimeDropDown = new TreeMap<String, Long>();
    private List<WtOverTime> listWtOverTime = new ArrayList<>();
    private ImplementationOfOverTimeModel model;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try{
            String implementOfOTId = FacesUtil.getRequestParameter("implementOfOTId");
            model = new ImplementationOfOverTimeModel();
            isUpdate = Boolean.FALSE;
            if (StringUtils.isNotEmpty(implementOfOTId)) {
                ImplementationOfOverTime implementationOfOverTime = implementationOfOverTimeService.getEntityByPkWithDetail(Long.parseLong(implementOfOTId));
                if(implementOfOTId != null){
                    model = getModelFromEntity(implementationOfOverTime);
                    isUpdate = Boolean.TRUE;
                }
            }
            
            listDrowDown();
        }catch (Exception e){
            LOGGER.error("Error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        listWtOverTime = null;
        wtOverTimeDropDown = null;
        isUpdate = null;
        wtOverTimeService = null;
        model = null;
        empDataService = null;
    }
    
    public void listDrowDown() throws Exception {
        //cost center
        listWtOverTime = wtOverTimeService.getAllData();
        for (WtOverTime wtOverTime : listWtOverTime) {
            wtOverTimeDropDown.put(wtOverTime.getName(), wtOverTime.getId());
        }
        MapUtil.sortByValue(wtOverTimeDropDown);
    }
    
    public List<EmpData> doAutoCompletEmployee(String param) {
        List<EmpData> empDatas = new ArrayList<EmpData>();
        try {
            empDatas = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(param));
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
        return empDatas;
    }
    
    private ImplementationOfOverTimeModel getModelFromEntity(ImplementationOfOverTime entity) {
        ImplementationOfOverTimeModel model = new ImplementationOfOverTimeModel();
        model.setId(entity.getId());
        model.setEmpDataId(entity.getEmpData().getId());
        if (entity.getEmpData() != null) {
            model.setEmpData(entity.getEmpData());
        }
        model.setWtOverTimeId(entity.getWtOverTime().getId());
        model.setStartTime(entity.getStartTime());
        model.setEndTime(entity.getEndTime());
        model.setImplementationDate(entity.getImplementationDate());
        model.setImplementationNumber(entity.getCode());
        return model;
    }

    private ImplementationOfOverTime getEntityFromViewModel(ImplementationOfOverTimeModel model) {
        ImplementationOfOverTime implementationOfOT = new ImplementationOfOverTime();
        if (model.getId() != null) {
            implementationOfOT.setId(model.getId());
        }
        implementationOfOT.setEmpData(model.getEmpData());
        implementationOfOT.setWtOverTime(new WtOverTime(model.getWtOverTimeId()));
        implementationOfOT.setStartTime(model.getStartTime());
        implementationOfOT.setEndTime(model.getEndTime());
        implementationOfOT.setImplementationDate(model.getImplementationDate());
        implementationOfOT.setCode(model.getImplementationNumber());
        return implementationOfOT;
    }
    
    public void doSave() {
        System.out.println("masuk dosave");
        ImplementationOfOverTime implementationOfOT = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                implementationOfOverTimeService.update(implementationOfOT);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                implementationOfOverTimeService.save(implementationOfOT);
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
    
    public WtOverTimeService getWtOverTimeService() {
        return wtOverTimeService;
    }

    public void setWtOverTimeService(WtOverTimeService wtOverTimeService) {
        this.wtOverTimeService = wtOverTimeService;
    }

    public ImplementationOfOverTimeService getImplementationOfOverTimeService() {
        return implementationOfOverTimeService;
    }

    public void setImplementationOfOverTimeService(ImplementationOfOverTimeService implementationOfOverTimeService) {
        this.implementationOfOverTimeService = implementationOfOverTimeService;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public Map<String, Long> getWtOverTimeDropDown() {
        return wtOverTimeDropDown;
    }

    public void setWtOverTimeDropDown(Map<String, Long> wtOverTimeDropDown) {
        this.wtOverTimeDropDown = wtOverTimeDropDown;
    }

    public List<WtOverTime> getListWtOverTime() {
        return listWtOverTime;
    }

    public void setListWtOverTime(List<WtOverTime> listWtOverTime) {
        this.listWtOverTime = listWtOverTime;
    }

    public ImplementationOfOverTimeModel getModel() {
        return model;
    }

    public void setModel(ImplementationOfOverTimeModel model) {
        this.model = model;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }
    
    
}
