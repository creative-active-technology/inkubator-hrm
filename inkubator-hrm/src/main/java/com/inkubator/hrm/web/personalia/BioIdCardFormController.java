package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.web.reference.*;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioIdCard;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.service.BioIdCardService;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.BioIdCardModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.Calendar;
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
@ManagedBean(name = "bioIdCardFormController")
@ViewScoped
public class BioIdCardFormController extends BaseController {

    private BioIdCardModel bioIdCardModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{bioIdCardService}")
    private BioIdCardService bioIdCardService;
    @ManagedProperty(value = "#{cityService}")
    private CityService cityService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            bioIdCardModel = new BioIdCardModel();
            isUpdate = Boolean.FALSE;
            String bioDataId = FacesUtil.getRequestParameter("bioDataId");
            bioIdCardModel.setBioDataId(Long.parseLong(bioDataId));

            String bioIdCardId = FacesUtil.getRequestParameter("bioIdCardId");
            if (StringUtils.isNotEmpty(bioIdCardId)) {
                BioIdCard bioIdCard = bioIdCardService.getEntityByPKWithDetail(Long.parseLong(bioIdCardId));
                if (bioIdCardId != null) {
                    bioIdCardModel = getModelFromEntity(bioIdCard);
                    isUpdate = Boolean.TRUE;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        bioIdCardService = null;
//        bioIdCardModel = null;
        isUpdate = null;
    }

    public BioIdCardModel getBioIdCardModel() {
        return bioIdCardModel;
    }

    public void setBioIdCardModel(BioIdCardModel bioIdCardModel) {
        this.bioIdCardModel = bioIdCardModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setBioIdCardService(BioIdCardService bioIdCardService) {
        this.bioIdCardService = bioIdCardService;
    }

    

    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    
    public void doSave() {
        BioIdCard bioIdCard = getEntityFromViewModel(bioIdCardModel);
        try {
            if (isUpdate) {
                bioIdCardService.update(bioIdCard);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                bioIdCardService.save(bioIdCard);
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

    private BioIdCard getEntityFromViewModel(BioIdCardModel bioIdCardModel) {
        BioIdCard bioIdCard = new BioIdCard();
        if (bioIdCardModel.getId() != null) {
            bioIdCard.setId(bioIdCardModel.getId());
        }
        bioIdCard.setBioData(new BioData(bioIdCardModel.getBioDataId()));
        bioIdCard.setCity(bioIdCardModel.getCity());
        bioIdCard.setType(bioIdCardModel.getType());
        bioIdCard.setCardNumber(bioIdCardModel.getCardNumber());
        bioIdCard.setValidDate(bioIdCardModel.getValidDate());
        bioIdCard.setIssuedDate(bioIdCardModel.getIssuedDate());
        return bioIdCard;
    }

    private BioIdCardModel getModelFromEntity(BioIdCard entity) {
        BioIdCardModel bioIdCardModel = new BioIdCardModel();
        bioIdCardModel.setId(entity.getId());
        bioIdCardModel.setBioDataId(entity.getBioData().getId());
        bioIdCardModel.setCity(entity.getCity());
        bioIdCardModel.setType(entity.getType());
        bioIdCardModel.setCardNumber(entity.getCardNumber());
        bioIdCardModel.setValidDate(entity.getValidDate());
        bioIdCardModel.setIssuedDate(entity.getIssuedDate());
        return bioIdCardModel;
    }

    public List<City> completeCity(String query) {
        try {
            List<City> allCity = cityService.getAllData();
            List<City> queried = new ArrayList<City>();
            for (City city : allCity) {
                if (city.getCityName().toLowerCase().contains(query) || city.getCityName().contains(query)) {
                    queried.add(city);
                }
            }

            return queried;
        } catch (Exception ex) {
            Logger.getLogger(BioIdCardFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
