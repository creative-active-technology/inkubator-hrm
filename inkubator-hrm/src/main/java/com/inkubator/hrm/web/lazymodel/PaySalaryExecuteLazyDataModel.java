/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.hrm.web.model.PayTempKalkulasiModel;
import com.inkubator.hrm.web.search.PayTempKalkulasiSearchParameter;
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
public class PaySalaryExecuteLazyDataModel extends LazyDataModel<PayTempKalkulasiModel> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(PaySalaryExecuteLazyDataModel.class);
    private final String searchParameter;
    private final PayTempKalkulasiService service;
    private List<PayTempKalkulasiModel> payTempKalkulasiList = new ArrayList<>();
    private Integer jumlahData;

    public PaySalaryExecuteLazyDataModel(String searchParameter, PayTempKalkulasiService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<PayTempKalkulasiModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    payTempKalkulasiList = service.getByParam(searchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalPayTempKalkulasiByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    payTempKalkulasiList = service.getByParam(searchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalPayTempKalkulasiByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                payTempKalkulasiList = service.getByParam(searchParameter, first, pageSize, Order.desc("name"));
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalPayTempKalkulasiByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return payTempKalkulasiList;
    }
    
    @Override
    public Object getRowKey(PayTempKalkulasiModel payTempKalkulasiModel) {
        return payTempKalkulasiModel.getId();
    }

    @Override
    public PayTempKalkulasiModel getRowData(String id) {
        for (PayTempKalkulasiModel payTempKalkulasiModel : payTempKalkulasiList) {
            if (id.equals(String.valueOf(payTempKalkulasiModel.getId()))) {
                return payTempKalkulasiModel;
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
