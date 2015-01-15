/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.PayTempAttendanceStatus;
import com.inkubator.hrm.entity.Reimbursment;
import com.inkubator.hrm.service.InclusionReimbursmentService;
import com.inkubator.hrm.service.PayTempAttendanceStatusService;
import com.inkubator.hrm.web.model.InclusionReimbursmentModel;
import com.inkubator.hrm.web.model.PayTempAttendanceStatusModel;

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
 * @author Ahmad Mudzakkir Amal
 */
public class PayTempAttendanceStatusLazyDataModel extends LazyDataModel<PayTempAttendanceStatus> implements Serializable{
	
    private static final Logger LOGGER = Logger.getLogger(PayTempAttendanceStatusLazyDataModel.class);
    private final PayTempAttendanceStatusService service;
    private PayTempAttendanceStatusModel payTempAttendanceStatusModel;
    private List<PayTempAttendanceStatus> payTempAttendanceStatusList = new ArrayList<>();
    private final String parameter;
    private Integer jumlahData;

    public PayTempAttendanceStatusLazyDataModel(PayTempAttendanceStatusService service, PayTempAttendanceStatusModel payTempAttendanceStatusModel, String parameter) {
        this.service = service;
        this.payTempAttendanceStatusModel = payTempAttendanceStatusModel;
        this.parameter = parameter;
    }

    
    
    @Override
    public List<PayTempAttendanceStatus> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("code");
                }
                //payTempAttendanceStatusList = service.getByParam(parameter, payTempAttendanceStatusModel, first, pageSize, order);
                //jumlahData = Integer.parseInt(String.valueOf(service.getTotalResourceTypeByParam(parameter, payTempAttendanceStatusModel)));
                
                payTempAttendanceStatusList = new ArrayList<PayTempAttendanceStatus>();
                jumlahData = 0;
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return payTempAttendanceStatusList;
    }
    
    @Override
    public Object getRowKey(PayTempAttendanceStatus payTempAttendanceStatus) {
        return payTempAttendanceStatus.getId();
    }

    @Override
    public PayTempAttendanceStatus getRowData(String id) {
        for (PayTempAttendanceStatus payTempAttendanceStatus : payTempAttendanceStatusList) {
            if (id.equals(String.valueOf(payTempAttendanceStatus.getId()))) {
                return payTempAttendanceStatus;
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
