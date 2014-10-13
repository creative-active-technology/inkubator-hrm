/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import ch.lambdaj.Lambda;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.JsonConverter;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.TempJadwalKaryawanService;
import com.inkubator.hrm.service.WtGroupWorkingService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.json.JSONObject;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "scheduleApprovalFormController")
@ViewScoped
public class ScheduleApprovalFormController extends BaseController {

    private ApprovalActivity selectedApprovalActivity;
    @ManagedProperty(value = "#{tempJadwalKaryawanService}")
    private TempJadwalKaryawanService tempJadwalKaryawanService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{wtGroupWorkingService}")
    private WtGroupWorkingService wtGroupWorkingService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private Date beginScheduleDate;
    private Date endScheduleDate;
    private List<EmpData> dataToshow = new ArrayList<>();
    private WtGroupWorking selectedWtGroupWorking;
    private List<EmpData> dataToSave = new ArrayList<>();
    private String comment;
    private Boolean isWaitingApproval;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution");
            selectedApprovalActivity = approvalActivityService.getEntiyByPK(Long.parseLong(id.substring(1)));
            isWaitingApproval = selectedApprovalActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING;
            
            JSONObject jSONObject = new JSONObject(selectedApprovalActivity.getPendingData());
            long workingGroupId = Long.parseLong(jSONObject.getString("groupWorkingId"));
            String listEmp = jSONObject.getString("listEmpId");
            TypeToken<List<Long>> token = new TypeToken<List<Long>>() {
            };
            Gson gson = new GsonBuilder().create();
            List<Long> dataEmpId = gson.fromJson(listEmp, token.getType());
            dataToshow = empDataService.getEmpDataByListId(dataEmpId);
            System.out.println(" Jumalh employenya " + dataToshow.size());
            Date createDate = new SimpleDateFormat("dd-MM-yyyy").parse(jSONObject.getString("createDate"));
            selectedWtGroupWorking = wtGroupWorkingService.getEntiyByPK(workingGroupId);
            Date startDate = selectedWtGroupWorking.getBeginTime();
            Date endDate = selectedWtGroupWorking.getEndTime();
            int numberOfDay = DateTimeUtil.getTotalDayDifference(startDate, endDate);
            int totalDateDif = DateTimeUtil.getTotalDayDifference(startDate, createDate) + 1;
            int num = numberOfDay + 1;
            int hasilBagi = (totalDateDif) / (num);
            Date tanggalAkhirJadwal = DateTimeUtil.getDateFrom(startDate, (hasilBagi * num) - 1, CommonUtilConstant.DATE_FORMAT_DAY);
            if (new SimpleDateFormat("ddMMyyyy").format(tanggalAkhirJadwal).equals(new SimpleDateFormat("ddMMyyyy").format(new Date()))) {
                beginScheduleDate = DateTimeUtil.getDateFrom(startDate, (hasilBagi * num) - num, CommonUtilConstant.DATE_FORMAT_DAY);
            } else {
                beginScheduleDate = DateTimeUtil.getDateFrom(startDate, (hasilBagi * num), CommonUtilConstant.DATE_FORMAT_DAY);
            }
            endScheduleDate = DateTimeUtil.getDateFrom(beginScheduleDate, numberOfDay, CommonUtilConstant.DATE_FORMAT_DAY);

        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
        selectedApprovalActivity = null;
        approvalActivityService = null;
        tempJadwalKaryawanService = null;
        wtGroupWorkingService = null;
        empDataService = null;
        beginScheduleDate = null;
        endScheduleDate = null;
        dataToshow = null;
        selectedWtGroupWorking = null;
        dataToSave = null;
        comment = null;
        isWaitingApproval= null;
    }

    public ApprovalActivity getSelectedApprovalActivity() {
        return selectedApprovalActivity;
    }

    public void setSelectedApprovalActivity(
            ApprovalActivity selectedApprovalActivity) {
        this.selectedApprovalActivity = selectedApprovalActivity;
    }

    public void setApprovalActivityService(
            ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }

    public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setTempJadwalKaryawanService(
			TempJadwalKaryawanService tempJadwalKaryawanService) {
		this.tempJadwalKaryawanService = tempJadwalKaryawanService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }
	
	public Date getBeginScheduleDate() {
        return beginScheduleDate;
    }

    public void setBeginScheduleDate(Date beginScheduleDate) {
        this.beginScheduleDate = beginScheduleDate;
    }

    public Date getEndScheduleDate() {
        return endScheduleDate;
    }

    public void setEndScheduleDate(Date endScheduleDate) {
        this.endScheduleDate = endScheduleDate;
    }

    public List<EmpData> getDataToshow() {
        return dataToshow;
    }

    public void setDataToshow(List<EmpData> dataToshow) {
        this.dataToshow = dataToshow;
    }

    public List<EmpData> getDataToSave() {
        return dataToSave;
    }

    public void setDataToSave(List<EmpData> dataToSave) {
        this.dataToSave = dataToSave;
    }
    
    public void setWtGroupWorkingService(WtGroupWorkingService wtGroupWorkingService) {
        this.wtGroupWorkingService = wtGroupWorkingService;
    }

    public WtGroupWorking getSelectedWtGroupWorking() {
        return selectedWtGroupWorking;
    }

    public void setSelectedWtGroupWorking(WtGroupWorking selectedWtGroupWorking) {
        this.selectedWtGroupWorking = selectedWtGroupWorking;
    }
    
    public Boolean getIsWaitingApproval() {
		return isWaitingApproval;
	}

	public void setIsWaitingApproval(Boolean isWaitingApproval) {
		this.isWaitingApproval = isWaitingApproval;
	}

	public String doBack() {
        return "home";
    }

    public String doApproved() {
        try {        	
    		List<Long> listEmpId = Lambda.extract(dataToSave, Lambda.on(EmpData.class).getId());
    		String dataToJson = JsonConverter.getJson(listEmpId.toArray(new Long[listEmpId.size()]),"dd-MMMM-yyyy");
        	
    		JsonObject jsonObject = (JsonObject) JsonConverter.getClassFromJson(selectedApprovalActivity.getPendingData(), JsonObject.class, "dd-MMMM-yyyy");
    		jsonObject.addProperty("listEmpId", dataToJson);
    		
    		tempJadwalKaryawanService.approved(selectedApprovalActivity.getId(), jsonObject.toString(), comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.approved_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/home.htm?faces-redirect=true";
        } catch (Exception e) {
            LOGGER.error("Error when approved process ", e);
        }
        return null;
    }

    public String doRejected() {
        try {
			tempJadwalKaryawanService.rejected(selectedApprovalActivity.getId(), comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.rejected_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/home.htm?faces-redirect=true";
        } catch (Exception e) {
            LOGGER.error("Error when rejected process ", e);
        }
        return null;
    }

    public void doDetail() {
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedApprovalActivity.getId()));
        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("id", values);
        Map<String, Object> options = new HashMap<>();
        options.put("modal", false);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 800);
        options.put("contentHeight", 500);
        RequestContext.getCurrentInstance().openDialog("schedule_shift_detail", options, dataToSend);

    }
    
}
