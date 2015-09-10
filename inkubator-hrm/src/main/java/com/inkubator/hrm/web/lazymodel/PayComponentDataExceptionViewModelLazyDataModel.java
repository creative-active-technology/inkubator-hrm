/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.hrm.web.model.PayComponentDataExceptionModelView;
import com.inkubator.hrm.web.search.PayComponentDataExceptionSearchParameter;
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
public class PayComponentDataExceptionViewModelLazyDataModel extends LazyDataModel<PayComponentDataExceptionModelView> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(PayComponentDataExceptionViewModelLazyDataModel.class);
    private final PaySalaryComponentService service;
    private List<PayComponentDataExceptionModelView> payComponentDataExceptionList = new ArrayList<>();
    private final PayComponentDataExceptionSearchParameter parameter;
    private Integer jumlahData;

    public PayComponentDataExceptionViewModelLazyDataModel(PaySalaryComponentService service, PayComponentDataExceptionSearchParameter parameter) {
        this.service = service;
        this.parameter = parameter;
    }

    @Override
    public List<PayComponentDataExceptionModelView> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        try {
            Order order = null;
            if(sortField != null){
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            }else{
                order = Order.desc("name");
            }
            payComponentDataExceptionList = service.getAllDataByParamDataException(parameter, first, pageSize, order);

            jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParamDataException(parameter)));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return payComponentDataExceptionList;
    }

    @Override
    public Object getRowKey(PayComponentDataExceptionModelView componentDataExceptionModelView) {
        return componentDataExceptionModelView.getId();
    }

    @Override
    public PayComponentDataExceptionModelView getRowData(String id) {
        for (PayComponentDataExceptionModelView componentDataExceptionModelView : payComponentDataExceptionList) {
            if (id.equals(String.valueOf(componentDataExceptionModelView.getId()))) {
                return componentDataExceptionModelView;
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
