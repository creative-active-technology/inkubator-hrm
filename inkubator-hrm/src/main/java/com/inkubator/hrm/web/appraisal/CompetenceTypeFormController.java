/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.appraisal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
import com.inkubator.hrm.entity.AppraisalCompetencyType;
import com.inkubator.hrm.entity.AppraisalCompetencyTypeGolJab;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.entity.OrgTypeOfSpec;
import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.entity.OrgTypeOfSpecListKlasifikasi;
import com.inkubator.hrm.entity.OrgTypeOfSpecListKlasifikasiId;
import com.inkubator.hrm.entity.ReimbursmentSchema;
import com.inkubator.hrm.entity.ReimbursmentSchemaEmployeeType;
import com.inkubator.hrm.service.AppraisalCompetencyTypeGolJabService;
import com.inkubator.hrm.service.AppraisalCompetencyTypeService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.KlasifikasiKerjaService;
import com.inkubator.hrm.service.OrgTypeOfSpecListService;
import com.inkubator.hrm.service.OrgTypeOfSpecService;
import com.inkubator.hrm.web.model.AppraisalCompetencyTypeModel;
import com.inkubator.hrm.web.model.OrgTypeOfSpecListModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "competenceTypeFormController")
@ViewScoped
public class CompetenceTypeFormController extends BaseController{
	
	@ManagedProperty(value = "#{appraisalCompetencyTypeGolJabService}")
    private AppraisalCompetencyTypeGolJabService appraisalCompetencyTypeGolJabService;
    @ManagedProperty(value = "#{appraisalCompetencyTypeService}")
    private AppraisalCompetencyTypeService appraisalCompetencyTypeService;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
    private AppraisalCompetencyType selected;
    private AppraisalCompetencyTypeModel model;
    private Boolean isUpdate = Boolean.FALSE;
    
    private Map<String, Long> mapGolJabatan = new TreeMap<String, Long>();
    private DualListModel<GolonganJabatan> dualListModelGolJabatan = new DualListModel<>();
    
    @PostConstruct
    @Override
    public void initialization(){
        super.initialization();
        try{
            String id = FacesUtil.getRequestParameter("execution");
            model = new AppraisalCompetencyTypeModel();
           
            if(StringUtils.isNotBlank(id)){
            	isUpdate = Boolean.TRUE;
            	AppraisalCompetencyType appraisalCompetencyType = appraisalCompetencyTypeService.getEntiyByPK(Long.parseLong(id.substring(1)));
                if(appraisalCompetencyType != null){
                	isUpdate = Boolean.TRUE;
                	model = getModelFromEntity(appraisalCompetencyType);
                }
            }
            
            doDualListModelKlasifikasiKerja(isUpdate, model);
            doSelectOneMenuOrgTypeOfSpecList();
        } catch (Exception e){
            LOGGER.error("error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit(){
		appraisalCompetencyTypeGolJabService = null;
		appraisalCompetencyTypeService = null;
		golonganJabatanService = null;
		isUpdate = null;
		model = null;
		selected = null;
		mapGolJabatan = null;
		dualListModelGolJabatan = null;
        
    }
    
    private AppraisalCompetencyTypeModel getModelFromEntity(AppraisalCompetencyType entity){
    	AppraisalCompetencyTypeModel model = new AppraisalCompetencyTypeModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        return model;
    }
    
    private AppraisalCompetencyType getEntityFromViewModel(AppraisalCompetencyTypeModel model){
    	AppraisalCompetencyType appraisalCompetencyType = new AppraisalCompetencyType();
        if(model.getId() != null){
            appraisalCompetencyType.setId(model.getId());
        }
        appraisalCompetencyType.setCode(model.getCode());
        appraisalCompetencyType.setName(model.getName());
        appraisalCompetencyType.setDescription(model.getDescription());
        return appraisalCompetencyType;
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
    	/*OrgTypeOfSpecList orgTypeOfSpecList = getEntityFromViewModel(model);
    	if (isUpdate) {
            return doUpdate(orgTypeOfSpecList);
        } else {
        	orgTypeOfSpecList.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            return doInsert(orgTypeOfSpecList);
        }*/
    	return null;
    }
    
    private String doUpdate(OrgTypeOfSpecList orgTypeOfSpecList) {
        /*try {
            
        	Set<OrgTypeOfSpecListKlasifikasi> dataToSave = new HashSet<>(0);
        	List<KlasifikasiKerja> listSelectedKlasifikasiKerja = dualListModelGolJabatan.getTarget();
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
        }*/
        return null;
    }
    
    private String doInsert(OrgTypeOfSpecList orgTypeOfSpecList) {
        /*try {
                Set<OrgTypeOfSpecListKlasifikasi> dataToSave = new HashSet<>(0);
                List<KlasifikasiKerja> listSelectedKlasifikasiKerja = dualListModelGolJabatan.getTarget();
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
        }*/
        return null;
    }

    

    public AppraisalCompetencyTypeModel getModel() {
        return model;
    }

    public void setModel(AppraisalCompetencyTypeModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public Map<String, Long> getDropDownOrgTypeOfSpec() {
        return mapGolJabatan;
    }

    public void setDropDownOrgTypeOfSpec(Map<String, Long> dropDownOrgTypeOfSpec) {
        this.mapGolJabatan = dropDownOrgTypeOfSpec;
    }
    
    public AppraisalCompetencyType getSelected() {
		return selected;
	}

	public void setSelected(AppraisalCompetencyType selected) {
		this.selected = selected;
	}

	//method for add selection dropdown
    public void doSelectOneMenuOrgTypeOfSpecList() throws Exception{
       /*orgTypeOfSpec = orgTypeOfSpecService.getAllData();
     
       for(OrgTypeOfSpec orgTypeOfSpecs : orgTypeOfSpec){
           mapGolJabatan.put(orgTypeOfSpecs.getName(), orgTypeOfSpecs.getId());
       }*/
    }
    
    //method for add list selection for dualListModel
    public void doDualListModelKlasifikasiKerja(Boolean isUpdate, AppraisalCompetencyTypeModel model) throws Exception{
    	 List<GolonganJabatan> listGolonganJabatan = golonganJabatanService.getAllData();
    	if(isUpdate){
    		List<AppraisalCompetencyTypeGolJab> listCompTypeGolJab = appraisalCompetencyTypeGolJabService.getListByAppraisalCompetenceTypeId(model.getId());
    		List<GolonganJabatan> listGolJabatanFromCompetencyType = listCompTypeGolJab.stream().map(AppraisalCompetencyTypeGolJab::getGolonganJabatan).collect(Collectors.toList());
    		listGolonganJabatan.removeAll(listGolJabatanFromCompetencyType);
    		dualListModelGolJabatan = new DualListModel<>(listGolonganJabatan, listGolJabatanFromCompetencyType);
    	}else{
    		dualListModelGolJabatan.setSource(listGolonganJabatan);
    	}
    }

    
	public String doBack(){
		return "/protected/organisation/org_typespec_list_view.htm?faces-redirect=true";
	}
    
}
