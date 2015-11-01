/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.entity.OrgTypeOfSpec;
import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.entity.OrgTypeOfSpecListKlasifikasi;
import com.inkubator.hrm.entity.OrgTypeOfSpecListKlasifikasiId;
import com.inkubator.hrm.entity.ReimbursmentSchema;
import com.inkubator.hrm.entity.ReimbursmentSchemaEmployeeType;
import com.inkubator.hrm.service.KlasifikasiKerjaService;
import com.inkubator.hrm.service.OrgTypeOfSpecListService;
import com.inkubator.hrm.service.OrgTypeOfSpecService;
import com.inkubator.hrm.web.model.OrgTypeOfSpecListModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author EKA
 */
@ManagedBean(name = "orgTypeOfSpecListFormController")
@ViewScoped
public class OrgTypeOfSpecListFormController extends BaseController{
    @ManagedProperty(value = "#{orgTypeOfSpecListService}")
    private OrgTypeOfSpecListService service;
    private OrgTypeOfSpecList selected;
    private OrgTypeOfSpecListModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{orgTypeOfSpecService}")
    private OrgTypeOfSpecService orgTypeOfSpecService;
    @ManagedProperty(value = "#{klasifikasiKerjaService}")
    private KlasifikasiKerjaService klasifikasiKerjaService;
    
    private List<OrgTypeOfSpec> orgTypeOfSpec = new ArrayList<OrgTypeOfSpec>();
    private Map<String, Long> dropDownOrgTypeOfSpec = new TreeMap<String, Long>();
    
    private DualListModel<KlasifikasiKerja> dualListModel = new DualListModel<>();
    
    @PostConstruct
    @Override
    public void initialization(){
        super.initialization();
        try{
            String orgTypeOfSpecListId = FacesUtil.getRequestParameter("execution");
            model = new OrgTypeOfSpecListModel();
            isUpdate = Boolean.FALSE;
            OrgTypeOfSpecList orgTypeOfSpecList = new OrgTypeOfSpecList();
            if(StringUtils.isNotEmpty(orgTypeOfSpecListId)){
                orgTypeOfSpecList = service.getAllDataWithDetail(Long.parseLong(orgTypeOfSpecListId.substring(1)));
                if(orgTypeOfSpecList != null){
                	isUpdate = Boolean.TRUE;
                	model = getModelFromEntity(orgTypeOfSpecList);
                	doDualListModelKlasifikasiKerja(isUpdate, orgTypeOfSpecList);
                }
            }else{
            	isUpdate = Boolean.FALSE;
                doDualListModelKlasifikasiKerja(isUpdate, orgTypeOfSpecList);
            }
            
            doSelectOneMenuOrgTypeOfSpecList();
        } catch (Exception e){
            LOGGER.error("error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit(){
        isUpdate = null;
        model = null;
        service = null;
        selected = null;
        orgTypeOfSpecService = null;
        orgTypeOfSpec = null;
        dropDownOrgTypeOfSpec = null;
        dualListModel = null;
        klasifikasiKerjaService = null;
    }
    
    private OrgTypeOfSpecListModel getModelFromEntity(OrgTypeOfSpecList entity){
        OrgTypeOfSpecListModel model = new OrgTypeOfSpecListModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setOrgTypeOfSpecId(entity.getOrgTypeOfSpec().getId());
        model.setDescription(entity.getDescription());
        return model;
    }
    
    private OrgTypeOfSpecList getEntityFromViewModel(OrgTypeOfSpecListModel model){
        OrgTypeOfSpecList orgTypeofSpecList = new OrgTypeOfSpecList();
        if(model.getId() != null){
            orgTypeofSpecList.setId(model.getId());
        }
        orgTypeofSpecList.setCode(model.getCode());
        orgTypeofSpecList.setName(model.getName());
        orgTypeofSpecList.setOrgTypeOfSpec(new OrgTypeOfSpec(model.getOrgTypeOfSpecId()));
        orgTypeofSpecList.setDescription(model.getDescription());
        return orgTypeofSpecList;
    }
    
    /*public void doSave(){
        OrgTypeOfSpecList orgTypeOfSpecList = getEntityFromViewModel(model);
        try{
            if(isUpdate){
                service.update(orgTypeOfSpecList);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(orgTypeOfSpecList);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex){
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }*/
    
    public String doSave(){
    	OrgTypeOfSpecList orgTypeOfSpecList = getEntityFromViewModel(model);
    	if (isUpdate) {
            return doUpdate(orgTypeOfSpecList);
        } else {
        	orgTypeOfSpecList.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            return doInsert(orgTypeOfSpecList);
        }
    }
    
    private String doUpdate(OrgTypeOfSpecList orgTypeOfSpecList) {
        try {
            
        	Set<OrgTypeOfSpecListKlasifikasi> dataToSave = new HashSet<>(0);
        	List<KlasifikasiKerja> listSelectedKlasifikasiKerja = dualListModel.getTarget();
        	for (KlasifikasiKerja klasifikasiKerja : listSelectedKlasifikasiKerja) {
            	OrgTypeOfSpecListKlasifikasi orgTypeOfSpecListKlasifikasi = new OrgTypeOfSpecListKlasifikasi();
            	orgTypeOfSpecListKlasifikasi.setId(new OrgTypeOfSpecListKlasifikasiId(orgTypeOfSpecList.getId(), klasifikasiKerja.getId()));
            	orgTypeOfSpecListKlasifikasi.setOrgTypeOfSpecList(orgTypeOfSpecList);
            	orgTypeOfSpecListKlasifikasi.setKlasifikasiKerja(klasifikasiKerja);
            	orgTypeOfSpecListKlasifikasi.setCreatedBy(UserInfoUtil.getUserName());
            	orgTypeOfSpecListKlasifikasi.setCreatedOn(new Date());
                dataToSave.add(orgTypeOfSpecListKlasifikasi);
            }
        	orgTypeOfSpecList.setOrgTypeOfSpecListKlasifikasis(dataToSave);
            service.update(orgTypeOfSpecList);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                return "/protected/organisation/org_typespec_list_detail.htm?faces-redirect=true&execution=e" + orgTypeOfSpecList.getId();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }
    
    private String doInsert(OrgTypeOfSpecList orgTypeOfSpecList) {
        try {
                Set<OrgTypeOfSpecListKlasifikasi> dataToSave = new HashSet<>(0);
                List<KlasifikasiKerja> listSelectedKlasifikasiKerja = dualListModel.getTarget();
                for (KlasifikasiKerja klasifikasiKerja : listSelectedKlasifikasiKerja) {
                	OrgTypeOfSpecListKlasifikasi orgTypeOfSpecListKlasifikasi = new OrgTypeOfSpecListKlasifikasi();
                	orgTypeOfSpecListKlasifikasi.setId(new OrgTypeOfSpecListKlasifikasiId(orgTypeOfSpecList.getId(), klasifikasiKerja.getId()));
                	orgTypeOfSpecListKlasifikasi.setOrgTypeOfSpecList(orgTypeOfSpecList);
                	orgTypeOfSpecListKlasifikasi.setKlasifikasiKerja(klasifikasiKerja);
                	orgTypeOfSpecListKlasifikasi.setCreatedBy(UserInfoUtil.getUserName());
                	orgTypeOfSpecListKlasifikasi.setCreatedOn(new Date());
                    dataToSave.add(orgTypeOfSpecListKlasifikasi);
                }
                orgTypeOfSpecList.setOrgTypeOfSpecListKlasifikasis(dataToSave);
                service.save(orgTypeOfSpecList);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                return "/protected/organisation/org_typespec_list_detail.htm?faces-redirect=true&execution=e" + orgTypeOfSpecList.getId();
                

            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    public OrgTypeOfSpecListService getService() {
        return service;
    }

    public void setService(OrgTypeOfSpecListService service) {
        this.service = service;
    }

    public OrgTypeOfSpecList getSelected() {
        return selected;
    }

    public void setSelected(OrgTypeOfSpecList selected) {
        this.selected = selected;
    }

    public OrgTypeOfSpecListModel getModel() {
        return model;
    }

    public void setModel(OrgTypeOfSpecListModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public OrgTypeOfSpecService getOrgTypeOfSpecService() {
        return orgTypeOfSpecService;
    }

    public void setOrgTypeOfSpecService(OrgTypeOfSpecService orgTypeOfSpecService) {
        this.orgTypeOfSpecService = orgTypeOfSpecService;
    }

    public List<OrgTypeOfSpec> getOrgTypeOfSpec() {
        return orgTypeOfSpec;
    }

    public void setOrgTypeOfSpec(List<OrgTypeOfSpec> orgTypeOfSpec) {
        this.orgTypeOfSpec = orgTypeOfSpec;
    }

    public Map<String, Long> getDropDownOrgTypeOfSpec() {
        return dropDownOrgTypeOfSpec;
    }

    public void setDropDownOrgTypeOfSpec(Map<String, Long> dropDownOrgTypeOfSpec) {
        this.dropDownOrgTypeOfSpec = dropDownOrgTypeOfSpec;
    }
    
    //method for add selection dropdown
    public void doSelectOneMenuOrgTypeOfSpecList() throws Exception{
       orgTypeOfSpec = orgTypeOfSpecService.getAllData();
     
       for(OrgTypeOfSpec orgTypeOfSpecs : orgTypeOfSpec){
           dropDownOrgTypeOfSpec.put(orgTypeOfSpecs.getName(), orgTypeOfSpecs.getId());
       }
    }
    
    //method for add list selection for dualListModel
    public void doDualListModelKlasifikasiKerja(Boolean isUpdate, OrgTypeOfSpecList orgTypeOfSpecList) throws Exception{
    	List<KlasifikasiKerja> sourceKlasifikasiKerja = klasifikasiKerjaService.getAllData();
    	if(isUpdate){
    		List<KlasifikasiKerja> selectedListKlasifikasiKerja = orgTypeOfSpecList.getKlasifikasiKerja();
    		sourceKlasifikasiKerja.removeAll(selectedListKlasifikasiKerja);
    		dualListModel = new DualListModel<>(sourceKlasifikasiKerja, selectedListKlasifikasiKerja);
    	}else{
    		dualListModel.setSource(sourceKlasifikasiKerja);
    	}
    }

	public DualListModel<KlasifikasiKerja> getDualListModel() {
		return dualListModel;
	}

	public void setDualListModel(DualListModel<KlasifikasiKerja> dualListModel) {
		this.dualListModel = dualListModel;
	}

	public KlasifikasiKerjaService getKlasifikasiKerjaService() {
		return klasifikasiKerjaService;
	}

	public void setKlasifikasiKerjaService(
			KlasifikasiKerjaService klasifikasiKerjaService) {
		this.klasifikasiKerjaService = klasifikasiKerjaService;
	}
    
	public String doBack(){
		return "/protected/organisation/org_typespec_list_view.htm?faces-redirect=true";
	}
    
}
