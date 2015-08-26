/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioSpesifikasiAbility;
import com.inkubator.hrm.entity.BioSpesifikasiAbilityId;
import com.inkubator.hrm.entity.SpecificationAbility;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.service.BioSpesifikasiAbilityService;
import com.inkubator.hrm.service.SpecificationAbilityService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.BioSpesifikasiAbilityModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
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
@ManagedBean(name = "bioSpesifikasiAbilityController")
@ViewScoped
public class BioSpesifikasiAbilityFormController extends BaseController{
    @ManagedProperty(value = "#{bioSpesifikasiAbilityService}")
    private BioSpesifikasiAbilityService bioSpesifikasiAbilityService;
    @ManagedProperty(value = "#{bioDataService}")
    private BioDataService bioDataService;
    @ManagedProperty(value = "#{specificationAbilityService}")
    private SpecificationAbilityService specificationAbilityService;

    private Long bioDataId;
    private BioSpesifikasiAbility selected;
    private BioSpesifikasiAbilityModel model;
    private Boolean isEdit;
    private String isRevision;
    private String isEditOnRevision;
    
    //List Dropdown
    private List<SpecificationAbility> specAbility = new ArrayList<SpecificationAbility>();
    private Map<String, Long> listSpecAbility;
    private Map<String, String> listValue;
    private SpecificationAbility selectSpecAbility;

    @PreDestroy
    private void cleanAndExit() {
        bioSpesifikasiAbilityService = null;
        bioDataService = null;
        specificationAbilityService = null;
        bioDataId = null;
        selected = null;
        model = null;
        isEdit = null;
        specAbility = null;
        listSpecAbility = null;
        listValue = null;
        selectSpecAbility = null;
    }
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            String biodataId = FacesUtil.getRequestParameter("bioDataId");
            bioDataId = Long.valueOf(biodataId);
            model = new BioSpesifikasiAbilityModel();
            isEdit = Boolean.FALSE;
            
            //parameter is Revision untuk flag jika ini datangnya dari request perubahan biodata
            isRevision = FacesUtil.getRequestParameter("isRevision");
            if(StringUtils.isNotBlank(isRevision)){
            	isEditOnRevision = FacesUtil.getRequestParameter("isEditOnRevision");
            	if(StringUtils.equals(isEditOnRevision, "Yes")){
            		Map<String, Object> sessionMap = FacesUtil.getExternalContext().getSessionMap();
            		BioSpesifikasiAbility  bioSpesifikasiAbility = (BioSpesifikasiAbility ) sessionMap.get("selectedBioSpesifikasiAbility");
            		model = getModelFromEntity(bioSpesifikasiAbility);
            		doChangeValue();
            	}
            }else{
            	String bioSpecAbiId = FacesUtil.getRequestParameter("bioSpecAbiId");
                if (StringUtils.isNotEmpty(bioSpecAbiId)) {
                    BioSpesifikasiAbility bioSpesifikasiAbility = bioSpesifikasiAbilityService.getEntityByBioSpesifikasiAbilityId(new BioSpesifikasiAbilityId(bioDataId, Long.valueOf(bioSpecAbiId)));
                    if (bioSpesifikasiAbility != null) {
                        model = getModelFromEntity(bioSpesifikasiAbility);
                        isEdit = Boolean.TRUE;
                        bioDataId = bioSpesifikasiAbility.getBioData().getId();
                        doChangeValue();
                    }
                }
            }
            
            
            
        listSpecAbility = new TreeMap<>();
        //get all specification list
        specAbility = specificationAbilityService.getAllData();
        for (SpecificationAbility specificationAbility : specAbility) {
            listSpecAbility.put(specificationAbility.getName(), specificationAbility.getId());
        }
        MapUtil.sortByValue(listSpecAbility);
        
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    private BioSpesifikasiAbilityModel getModelFromEntity(BioSpesifikasiAbility entity) {
        BioSpesifikasiAbilityModel model = new BioSpesifikasiAbilityModel();
        model.setBioDataId(entity.getBioData().getId());
        model.setOldId(entity.getSpecificationAbility().getId());
        model.setOptionAbility(entity.getOptionAbility());
        model.setSpecId(entity.getSpecificationAbility().getId());
        model.setScore(entity.getValue());
        return model;
    }
    
    public void doChangeValue() throws Exception{
        selectSpecAbility = specificationAbilityService.getEntiyByPK(model.getSpecId());
        StringTokenizer st2 = new StringTokenizer(selectSpecAbility.getScaleValue(), "|");
        StringTokenizer st3 = new StringTokenizer(selectSpecAbility.getOptionAbility(), "|");
        listValue = new TreeMap<String, String>();
        while (st2.hasMoreElements() && st3.hasMoreElements()) {
            listValue.put(st3.nextElement().toString(), st2.nextElement().toString());
        }
        MapUtil.sortByValue(listValue);
    }

    private BioSpesifikasiAbility getEntityFromViewModel(BioSpesifikasiAbilityModel model) {
        BioSpesifikasiAbility bioSpesifikasiAbility = new BioSpesifikasiAbility();
        String optionAbility = "";
        if (model.getId() != null) {
//            bioSpesifikasiAbility.setId(model.getId());
        }
        
    	if(StringUtils.equals(isEditOnRevision, "Yes")){
    		bioSpesifikasiAbility.setId(new BioSpesifikasiAbilityId(bioDataId, model.getSpecId()));
    	}
        
        bioSpesifikasiAbility.setBioData(new BioData(bioDataId));
        bioSpesifikasiAbility.setValue(model.getScore());
        bioSpesifikasiAbility.setSpecificationAbility(new SpecificationAbility(model.getSpecId()));
        for (Iterator it = listValue.entrySet().iterator(); it.hasNext(); ) {  
            Map.Entry e = (Map.Entry) it.next();   
            String value = e.getValue().toString();
            // now do something with key and value
            if(value.equals(model.getScore())){
                optionAbility = e.getKey().toString();
            }
        }
        bioSpesifikasiAbility.setOptionAbility(optionAbility);
        return bioSpesifikasiAbility;
    }
    
    public void doSave() {
        BioSpesifikasiAbility bioSpesifikasiAbility = getEntityFromViewModel(model);
        try {
        	/** jika tidak blank, berarti datangnya dari proses revisi biodata, jangan langsung di save / update,
    	 	cukup di return kembali Object BioSpesifikasiAbility yang telah di add / edit untuk kemudian di proses kembali di form revisi, 
    	 	ini dikarenakan proses revisi menggunakan approval sehingga data yang telah di ubah
    	 	tidak langsung di persist ke table yang bersangkutan, melainkan di tampung dahulu di json pendingData (Approval Activity)*/
	    	if(StringUtils.isNotBlank(isRevision)){
	    		RequestContext.getCurrentInstance().closeDialog(bioSpesifikasiAbility);
	    	}else{
	    		if (isEdit) {
	                bioSpesifikasiAbilityService.updateBioSpecAbility(bioSpesifikasiAbility, model.getOldId());
	                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
	            } else {
	                bioSpesifikasiAbilityService.save(bioSpesifikasiAbility);
	                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
	            }
	    	}
            
            cleanAndExit();
        } catch (BussinessException ex) { 
         
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public SpecificationAbility getSelectSpecAbility() {
        return selectSpecAbility;
    }

    public void setSelectSpecAbility(SpecificationAbility selectSpecAbility) {
        this.selectSpecAbility = selectSpecAbility;
    }
    
    public BioSpesifikasiAbilityService getBioSpesifikasiAbilityService() {
        return bioSpesifikasiAbilityService;
    }

    public void setBioSpesifikasiAbilityService(BioSpesifikasiAbilityService bioSpesifikasiAbilityService) {
        this.bioSpesifikasiAbilityService = bioSpesifikasiAbilityService;
    }

    public BioDataService getBioDataService() {
        return bioDataService;
    }

    public void setBioDataService(BioDataService bioDataService) {
        this.bioDataService = bioDataService;
    }

    public SpecificationAbilityService getSpecificationAbilityService() {
        return specificationAbilityService;
    }

    public void setSpecificationAbilityService(SpecificationAbilityService specificationAbilityService) {
        this.specificationAbilityService = specificationAbilityService;
    }

    public Long getBioDataId() {
        return bioDataId;
    }

    public void setBioDataId(Long bioDataId) {
        this.bioDataId = bioDataId;
    }

    public BioSpesifikasiAbility getSelected() {
        return selected;
    }

    public void setSelected(BioSpesifikasiAbility selected) {
        this.selected = selected;
    }

    public BioSpesifikasiAbilityModel getModel() {
        return model;
    }

    public void setModel(BioSpesifikasiAbilityModel model) {
        this.model = model;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    public List<SpecificationAbility> getSpecAbility() {
        return specAbility;
    }

    public void setSpecAbility(List<SpecificationAbility> specAbility) {
        this.specAbility = specAbility;
    }

    public Map<String, Long> getListSpecAbility() {
        return listSpecAbility;
    }

    public void setListSpecAbility(Map<String, Long> listSpecAbility) {
        this.listSpecAbility = listSpecAbility;
    }

    public Map<String, String> getListValue() {
        return listValue;
    }

    public void setListValue(Map<String, String> listValue) {
        this.listValue = listValue;
    }

	public String getIsRevision() {
		return isRevision;
	}

	public void setIsRevision(String isRevision) {
		this.isRevision = isRevision;
	}
    
    
    
    
}
