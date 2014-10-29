/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.CheckInAttendance;
import com.inkubator.hrm.service.CheckInAttendanceService;
import com.inkubator.hrm.web.search.CheckInAttendanceSearchParameter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Deni
 */
public class CheckInAttendanceLazyDataModel  extends LazyDataModel<CheckInAttendance> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(CheckInAttendanceLazyDataModel.class);
    private final CheckInAttendanceSearchParameter searchParameter;
    private final CheckInAttendanceService service;
    private List<CheckInAttendance> checkInAttendanceList = new ArrayList<>();
    private Integer jumlahData;

    public CheckInAttendanceLazyDataModel(CheckInAttendanceSearchParameter searchParameter, CheckInAttendanceService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<CheckInAttendance> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    checkInAttendanceList = service.getByParamWithDetail(searchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalCheckInAttendanceByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    checkInAttendanceList = service.getByParamWithDetail(searchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalCheckInAttendanceByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                checkInAttendanceList = service.getByParamWithDetail(searchParameter, first, pageSize, Order.desc("empData"));
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalCheckInAttendanceByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return checkInAttendanceList;
    }
    
    @Override
    public Object getRowKey(CheckInAttendance checkInAttendance) {
        return checkInAttendance.getId();
    }

    @Override
    public CheckInAttendance getRowData(String id) {
        for (CheckInAttendance checkInAttendance : checkInAttendanceList) {
            if (id.equals(String.valueOf(checkInAttendance.getId()))) {
                return checkInAttendance;
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