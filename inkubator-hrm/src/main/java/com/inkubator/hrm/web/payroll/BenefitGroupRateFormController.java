package com.inkubator.hrm.web.payroll;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BenefitGroup;
import com.inkubator.hrm.entity.BenefitGroupRate;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.service.BenefitGroupRateService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.BenefitGroupRateModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Taufik Hidayat
 */
@ManagedBean(name = "benefitGroupRateFormController")
@ViewScoped
public class BenefitGroupRateFormController extends BaseController {

    private BenefitGroupRateModel benefitGroupRateModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{benefitGroupRateService}")
    private BenefitGroupRateService benefitGroupRateService;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
    private Map<String, Long> golonganJabatans = new TreeMap<>();

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            benefitGroupRateModel = new BenefitGroupRateModel();
            isUpdate = Boolean.FALSE;
            String benefitGroupId = FacesUtil.getRequestParameter("benefitGroupId");
            benefitGroupRateModel.setBenefitGroupId(Long.parseLong(benefitGroupId));
           
            List<GolonganJabatan> listGolonganJabatans = golonganJabatanService.getAllWithDetail();

            for (GolonganJabatan golonganJabatan : listGolonganJabatans) {
                golonganJabatans.put(golonganJabatan.getCode(), golonganJabatan.getId());
            }

            MapUtil.sortByValue(golonganJabatans);

            String benefitGroupRateId = FacesUtil.getRequestParameter("benefitGroupRateId");
            if (StringUtils.isNotEmpty(benefitGroupRateId)) {
                BenefitGroupRate benefitGroupRate = benefitGroupRateService.getEntityByPKWithDetail(Long.parseLong(benefitGroupRateId));
                if (benefitGroupRateId != null) {
                    benefitGroupRateModel = getModelFromEntity(benefitGroupRate);
                    isUpdate = Boolean.TRUE;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        benefitGroupRateService = null;
//        benefitGroupRateModel = null;
        isUpdate = null;
    }

    public BenefitGroupRateModel getBenefitGroupRateModel() {
        return benefitGroupRateModel;
    }

    public void setBenefitGroupRateModel(BenefitGroupRateModel benefitGroupRateModel) {
        this.benefitGroupRateModel = benefitGroupRateModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setBenefitGroupRateService(BenefitGroupRateService benefitGroupRateService) {
        this.benefitGroupRateService = benefitGroupRateService;
    }   

    public void setGolonganJabatanService(GolonganJabatanService golonganJabatanService) {
        this.golonganJabatanService = golonganJabatanService;
    }    

    public Map<String, Long> getGolonganJabatans() {
        return golonganJabatans;
    }

    public void setGolonganJabatans(Map<String, Long> golonganJabatans) {
        this.golonganJabatans = golonganJabatans;
    }

    
    
    public void doSave() {
        BenefitGroupRate benefitGroupRate = getEntityFromViewModel(benefitGroupRateModel);
        try {
            if (isUpdate) {
                benefitGroupRateService.update(benefitGroupRate);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                benefitGroupRateService.save(benefitGroupRate);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private BenefitGroupRate getEntityFromViewModel(BenefitGroupRateModel benefitGroupRateModel) {
        try {
            BenefitGroupRate benefitGroupRate = new BenefitGroupRate();
            if (benefitGroupRateModel.getId() != null) {
                benefitGroupRate.setId(benefitGroupRateModel.getId());
            }
            benefitGroupRate.setBenefitGroup(new BenefitGroup(benefitGroupRateModel.getBenefitGroupId()));
            benefitGroupRate.setGolonganJabatan(new GolonganJabatan(benefitGroupRateModel.getGolonganJabatanId()));
            benefitGroupRate.setNominal(benefitGroupRateModel.getNominal());
            return benefitGroupRate;
        } catch (Exception ex) {
            Logger.getLogger(BenefitGroupRateFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private BenefitGroupRateModel getModelFromEntity(BenefitGroupRate entity) {
        BenefitGroupRateModel benefitGroupRateModel = new BenefitGroupRateModel();
        benefitGroupRateModel.setId(entity.getId());
        benefitGroupRateModel.setBenefitGroupId(entity.getBenefitGroup().getId());
        benefitGroupRateModel.setGolonganJabatanId(entity.getGolonganJabatan().getId());
        benefitGroupRateModel.setNominal(entity.getNominal());
        return benefitGroupRateModel;
    }
    
}
