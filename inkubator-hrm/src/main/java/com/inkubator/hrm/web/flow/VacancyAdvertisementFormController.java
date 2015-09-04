
package com.inkubator.hrm.web.flow;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

import ch.lambdaj.Lambda;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.RecruitAdvertisementMedia;
import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisement;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisementDetail;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.RecruitAdvertisementMediaService;
import com.inkubator.hrm.service.RecruitHireApplyService;
import com.inkubator.hrm.service.RecruitVacancyAdvertisementService;
import com.inkubator.hrm.service.TransactionCodeficationService;
import com.inkubator.hrm.util.KodefikasiUtil;
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
	@Autowired
	private ApprovalActivityService approvalActivityService;
	@Autowired
	private TransactionCodeficationService transactionCodeficationService;
	
	public void initialProcessFlow(RequestContext context){
		try {			
			//initialization
			List<RecruitAdvertisementMedia> listAdvertisementMedia = recruitAdvertisementMediaService.getAllData();
			context.getFlowScope().put("listAdvertisementMedia", listAdvertisementMedia);
			List<RecruitHireApply> listHireApply = recruitHireApplyService.getAllData();
			context.getFlowScope().put("listHireApply", listHireApply);				
			VacancyAdvertisementModel model = null;				
			
			/**di cek terlebih dahulu, apakah datang dari approval atau bukan
			 * jika datangnya dari proses approval, artinya user akan melakukan revisi data yg masih dalam bentuk json */
			Long id = context.getFlowScope().getLong("id");
			Long activity = context.getFlowScope().getLong("activity");
			if(id != null){			
				RecruitVacancyAdvertisement entity = recruitVacancyAdvertisementService.getEntityByPkWithDetail(id);
				model = getModelFromEntity(entity);				
			} else if(activity != null) {
				//additional, for approval information purpose
                ApprovalActivity currentActivity = approvalActivityService.getEntityByPkWithDetail(activity);
                ApprovalActivity askingRevisedActivity = approvalActivityService.getEntityByActivityNumberAndSequence(currentActivity.getActivityNumber(),
                        currentActivity.getSequence() - 1);
                
                //parsing data from json to object
                model = getModelFromJson(currentActivity.getPendingData());               
				model.setIsRevised(Boolean.TRUE);
				model.setCurrentActivity(currentActivity);
				model.setAskingRevisedActivity(askingRevisedActivity);
				
			} else {
				//default value
				model = new VacancyAdvertisementModel();
				//set kodefikasi nomor
		    	TransactionCodefication transactionCodefication = transactionCodeficationService.getEntityByModulCode(HRMConstant.VACANCY_ADVERTISEMENT_KODE);
		    	if(!ObjectUtils.equals(transactionCodefication, null)){
		    		model.setVacancyAdvCode(KodefikasiUtil.getKodefikasiOnlyPattern(transactionCodefication.getCode()));
		    	}
				List<VacancyAdvertisementDetailModel> listAdvertisementDetail = new ArrayList<VacancyAdvertisementDetailModel>();
				model.setListAdvertisementDetail(listAdvertisementDetail);
				
			}
			
			VacancyAdvertisementDetailModel detailModel = new VacancyAdvertisementDetailModel();
			context.getFlowScope().put("model", model);
			context.getFlowScope().put("detailModel", detailModel);
			
		} catch (Exception ex) {
			LOGGER.error("Error", ex);
		}
	}
	
	public String doAddRecruitmentRequestValidation(RequestContext context){
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
	
	public String doUpdateRecruitmentRequestValidation(RequestContext context){
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
	
	public void initAddRecruitmentRequest(RequestContext context){
		//initialization object new
		VacancyAdvertisementDetailModel detailModel = (VacancyAdvertisementDetailModel) context.getFlowScope().get("detailModel");
		detailModel =  new VacancyAdvertisementDetailModel();
		context.getFlowScope().put("detailModel", detailModel);
	}
	
	public void initUpdateRecruitmentRequest(RequestContext context){
		VacancyAdvertisementDetailModel detailModel = (VacancyAdvertisementDetailModel) context.getFlowScope().get("detailModel");
		VacancyAdvertisementModel model = (VacancyAdvertisementModel) context.getFlowScope().get("model");
		List<VacancyAdvertisementDetailModel> listAdvertisementDetail =  model.getListAdvertisementDetail();
		
		//get index nya dalam list, untuk digunakan pas proses update (yg belum persist ke database alias belum dapat ID)
		int index =listAdvertisementDetail.indexOf(detailModel);
		detailModel.setIndexList(index);
		
		context.getFlowScope().put("detailModel", detailModel);
	}
	
	public void doDeleteRecruitmentRequest(RequestContext context){
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
        return message;
	}
	
	public String doRevised(RequestContext context){
		String message = "error";
		
		VacancyAdvertisementModel model = (VacancyAdvertisementModel) context.getFlowScope().get("model");
		RecruitVacancyAdvertisement entity = getEntityFromModel(model);
        try {
        	if(entity.getId() == null) {
	            message = recruitVacancyAdvertisementService.saveWithRevised(entity, model.getCurrentActivity().getId());
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
        return message;
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
	
	private VacancyAdvertisementModel getModelFromJson(String json) throws Exception {
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
		RecruitVacancyAdvertisement entity = gson.fromJson(json, RecruitVacancyAdvertisement.class);
		
		JsonObject jsonObject =  gson.fromJson(json, JsonObject.class);
		List<RecruitVacancyAdvertisementDetail> recruitVacancyAdvertisementDetails = gson.fromJson(jsonObject.get("recruitVacancyAdvertisementDetails"), new TypeToken<List<RecruitVacancyAdvertisementDetail>>(){}.getType());		
		
		//relational object
		for(RecruitVacancyAdvertisementDetail advertisementDetail: recruitVacancyAdvertisementDetails){
			RecruitHireApply hireApply = recruitHireApplyService.getEntityByPkWithDetail(advertisementDetail.getHireApply().getId());
			advertisementDetail.setHireApply(hireApply);
		}
		
		//convert List to HashSet
		entity.setRecruitVacancyAdvertisementDetails(new HashSet<RecruitVacancyAdvertisementDetail>(recruitVacancyAdvertisementDetails));
		
		return this.getModelFromEntity(entity);
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
	
	public void doResetAddRecruitmentRequest(RequestContext context){
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
