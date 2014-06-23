package com.inkubator.hrm.web.organisation;

import java.util.List;

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
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.Pangkat;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.PangkatService;
import com.inkubator.hrm.web.model.GolonganJabatanModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "golonganJabatanFormController")
@ViewScoped
public class GolonganJabatanFormController extends BaseController {

    private GolonganJabatanModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golJabatanService;
    @ManagedProperty(value = "#{pangkatService}")
    private PangkatService pangkatService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
	        isUpdate = Boolean.FALSE;
	        
	        model = new GolonganJabatanModel();
	        List<Pangkat> pangkats = pangkatService.getAllData();
			model.setPangkats(pangkats);
	        
	        String param = FacesUtil.getRequestParameter("param");
	        if (StringUtils.isNumeric(param)) {
	            try {
	                GolonganJabatan golonganJabatan = golJabatanService.getEntiyByPK(Long.parseLong(param));
	                if (golonganJabatan != null) {
	                    getViewModelFromEntity(golonganJabatan);
	                    isUpdate = Boolean.TRUE;
	                }
	            } catch (Exception e) {
	                LOGGER.error("Error", e);
	            }
	        }
        } catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    @PreDestroy
    public void cleanAndExit() {
        golJabatanService = null;
        pangkatService = null;
        model = null;
        isUpdate = null;
    }

	public GolonganJabatanModel getModel() {
		return model;
	}

	public void setModel(GolonganJabatanModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public void setGolJabatanService(GolonganJabatanService golJabatanService) {
		this.golJabatanService = golJabatanService;
	}

	public void setPangkatService(PangkatService pangkatService) {
		this.pangkatService = pangkatService;
	}

	public void doSave() {
        GolonganJabatan golonganJabatan = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                golJabatanService.update(golonganJabatan);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                golJabatanService.save(golonganJabatan);
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

    private GolonganJabatan getEntityFromViewModel(GolonganJabatanModel model) {
        GolonganJabatan golonganJabatan = new GolonganJabatan();
        if (model.getId() != null) {
        	golonganJabatan.setId(model.getId());
        }
        golonganJabatan.setCode(model.getCode());
        golonganJabatan.setName(model.getName());
        golonganJabatan.setOvertime(model.getOvertime());
        golonganJabatan.setLevel(model.getLevel());
        golonganJabatan.setPangkat(new Pangkat(model.getPangkatId()));
        return golonganJabatan;
    }
    
    private void getViewModelFromEntity(GolonganJabatan golonganJabatan){
    	model.setId(golonganJabatan.getId());
    	model.setCode(golonganJabatan.getCode());
    	model.setName(golonganJabatan.getName());
    	model.setLevel(golonganJabatan.getLevel());
    	model.setOvertime(golonganJabatan.getOvertime());
    	model.setPangkatId(golonganJabatan.getPangkat().getId());
    }
}
