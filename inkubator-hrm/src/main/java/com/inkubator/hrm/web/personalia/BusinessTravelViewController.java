package com.inkubator.hrm.web.personalia;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BusinessTravel;
import com.inkubator.hrm.entity.BusinessTravelComponent;
import com.inkubator.hrm.service.BusinessTravelComponentService;
import com.inkubator.hrm.service.BusinessTravelService;
import com.inkubator.hrm.util.TerbilangUtil;
import com.inkubator.hrm.web.lazymodel.BusinessTravelLazyDataModel;
import com.inkubator.hrm.web.search.BusinessTravelSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "businessTravelViewController")
@ViewScoped
public class BusinessTravelViewController extends BaseController {

	private Date printDate;
	private Double totalAmount;
	private String totalAmountTerbilang;
	private List<BusinessTravelComponent> businessTravelComponents;
    private BusinessTravelSearchParameter searchParameter;
    private LazyDataModel<BusinessTravel> lazyDataBusinessTravel;
    private BusinessTravel selectedBusinessTravel;
    @ManagedProperty(value = "#{businessTravelService}")
    private BusinessTravelService businessTravelService;
    @ManagedProperty(value = "#{businessTravelComponentService}")
    private BusinessTravelComponentService businessTravelComponentService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new BusinessTravelSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        businessTravelService = null;
        searchParameter = null;
        lazyDataBusinessTravel = null;
        selectedBusinessTravel = null;
        totalAmount = null;
        totalAmountTerbilang = null;
        businessTravelComponents = null;
        businessTravelComponentService = null;
        printDate = null;
    }    

	public LazyDataModel<BusinessTravel> getLazyDataBusinessTravel() {
		if(lazyDataBusinessTravel == null){
			lazyDataBusinessTravel = new BusinessTravelLazyDataModel(searchParameter, businessTravelService);
		}
		return lazyDataBusinessTravel;
	}

	public void setLazyDataBusinessTravel(LazyDataModel<BusinessTravel> lazyDataBusinessTravel) {
		this.lazyDataBusinessTravel = lazyDataBusinessTravel;
	}

	public BusinessTravelSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(BusinessTravelSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public BusinessTravel getSelectedBusinessTravel() {
		return selectedBusinessTravel;
	}

	public void setSelectedBusinessTravel(BusinessTravel selectedBusinessTravel) {
		this.selectedBusinessTravel = selectedBusinessTravel;
	}

	public void setBusinessTravelService(BusinessTravelService businessTravelService) {
		this.businessTravelService = businessTravelService;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<BusinessTravelComponent> getBusinessTravelComponents() {
		return businessTravelComponents;
	}

	public void setBusinessTravelComponents(
			List<BusinessTravelComponent> businessTravelComponents) {
		this.businessTravelComponents = businessTravelComponents;
	}

	public void setBusinessTravelComponentService(
			BusinessTravelComponentService businessTravelComponentService) {
		this.businessTravelComponentService = businessTravelComponentService;
	}

	public Date getPrintDate() {
		return printDate;
	}

	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}

	public String getTotalAmountTerbilang() {
		return totalAmountTerbilang;
	}

	public void setTotalAmountTerbilang(String totalAmountTerbilang) {
		this.totalAmountTerbilang = totalAmountTerbilang;
	}

	public void doSearch() {
        lazyDataBusinessTravel = null;
    }

    public String doDetail() {
        return "/protected/personalia/business_travel_detail.htm?faces-redirect=true&execution=e" + selectedBusinessTravel.getBusinessTravelNo();
    }

    public void doSelectEntity() {
        try {
            selectedBusinessTravel = this.businessTravelService.getEntiyByPK(selectedBusinessTravel.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doBeforePrint(){
    	try {
    		printDate = new Date();
	    	selectedBusinessTravel = businessTravelService.getEntityByPkWithDetail(selectedBusinessTravel.getId());
	        businessTravelComponents = businessTravelComponentService.getAllDataByBusinessTravelId(selectedBusinessTravel.getId());
	        totalAmount  = 0.0;
	        for(BusinessTravelComponent btc :businessTravelComponents){
	        	totalAmount = totalAmount + btc.getPayByAmount();
	        }
	        TerbilangUtil terbilang = new TerbilangUtil(Double.valueOf(totalAmount).longValue());
	        totalAmountTerbilang = terbilang.toString();
	        String capitalizeWord = StringUtils.capitalize(StringUtils.substring(totalAmountTerbilang, 0, 1));
	        totalAmountTerbilang = capitalizeWord + StringUtils.substring(totalAmountTerbilang, 1, totalAmountTerbilang.length());
	        
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            businessTravelService.delete(selectedBusinessTravel);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete BusinessTravel", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete BusinessTravel", ex);
        }
    }

    public void doAdd() {
        try {
            ExternalContext red = FacesUtil.getExternalContext();
            red.redirect(red.getRequestContextPath() + "/flow-protected/business_travel");
        } catch (IOException ex) {
          LOGGER.error("Erorr", ex);
        }
    }

    public void doUpdate() {
    	try {
            ExternalContext red = FacesUtil.getExternalContext();
            red.redirect(red.getRequestContextPath() + "/flow-protected/business_travel?id=" + selectedBusinessTravel.getId());
        } catch (IOException ex) {
          LOGGER.error("Erorr", ex);
        }
    }
}
