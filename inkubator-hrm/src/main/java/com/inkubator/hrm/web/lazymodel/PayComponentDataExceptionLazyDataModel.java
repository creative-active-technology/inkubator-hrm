/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.PayComponentDataException;
import com.inkubator.hrm.service.PayComponentDataExceptionService;
import com.inkubator.hrm.web.model.PayComponentDataExceptionModel;
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
public class PayComponentDataExceptionLazyDataModel extends LazyDataModel<PayComponentDataException> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(PayComponentDataExceptionLazyDataModel.class);
    private final PayComponentDataExceptionService service;
    private PayComponentDataExceptionModel payComponentDataExceptionModel;
    private List<PayComponentDataException> payComponentDataExceptionList = new ArrayList<>();
    private final String parameter;
    private final String paySalaryComponentId;
    private Integer jumlahData;

    public PayComponentDataExceptionLazyDataModel(PayComponentDataExceptionService service, PayComponentDataExceptionModel payComponentDataExceptionModel, String paySalaryComponentId, String parameter) {
        this.service = service;
        this.payComponentDataExceptionModel = payComponentDataExceptionModel;
        this.parameter = parameter;
        this.paySalaryComponentId = paySalaryComponentId;
    }

    @Override
    public List<PayComponentDataException> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        try {
            Order order = null;
            if(sortField != null){
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            }else{
                order = Order.desc("bioData.firstName");
            }
            payComponentDataExceptionList = service.getByParamWithDetailForDetail(parameter, paySalaryComponentId, first, pageSize, order);
            jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParamForDetail(parameter, paySalaryComponentId)));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return payComponentDataExceptionList;
    }

    @Override
    public Object getRowKey(PayComponentDataException payComponentDataException) {
        return payComponentDataException.getId();
    }

    @Override
    public PayComponentDataException getRowData(String id) {
        for (PayComponentDataException payComponentDataException : payComponentDataExceptionList) {
            if (id.equals(String.valueOf(payComponentDataException.getId()))) {
                return payComponentDataException;
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
