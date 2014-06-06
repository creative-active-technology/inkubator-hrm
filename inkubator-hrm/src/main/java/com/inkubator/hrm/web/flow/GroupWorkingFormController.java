/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.flow;

import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.hrm.entity.WtWorkingHour;
import com.inkubator.hrm.service.WtWorkingHourService;
import com.inkubator.hrm.web.model.GroupWorkingModel;
import com.inkubator.hrm.web.model.ScheduleShiftModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    @Autowired
    private WtWorkingHourService wtWorkingHourService;
    org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(getClass());
    private LazyDataModel<ScheduleShiftModel> lazyDataModel;

    public GroupWorkingModel createGroupWorkingModel(RequestContext context) {
        GroupWorkingModel groupWorkingModel = new GroupWorkingModel();
        return groupWorkingModel;
    }

    public void doCalculateDate(RequestContext context) {
        GroupWorkingModel workingModel = (GroupWorkingModel) context.getFlowScope().get("groupWorkingModel");
        Date beginDate = workingModel.getBeginTime();
        Date endDate = workingModel.getEndTime();

        try {
            List<WtWorkingHour> data = wtWorkingHourService.getAllData();
            Map<String, Long> mapData = new TreeMap<>();
            for (WtWorkingHour wtWorkingHour : data) {
                mapData.put(wtWorkingHour.getName() + " | " + wtWorkingHour.getWorkingHourBegin() + " - " + wtWorkingHour.getWorkingHourEnd(), wtWorkingHour.getId());
            }
            List<ScheduleShiftModel> dataShiftModels = new ArrayList<>();
            int jumlah = DateTimeUtil.getTotalDay(beginDate, endDate);
            for (int i = 0; i < jumlah; i++) {
                System.out.println(" hahahhahahah");
                ScheduleShiftModel model = new ScheduleShiftModel();
//                model.setTanggalKerja(beginDate);
                Date dateToInsert = DateTimeUtil.getDateFrom(beginDate, i, CommonUtilConstant.DATE_FORMAT_DAY);
                model.setTanggalKerja(dateToInsert);
                dataShiftModels.add(model);
            }
            for (ScheduleShiftModel scheduleShiftModel : dataShiftModels) {
                LOGGER.info(" Ini tanggal nuya " + scheduleShiftModel.getTanggalKerja());
            }
            workingModel.setDataShiftModels(dataShiftModels);
            List<ScheduleShiftModel> dataToShow = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                dataToShow.add(dataShiftModels.get(i));
            }
            workingModel.setDataToShow(dataToShow);
            workingModel.setPageNumber(1);
            workingModel.setMapData(mapData);
            context.getFlashScope().put("groupWorkingModel", workingModel);
        } catch (Exception ex) {
            Logger.getLogger(GroupWorkingFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void doNeckTable(RequestContext context) {
        System.out.println(" di eksekusususuus");
        GroupWorkingModel workingModel = (GroupWorkingModel) context.getFlowScope().get("groupWorkingModel");
        int page = workingModel.getPageNumber();
        System.out.println("Nilai page" + page);
      
        List<ScheduleShiftModel> dataShiftModels = workingModel.getDataShiftModels();
        int batas;
        double sisaBagi = dataShiftModels.size() / 7;
          if(page<sisaBagi+1){
              workingModel.setPageNumber(page + 1);
        }else{
              workingModel.setPageNumber(page);
          }
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
        workingModel.setDataToShow(dataToShow);
        context.getFlashScope().put("groupWorkingModel", workingModel);
    }

    public void doBackTable(RequestContext requestContext) {
        GroupWorkingModel workingModel = (GroupWorkingModel) requestContext.getFlowScope().get("groupWorkingModel");
        int page = workingModel.getPageNumber();
        System.out.println("Nilai page" + page);
        if (page > 1) {
            workingModel.setPageNumber(page - 1);
        } else {
            workingModel.setPageNumber(page);   
        }

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
        workingModel.setDataToShow(dataToShow);
        requestContext.getFlashScope().put("groupWorkingModel", workingModel);
    }
}
