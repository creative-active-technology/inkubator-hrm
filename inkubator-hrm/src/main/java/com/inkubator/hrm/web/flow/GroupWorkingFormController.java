/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.flow;

import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.entity.WtScheduleShift;
import com.inkubator.hrm.entity.WtWorkingHour;
import com.inkubator.hrm.service.WtGroupWorkingService;
import com.inkubator.hrm.service.WtWorkingHourService;
import com.inkubator.hrm.web.model.GroupWorkingModel;
import com.inkubator.hrm.web.model.ScheduleShiftModel;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.faces.application.FacesMessage;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

/**
 *
 * @author Deni Husni FR
 */
@Component(value = "groupWorkingFormController")
@Lazy
public class GroupWorkingFormController implements Serializable {

    org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(getClass());
    @Autowired
    private WtWorkingHourService wtWorkingHourService;
    @Autowired
    private WtGroupWorkingService wtGroupWorkingService;

    private LazyDataModel<ScheduleShiftModel> lazyDataModel;

    public GroupWorkingModel createGroupWorkingModel(RequestContext context) {
        GroupWorkingModel groupWorkingModel = new GroupWorkingModel();
        if (context.getFlowScope().get("param") != null) {
            Long id = Long.parseLong(context.getFlowScope().get("param").toString());
            try {
                WtGroupWorking groupWorking = this.wtGroupWorkingService.getByPKIdWithDetail(id);
                groupWorkingModel.setBeginTime(groupWorking.getBeginTime());
                groupWorkingModel.setCode(groupWorking.getCode());
                List<WtScheduleShift> getWtScheduleShifts = new ArrayList<>(groupWorking.getWtScheduleShifts());
                Collections.sort(getWtScheduleShifts, shortByDate1);
                List<ScheduleShiftModel> dataShiftModels = new ArrayList<>();
                int i = 0;
                for (WtScheduleShift wtScheduleShift : getWtScheduleShifts) {
                    ScheduleShiftModel model = new ScheduleShiftModel();
                    model.setJamKerjaId(wtScheduleShift.getWtWorkingHour().getId());
                    model.setTanggalKerja(wtScheduleShift.getScheduleDate());
                    model.setIsRenderCombo(Boolean.TRUE);
                    if (i + 7 < getWtScheduleShifts.size()) {
                        model.setJamKerjaId2(getWtScheduleShifts.get(i + 7).getWtWorkingHour().getId());
                        model.setTanggalKerja2(getWtScheduleShifts.get(i + 7).getScheduleDate());
                        model.setIsRenderCombo2(Boolean.TRUE);
                    }
                    i++;
//                    if (getWtScheduleShifts.size() > 7) {
//                        model.setIsRenderCombo2(Boolean.TRUE);
//
//                    }
                    dataShiftModels.add(model);
                }
                groupWorkingModel.setDataShiftModels(dataShiftModels);
//                groupWorkingModel.setDataToShow(dataShiftModels);
                groupWorkingModel.setEndTime(groupWorking.getEndTime());
                groupWorkingModel.setId(groupWorking.getId());
                groupWorkingModel.setIsPeriodic(groupWorking.getIsPeriodic());
                groupWorkingModel.setName(groupWorking.getName());
                groupWorkingModel.setOvertimeBasedOnAttendance(groupWorking.getOvertimeBasedOnAttendance());
                groupWorkingModel.setOvertimeBasedOnRequest(groupWorking.getOvertimeBasedOnRequest());
                groupWorkingModel.setWorkingTimePerday(groupWorking.getWorkingTimePerday());
                groupWorkingModel.setWorkingTimePerweek(groupWorking.getWorkingTimePerweek());

            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }

        return groupWorkingModel;
    }

    public void doCalculateDate(RequestContext context) {
        try {

            GroupWorkingModel groupWorkingModel = (GroupWorkingModel) context.getFlowScope().get("groupWorkingModel");
            if (groupWorkingModel.getId() == null) {
                groupWorkingModel = onCreateCondition(groupWorkingModel);
            } else {
                groupWorkingModel = onEditCondition(groupWorkingModel);
            }

            context.getFlashScope().put("groupWorkingModel", groupWorkingModel);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }

    private GroupWorkingModel onEditCondition(GroupWorkingModel workingModel) throws Exception {
        Date beginDate = workingModel.getBeginTime();
        Date endDate = workingModel.getEndTime();
        List<ScheduleShiftModel> dataShiftModels = workingModel.getDataShiftModels();
        List<ScheduleShiftModel> dataToShow = new ArrayList<>();
        int jumlahTotalDay = DateTimeUtil.getTotalDay(beginDate, endDate);
        workingModel.setIsDisable(Boolean.TRUE);
        List<WtWorkingHour> data = wtWorkingHourService.getAllData();
        Map<String, Long> mapData = new TreeMap<>();
        for (WtWorkingHour wtWorkingHour : data) {
            mapData.put(wtWorkingHour.getName() + " | " + wtWorkingHour.getWorkingHourBegin() + " - " + wtWorkingHour.getWorkingHourEnd(), wtWorkingHour.getId());
        }
        if (jumlahTotalDay >= 7) {
            for (int i = 0; i < 7; i++) {
                dataToShow.add(dataShiftModels.get(i));
            }
        }
        if (jumlahTotalDay < 7) {
            for (int i = 0; i < jumlahTotalDay; i++) {
                dataToShow.add(dataShiftModels.get(i));

            }
        }
        if (jumlahTotalDay <= 14) {
            workingModel.setIsDisable(Boolean.FALSE);
        }
        workingModel.setMapData(mapData);
        workingModel.setDataToShow(dataToShow);
        workingModel.setPageNumber(1);
        return workingModel;
    }

    private GroupWorkingModel onCreateCondition(GroupWorkingModel workingModel) throws Exception {
//        GroupWorkingModel workingModel = (GroupWorkingModel) context.getFlowScope().get("groupWorkingModel");
        Date beginDate = workingModel.getBeginTime();
        Date endDate = workingModel.getEndTime();
        List<ScheduleShiftModel> dataShiftModels = new ArrayList<>();
        int jumlahTotalDay = DateTimeUtil.getTotalDay(beginDate, endDate);
        workingModel.setIsDisable(Boolean.TRUE);
        List<WtWorkingHour> data = wtWorkingHourService.getAllData();
        Map<String, Long> mapData = new TreeMap<>();
        for (WtWorkingHour wtWorkingHour : data) {
            mapData.put(wtWorkingHour.getName() + " | " + wtWorkingHour.getWorkingHourBegin() + " - " + wtWorkingHour.getWorkingHourEnd(), wtWorkingHour.getId());
        }
        for (int i = 0; i < jumlahTotalDay; i++) {
            ScheduleShiftModel model = new ScheduleShiftModel();
            Date dateToInsert = DateTimeUtil.getDateFrom(beginDate, i, CommonUtilConstant.DATE_FORMAT_DAY);
            Date dateToInsert2 = DateTimeUtil.getDateFrom(beginDate, i + 7, CommonUtilConstant.DATE_FORMAT_DAY);
            if (dateToInsert.after(endDate)) {
                model.setIsRenderCombo(Boolean.FALSE);
            } else {
                model.setTanggalKerja(dateToInsert);
                model.setIsRenderCombo(Boolean.TRUE);
            }
            if (dateToInsert2.after(endDate)) {
                model.setIsRenderCombo2(Boolean.FALSE);
            } else {
                model.setTanggalKerja2(dateToInsert2);
                model.setIsRenderCombo2(Boolean.TRUE);
            }
            model.setTanggalKerja(dateToInsert);
            dataShiftModels.add(model);
        }
        workingModel.setDataShiftModels(dataShiftModels);
        List<ScheduleShiftModel> dataToShow = new ArrayList<>();
        if (jumlahTotalDay >= 7) {
            for (int i = 0; i < 7; i++) {
                dataToShow.add(dataShiftModels.get(i));
            }
        }
        if (jumlahTotalDay < 7) {
            for (int i = 0; i < jumlahTotalDay; i++) {
                dataToShow.add(dataShiftModels.get(i));

            }
        }

        if (jumlahTotalDay <= 14) {
            workingModel.setIsDisable(Boolean.FALSE);
        }
        workingModel.setDataToShow(dataToShow);
        workingModel.setPageNumber(1);
        workingModel.setMapData(mapData);
        return workingModel;
    }
//    private Comparator<ScheduleShiftModel> shortByDate = new Comparator<ScheduleShiftModel>() {
//        @Override
//        public int compare(ScheduleShiftModel o1, ScheduleShiftModel o2) {
//            return o1.getTanggalKerja().compareTo(o2.getTanggalKerja());
//        }
//    };

    private final Comparator<WtScheduleShift> shortByDate1 = new Comparator<WtScheduleShift>() {
        @Override
        public int compare(WtScheduleShift o1, WtScheduleShift o2) {
            return o1.getScheduleDate().compareTo(o2.getScheduleDate());
        }
    };

    public void doNeckTable(RequestContext context) {
        System.out.println(" di eksekusususuus nexk");
        GroupWorkingModel workingModel = (GroupWorkingModel) context.getFlowScope().get("groupWorkingModel");
        int page = workingModel.getPageNumber();
        List<ScheduleShiftModel> dataShiftModels = workingModel.getDataShiftModels();
//        List<ScheduleShiftModel> dataToInsert = workingModel.getDataToShow();
        int batas;
        double sisaBagi = dataShiftModels.size() / 7;
        if (page < sisaBagi + 1) {

            workingModel.setPageNumber(page + 2);
            System.out.println("Nilai Sisa bagi " + sisaBagi);
            System.out.println("page numbar " + workingModel.getPageNumber());
            if (workingModel.getPageNumber() != sisaBagi + 1) {
                batas = workingModel.getPageNumber() * 7;
            } else {
                batas = dataShiftModels.size();
            }
            List<ScheduleShiftModel> dataToShow = new ArrayList<>();
            for (int i = (workingModel.getPageNumber() * 7) - 7; i < batas; i++) {
                dataToShow.add(dataShiftModels.get(i));
            }
            if (workingModel.getPageNumber() >= sisaBagi + 1) {
                workingModel.setIsDisable(Boolean.FALSE);
            }
            workingModel.setDataToShow(dataToShow);
            context.getFlashScope().put("groupWorkingModel", workingModel);

        } else {
            workingModel.setPageNumber(page);

        }

    }

    public void doBackTable(RequestContext requestContext) {
        GroupWorkingModel workingModel = (GroupWorkingModel) requestContext.getFlowScope().get("groupWorkingModel");
        int page = workingModel.getPageNumber();
        System.out.println("Nilai page" + page);
        if (page > 1) {
            workingModel.setPageNumber(page - 2);
            List<ScheduleShiftModel> dataShiftModels = workingModel.getDataShiftModels();
            int batas;
            double sisaBagi = dataShiftModels.size() / 7;
            System.out.println("Nilai Sisa bagi " + sisaBagi);
            if (workingModel.getPageNumber() != sisaBagi + 1) {
                batas = workingModel.getPageNumber() * 7;
            } else {
                batas = dataShiftModels.size();
            }
            List<ScheduleShiftModel> dataToShow = new ArrayList<>();
            for (int i = (workingModel.getPageNumber() * 7) - 7; i < batas; i++) {
                dataToShow.add(dataShiftModels.get(i));
            }

            if (workingModel.getPageNumber() < sisaBagi + 1) {
                workingModel.setIsDisable(Boolean.TRUE);
            }
            workingModel.setDataToShow(dataToShow);
            requestContext.getFlashScope().put("groupWorkingModel", workingModel);
        } else {
            workingModel.setPageNumber(page);
            workingModel.setIsDisable(Boolean.TRUE);
        }

    }

    public String doSave(RequestContext context) {
        GroupWorkingModel workingModel = (GroupWorkingModel) context.getFlowScope().get("groupWorkingModel");
//        List<ScheduleShiftModel> dataShiftModels = workingModel.getDataShiftModels();
//        for (ScheduleShiftModel scheduleShiftModel : dataShiftModels) {
//            if (scheduleShiftModel.getJamKerjaId() != null || scheduleShiftModel.getJamKerjaId2() != null) {
//                System.out.println(scheduleShiftModel);
//            }
//        }
        try {
            if (workingModel.getId() == null) {
                wtGroupWorkingService.save(workingModel);
                return "yes";
            } else {
                wtGroupWorkingService.update(workingModel);
                return "yes";
            }

//        } catch (Exception ex) {
//            LOGGER.error("Error", ex);
//            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "working_group.error_duplicate_code",
//                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//            return "no";
//        }
        } catch (BussinessException ex) { //data already exist(duplicate)
//            LOGGER.error("Error", ex);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "no";
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;

    }
}
