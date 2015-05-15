/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.WtOverTime;
import com.inkubator.hrm.service.WtOverTimeService;
import com.inkubator.hrm.web.search.WtOverTimeSearchParameter;
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
public class WtOverTimeLazyModel extends LazyDataModel<WtOverTime> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(WtOverTimeLazyModel.class);
    private final WtOverTimeSearchParameter wtOverTimeSearchParameter;
    private final WtOverTimeService wtOverTimeService;
    private List<WtOverTime> wtOverTimes = new ArrayList<>();
    private Integer jumlahData;

    public WtOverTimeLazyModel(WtOverTimeSearchParameter wtOverTimeSearchParameter, WtOverTimeService wtOverTimeService) {
        this.wtOverTimeSearchParameter = wtOverTimeSearchParameter;
        this.wtOverTimeService = wtOverTimeService;
    }

    @Override
    public List<WtOverTime> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    wtOverTimes = wtOverTimeService.getByParam(wtOverTimeSearchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(wtOverTimeService.getTotalWtOverTimeByParam(wtOverTimeSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    wtOverTimes = wtOverTimeService.getByParam(wtOverTimeSearchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(wtOverTimeService.getTotalWtOverTimeByParam(wtOverTimeSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                wtOverTimes = wtOverTimeService.getByParam(wtOverTimeSearchParameter, first, pageSize, Order.desc("code"));
                jumlahData = Integer.parseInt(String.valueOf(wtOverTimeService.getTotalWtOverTimeByParam(wtOverTimeSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
//            jumlahData = Integer.parseInt(String.valueOf(loginHistoryService.getTotalLoginHistoryByParam(loginHistorySearchParameter)));
        LOGGER.info("Success Load Lazy data Model");
        setPageSize(pageSize);
        setRowCount(jumlahData);
        return wtOverTimes;
    }

    @Override
    public Object getRowKey(WtOverTime hrmRole) {
        return hrmRole.getId();
    }

    @Override
    public WtOverTime getRowData(String id) {
        for (WtOverTime hrmRole : wtOverTimes) {
            if (id.equals(String.valueOf(hrmRole.getId()))) {
                return hrmRole;
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
