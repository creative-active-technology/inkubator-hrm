/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.WtScheduleShift;
import com.inkubator.hrm.service.WtScheduleShiftService;
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
 * @author Deni Husni FR
 */
public class WtScheduleShiftLazyModel extends LazyDataModel<WtScheduleShift> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(WtScheduleShiftLazyModel.class);
    private final Long workingGroupId;
    private final WtScheduleShiftService wtScheduleShiftService;
    private List<WtScheduleShift> wtScheduleShifts = new ArrayList<>();
    private Integer jumlahData;

    public WtScheduleShiftLazyModel(Long workingGroupId, WtScheduleShiftService wtScheduleShiftService) {
        this.workingGroupId = workingGroupId;
        this.wtScheduleShiftService = wtScheduleShiftService;
    }

    @Override
    public List<WtScheduleShift> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    wtScheduleShifts = wtScheduleShiftService.getByParam(workingGroupId, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(wtScheduleShiftService.getTotalWtScheduleShiftByParam(workingGroupId)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    wtScheduleShifts = wtScheduleShiftService.getByParam(workingGroupId, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(wtScheduleShiftService.getTotalWtScheduleShiftByParam(workingGroupId)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                wtScheduleShifts = wtScheduleShiftService.getByParam(workingGroupId, first, pageSize, Order.asc("scheduleDate"));
                jumlahData = Integer.parseInt(String.valueOf(wtScheduleShiftService.getTotalWtScheduleShiftByParam(workingGroupId)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
//            jumlahData = Integer.parseInt(String.valueOf(loginHistoryService.getTotalLoginHistoryByParam(loginHistorySearchParameter)));
        LOGGER.info("Success Load Lazy data Model");
        setPageSize(pageSize);
        setRowCount(jumlahData);
        return wtScheduleShifts;
    }

    @Override
    public Object getRowKey(WtScheduleShift wtScheduleShift) {
        return wtScheduleShift.getId();
    }

    @Override
    public WtScheduleShift getRowData(String id) {
        for (WtScheduleShift wtScheduleShift : wtScheduleShifts) {
            if (id.equals(String.valueOf(wtScheduleShift.getId()))) {
                return wtScheduleShift;
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
