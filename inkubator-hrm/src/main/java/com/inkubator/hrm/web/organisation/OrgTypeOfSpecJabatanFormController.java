/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.OrgTypeOfSpec;
import com.inkubator.hrm.entity.OrgTypeOfSpecJabatan;
import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.OrgTypeOfSpecJabatanService;
import com.inkubator.hrm.service.OrgTypeOfSpecListService;
import com.inkubator.hrm.service.OrgTypeOfSpecService;
import com.inkubator.hrm.web.model.OrgTypeOfSpecJabatanModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
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
import org.primefaces.model.DualListModel;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "orgTypeOfSpecJabatanFormController")
@ViewScoped
public class OrgTypeOfSpecJabatanFormController extends BaseController {

    @ManagedProperty(value = "#{orgTypeOfSpecJabatanService}")
    private OrgTypeOfSpecJabatanService orgTypeOfSpecJabatanService;
    @ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
    @ManagedProperty(value = "#{orgTypeOfSpecService}")
    private OrgTypeOfSpecService orgTypeOfSpecService;
    @ManagedProperty(value = "#{orgTypeOfSpecListService}")
    private OrgTypeOfSpecListService orgTypeOfSpecListService;
    private OrgTypeOfSpecJabatanModel model;
    private Boolean isUpdate;
    private Map<String, Long> dropDownSpecList = new TreeMap<String, Long>();
    private DualListModel<OrgTypeOfSpecList> dualListOrgTypeOfSpecList = new DualListModel<>();

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            String jabatanId = FacesUtil.getRequestParameter("jabatanId");
            model = new OrgTypeOfSpecJabatanModel();
            isUpdate = Boolean.FALSE;
            Jabatan jabatan = jabatanService.getEntiyByPK(Long.valueOf(jabatanId));
            model.setJabatanCode(jabatan.getCode());
            model.setJabatanName(jabatan.getName());
            model.setJabatanId(Long.valueOf(jabatanId));
            if (StringUtils.isNotEmpty(jabatanId)) {
//                OrgTypeOfSpecJabatan orgTypeOfSpecJabatan = orgTypeOfSpecJabatanService.getEntiyByPK(Long.parseLong(savingTypeId));
//                if(savingTypeId != null){
//                    model = getModelFromEntity(savingType);
//                    isUpdate = Boolean.TRUE;
//                }
            }

            //drop down
            List<OrgTypeOfSpec> listTypeOfSpec = orgTypeOfSpecService.getAllData();
            for (OrgTypeOfSpec orgTypeOfSpec : listTypeOfSpec) {
                dropDownSpecList.put(orgTypeOfSpec.getName(), orgTypeOfSpec.getId());
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        isUpdate = null;
        model = null;
        orgTypeOfSpecJabatanService = null;
        orgTypeOfSpecService = null;
        jabatanService = null;
        dropDownSpecList = null;
//        dualListOrgTypeOfSpecList = null;
        orgTypeOfSpecListService = null;
    }
    
    private OrgTypeOfSpecJabatan getEntityFromViewModel(OrgTypeOfSpecJabatanModel model) {
        OrgTypeOfSpecJabatan orgTypeOfSpecJabatan = new OrgTypeOfSpecJabatan();
        orgTypeOfSpecJabatan.setJabatan(new Jabatan(model.getJabatanId()));
        return orgTypeOfSpecJabatan;
    }
    
    public void doSave() {
        
        OrgTypeOfSpecJabatan orgTypeOfSpecJabatan = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                orgTypeOfSpecJabatanService.update(orgTypeOfSpecJabatan);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                orgTypeOfSpecJabatanService.save(orgTypeOfSpecJabatan, dualListOrgTypeOfSpecList.getTarget());
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doChangeDualSpecList() throws Exception {
        List<OrgTypeOfSpecList> listOrgTypeOfSpec = orgTypeOfSpecListService.getAllDataByOrgTypeOfSpecIdAndOrderByCode(model.getOrgTypeOfSpecListId());
        dualListOrgTypeOfSpecList.setSource(listOrgTypeOfSpec);
    }

    public OrgTypeOfSpecJabatanService getOrgTypeOfSpecJabatanService() {
        return orgTypeOfSpecJabatanService;
    }

    public void setOrgTypeOfSpecJabatanService(OrgTypeOfSpecJabatanService orgTypeOfSpecJabatanService) {
        this.orgTypeOfSpecJabatanService = orgTypeOfSpecJabatanService;
    }

    public OrgTypeOfSpecJabatanModel getModel() {
        return model;
    }

    public void setModel(OrgTypeOfSpecJabatanModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public JabatanService getJabatanService() {
        return jabatanService;
    }

    public void setJabatanService(JabatanService jabatanService) {
        this.jabatanService = jabatanService;
    }

    public OrgTypeOfSpecService getOrgTypeOfSpecService() {
        return orgTypeOfSpecService;
    }

    public void setOrgTypeOfSpecService(OrgTypeOfSpecService orgTypeOfSpecService) {
        this.orgTypeOfSpecService = orgTypeOfSpecService;
    }

    public Map<String, Long> getDropDownSpecList() {
        return dropDownSpecList;
    }

    public void setDropDownSpecList(Map<String, Long> dropDownSpecList) {
        this.dropDownSpecList = dropDownSpecList;
    }

    public DualListModel<OrgTypeOfSpecList> getDualListOrgTypeOfSpecList() {
        return dualListOrgTypeOfSpecList;
    }

    public void setDualListOrgTypeOfSpecList(DualListModel<OrgTypeOfSpecList> dualListOrgTypeOfSpecList) {
        this.dualListOrgTypeOfSpecList = dualListOrgTypeOfSpecList;
    }

    public OrgTypeOfSpecListService getOrgTypeOfSpecListService() {
        return orgTypeOfSpecListService;
    }

    public void setOrgTypeOfSpecListService(OrgTypeOfSpecListService orgTypeOfSpecListService) {
        this.orgTypeOfSpecListService = orgTypeOfSpecListService;
    }

}
