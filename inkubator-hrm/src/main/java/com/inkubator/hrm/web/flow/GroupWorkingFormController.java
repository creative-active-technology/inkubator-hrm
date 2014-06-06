/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.flow;

import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.hrm.web.model.GroupWorkingModel;
import com.inkubator.hrm.web.model.ScheduleShiftModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.LazyDataModel;
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
            context.getFlashScope().put("groupWorkingModel", workingModel);
        } catch (Exception ex) {
            Logger.getLogger(GroupWorkingFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
