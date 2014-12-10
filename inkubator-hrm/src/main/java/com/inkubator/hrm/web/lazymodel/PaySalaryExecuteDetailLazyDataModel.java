/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.service.PayTempKalkulasiService;
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
 * @author deni
 */
public class PaySalaryExecuteDetailLazyDataModel extends LazyDataModel<PayTempKalkulasi> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(PaySalaryExecuteDetailLazyDataModel.class);
    private final String searchParameter;
    private final PayTempKalkulasiService service;
    private List<PayTempKalkulasi> payTempLisstList = new ArrayList<>();
    private Long paySalaryComponentId;
    private Integer jumlahData;

    public PaySalaryExecuteDetailLazyDataModel(String searchParameter, PayTempKalkulasiService service, Long paySalaryComponentId) {
        this.searchParameter = searchParameter;
        this.service = service;
        this.paySalaryComponentId = paySalaryComponentId;
    }

    
    
    @Override
    public List<PayTempKalkulasi> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    payTempLisstList = service.getByParamForDetail(searchParameter, first, pageSize, Order.asc(sortField), paySalaryComponentId);
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalPayTempKalkulasiByParamForDetail(searchParameter, paySalaryComponentId)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    payTempLisstList = service.getByParamForDetail(searchParameter, first, pageSize, Order.desc(sortField), paySalaryComponentId);
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalPayTempKalkulasiByParamForDetail(searchParameter, paySalaryComponentId)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                payTempLisstList = service.getByParamForDetail(searchParameter, first, pageSize, Order.desc("empData"), paySalaryComponentId);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalPayTempKalkulasiByParamForDetail(searchParameter, paySalaryComponentId)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return payTempLisstList;
    }
    
    @Override
    public Object getRowKey(PayTempKalkulasi payTempKalkulasi) {
        return payTempKalkulasi.getId();
    }

    @Override
    public PayTempKalkulasi getRowData(String id) {
        for (PayTempKalkulasi payTempKalkulasi : payTempLisstList) {
            if (id.equals(String.valueOf(payTempKalkulasi.getId()))) {
                return payTempKalkulasi;
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
