/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.BusinessTravel;
import com.inkubator.hrm.entity.BusinessTravelComponent;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.BusinessTravelService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "businessTravelApprovalFormController")
@ViewScoped
public class BusinessTravelApprovalFormController extends BaseController {

    private Double totalAmount = 0.0;
    private BusinessTravel selectedBusinessTravel;
    private List<BusinessTravelComponent> businessTravelComponents;
    private String comment;
    private Boolean isWaitingApproval;
    private Boolean isApprover;
    private Boolean isRequester;
    private ApprovalActivity selectedApprovalActivity;
    @ManagedProperty(value = "#{businessTravelService}")
    private BusinessTravelService businessTravelService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution");
            selectedApprovalActivity = approvalActivityService.getEntiyByPK(Long.parseLong(id.substring(1)));
            isWaitingApproval = selectedApprovalActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING;
            isApprover = StringUtils.equals(UserInfoUtil.getUserName(), selectedApprovalActivity.getApprovedBy());
            isRequester = StringUtils.equals(UserInfoUtil.getUserName(), selectedApprovalActivity.getRequestBy());

            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            JsonObject jsonObject = gson.fromJson(selectedApprovalActivity.getPendingData(), JsonObject.class);
            businessTravelComponents = gson.fromJson(jsonObject.get("businessTravelComponents"), new TypeToken<List<BusinessTravelComponent>>() {
            }.getType());
            selectedBusinessTravel = gson.fromJson(selectedApprovalActivity.getPendingData(), BusinessTravel.class);
            EmpData empData = empDataService.getByIdWithDetail(selectedBusinessTravel.getEmpData().getId());
            selectedBusinessTravel.setEmpData(empData);
            for (BusinessTravelComponent btc : businessTravelComponents) {
                totalAmount = totalAmount + btc.getPayByAmount();
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
        businessTravelComponents = null;
        selectedBusinessTravel = null;
        businessTravelService = null;
        selectedApprovalActivity = null;
        totalAmount = null;
        approvalActivityService = null;
        comment = null;
        empDataService = null;
        isWaitingApproval = null;
        isApprover = null;
        isRequester = null;
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

    public List<BusinessTravelComponent> getBusinessTravelComponents() {
        return businessTravelComponents;
    }

    public void setBusinessTravelComponents(List<BusinessTravelComponent> businessTravelComponents) {
        this.businessTravelComponents = businessTravelComponents;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getIsWaitingApproval() {
        return isWaitingApproval;
    }

    public void setIsWaitingApproval(Boolean isWaitingApproval) {
        this.isWaitingApproval = isWaitingApproval;
    }
    
    public Boolean getIsApprover() {
		return isApprover;
	}

	public void setIsApprover(Boolean isApprover) {
		this.isApprover = isApprover;
	}

	public Boolean getIsRequester() {
		return isRequester;
	}

	public void setIsRequester(Boolean isRequester) {
		this.isRequester = isRequester;
	}

	public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public Boolean getIsPaginator() {
        return businessTravelComponents.size() > 11;
    }

    public String doBack() {
        return "/protected/home.htm?faces-redirect=true";
    }

    public String doApproved() {
        try {
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            JsonParser parser = new JsonParser();
            JsonArray arrayComponents = new JsonArray();
            JsonObject jsonObject = gson.fromJson(selectedApprovalActivity.getPendingData(), JsonObject.class);
            for (BusinessTravelComponent btc : businessTravelComponents) {
                JsonObject component = (JsonObject) parser.parse(gson.toJson(btc));
                arrayComponents.add(component);
            }
            jsonObject.add("businessTravelComponents", arrayComponents);

            businessTravelService.approved(selectedApprovalActivity.getId(), gson.toJson(jsonObject), comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.approved_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/home.htm?faces-redirect=true";
        } catch (BussinessException ex) {            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error when approved process ", e);
        }
        return null;
    }

    public String doRejected() {
        try {
            businessTravelService.rejected(selectedApprovalActivity.getId(), comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.rejected_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/home.htm?faces-redirect=true";
        } catch (BussinessException ex) {            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error when rejected process ", e);
        }
        return null;
    }
    
    public String doCancelled() {
        try {
            businessTravelService.cancelled(selectedApprovalActivity.getId(), comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.cancelled_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/home.htm?faces-redirect=true";
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error when rejected process ", e);
        }
        return null;
    }

    public void doAdjustPayByAmount() {
        totalAmount = 0.0;
        for (BusinessTravelComponent btc : businessTravelComponents) {
            totalAmount = totalAmount + btc.getPayByAmount();
        }
    }

}
