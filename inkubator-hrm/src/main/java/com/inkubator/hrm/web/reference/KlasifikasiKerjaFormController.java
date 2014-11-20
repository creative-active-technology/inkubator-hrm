package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.service.KlasifikasiKerjaService;
import com.inkubator.hrm.web.model.KlasifikasiKerjaModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
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
@ManagedBean(name = "klasifikasiKerjaFormController")
@ViewScoped
public class KlasifikasiKerjaFormController extends BaseController {

    private KlasifikasiKerjaModel klasifikasiKerjaModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{klasifikasiKerjaService}")
    private KlasifikasiKerjaService klasifikasiKerjaService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        klasifikasiKerjaModel = new KlasifikasiKerjaModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                KlasifikasiKerja klasifikasiKerja = klasifikasiKerjaService.getEntiyByPK(Long.parseLong(param));
                if (klasifikasiKerja != null) {
                    klasifikasiKerjaModel.setId(klasifikasiKerja.getId());
                    klasifikasiKerjaModel.setKlasifikasiKerjaCode(klasifikasiKerja.getCode());
                    klasifikasiKerjaModel.setKlasifikasiKerjaName(klasifikasiKerja.getName());
                    klasifikasiKerjaModel.setDescription(klasifikasiKerja.getDescription());
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        klasifikasiKerjaService = null;
        klasifikasiKerjaModel = null;
        isUpdate = null;
    }

    public KlasifikasiKerjaModel getKlasifikasiKerjaModel() {
        return klasifikasiKerjaModel;
    }

    public void setKlasifikasiKerjaModel(KlasifikasiKerjaModel klasifikasiKerjaModel) {
        this.klasifikasiKerjaModel = klasifikasiKerjaModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setKlasifikasiKerjaService(KlasifikasiKerjaService klasifikasiKerjaService) {
        this.klasifikasiKerjaService = klasifikasiKerjaService;
    }

    public void doSave() {
        KlasifikasiKerja klasifikasiKerja = getEntityFromViewModel(klasifikasiKerjaModel);
        try {
            if (isUpdate) {
                klasifikasiKerjaService.update(klasifikasiKerja);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                klasifikasiKerjaService.save(klasifikasiKerja);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private KlasifikasiKerja getEntityFromViewModel(KlasifikasiKerjaModel klasifikasiKerjaModel) {
        KlasifikasiKerja klasifikasiKerja = new KlasifikasiKerja();
        if (klasifikasiKerjaModel.getId() != null) {
            klasifikasiKerja.setId(klasifikasiKerjaModel.getId());
        }
        klasifikasiKerja.setCode(klasifikasiKerjaModel.getKlasifikasiKerjaCode());
        klasifikasiKerja.setName(klasifikasiKerjaModel.getKlasifikasiKerjaName());
        klasifikasiKerja.setDescription(klasifikasiKerjaModel.getDescription());
        return klasifikasiKerja;
    }
}
