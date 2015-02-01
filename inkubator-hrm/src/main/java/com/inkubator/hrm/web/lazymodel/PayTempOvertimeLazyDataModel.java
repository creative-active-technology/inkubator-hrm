/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.PayTempOvertime;
import com.inkubator.hrm.entity.SavingType;
import com.inkubator.hrm.service.PayTempOvertimeService;
import com.inkubator.hrm.web.search.PayTempOvertimeSearchParameter;
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
public class PayTempOvertimeLazyDataModel  extends LazyDataModel<PayTempOvertime> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(PayTempOvertimeLazyDataModel.class);
    private final PayTempOvertimeSearchParameter searchParameter;
    private final PayTempOvertimeService service;
    private List<PayTempOvertime> payTempOvertimeList = new ArrayList<>();
    private Integer jumlahData;

    public PayTempOvertimeLazyDataModel(PayTempOvertimeSearchParameter searchParameter, PayTempOvertimeService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<PayTempOvertime> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("overtime");
                }
                payTempOvertimeList = service.getByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return payTempOvertimeList;
    }
    
    @Override
    public Object getRowKey(PayTempOvertime tempOvertime) {
        return tempOvertime.getId();
    }

    @Override
    public PayTempOvertime getRowData(String id) {
        for (PayTempOvertime payTempOvertime : payTempOvertimeList) {
            if (id.equals(String.valueOf(payTempOvertime.getId()))) {
                return payTempOvertime;
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
