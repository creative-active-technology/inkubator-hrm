/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.ImplementationOfOverTime;
import com.inkubator.hrm.service.ImplementationOfOverTimeService;
import com.inkubator.hrm.web.search.ImplementationOfOvertimeSearchParameter;
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
public class ImplementationOfOverTimeLazyDataModel extends LazyDataModel<ImplementationOfOverTime> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(ImplementationOfOverTimeLazyDataModel.class);
    private final ImplementationOfOvertimeSearchParameter searchParameter;
    private final ImplementationOfOverTimeService service;
    private List<ImplementationOfOverTime> implementationOfOverTimeList = new ArrayList<>();
    private Integer jumlahData;

    public ImplementationOfOverTimeLazyDataModel(ImplementationOfOvertimeSearchParameter searchParameter, ImplementationOfOverTimeService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<ImplementationOfOverTime> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    implementationOfOverTimeList = service.getAllDataWithDetail(searchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalImplementationOfOverTimeByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    implementationOfOverTimeList = service.getAllDataWithDetail(searchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalImplementationOfOverTimeByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                implementationOfOverTimeList = service.getAllDataWithDetail(searchParameter, first, pageSize, Order.desc("code"));
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalImplementationOfOverTimeByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return implementationOfOverTimeList;
    }
    
    @Override
    public Object getRowKey(ImplementationOfOverTime implementationOfOverTime) {
        return implementationOfOverTime.getId();
    }

    @Override
    public ImplementationOfOverTime getRowData(String id) {
        for (ImplementationOfOverTime implementationOfOverTime : implementationOfOverTimeList) {
            if (id.equals(String.valueOf(implementationOfOverTime.getId()))) {
                return implementationOfOverTime;
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