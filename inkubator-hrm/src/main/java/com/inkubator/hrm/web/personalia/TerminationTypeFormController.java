package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.SystemLetterReference;
import com.inkubator.hrm.entity.TerminationType;
import com.inkubator.hrm.service.SystemLetterReferenceService;
import com.inkubator.hrm.service.TerminationTypeService;
import com.inkubator.hrm.web.model.TerminationTypeModel;
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
@ManagedBean(name = "terminationTypeFormController")
@ViewScoped
public class TerminationTypeFormController extends BaseController {

    private TerminationTypeModel terminationTypeModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{terminationTypeService}")
    private TerminationTypeService terminationTypeService;
    private Map<String, Long> letterRefferences = new TreeMap<>();
    @ManagedProperty(value = "#{systemLetterReferenceService}")
    private SystemLetterReferenceService systemLetterReferenceService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("param");
            terminationTypeModel = new TerminationTypeModel();
            isUpdate = Boolean.FALSE;
            List<SystemLetterReference> dataDrops = systemLetterReferenceService.getAllData();
            for (SystemLetterReference dataDrop : dataDrops) {
                letterRefferences.put(dataDrop.getName(), dataDrop.getId());
            }
            if (StringUtils.isNumeric(param)) {

//                try {
                TerminationType terminationType = terminationTypeService.getEntiyByPK(Long.parseLong(param));
                if (terminationType != null) {
                    isUpdate = Boolean.TRUE;
                    terminationTypeModel.setId(terminationType.getId());
                    terminationTypeModel.setCode(terminationType.getCode());
                    terminationTypeModel.setName(terminationType.getName());
                    terminationTypeModel.setDescription(terminationType.getDescription());
                    terminationTypeModel.setReffLetterId(terminationType.getSystemLetterReference().getId());

                }
//                } catch (Exception e) {
//                    LOGGER.error("Error", e);
//                }
            }
        } catch (Exception ex) {
            Logger.getLogger(TerminationTypeFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        terminationTypeService = null;
//        terminationTypeModel = null;
        isUpdate = null;
    }

    public TerminationTypeModel getTerminationTypeModel() {
        return terminationTypeModel;
    }

    public void setTerminationTypeModel(TerminationTypeModel terminationTypeModel) {
        this.terminationTypeModel = terminationTypeModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setTerminationTypeService(TerminationTypeService terminationTypeService) {
        this.terminationTypeService = terminationTypeService;
    }

    public void doSave() {
        TerminationType terminationType = getEntityFromViewModel(terminationTypeModel);
        try {
            if (isUpdate) {
                terminationTypeService.update(terminationType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                terminationTypeService.save(terminationType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private TerminationType getEntityFromViewModel(TerminationTypeModel terminationTypeModel) {
        TerminationType terminationType = new TerminationType();
        if (terminationTypeModel.getId() != null) {
            terminationType.setId(terminationTypeModel.getId());
        }
        terminationType.setCode(terminationTypeModel.getCode());
        terminationType.setName(terminationTypeModel.getName());
        terminationType.setDescription(terminationTypeModel.getDescription());
        terminationType.setSystemLetterReference(new SystemLetterReference(terminationTypeModel.getReffLetterId()));
        return terminationType;
    }

    public Map<String, Long> getLetterRefferences() {
        return letterRefferences;
    }

    public void setLetterRefferences(Map<String, Long> letterRefferences) {
        this.letterRefferences = letterRefferences;
    }

    public void setSystemLetterReferenceService(SystemLetterReferenceService systemLetterReferenceService) {
        this.systemLetterReferenceService = systemLetterReferenceService;
    }

}
