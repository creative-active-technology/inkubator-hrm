package com.inkubator.hrm.web.organisation;

import java.util.Map;

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
import com.inkubator.hrm.entity.PaySalaryGrade;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.PangkatService;
import com.inkubator.hrm.service.PaySalaryGradeService;
import com.inkubator.hrm.web.model.GolonganJabatanModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.List;
import java.util.TreeMap;

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
    @ManagedProperty(value = "#{paySalaryGradeService}")
    private PaySalaryGradeService paySalaryGradeService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            isUpdate = Boolean.FALSE;

            model = new GolonganJabatanModel();
            Map<Long, String> pangkats = pangkatService.getAllDataMaps();
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
            Map<String, Long> dropDownPaySalaryGrade = new TreeMap<String, Long>();;
            List<PaySalaryGrade> listPaySalaryGrade = paySalaryGradeService.getAllData();
            String nameSalaryGrade = "";
            for (PaySalaryGrade paySalaryGrade : listPaySalaryGrade) {
                nameSalaryGrade = paySalaryGrade.getGradeSalary() + " - " + paySalaryGrade.getMinSalary() + " - " + paySalaryGrade.getMaxSalary();
                dropDownPaySalaryGrade.put(nameSalaryGrade, paySalaryGrade.getId());
            }
            model.setDropDownPaySalaryGrade(dropDownPaySalaryGrade);
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
        } catch (BussinessException ex) {
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
        golonganJabatan.setOvertime(model.getOvertime());
        golonganJabatan.setPaySalaryGrade(new PaySalaryGrade(model.getPaySalaryGradeId()));
//        golonganJabatan.setPangkat(new Pangkat(model.getPangkatId()));
        golonganJabatan.setPointMin(model.getPointMin());
        golonganJabatan.setPointMid(model.getPointMid());
        golonganJabatan.setPointMax(model.getPointMax());
        golonganJabatan.setRatioCompact(model.getRatioCompact());
        return golonganJabatan;
    }

    private void getViewModelFromEntity(GolonganJabatan golonganJabatan) {
        model.setId(golonganJabatan.getId());
        model.setCode(golonganJabatan.getCode());
        model.setOvertime(golonganJabatan.getOvertime());
        if(golonganJabatan.getPaySalaryGrade() != null){
            model.setPaySalaryGradeId(golonganJabatan.getPaySalaryGrade().getId());
        }
//    	model.setPangkatId(golonganJabatan.getPangkat().getId());
        model.setPointMin(golonganJabatan.getPointMin());
        model.setPointMid(golonganJabatan.getPointMid());
        model.setPointMax(golonganJabatan.getPointMax());
        model.setRatioCompact(golonganJabatan.getRatioCompact());
    }

    public PaySalaryGradeService getPaySalaryGradeService() {
        return paySalaryGradeService;
    }

    public void setPaySalaryGradeService(PaySalaryGradeService paySalaryGradeService) {
        this.paySalaryGradeService = paySalaryGradeService;
    }
    
    
}
