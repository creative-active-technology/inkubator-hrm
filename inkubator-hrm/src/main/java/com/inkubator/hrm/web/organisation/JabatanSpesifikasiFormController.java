/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.JabatanSpesifikasi;
import com.inkubator.hrm.entity.SpecificationAbility;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.JabatanSpesifikasiService;
import com.inkubator.hrm.service.SpecificationAbilityService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.JabatanSpesifikasiModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@ManagedBean(name = "jabatanSpesifikasiFormController")
@ViewScoped
public class JabatanSpesifikasiFormController extends BaseController{
    //service
    @ManagedProperty(value = "#{specificationAbilityService}")
    private SpecificationAbilityService specificationAbilityService;
    @ManagedProperty(value = "#{jabatanSpesifikasiService}")
    private JabatanSpesifikasiService service;
    @ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
    
    //list
    private List<SpecificationAbility> specAbility = new ArrayList<SpecificationAbility>();
    private Map<String, Long> listSpecAbility;
    private Map<String, String> listValue;
    
    private JabatanSpesifikasiModel model;
    private SpecificationAbility selectSpecAbility;
    private Boolean isEdit;
    private long jabatanId;
    
    public JabatanService getJabatanService() {
        return jabatanService;
    }

    public void setJabatanService(JabatanService jabatanService) {
        this.jabatanService = jabatanService;
    }
    
    public long getJabatanId() {
        return jabatanId;
    }

    public void setJabatanId(long jabatanId) {
        this.jabatanId = jabatanId;
    }
    
    
    public JabatanSpesifikasiService getService() {
        return service;
    }

    public void setService(JabatanSpesifikasiService service) {
        this.service = service;
    }
    
    
    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }
    
    

    public Map<String, String> getListValue() {
        return listValue;
    }

    public void setListValue(Map<String, String> listValue) {
        this.listValue = listValue;
    }
    
    public JabatanSpesifikasiModel getModel() {
        return model;
    }

    public void setModel(JabatanSpesifikasiModel model) {
        this.model = model;
    }
    
    public List<SpecificationAbility> getSpecAbility() {
        return specAbility;
    }

    public void setSpecAbility(List<SpecificationAbility> specAbility) {
        this.specAbility = specAbility;
    }

    public SpecificationAbilityService getSpecificationAbilityService() {
        return specificationAbilityService;
    }

    public void setSpecificationAbilityService(SpecificationAbilityService specificationAbilityService) {
        this.specificationAbilityService = specificationAbilityService;
    }

    public Map<String, Long> getListSpecAbility() {
        return listSpecAbility;
    }

    public void setListSpecAbility(Map<String, Long> listSpecAbility) {
        this.listSpecAbility = listSpecAbility;
    }
    
    @PostConstruct
    @Override
    public void initialization() {
        
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        model = new JabatanSpesifikasiModel();
        try {
            if (param.contains("i")) {
                jabatanId = Long.parseLong(param.substring(1));
                isEdit = Boolean.FALSE;
            }
            if(param.contains("e")){
                isEdit = Boolean.TRUE;
                long jobSpekId = Long.parseLong(param.substring(1));
                JabatanSpesifikasi jabatanSpesifikasi = service.getEntiyByPK(jobSpekId);
                model.setId(jabatanSpesifikasi.getId());
                model.setOptionAbility(jabatanSpesifikasi.getOptionAbility());
                model.setValue(jabatanSpesifikasi.getValue());
                model.setSpecId(jabatanSpesifikasi.getSpecificationAbility().getId());
                jabatanId = jabatanSpesifikasi.getJabatan().getId();
                //select one menu dropdown
                doChangeValue();
                
            }
            //get all specification list
            specAbility = specificationAbilityService.getAllData();
        } catch (Exception ex) {
            Logger.getLogger(JabatanSpesifikasiFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        listSpecAbility = new TreeMap<>();
        for (SpecificationAbility specificationAbility : specAbility) {
            listSpecAbility.put(specificationAbility.getName(), specificationAbility.getId());
        }
        MapUtil.sortByValue(listSpecAbility);
        
        
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
    
    @PreDestroy
    public void cleanAndExit() {
        specificationAbilityService = null;
        specAbility = null;
        listSpecAbility = null;
        model = null;
        listValue = null;
    }
    
    public void doSave() {
        JabatanSpesifikasi jabatanSpesifikasi = getEntityFromViewModel(model);
        try {
            if (isEdit) {
                service.update(jabatanSpesifikasi);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(jabatanSpesifikasi);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    private JabatanSpesifikasi getEntityFromViewModel(JabatanSpesifikasiModel model) {
        
        String optionAbility = "";
        JabatanSpesifikasi jabatanSpesifikasi = new JabatanSpesifikasi();
        if (model.getId() != null) {
            jabatanSpesifikasi.setId(model.getId());
        }
        jabatanSpesifikasi.setJabatan(new Jabatan(jabatanId));
        jabatanSpesifikasi.setSpecificationAbility(new SpecificationAbility(model.getSpecId()));
        jabatanSpesifikasi.setValue(model.getValue());
        for (Iterator it = listValue.entrySet().iterator(); it.hasNext(); ) {  
            Map.Entry e = (Map.Entry) it.next();   
            String value = e.getValue().toString();
            // now do something with key and value
            if(value.equals(model.getValue())){
                optionAbility = e.getKey().toString();
            }
        }
        jabatanSpesifikasi.setOptionAbility(optionAbility);
        
        return jabatanSpesifikasi;
    }
}
