/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.web.lazymodel;

import com.inkubator.sms.gateway.entity.SmsActivity;
import com.inkubator.sms.gateway.entity.TaskDefinition;
import com.inkubator.sms.gateway.service.SmsActivityService;
import com.inkubator.sms.gateway.service.TaskDefinitionService;
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
 * @author denifahri
 */
public class SmsActivityLazy extends LazyDataModel<SmsActivity> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(SmsActivityLazy.class);
    private final String parameter;
    private final SmsActivityService smsActivityService;
    private List<SmsActivity> listSmsActivitys = new ArrayList<>();
    private int total;

    public SmsActivityLazy(String parameter, SmsActivityService smsActivityService) {
        this.parameter = parameter;
        this.smsActivityService = smsActivityService;
    }

    

    @Override
    public List<SmsActivity> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        try {
            if (sortField != null) {
                if (sortOrder == SortOrder.ASCENDING) {
                    System.out.println(" Order dieksekusi");
                    listSmsActivitys = smsActivityService.getAllByFullTextService(parameter, first, pageSize, Order.asc(sortField));
                } else {
                    listSmsActivitys = smsActivityService.getAllByFullTextService(parameter, first, pageSize, Order.desc(sortField));
                }
            } else {
                listSmsActivitys = smsActivityService.getAllByFullTextService(parameter, first, pageSize, Order.asc("name"));
            }
            total = smsActivityService.getTotalByFullTextService(parameter);
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
        setPageSize(pageSize);
        setRowCount(total);
        return listSmsActivitys;
    }

    @Override
    public Object getRowKey(SmsActivity definition) {
        return definition.getId();
    }

    @Override
    public SmsActivity getRowData(String id) {
        for (SmsActivity definition : listSmsActivitys) {
            if (id.equals(String.valueOf(definition.getId()))) {
                return definition;
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
