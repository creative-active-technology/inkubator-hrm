package com.inkubator.hrm.web.appraisal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.AppraisalSystemScoring;
import com.inkubator.hrm.entity.SpecificationAbility;
import com.inkubator.hrm.service.AppraisalSystemScoringService;
import com.inkubator.hrm.web.model.AppraisalSystemScoringModel;
import com.inkubator.hrm.web.model.SpecAbilityModel;
import com.inkubator.hrm.web.search.AppraisalSystemScoringSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

@ManagedBean(name = "appraisalSystemScoringFormController")
@ViewScoped
public class AppraisalSystemScoringFormController extends BaseController {
	@ManagedProperty(value = "#{appraisalSystemScoringService}")
	private AppraisalSystemScoringService appraisalSystemScoringService;
	private AppraisalSystemScoringModel appraisalSystemScoringModel;
	private AppraisalSystemScoring selectedAppraisalSystemScoring;
	private Boolean isDisable;
	private Boolean isUpdate;

	@PostConstruct
	@Override
	public void initialization() {
		super.initialization();
		appraisalSystemScoringModel = new AppraisalSystemScoringModel();
		isUpdate = Boolean.FALSE;
		isDisable = Boolean.TRUE;
	}

	@PreDestroy
	public void cleanAndExit() {
		appraisalSystemScoringService = null;
		appraisalSystemScoringModel = null;
		selectedAppraisalSystemScoring = null;
	}

	public String doSave() {
		AppraisalSystemScoring appraisalSystemScoring = getEntityFromViewModel(appraisalSystemScoringModel);
		try {
			if (isUpdate) {
				appraisalSystemScoringService.update(appraisalSystemScoring);
				MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info",
						"global.update_successfully",
						FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

			} else {
				appraisalSystemScoringService.save(appraisalSystemScoring, appraisalSystemScoringModel.getTotalOption(),
						appraisalSystemScoringModel.getLabels(), appraisalSystemScoringModel.getScaleValue(),
						appraisalSystemScoringModel.getDescription());
				MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info",
						"global.added_successfully",
						FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
			}
			return "/protected/appraisal/appraisal_system_scoring_view.htm?faces-redirect=true";
		} catch (BussinessException ex) {
			MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(),
					FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
		} catch (Exception ex) {
			LOGGER.error("Error", ex);
		}
		return null;
	}

	private AppraisalSystemScoring getEntityFromViewModel(AppraisalSystemScoringModel appraisalSystemScoringModel) {
		AppraisalSystemScoring appraisalSystemScoring = new AppraisalSystemScoring();
		appraisalSystemScoring.setName(appraisalSystemScoringModel.getName());
		return appraisalSystemScoring;
	}

	/*
	 * rule, if total option is 4 -> then scale value 0, 33, 66, 100 3 -> then
	 * scale value 0, 50, 100 6 -> then scale value 0, 20, 40, 60, 80, 100
	 */
	public void doAdjustScaleValue() {
		int scaleValue = 0;
		isDisable = Boolean.FALSE;
		// find divisor value, only if totalOption is more than 1(one)
		int divisor = 0;
		if (appraisalSystemScoringModel.getTotalOption() > 1) {
			divisor = 100 / (appraisalSystemScoringModel.getTotalOption() - 1);
		}

		for (int i = 0; i < appraisalSystemScoringModel.getTotalOption(); i++) {
			// adjust value to 100, only if end of loop or totalOtion is equal
			// with 1(one)
			if (appraisalSystemScoringModel.getTotalOption() == 1
					|| appraisalSystemScoringModel.getTotalOption() == i + 1) {
				scaleValue = 100;
			}
			appraisalSystemScoringModel.getScaleValue()[i] = scaleValue;
			scaleValue = scaleValue + divisor;
		}
	}

	public String doBack() {
		return "/protected/appraisal/appraisal_system_scoring_view.htm?faces-redirect=true";
	}

	public void doReset() {
		appraisalSystemScoringModel = new AppraisalSystemScoringModel();
		isDisable = Boolean.TRUE;
	}

	public AppraisalSystemScoringService getAppraisalSystemScoringService() {
		return appraisalSystemScoringService;
	}

	public void setAppraisalSystemScoringService(AppraisalSystemScoringService appraisalSystemScoringService) {
		this.appraisalSystemScoringService = appraisalSystemScoringService;
	}

	public AppraisalSystemScoringModel getAppraisalSystemScoringModel() {
		return appraisalSystemScoringModel;
	}

	public void setAppraisalSystemScoringModel(AppraisalSystemScoringModel appraisalSystemScoringModel) {
		this.appraisalSystemScoringModel = appraisalSystemScoringModel;
	}

	public AppraisalSystemScoring getSelectedAppraisalSystemScoring() {
		return selectedAppraisalSystemScoring;
	}

	public void setSelectedAppraisalSystemScoring(AppraisalSystemScoring selectedAppraisalSystemScoring) {
		this.selectedAppraisalSystemScoring = selectedAppraisalSystemScoring;
	}

	public Boolean getIsDisable() {
		return isDisable;
	}

	public void setIsDisable(Boolean isDisable) {
		this.isDisable = isDisable;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

}
