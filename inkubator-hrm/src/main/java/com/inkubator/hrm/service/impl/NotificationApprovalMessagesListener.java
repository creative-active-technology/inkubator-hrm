/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.NumberTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.inkubator.common.notification.model.VelocityTempalteModel;
import com.inkubator.common.notification.service.VelocityTemplateSender;
import com.inkubator.common.util.JsonConverter;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.WtEmpCorrectionAttendanceDetail;
import com.inkubator.hrm.json.util.DateJsonDeserializer;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.web.model.VacancyAdvertisementDetailModel;

/**
 *
 * @author rizkykojek
 */
public class NotificationApprovalMessagesListener extends IServiceImpl implements MessageListener {
    
    private String applicationUrl;
    private String serverName;
    private String applicationName;
    private String ownerEmail;
    private String ownerCompany;
    private String ownerAdministrator;
    
    @Autowired
    private JsonConverter jsonConverter;
    @Autowired
    private VelocityTemplateSender velocityTemplateSender;
    @Autowired
    private ApprovalActivityDao approvalActivityDao;
    @Autowired
    private HrmUserDao hrmUserDao;
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 50, rollbackFor = Exception.class)
    public void onMessage(Message message) {
        try {
            LOGGER.info("Begin Send Email Approval");
            TextMessage textMessage = (TextMessage) message;
            String json = textMessage.getText();
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("dd-MMMM-yyyy");
            Gson gson = gsonBuilder.create();
            Gson gsonDateSerializer = JsonUtil.getHibernateEntityGsonBuilder().registerTypeAdapter(Date.class, new DateJsonDeserializer()).create();
            JsonObject jsonObject = (JsonObject) gson.fromJson(json, JsonObject.class);
            String locale = jsonObject.get("locale").getAsString();
          
            ApprovalActivity appActivity = approvalActivityDao.getEntiyByPK(jsonObject.get("approvalActivityId").getAsLong());
           
            HrmUser approverUser = hrmUserDao.getByUserId(appActivity.getApprovedBy());
            HrmUser requesterUser = hrmUserDao.getByUserId(appActivity.getRequestBy());
            
            VelocityTempalteModel vtm = new VelocityTempalteModel();
            List<String> toSend = new ArrayList<>();
            List<String> toSentCC = new ArrayList<String>();
            List<String> toSentBCC = new ArrayList<String>();
            
            vtm.setFrom(ownerEmail);
            /*if(appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING) {
             //kirim email ke approver nya jika status waiting
             toSend.add(approverUser.getEmailAddress()); 
             } else if(appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_CANCELLED){
             //kirim email ke approver nya jika status cancelled. Dan cc email ke requester
             toSend.add(approverUser.getEmailAddress());
             toSentCC.add(requesterUser.getEmailAddress());
             } else if((appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED) && appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED) {
             //kirim email ke requester nya jika statusnya sudah di approved/rejected. Dan cc email (if any)
             toSend.add(requesterUser.getEmailAddress()); 
             for(JsonElement el:jsonObject.get("ccEmailAddresses").getAsJsonArray()){
             toSentCC.add(el.getAsString());
             }
             }*/
            toSend.add("deni.arianto1606@gmail.com");
            toSend.add("rizal2_dhfr@yahoo.com");
//            toSend.add("yosa.mareta@gmail.com");
            toSend.add("guntur@incubatechnology.com");
            toSentCC.add("rizkykojek@gmail.com");
            toSentCC.add("amjadicky@gmail.com");
            vtm.setTo(toSend.toArray(new String[toSend.size()]));
            vtm.setCc(toSentCC.toArray(new String[toSentCC.size()]));
            vtm.setBcc(toSentBCC.toArray(new String[toSentBCC.size()]));
            
            Map maptoSend = new HashMap();
            if (StringUtils.equals(locale, "en")) {
                //not yet implemented

            } else {
                if (Objects.equals(appActivity.getApprovalStatus(), HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL)) {
                    //configure email parameter based on approval name
                    switch (appActivity.getApprovalDefinition().getName()) {
	                    case HRMConstant.VACANCY_ADVERTISEMENT:
	                    	List<VacancyAdvertisementDetailModel> listAdvertisementDetail = gsonDateSerializer.fromJson(jsonObject.get("listAdvertisementDetail").getAsString(), new TypeToken<List<VacancyAdvertisementDetailModel>>() {}.getType());
	                        
	                        vtm.setSubject("Pengajuan Iklan Lowongan");
	                        vtm.setTemplatePath("email_vacancy_advertisement_waiting_approval.vm");
	                        maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
	                        maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
	                        maptoSend.put("nik", requesterUser.getEmpData().getNik());
	                        maptoSend.put("effectiveDate", jsonObject.get("effectiveDate").getAsString());
	                        maptoSend.put("advertisementMediaName", jsonObject.get("advertisementMediaName").getAsString());
	                        maptoSend.put("applyDate", jsonObject.get("applyDate").getAsString());
	                        maptoSend.put("listAdvertisementDetail", listAdvertisementDetail);
	                        maptoSend.put("dateTool", new DateTool());
	                        maptoSend.put("numTool", new NumberTool());
	                        maptoSend.put("locale", new Locale(locale));
	                        break;
                        
	                    case HRMConstant.EMP_CORRECTION_ATTENDANCE:
	                    	List<WtEmpCorrectionAttendanceDetail> listCorrectionAttendance = gsonDateSerializer.fromJson(jsonObject.get("listCorrectionAttendance").getAsString(), new TypeToken<List<WtEmpCorrectionAttendanceDetail>>() {}.getType());
	                        
	                        vtm.setTemplatePath("email_correction_attendance_waiting_approval.vm");
	                        maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
	                        maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
	                        maptoSend.put("nik", requesterUser.getEmpData().getNik());
	                        maptoSend.put("startDate", jsonObject.get("startDate").getAsString());
	                        maptoSend.put("endDate", jsonObject.get("endDate").getAsString());
	                        maptoSend.put("applyDate", jsonObject.get("applyDate").getAsString());
	                        maptoSend.put("listCorrectionAttendance", listCorrectionAttendance);
	                        maptoSend.put("dateTool", new DateTool());
	                        maptoSend.put("locale", new Locale(locale));
	                        break;
                        
                        case HRMConstant.BUSINESS_TRAVEL:
                            vtm.setSubject("Permohonan Perjalanan Dinas");
                            vtm.setTemplatePath("email_travel_waiting_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("businessTravelNo", jsonObject.get("businessTravelNo").getAsString());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("destination", jsonObject.get("destination").getAsString());
                            maptoSend.put("start", jsonObject.get("startDate").getAsString());
                            maptoSend.put("end", jsonObject.get("endDate").getAsString());
                            maptoSend.put("description", jsonObject.get("description").getAsString());
                            maptoSend.put("totalAmount", jsonObject.get("totalAmount").getAsString());
                            maptoSend.put("deadline", jsonObject.get("deadline").getAsString());
                            
                            break;
                        
                        case HRMConstant.REIMBURSEMENT:
                            vtm.setSubject("Permohonan Penggantian Biaya");
                            vtm.setTemplatePath("email_reimbursment_waiting_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("reimbursementType", jsonObject.get("reimbursementType").getAsString());
                            maptoSend.put("applicationDate", jsonObject.get("applicationDate").getAsString());
                            maptoSend.put("nominal", jsonObject.get("nominal").getAsString());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("deadline", jsonObject.get("deadline").getAsString());
                            
                            break;
                            
                        case HRMConstant.REIMBURSEMENT_DISBURSEMENT:
                            vtm.setSubject("Permohonan Pencairan Penggantian Biaya");
                            vtm.setTemplatePath("email_rmbs_disbursement_waiting_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("deadline", jsonObject.get("deadline").getAsString());
                            break;
                        
                        case HRMConstant.LOAN:
                            vtm.setSubject("Permohonan Pinjaman Lunak");
                            vtm.setTemplatePath("email_loan_waiting_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("loanSchemaName", jsonObject.get("loanSchemaName").getAsString());
                            maptoSend.put("nominalPrincipal", jsonObject.get("nominalPrincipal").getAsString());
                            maptoSend.put("interestRate", jsonObject.get("interestRate").getAsString());
                            maptoSend.put("nominalInstallment", jsonObject.get("nominalInstallment").getAsString());
                            maptoSend.put("interestInstallment", jsonObject.get("interestInstallment").getAsString());
                            maptoSend.put("totalNominalInstallment", jsonObject.get("totalNominalInstallment").getAsString());
                            break;
                        
                        case HRMConstant.SHIFT_SCHEDULE:
                            vtm.setSubject("Permohonan Perubahan Jadwal Kerja Karyawan");
                            vtm.setTemplatePath("email_shift_schedule_waiting_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("jabatan", requesterUser.getEmpData().getJabatanByJabatanId().getName());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            break;
                        
                        case HRMConstant.LEAVE:
                            vtm.setSubject("Permohonan Cuti");
                            vtm.setTemplatePath("email_leave_waiting_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("leaveName", jsonObject.get("leaveName").getAsString());
                            maptoSend.put("startDate", jsonObject.get("startDate").getAsString());
                            maptoSend.put("endDate", jsonObject.get("endDate").getAsString());
                            maptoSend.put("fillingDate", jsonObject.get("fillingDate").getAsString());
                            maptoSend.put("materialJobsAbandoned", jsonObject.get("materialJobsAbandoned").getAsString());
                            break;
                        
                        case HRMConstant.LEAVE_CANCELLATION:
                            vtm.setSubject("Permohonan Pembatalan Cuti");
                            vtm.setTemplatePath("email_leave_cancellation_waiting_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("leaveName", jsonObject.get("leaveName").getAsString());
                            maptoSend.put("startDate", jsonObject.get("startDate").getAsString());
                            maptoSend.put("endDate", jsonObject.get("endDate").getAsString());
                            maptoSend.put("fillingDate", jsonObject.get("fillingDate").getAsString());
                            maptoSend.put("materialJobsAbandoned", jsonObject.get("materialJobsAbandoned").getAsString());
                            maptoSend.put("cancellationDate", jsonObject.get("cancellationDate").getAsString());
                            break;
                        
                        case HRMConstant.OVERTIME:
                            vtm.setSubject("Permohonan Lembur");
                            vtm.setTemplatePath("email_overtime_waiting_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("overTimeName", jsonObject.get("overTimeName").getAsString());
                            maptoSend.put("hour", jsonObject.get("hour").getAsString());
                            maptoSend.put("minute", jsonObject.get("minute").getAsString());
                            maptoSend.put("overTimeDate", jsonObject.get("overTimeDate").getAsString());
                            maptoSend.put("implementationNumber", jsonObject.get("implementationNumber").getAsString());
                            break;
                            
                        case HRMConstant.ANNOUNCEMENT:
                        	TypeToken<List<String>> token = new TypeToken<List<String>>() {};
                            List<String> dataGolonganJabatan = gson.fromJson(jsonObject.get("listGolonganJabatan"), token.getType());
                            List<String> dataUnitKerja = gson.fromJson(jsonObject.get("listUnitKerja"), token.getType());
                            List<String> dataEmployeeType = gson.fromJson(jsonObject.get("listEmployeeType"), token.getType());
                            
                            vtm.setSubject("Pengajuan Pengumuman");
                            vtm.setTemplatePath("email_announcement_waiting_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("subjek", jsonObject.get("subjek").getAsString());
                            maptoSend.put("content", jsonObject.get("content").getAsString());
                            maptoSend.put("company", jsonObject.get("company").getAsString());
                            maptoSend.put("listEmployeeType", dataEmployeeType);
                            maptoSend.put("listUnitKerja", dataUnitKerja);
                            maptoSend.put("listGolonganJabatan", dataGolonganJabatan);
                            break;
                        case HRMConstant.PERMIT:
                        	vtm.setSubject("Permohonan Izin");
                            vtm.setTemplatePath("email_permit_waiting_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("startDate", jsonObject.get("startDate").getAsString());
                            maptoSend.put("endDate", jsonObject.get("endDate").getAsString());
                            maptoSend.put("permitClassification", jsonObject.get("permitClassification").getAsString());
                            break;
                        case HRMConstant.RECRUITMENT_REQUEST:
                        	vtm.setSubject("PENGAJUAN PERMINTAAN TENAGA KERJA");
                            vtm.setTemplatePath("email_recruitment_request_waiting_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("periodeStart", jsonObject.get("periodeStart").getAsString());
                            maptoSend.put("periodeEnd", jsonObject.get("periodeEnd").getAsString());
                            maptoSend.put("jabatan", jsonObject.get("jabatan").getAsString());
                            maptoSend.put("mppName", jsonObject.get("mppName").getAsString());
                            maptoSend.put("salaryMin", jsonObject.get("salaryMin").getAsString());
                            maptoSend.put("salaryMax", jsonObject.get("salaryMax").getAsString());
                            maptoSend.put("candidateCountRequest", jsonObject.get("candidateCountRequest").getAsString());
                            break;
                        case HRMConstant.RECRUIT_MPP_APPLY:
                        	TypeToken<List<String>> token2 = new TypeToken<List<String>>() {};
                        	List<String> listNamaJabatan = gson.fromJson(jsonObject.get("listNamaJabatan"), token2.getType());
                            
                            vtm.setSubject("PERSETUJUAN RENCANA KETENAGAKERJAAN");
                            vtm.setTemplatePath("email_mpp_apply_waiting_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("recruitMppApplyName", jsonObject.get("recruitMppApplyName").getAsString());
                            maptoSend.put("applyDate", jsonObject.get("applyDate").getAsString());
                            maptoSend.put("startDate", jsonObject.get("startDate").getAsString());
                            maptoSend.put("endDate", jsonObject.get("endDate").getAsString());
                            maptoSend.put("listNamaJabatan", listNamaJabatan);
                            break;
                        case HRMConstant.EMPLOYEE_CAREER_TRANSITION:
                            vtm.setSubject("Pengajuan Transisi Karir");
                            vtm.setTemplatePath("email_career_transition_waiting_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("beforeNik", jsonObject.get("beforeNik").getAsString());
                            maptoSend.put("beforeJoinDate", jsonObject.get("beforeJoinDate").getAsString());
                            maptoSend.put("beforeEmployeeType", jsonObject.get("beforeEmployeeType").getAsString());
                            maptoSend.put("beforeJabatan", jsonObject.get("beforeJabatan").getAsString());
                            maptoSend.put("beforeDepartment", jsonObject.get("beforeDepartment").getAsString());
                            maptoSend.put("afterNik", jsonObject.get("afterNik").getAsString());
                            maptoSend.put("afterJoinDate", jsonObject.get("afterJoinDate").getAsString());
                            maptoSend.put("afterEmployeeType", jsonObject.get("afterEmployeeType").getAsString());
                            maptoSend.put("afterJabatan", jsonObject.get("afterJabatan").getAsString());
                            maptoSend.put("afterDepartment", jsonObject.get("afterDepartment").getAsString());
                            break;
                        default:
                            break;
                    }
                    
                } else if ((appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED)
                        || (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED)) {
                    //configure email parameter based on approval name	
                    switch (appActivity.getApprovalDefinition().getName()) {
	                    case HRMConstant.VACANCY_ADVERTISEMENT:
	                    	List<VacancyAdvertisementDetailModel> listAdvertisementDetail = gsonDateSerializer.fromJson(jsonObject.get("listAdvertisementDetail").getAsString(), new TypeToken<List<VacancyAdvertisementDetailModel>>() {}.getType());

	                    	vtm.setSubject("Pengajuan Iklan Lowongan");
	                        vtm.setTemplatePath("email_vacancy_advertisement_approved_or_rejected.vm");
	                        maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
	                        maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
	                        maptoSend.put("nik", requesterUser.getEmpData().getNik());
	                        maptoSend.put("effectiveDate", jsonObject.get("effectiveDate").getAsString());
	                        maptoSend.put("advertisementMediaName", jsonObject.get("advertisementMediaName").getAsString());
	                        maptoSend.put("applyDate", jsonObject.get("applyDate").getAsString());
	                        maptoSend.put("listAdvertisementDetail", listAdvertisementDetail);
	                        maptoSend.put("dateTool", new DateTool());
	                        maptoSend.put("numTool", new NumberTool());
	                        maptoSend.put("locale", new Locale(locale));
	                        maptoSend.put("statusDesc", getStatusDesc(appActivity.getApprovalStatus(), locale));
	                        break;
                        
	                    case HRMConstant.EMP_CORRECTION_ATTENDANCE:
	                    	List<WtEmpCorrectionAttendanceDetail> listCorrectionAttendance = gsonDateSerializer.fromJson(jsonObject.get("listCorrectionAttendance").getAsString(), new TypeToken<List<WtEmpCorrectionAttendanceDetail>>() {}.getType());
	                        
	                        vtm.setSubject("Pengajuan Koreksi Kehadiran");
	                        vtm.setTemplatePath("email_correction_attendance_approved_or_rejected.vm");
	                        maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
	                        maptoSend.put("nik", requesterUser.getEmpData().getNik());
	                        maptoSend.put("startDate", jsonObject.get("startDate").getAsString());
	                        maptoSend.put("endDate", jsonObject.get("endDate").getAsString());
	                        maptoSend.put("applyDate", jsonObject.get("applyDate").getAsString());
	                        maptoSend.put("listCorrectionAttendance", listCorrectionAttendance);
	                        maptoSend.put("dateTool", new DateTool());
	                        maptoSend.put("locale", new Locale(locale));
	                        maptoSend.put("statusDesc", getStatusDesc(appActivity.getApprovalStatus(), locale));
	                        break;
                        
                    	case HRMConstant.BUSINESS_TRAVEL:
                            vtm.setSubject("Permohonan Perjalanan Dinas");
                            vtm.setTemplatePath("email_travel_approved_or_rejected_approval.vm");
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("businessTravelNo", jsonObject.get("businessTravelNo").getAsString());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("destination", jsonObject.get("destination").getAsString());
                            maptoSend.put("start", jsonObject.get("startDate").getAsString());
                            maptoSend.put("end", jsonObject.get("endDate").getAsString());
                            maptoSend.put("description", jsonObject.get("description").getAsString());
                            maptoSend.put("totalAmount", jsonObject.get("totalAmount").getAsString());
                            maptoSend.put("statusDesc", getStatusDesc(appActivity.getApprovalStatus(), locale));
                            break;
                        
                        case HRMConstant.REIMBURSEMENT:
                            vtm.setSubject("Permohonan Pergantian Biaya");
                            vtm.setTemplatePath("email_reimbursment_approved_or_rejected.vm");
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("reimbursementType", jsonObject.get("reimbursementType").getAsString());
                            maptoSend.put("applicationDate", jsonObject.get("applicationDate").getAsString());
                            maptoSend.put("nominal", jsonObject.get("nominal").getAsString());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("statusDesc", getStatusDesc(appActivity.getApprovalStatus(), locale));
                            break;
                            
                        case HRMConstant.REIMBURSEMENT_DISBURSEMENT:
                            vtm.setSubject("Permohonan Pencairan Pergantian Biaya");
                            vtm.setTemplatePath("email_rmbs_disbursement_approved_or_rejected.vm");
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("statusDesc", getStatusDesc(appActivity.getApprovalStatus(), locale));
                            break;
                        
                        case HRMConstant.LOAN:
                            vtm.setSubject("Permohonan Pinjaman Lunak");
                            vtm.setTemplatePath("email_loan_approved_or_rejected_approval.vm");
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("loanSchemaName", jsonObject.get("loanSchemaName").getAsString());
                            maptoSend.put("nominalPrincipal", jsonObject.get("nominalPrincipal").getAsString());
                            maptoSend.put("interestRate", jsonObject.get("interestRate").getAsString());
                            maptoSend.put("nominalInstallment", jsonObject.get("nominalInstallment").getAsString());
                            maptoSend.put("interestInstallment", jsonObject.get("interestInstallment").getAsString());
                            maptoSend.put("totalNominalInstallment", jsonObject.get("totalNominalInstallment").getAsString());
                            maptoSend.put("statusDesc", getStatusDesc(appActivity.getApprovalStatus(), locale));
                            break;
                        
                        case HRMConstant.SHIFT_SCHEDULE:
                            vtm.setSubject("Permohonan Perubahan Jadwal Kerja Karyawan");
                            vtm.setTemplatePath("email_shift_schedule_approved_or_rejected_approval.vm");
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("statusDesc", getStatusDesc(appActivity.getApprovalStatus(), locale));
                            break;
                        
                        case HRMConstant.LEAVE:
                            vtm.setSubject("Permohonan Cuti");
                            vtm.setTemplatePath("email_leave_approved_or_rejected_approval.vm");
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("leaveName", jsonObject.get("leaveName").getAsString());
                            maptoSend.put("startDate", jsonObject.get("startDate").getAsString());
                            maptoSend.put("endDate", jsonObject.get("endDate").getAsString());
                            maptoSend.put("fillingDate", jsonObject.get("fillingDate").getAsString());
                            maptoSend.put("materialJobsAbandoned", jsonObject.get("materialJobsAbandoned").getAsString());
                            maptoSend.put("statusDesc", getStatusDesc(appActivity.getApprovalStatus(), locale));
                            break;
                        
                        case HRMConstant.LEAVE_CANCELLATION:
                            vtm.setSubject("Permohonan Pembatalan Cuti");
                            vtm.setTemplatePath("email_leave_cancellation_approved_or_rejected_approval.vm");
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("leaveName", jsonObject.get("leaveName").getAsString());
                            maptoSend.put("startDate", jsonObject.get("startDate").getAsString());
                            maptoSend.put("endDate", jsonObject.get("endDate").getAsString());
                            maptoSend.put("fillingDate", jsonObject.get("fillingDate").getAsString());
                            maptoSend.put("materialJobsAbandoned", jsonObject.get("materialJobsAbandoned").getAsString());
                            maptoSend.put("cancellationDate", jsonObject.get("cancellationDate").getAsString());
                            maptoSend.put("statusDesc", getStatusDesc(appActivity.getApprovalStatus(), locale));
                            break;
                        
                        case HRMConstant.OVERTIME:
                            vtm.setSubject("Permohonan Lembur");
                            vtm.setTemplatePath("email_overtime_approved_or_rejected_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("overTimeName", jsonObject.get("overTimeName").getAsString());
                            maptoSend.put("hour", jsonObject.get("hour").getAsString());
                            maptoSend.put("minute", jsonObject.get("minute").getAsString());
                            maptoSend.put("overTimeDate", jsonObject.get("overTimeDate").getAsString());
                            maptoSend.put("implementationNumber", jsonObject.get("implementationNumber").getAsString());
                            maptoSend.put("statusDesc", getStatusDesc(appActivity.getApprovalStatus(), locale));
                            break;
                            
                        case HRMConstant.ANNOUNCEMENT:
                            TypeToken<List<String>> token = new TypeToken<List<String>>() {};
                            List<String> dataGolonganJabatan = gson.fromJson(jsonObject.get("listGolonganJabatan"), token.getType());
                            List<String> dataUnitKerja = gson.fromJson(jsonObject.get("listUnitKerja"), token.getType());
                            List<String> dataEmployeeType = gson.fromJson(jsonObject.get("listEmployeeType"), token.getType());
                            
                            vtm.setSubject("Pengajuan Pengumuman");
                            vtm.setTemplatePath("email_announcement_approved_or_rejected_approval.vm");
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("statusDesc", getStatusDesc(appActivity.getApprovalStatus(), locale));
                            maptoSend.put("subjek", jsonObject.get("subjek").getAsString());
                            maptoSend.put("content", jsonObject.get("content").getAsString());
                            maptoSend.put("company", jsonObject.get("company").getAsString());
                            maptoSend.put("listEmployeeType", dataEmployeeType);
                            maptoSend.put("listUnitKerja", dataUnitKerja);
                            maptoSend.put("listGolonganJabatan", dataGolonganJabatan);
                            break;
                        case HRMConstant.PERMIT:
                        	vtm.setSubject("Permohonan Izin");
                            vtm.setTemplatePath("email_permit_approved_and_rejected_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("startDate", jsonObject.get("startDate").getAsString());
                            maptoSend.put("endDate", jsonObject.get("endDate").getAsString());
                            maptoSend.put("permitClassification", jsonObject.get("permitClassification").getAsString());
                            maptoSend.put("statusDesc", getStatusDesc(appActivity.getApprovalStatus(), locale));
                            break;
                        case HRMConstant.RECRUITMENT_REQUEST:
                        	vtm.setSubject("PERSETUJUAN PERMINTAAN TENAGA KERJA");
                            vtm.setTemplatePath("email_recruitment_request_approved_or_reject_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("periodeStart", jsonObject.get("periodeStart").getAsString());
                            maptoSend.put("periodeEnd", jsonObject.get("periodeEnd").getAsString());
                            maptoSend.put("jabatan", jsonObject.get("jabatan").getAsString());
                            maptoSend.put("mppName", jsonObject.get("mppName").getAsString());
                            maptoSend.put("salaryMin", jsonObject.get("salaryMin").getAsString());
                            maptoSend.put("salaryMax", jsonObject.get("salaryMax").getAsString());
                            maptoSend.put("candidateCountRequest", jsonObject.get("candidateCountRequest").getAsString());
                            maptoSend.put("statusDesc", getStatusDesc(appActivity.getApprovalStatus(), locale));
                            break;
                        case HRMConstant.RECRUIT_MPP_APPLY:
                        	TypeToken<List<String>> tokens = new TypeToken<List<String>>() {};
                        	List<String> listNamaJabatan = gson.fromJson(jsonObject.get("listNamaJabatan"), tokens.getType());
                            
                            vtm.setSubject("PERSETUJUAN RENCANA KETENAGAKERJAAN");
                            vtm.setTemplatePath("email_mpp_apply_approved_or_rejected_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("recruitMppApplyName", jsonObject.get("recruitMppApplyName").getAsString());
                            maptoSend.put("applyDate", jsonObject.get("applyDate").getAsString());
                            maptoSend.put("startDate", jsonObject.get("startDate").getAsString());
                            maptoSend.put("endDate", jsonObject.get("endDate").getAsString());
                            maptoSend.put("listNamaJabatan", listNamaJabatan);
                            maptoSend.put("statusDesc", getStatusDesc(appActivity.getApprovalStatus(), locale));
                            break;
                            
                        case HRMConstant.EMPLOYEE_CAREER_TRANSITION:
                            vtm.setSubject("Pengajuan Transisi Karir");
                            if(appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED){
                            	vtm.setTemplatePath("email_career_transition_approved.vm");
                            	maptoSend.put("employeeName", requesterUser.getEmpData().getBioData().getFullName());
                            	maptoSend.put("effectiveDate", jsonObject.get("effectiveDate").getAsString());
                                maptoSend.put("beforeNik", jsonObject.get("beforeNik").getAsString());
                                maptoSend.put("beforeJoinDate", jsonObject.get("beforeJoinDate").getAsString());
                                maptoSend.put("beforeEmployeeType", jsonObject.get("beforeEmployeeType").getAsString());
                                maptoSend.put("beforeJabatan", jsonObject.get("beforeJabatan").getAsString());
                                maptoSend.put("beforeDepartment", jsonObject.get("beforeDepartment").getAsString());
                                maptoSend.put("afterNik", jsonObject.get("afterNik").getAsString());
                                maptoSend.put("afterJoinDate", jsonObject.get("afterJoinDate").getAsString());
                                maptoSend.put("afterEmployeeType", jsonObject.get("afterEmployeeType").getAsString());
                                maptoSend.put("afterJabatan", jsonObject.get("afterJabatan").getAsString());
                                maptoSend.put("afterDepartment", jsonObject.get("afterDepartment").getAsString());
                            } else {
                            	vtm.setTemplatePath("email_career_transition_rejected.vm");
                            	maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            	maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                                maptoSend.put("beforeNik", jsonObject.get("beforeNik").getAsString());
                                maptoSend.put("beforeJoinDate", jsonObject.get("beforeJoinDate").getAsString());
                                maptoSend.put("beforeEmployeeType", jsonObject.get("beforeEmployeeType").getAsString());
                                maptoSend.put("beforeJabatan", jsonObject.get("beforeJabatan").getAsString());
                                maptoSend.put("beforeDepartment", jsonObject.get("beforeDepartment").getAsString());
                                maptoSend.put("afterNik", jsonObject.get("afterNik").getAsString());
                                maptoSend.put("afterJoinDate", jsonObject.get("afterJoinDate").getAsString());
                                maptoSend.put("afterEmployeeType", jsonObject.get("afterEmployeeType").getAsString());
                                maptoSend.put("afterJabatan", jsonObject.get("afterJabatan").getAsString());
                                maptoSend.put("afterDepartment", jsonObject.get("afterDepartment").getAsString());
                                maptoSend.put("statusDesc", getStatusDesc(appActivity.getApprovalStatus(), locale));
                            }
                            
                            break;
                        default:
                            break;
                    }
                    
                } else if ((appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_CANCELLED)) {
                    //configure email parameter based on approval name	
                    switch (appActivity.getApprovalDefinition().getName()) {
                        case HRMConstant.BUSINESS_TRAVEL:
                            vtm.setSubject("Permohonan Perjalanan Dinas");
                            vtm.setTemplatePath("email_travel_cancelled_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("businessTravelNo", jsonObject.get("businessTravelNo").getAsString());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("destination", jsonObject.get("destination").getAsString());
                            maptoSend.put("start", jsonObject.get("startDate").getAsString());
                            maptoSend.put("end", jsonObject.get("endDate").getAsString());
                            maptoSend.put("description", jsonObject.get("description").getAsString());
                            maptoSend.put("totalAmount", jsonObject.get("totalAmount").getAsString());
                            maptoSend.put("statusDesc", getStatusDesc(appActivity.getApprovalStatus(), locale));
                            break;
                        
                        case HRMConstant.REIMBURSEMENT:
                            vtm.setSubject("Permohonan Pergantian Biaya");
                            vtm.setTemplatePath("email_reimbursment_cancelled.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("reimbursementType", jsonObject.get("reimbursementType").getAsString());
                            maptoSend.put("applicationDate", jsonObject.get("applicationDate").getAsString());
                            maptoSend.put("nominal", jsonObject.get("nominal").getAsString());
                            maptoSend.put("statusDesc", getStatusDesc(appActivity.getApprovalStatus(), locale));
                            break;
                        
                        case HRMConstant.LOAN:
                            vtm.setSubject("Permohonan Pinjaman Lunak");
                            vtm.setTemplatePath("email_loan_cancelled_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("loanSchemaName", jsonObject.get("loanSchemaName").getAsString());
                            maptoSend.put("nominalPrincipal", jsonObject.get("nominalPrincipal").getAsString());
                            maptoSend.put("interestRate", jsonObject.get("interestRate").getAsString());
                            maptoSend.put("nominalInstallment", jsonObject.get("nominalInstallment").getAsString());
                            maptoSend.put("interestInstallment", jsonObject.get("interestInstallment").getAsString());
                            maptoSend.put("totalNominalInstallment", jsonObject.get("totalNominalInstallment").getAsString());
                            maptoSend.put("statusDesc", getStatusDesc(appActivity.getApprovalStatus(), locale));
                            break;
                        
                        case HRMConstant.SHIFT_SCHEDULE:
                            vtm.setSubject("Permohonan Perubahan Jadwal Kerja Karyawan");
                            vtm.setTemplatePath("email_shift_schedule_cancelled_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("statusDesc", getStatusDesc(appActivity.getApprovalStatus(), locale));
                            break;
                        
                        case HRMConstant.LEAVE:
                            vtm.setSubject("Permohonan Cuti");
                            vtm.setTemplatePath("email_leave_cancelled_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("leaveName", jsonObject.get("leaveName").getAsString());
                            maptoSend.put("startDate", jsonObject.get("startDate").getAsString());
                            maptoSend.put("endDate", jsonObject.get("endDate").getAsString());
                            maptoSend.put("fillingDate", jsonObject.get("fillingDate").getAsString());
                            maptoSend.put("materialJobsAbandoned", jsonObject.get("materialJobsAbandoned").getAsString());
                            maptoSend.put("statusDesc", getStatusDesc(appActivity.getApprovalStatus(), locale));
                            break;
                        case HRMConstant.OVERTIME:
                            vtm.setSubject("Permohonan Lembur");
                            vtm.setTemplatePath("email_overtime_cancelled_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("overTimeName", jsonObject.get("overTimeName").getAsString());
                            maptoSend.put("hour", jsonObject.get("hour").getAsString());
                            maptoSend.put("minute", jsonObject.get("minute").getAsString());
                            maptoSend.put("overTimeDate", jsonObject.get("overTimeDate").getAsString());
                            maptoSend.put("implementationNumber", jsonObject.get("implementationNumber").getAsString());
                            maptoSend.put("statusDesc", getStatusDesc(appActivity.getApprovalStatus(), locale));
                            break;
                        
                        default:
                            break;
                    }
                }
                
                if(jsonObject.get("urlLinkToApprove").getAsString() != null){
                	String urlLinkToApprove = serverName + "" + jsonObject.get("urlLinkToApprove").getAsString();
                    maptoSend.put("urlLinkToApprove", urlLinkToApprove);
                }else{
                	maptoSend.put("urlLinkToApprove", applicationUrl);
                }
                
                maptoSend.put("ownerAdministrator", ownerAdministrator);
                maptoSend.put("ownerCompany", ownerCompany);
                maptoSend.put("applicationUrl", applicationUrl);
                maptoSend.put("applicationName", applicationName);
                velocityTemplateSender.sendMail(vtm, maptoSend);

                //update approval activity, set notification true
                if (appActivity.getNotificationSend() == false) {
                    appActivity.setNotificationSend(true);
                    this.approvalActivityDao.update(appActivity);
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public String getApplicationUrl() {
        return applicationUrl;
    }
    
    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }
    
    public String getApplicationName() {
        return applicationName;
    }
    
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
    
    public String getOwnerEmail() {
        return ownerEmail;
    }
    
    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
    
    public String getOwnerCompany() {
        return ownerCompany;
    }
    
    public void setOwnerCompany(String ownerCompany) {
        this.ownerCompany = ownerCompany;
    }
    
    public String getOwnerAdministrator() {
        return ownerAdministrator;
    }
    
    public void setOwnerAdministrator(String ownerAdministrator) {
        this.ownerAdministrator = ownerAdministrator;
    }
    
    
    public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	private String getStatusDesc(Integer approvalStatus, String locale) {
        String statusDesc = StringUtils.EMPTY;
        
        if (StringUtils.equals(locale, "en")) {
            if (approvalStatus == HRMConstant.APPROVAL_STATUS_APPROVED) {
                statusDesc = "Request is approved";
            } else if (approvalStatus == HRMConstant.APPROVAL_STATUS_REJECTED) {
                statusDesc = "Request is rejected";
            } else if (approvalStatus == HRMConstant.APPROVAL_STATUS_CANCELLED) {
                statusDesc = "Request is cancelled";
            }
        } else {
            if (approvalStatus == HRMConstant.APPROVAL_STATUS_APPROVED) {
                statusDesc = "Permohonan Disetujui";
            } else if (approvalStatus == HRMConstant.APPROVAL_STATUS_REJECTED) {
                statusDesc = "Permohonan Ditolak";
            } else if (approvalStatus == HRMConstant.APPROVAL_STATUS_CANCELLED) {
                statusDesc = "Permohonan Dibatalkan";
            }
        }
        
        return statusDesc;
    }
    
}
