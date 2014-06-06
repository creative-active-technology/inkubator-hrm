/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.web.model.ScheduleShiftModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Deni Husni FR
 */
public class WtGroupTimeLazyModel extends LazyDataModel<ScheduleShiftModel> implements Serializable {

    private Date beginDate;
    private Date endDate;
    private List<ScheduleShiftModel> wtGroupWorkings = new ArrayList<>();
    private Integer jumlahData;

    public WtGroupTimeLazyModel(Date beginDate, Date endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }
    
    

    @Override
    public List<ScheduleShiftModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {

        return wtGroupWorkings;
    }

    @Override
    public Object getRowKey(ScheduleShiftModel hrmRole) {
        return hrmRole.getNo();
    }

    @Override
    public ScheduleShiftModel getRowData(String id) {
        for (ScheduleShiftModel groupWorking : wtGroupWorkings) {
            if (id.equals(String.valueOf(groupWorking.getNo()))) {
                return groupWorking;
            }
        }
        return null;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }
}
