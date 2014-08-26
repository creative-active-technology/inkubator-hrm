/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.CostCenter;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.TravelComponent;
import com.inkubator.hrm.entity.TravelComponentCostRate;
import com.inkubator.hrm.entity.TravelZone;
import com.inkubator.hrm.service.CostCenterService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.TravelComponentCostRateService;
import com.inkubator.hrm.service.TravelComponentService;
import com.inkubator.hrm.service.TravelZoneService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.TravelComponentCostRateModel;
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
@ManagedBean(name = "travelComponentCostRateFormController")
@ViewScoped
public class TravelComponentCostRateFormController extends BaseController{
    private TravelComponentCostRateModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{travelComponentCostRateService}")
    private TravelComponentCostRateService travelComponentCostRateService;
    @ManagedProperty(value = "#{costCenterService}")
    private CostCenterService costCenterService;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
    @ManagedProperty(value = "#{travelComponentService}")
    private TravelComponentService travelComponentService;
    @ManagedProperty(value = "#{travelZoneService}")
    private TravelZoneService travelZoneService;
    
    //Dropdown
    private Map<String, Long> dropDownCostCenter = new TreeMap<String, Long>();;
    private List<CostCenter> listCostCenter = new ArrayList<>();
    
    private Map<String, Long> dropDownGolonganJabatan = new TreeMap<String, Long>();;
    private List<GolonganJabatan> listGolonganJabatan = new ArrayList<>();
    
    private Map<String, Long> dropDownTravelComponent = new TreeMap<String, Long>();;
    private List<TravelComponent> listTravelComponent = new ArrayList<>();
    
    private Map<String, Long> dropDownTravelZone = new TreeMap<String, Long>();;
    private List<TravelZone> listTravelZone = new ArrayList<>();
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            model = new TravelComponentCostRateModel();
            isUpdate = Boolean.FALSE;
            String travelComponentCostRateId = FacesUtil.getRequestParameter("travelComponentCostRateId");

            if (StringUtils.isNotEmpty(travelComponentCostRateId)) {
                TravelComponentCostRate travelComponentCostRate = travelComponentCostRateService.getEntityByPkWithAllRelation(Long.parseLong(travelComponentCostRateId));
                if (travelComponentCostRateId != null) {
                    model = getModelFromEntity(travelComponentCostRate);
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
        travelComponentCostRateService = null;
        isUpdate = null;
        model = null;
        listTravelZone = null;
        dropDownTravelZone = null;  
        listTravelComponent = null;
        dropDownTravelComponent = null;
        listGolonganJabatan = null;
        dropDownGolonganJabatan = null;
        listCostCenter = null;
        dropDownCostCenter = null;
        travelComponentService = null;
        travelZoneService = null;
        golonganJabatanService = null;
        costCenterService = null;
    }
    
    public void listDrowDown() throws Exception{
        //cost center
        listCostCenter = costCenterService.getAllData();
        for (CostCenter costCenter : listCostCenter) {
            dropDownCostCenter.put(costCenter.getName(), costCenter.getId());
        }
        
        //golongan jabatan
        listGolonganJabatan = golonganJabatanService.getAllData();
        for (GolonganJabatan golonganJabatan : listGolonganJabatan) {
            dropDownGolonganJabatan.put(golonganJabatan.getCode(), golonganJabatan.getId());
        }
        //travel component
        listTravelComponent = travelComponentService.getAllData();
        for (TravelComponent travelComponent : listTravelComponent) {
            dropDownTravelComponent.put(travelComponent.getName(), travelComponent.getId());
        }
        //travel zone
        listTravelZone = travelZoneService.getAllData();
        for (TravelZone travelZone : listTravelZone) {
            dropDownTravelZone.put(travelZone.getName(), travelZone.getId());
        }
        
        MapUtil.sortByValue(dropDownCostCenter);
        MapUtil.sortByValue(dropDownGolonganJabatan);
        MapUtil.sortByValue(dropDownTravelComponent);
        MapUtil.sortByValue(dropDownTravelZone);
    }
    
    
    
    private TravelComponentCostRateModel getModelFromEntity(TravelComponentCostRate entity) {
        TravelComponentCostRateModel travelComponentCostRateModel = new TravelComponentCostRateModel();
        travelComponentCostRateModel.setId(entity.getId());
        travelComponentCostRateModel.setCode(entity.getCode());
        if(entity.getCostCenter() != null){
            travelComponentCostRateModel.setCostCenterId(entity.getCostCenter().getId());
        }
        travelComponentCostRateModel.setDefaultRateId(entity.getDefaultRate());
        travelComponentCostRateModel.setDescriptionId(entity.getDescription());
        if(entity.getGolonganJabatan() != null){
            travelComponentCostRateModel.setGolonganJabatanId(entity.getGolonganJabatan().getId());
        }
        if(entity.getTravelComponent() != null){
            travelComponentCostRateModel.setTravelComponentId(entity.getTravelComponent().getId());
        }
        if(entity.getTravelZone() != null){
            travelComponentCostRateModel.setTravelZoneId(entity.getTravelZone().getId());
        }
        return travelComponentCostRateModel;
    }

    
    public void doSave() throws Exception {
        TravelComponentCostRate  travelComponentCostRate = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                travelComponentCostRateService.update(travelComponentCostRate);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                travelComponentCostRateService.save(travelComponentCostRate);
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

    private TravelComponentCostRate getEntityFromViewModel(TravelComponentCostRateModel model) throws Exception {
        TravelComponentCostRate travelComponentCostRate = new TravelComponentCostRate();
        if (model.getId() != null) {
            travelComponentCostRate.setId(model.getId());
        }
        travelComponentCostRate.setCode(model.getCode());
        travelComponentCostRate.setCostCenter(new CostCenter(model.getCostCenterId()));
        travelComponentCostRate.setDefaultRate(model.getDefaultRateId());
        travelComponentCostRate.setDescription(model.getDescriptionId());
        travelComponentCostRate.setGolonganJabatan(new GolonganJabatan(model.getGolonganJabatanId()));
        travelComponentCostRate.setTravelComponent(new TravelComponent(model.getTravelComponentId()));
        travelComponentCostRate.setTravelZone(new TravelZone(model.getTravelZoneId()));
        return travelComponentCostRate;
    }

    public TravelComponentCostRateModel getModel() {
        return model;
    }

    public void setModel(TravelComponentCostRateModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public TravelComponentCostRateService getTravelComponentCostRateService() {
        return travelComponentCostRateService;
    }

    public void setTravelComponentCostRateService(TravelComponentCostRateService travelComponentCostRateService) {
        this.travelComponentCostRateService = travelComponentCostRateService;
    }

    public CostCenterService getCostCenterService() {
        return costCenterService;
    }

    public void setCostCenterService(CostCenterService costCenterService) {
        this.costCenterService = costCenterService;
    }

    public GolonganJabatanService getGolonganJabatanService() {
        return golonganJabatanService;
    }

    public void setGolonganJabatanService(GolonganJabatanService golonganJabatanService) {
        this.golonganJabatanService = golonganJabatanService;
    }

    public TravelComponentService getTravelComponentService() {
        return travelComponentService;
    }

    public void setTravelComponentService(TravelComponentService travelComponentService) {
        this.travelComponentService = travelComponentService;
    }

    public TravelZoneService getTravelZoneService() {
        return travelZoneService;
    }

    public void setTravelZoneService(TravelZoneService travelZoneService) {
        this.travelZoneService = travelZoneService;
    }

    public Map<String, Long> getDropDownCostCenter() {
        return dropDownCostCenter;
    }

    public void setDropDownCostCenter(Map<String, Long> dropDownCostCenter) {
        this.dropDownCostCenter = dropDownCostCenter;
    }

    public List<CostCenter> getListCostCenter() {
        return listCostCenter;
    }

    public void setListCostCenter(List<CostCenter> listCostCenter) {
        this.listCostCenter = listCostCenter;
    }

    public Map<String, Long> getDropDownGolonganJabatan() {
        return dropDownGolonganJabatan;
    }

    public void setDropDownGolonganJabatan(Map<String, Long> dropDownGolonganJabatan) {
        this.dropDownGolonganJabatan = dropDownGolonganJabatan;
    }

    public List<GolonganJabatan> getListGolonganJabatan() {
        return listGolonganJabatan;
    }

    public void setListGolonganJabatan(List<GolonganJabatan> listGolonganJabatan) {
        this.listGolonganJabatan = listGolonganJabatan;
    }

    public Map<String, Long> getDropDownTravelComponent() {
        return dropDownTravelComponent;
    }

    public void setDropDownTravelComponent(Map<String, Long> dropDownTravelComponent) {
        this.dropDownTravelComponent = dropDownTravelComponent;
    }

    public List<TravelComponent> getListTravelComponent() {
        return listTravelComponent;
    }

    public void setListTravelComponent(List<TravelComponent> listTravelComponent) {
        this.listTravelComponent = listTravelComponent;
    }

    public Map<String, Long> getDropDownTravelZone() {
        return dropDownTravelZone;
    }

    public void setDropDownTravelZone(Map<String, Long> dropDownTravelZone) {
        this.dropDownTravelZone = dropDownTravelZone;
    }

    public List<TravelZone> getListTravelZone() {
        return listTravelZone;
    }

    public void setListTravelZone(List<TravelZone> listTravelZone) {
        this.listTravelZone = listTravelZone;
    }
    
    
}
