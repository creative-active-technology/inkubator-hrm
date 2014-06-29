package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.service.CountryService;
import com.inkubator.hrm.web.model.CountryModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "countryFormController")
@ViewScoped
public class CountryFormController extends BaseController {

    private CountryModel countryModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{countryService}")
    private CountryService countryService;
    private UploadedFile file;
    private byte[] buffer;
    private Boolean infoRendered;
    
    

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        countryModel = new CountryModel();
        isUpdate = Boolean.FALSE;
        infoRendered = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                Country country = countryService.getEntiyByPK(Long.parseLong(param));
                if (country != null) {
                    countryModel.setId(country.getId());
                    countryModel.setCountryCode(country.getCountryCode());
                    countryModel.setCountryName(country.getCountryName());
                    countryModel.setFlagIcon(country.getFlagIcon());
                    countryModel.setPhoneCode(country.getPhoneCode());
                    isUpdate = Boolean.TRUE;
                    infoRendered = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        countryService = null;
        countryModel = null;
        isUpdate = null;
    }

    public CountryModel getCountryModel() {
        return countryModel;
    }

    public void setCountryModel(CountryModel countryModel) {
        this.countryModel = countryModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    public Boolean getInfoRendered() {
        return infoRendered;
    }

    public void setInfoRendered(Boolean infoRendered) {
        this.infoRendered = infoRendered;
    }

    
    
    public void doSave() {
        Country country = getEntityFromViewModel(countryModel);
        try {
            if (isUpdate) {
                countryService.update(country);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                countryService.save(country);
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

    private Country getEntityFromViewModel(CountryModel countryModel) {
        Country country = new Country();
        if (countryModel.getId() != null) {
            country.setId(countryModel.getId());
        }
        country.setCountryCode(countryModel.getCountryCode());

        country.setCountryName(countryModel.getCountryName());
        if (buffer != null) {
            country.setFlagIcon(buffer);
        } else {
            country.setFlagIcon(countryModel.getFlagIcon());
        }
        country.setPhoneCode(countryModel.getPhoneCode());
        return country;
    }

    public void handleFileUpload(FileUploadEvent event) {
        file = event.getFile();
        System.out.println(" File " + file);
        InputStream inputStream = null;
        try {
            inputStream = file.getInputstream();
            buffer = IOUtils.toByteArray(inputStream);
            System.out.println("Buffernya : " + buffer);
        } catch (IOException ex) {
            LOGGER.error("Error", ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                LOGGER.error("Error", ex);
            }
        }
//        fileName = file.getFileName();
    }
}
