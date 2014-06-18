package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.LanguageUsed;
import com.inkubator.hrm.service.LanguageService;
import com.inkubator.hrm.web.model.LanguageModel;
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
@ManagedBean(name = "languageFormController")
@ViewScoped
public class LanguageFormController extends BaseController {

    private LanguageModel languageModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{languageService}")
    private LanguageService languageService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        languageModel = new LanguageModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                LanguageUsed language = languageService.getEntiyByPK(Long.parseLong(param));
                if (language != null) {
                    languageModel.setId(language.getId());
                    languageModel.setLanguageName(language.getLanguageName());
                    languageModel.setDescription(language.getDescription());
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        languageService = null;
        languageModel = null;
        isUpdate = null;
    }

    public LanguageModel getLanguageModel() {
        return languageModel;
    }

    public void setLanguageModel(LanguageModel languageModel) {
        this.languageModel = languageModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setLanguageService(LanguageService languageService) {
        this.languageService = languageService;
    }

    public void doSave() {
        LanguageUsed language = getEntityFromViewModel(languageModel);
        try {
            if (isUpdate) {
                languageService.update(language);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                languageService.save(language);
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

    private LanguageUsed getEntityFromViewModel(LanguageModel languageModel) {
        LanguageUsed language = new LanguageUsed();
        if (languageModel.getId() != null) {
            language.setId(languageModel.getId());
        }
        language.setLanguageName(languageModel.getLanguageName());
        language.setDescription(languageModel.getDescription());
        return language;
    }
}
