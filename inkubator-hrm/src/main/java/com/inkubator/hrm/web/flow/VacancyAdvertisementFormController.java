
package com.inkubator.hrm.web.flow;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

import ch.lambdaj.Lambda;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.RecruitAdvertisementMedia;
import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisement;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisementDetail;
import com.inkubator.hrm.entity.RmbsApplication;
import com.inkubator.hrm.service.RecruitAdvertisementMediaService;
import com.inkubator.hrm.service.RecruitHireApplyService;
import com.inkubator.hrm.service.RecruitVacancyAdvertisementService;
import com.inkubator.hrm.web.model.VacancyAdvertisementDetailModel;
import com.inkubator.hrm.web.model.VacancyAdvertisementModel;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@Component(value = "vacancyAdvertisementFormController")
@Lazy
public class VacancyAdvertisementFormController implements Serializable {

	org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(getClass());
	
	@Autowired
	private RecruitVacancyAdvertisementService recruitVacancyAdvertisementService;
	@Autowired
	private RecruitAdvertisementMediaService recruitAdvertisementMediaService;
	@Autowired
	private RecruitHireApplyService recruitHireApplyService;
	
	public void initialProcessFlow(RequestContext context){
		try {			
			//initialization
			List<RecruitAdvertisementMedia> listAdvertisementMedia = recruitAdvertisementMediaService.getAllData();
			context.getFlowScope().put("listAdvertisementMedia", listAdvertisementMedia);
			List<RecruitHireApply> listHireApply = recruitHireApplyService.getAllData();
			context.getFlowScope().put("listHireApply", listHireApply);
			
			//binding value to model
			Long id = context.getFlowScope().getLong("id");	
			VacancyAdvertisementModel model = new VacancyAdvertisementModel();	
			model.setVacancyAdvCode(HRMConstant.VACANCY_ADVERTISEMENT_KODE + "-" + RandomNumberUtil.getRandomNumber(9));
			if(id != null){			
				RecruitVacancyAdvertisement entity = recruitVacancyAdvertisementService.getEntityByPkWithDetail(id);
				model = getModelFromEntity(entity);				
			}
			context.getFlowScope().put("model", model);
			
		} catch (Exception ex) {
			LOGGER.error("Error", ex);
		}
	}
	
	public String doAdvertisementValidation(RequestContext context){
		String message = "success";
		VacancyAdvertisementModel model = (VacancyAdvertisementModel) context.getFlowScope().get("model");
		if(model.getListAdvertisementDetail() == null){
			//if still null then create an Object
			List<VacancyAdvertisementDetailModel> listAdvertisementDetail = new ArrayList<VacancyAdvertisementDetailModel>();
			model.setListAdvertisementDetail(listAdvertisementDetail);
		}
		context.getFlowScope().put("model", model);
		return message;
	}
	
	public String doAddRecruitmentReqValidation(RequestContext context){
		String message = "success";
		VacancyAdvertisementDetailModel detailModel = (VacancyAdvertisementDetailModel) context.getFlowScope().get("detailModel");
		VacancyAdvertisementModel model = (VacancyAdvertisementModel) context.getFlowScope().get("model");
		List<VacancyAdvertisementDetailModel> listAdvertisementDetail =  model.getListAdvertisementDetail();
		if(null != Lambda.selectFirst(listAdvertisementDetail, Lambda.having(Lambda.on(VacancyAdvertisementDetailModel.class).getHireApplyCode(), Matchers.equalToIgnoringCase(detailModel.getHireApplyCode())))){
			//kirim error message, tidak boleh ada hireApply yg sama dalam satu list
			MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "vacancy_advertisement.error_code_request_already_selected", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
			message = "error";
		} else {
			//tambahkan ke list
			listAdvertisementDetail.add(detailModel);
			model.setListAdvertisementDetail(listAdvertisementDetail);
			context.getFlowScope().put("model", model);
		}
		
		return message;
	}
	
	public String doUpdateRecruitmentReqValidation(RequestContext context){
		String message = "success";
		VacancyAdvertisementDetailModel detailModel = (VacancyAdvertisementDetailModel) context.getFlowScope().get("detailModel");
		VacancyAdvertisementModel model = (VacancyAdvertisementModel) context.getFlowScope().get("model");
		List<VacancyAdvertisementDetailModel> listAdvertisementDetail =  model.getListAdvertisementDetail();
		int index =0;
		for(VacancyAdvertisementDetailModel advertisementDetail : listAdvertisementDetail){
			if(StringUtils.equals(advertisementDetail.getHireApplyCode(), detailModel.getHireApplyCode()) && index!=detailModel.getIndexList()){
				message = "error";
				//kirim error message, tidak boleh ada hireApply yg sama dalam satu list
				MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "vacancy_advertisement.error_code_request_already_selected", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
				return message;
			}
			index++;
		}
			
		//replace object ke list, berdasarkan index di list nya
		listAdvertisementDetail.set(detailModel.getIndexList(), detailModel);
		model.setListAdvertisementDetail(listAdvertisementDetail);
		context.getFlowScope().put("model", model);
		
		return message;
	}
	
	public void doAddRecruitmentReq(RequestContext context){
		//initialization object new
		VacancyAdvertisementDetailModel detailModel = (VacancyAdvertisementDetailModel) context.getFlowScope().get("detailModel");
		detailModel =  new VacancyAdvertisementDetailModel();
		context.getFlowScope().put("detailModel", detailModel);
	}
	
	public void doUpdateRecruitmentReq(RequestContext context){
		VacancyAdvertisementDetailModel detailModel = (VacancyAdvertisementDetailModel) context.getFlowScope().get("detailModel");
		VacancyAdvertisementModel model = (VacancyAdvertisementModel) context.getFlowScope().get("model");
		List<VacancyAdvertisementDetailModel> listAdvertisementDetail =  model.getListAdvertisementDetail();
		
		//get index nya dalam list, untuk digunakan pas proses update (yg belum persist ke database alias belum dapat ID)
		int index =listAdvertisementDetail.indexOf(detailModel);
		detailModel.setIndexList(index);
		
		context.getFlowScope().put("detailModel", detailModel);
	}
	
	public void doDeleteRecruitmentReq(RequestContext context){
		VacancyAdvertisementDetailModel detailModel = (VacancyAdvertisementDetailModel) context.getFlowScope().get("detailModel");
		VacancyAdvertisementModel model = (VacancyAdvertisementModel) context.getFlowScope().get("model");
		List<VacancyAdvertisementDetailModel> listAdvertisementDetail =  model.getListAdvertisementDetail();
		
		//remote object dalam list
		listAdvertisementDetail.remove(detailModel);
		model.setListAdvertisementDetail(listAdvertisementDetail);
		context.getFlowScope().put("model", model);
	}
	
	public String doSave(RequestContext context){
		String message = "error";
		
		VacancyAdvertisementModel model = (VacancyAdvertisementModel) context.getFlowScope().get("model");
		RecruitVacancyAdvertisement entity = getEntityFromModel(model);
        try {
        	if(entity.getId() == null) {
	            message = recruitVacancyAdvertisementService.saveWithApproval(entity);
	            if (StringUtils.equals(message, "success_need_approval")) {                
	                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	            } else {
	                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	            }
	            
	            //tujuannya agar waktu redirect dari flow ke detail, sudah didapatkan id-nya
				model.setId(entity.getId());
				context.getFlowScope().put("model", model);
				
        	} else {
        		recruitVacancyAdvertisementService.update(entity);
        		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        		
        		message = "success_without_approval";
        	}

        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
	}
	
	private RecruitVacancyAdvertisement getEntityFromModel(VacancyAdvertisementModel model){
		RecruitVacancyAdvertisement vacancyAdvertisement = new RecruitVacancyAdvertisement();
		vacancyAdvertisement.setId(model.getId());
		vacancyAdvertisement.setVacancyAdvCode(model.getVacancyAdvCode());
		vacancyAdvertisement.setEffectiveDate(model.getEffectiveDate());
		vacancyAdvertisement.setAdvertisementMedia(new RecruitAdvertisementMedia(model.getAdvertisementMediaId()));
		
		Set<RecruitVacancyAdvertisementDetail> listAdvertisementDetail =  new HashSet<RecruitVacancyAdvertisementDetail>();
		for(VacancyAdvertisementDetailModel detailModel:model.getListAdvertisementDetail()){
			RecruitVacancyAdvertisementDetail advertisementDetail =  new RecruitVacancyAdvertisementDetail();
			advertisementDetail.setId(advertisementDetail.getId());
			advertisementDetail.setVacancyAdvertisement(vacancyAdvertisement);
			advertisementDetail.setHireApply(new RecruitHireApply(detailModel.getHireApplyId()));
			advertisementDetail.setCost(detailModel.getCost());
			advertisementDetail.setPublishStart(detailModel.getPublishStart());
			advertisementDetail.setPublishEnd(detailModel.getPublishEnd());
			
			listAdvertisementDetail.add(advertisementDetail);
		}
		vacancyAdvertisement.setRecruitVacancyAdvertisementDetails(listAdvertisementDetail);
		
		return vacancyAdvertisement;
	}
	
	private VacancyAdvertisementModel getModelFromEntity(RecruitVacancyAdvertisement entity){
		VacancyAdvertisementModel model = new VacancyAdvertisementModel();
		model.setId(entity.getId());
		model.setVacancyAdvCode(entity.getVacancyAdvCode());
		model.setEffectiveDate(entity.getEffectiveDate());
		model.setAdvertisementMediaId(entity.getAdvertisementMedia().getId());
		model.setAdvertisementMediaName(entity.getAdvertisementMedia().getName());
		model.setAdvertisementMediaAddress(entity.getAdvertisementMedia().getAddress());
		model.setAdvertisementMediaPhone(entity.getAdvertisementMedia().getPhone());
		model.setAdvertisementMediaContact(entity.getAdvertisementMedia().getContactPerson());
		
		List<VacancyAdvertisementDetailModel> listAdvertisementDetail =  new ArrayList<VacancyAdvertisementDetailModel>();
		for(RecruitVacancyAdvertisementDetail det : entity.getRecruitVacancyAdvertisementDetails()){
			VacancyAdvertisementDetailModel advertisementDetailModel =  new VacancyAdvertisementDetailModel();
			advertisementDetailModel.setId(det.getId());
			advertisementDetailModel.setVacancyAdvertisementId(model.getId());
			advertisementDetailModel.setCost(det.getCost());
			advertisementDetailModel.setPublishStart(det.getPublishStart());
			advertisementDetailModel.setPublishEnd(det.getPublishEnd());
			advertisementDetailModel.setDescription(det.getDescription());
			advertisementDetailModel.setHireApplyId(det.getHireApply().getId());
			advertisementDetailModel.setHireApplyCode(det.getHireApply().getReqHireCode());
			advertisementDetailModel.setJabatanName(det.getHireApply().getJabatan().getName());
			advertisementDetailModel.setStaffCount(det.getHireApply().getCandidateCountRequest());
			
			listAdvertisementDetail.add(advertisementDetailModel);
		}
		model.setListAdvertisementDetail(listAdvertisementDetail);
		
		return model;
	}
	
	public void onChangeAdvertisementMedia(RequestContext context){
		try {
			VacancyAdvertisementModel model = (VacancyAdvertisementModel) context.getFlowScope().get("model");
			RecruitAdvertisementMedia advertisementMedia =  recruitAdvertisementMediaService.getEntiyByPK(model.getAdvertisementMediaId());
			
			model.setAdvertisementMediaName(advertisementMedia.getName());
			model.setAdvertisementMediaAddress(advertisementMedia.getAddress());
			model.setAdvertisementMediaContact(advertisementMedia.getContactPerson());
			model.setAdvertisementMediaPhone(advertisementMedia.getPhone());
			
			context.getFlowScope().put("model", model);
		} catch (Exception ex) {
			LOGGER.error("Error", ex);
		}
	}
	
	public void onChangeHireApply(RequestContext context){
		try {
			VacancyAdvertisementDetailModel detailModel = (VacancyAdvertisementDetailModel) context.getFlowScope().get("detailModel");
			RecruitHireApply hireApply = recruitHireApplyService.getEntityByPkWithDetail(detailModel.getHireApplyId());
			
			detailModel.setHireApplyId(hireApply.getId());
			detailModel.setHireApplyCode(hireApply.getReqHireCode());
			detailModel.setJabatanName(hireApply.getJabatan().getName());
			detailModel.setStaffCount(hireApply.getCandidateCountRequest());			
			
			context.getFlowScope().put("detailModel", detailModel);
		} catch (Exception ex) {
			LOGGER.error("Error", ex);
		}
	}
	
	public void doResetAddAdvertisement(RequestContext context){
		VacancyAdvertisementModel model = (VacancyAdvertisementModel) context.getFlowScope().get("model");
		
		//reset value
		model.setEffectiveDate(null);
		model.setAdvertisementMediaId(null);
		model.setAdvertisementMediaName(null);
		model.setAdvertisementMediaAddress(null);
		model.setAdvertisementMediaPhone(null);
		model.setAdvertisementMediaContact(null);
		
		context.getFlowScope().put("model", model);
	}
	
	public void doResetAddRecruitmentReq(RequestContext context){
		VacancyAdvertisementDetailModel detailModel = (VacancyAdvertisementDetailModel) context.getFlowScope().get("detailModel");
		
		//reset value
		detailModel.setHireApplyId(null);
		detailModel.setHireApplyCode(null);
		detailModel.setJabatanName(null);
		detailModel.setStaffCount(null);
		detailModel.setPublishStart(null);
		detailModel.setPublishEnd(null);
		detailModel.setCost(null);
		detailModel.setDescription(null);
		
		context.getFlowScope().put("detailModel", detailModel);
	}
	
}
