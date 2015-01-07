/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.web.lazymodel;

import com.inkubator.sms.gateway.entity.LoginHistory;
import com.inkubator.sms.gateway.service.LoginHistoryService;
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
public class LoginHistoryLazy extends LazyDataModel<LoginHistory> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(SmsActivityLazy.class);
    private final String parameter;
    private final LoginHistoryService loginHistoryService;
    private List<LoginHistory> listLoginHistory = new ArrayList<>();
    private int total;

    public LoginHistoryLazy(String parameter, LoginHistoryService loginHistoryService) {
        this.parameter = parameter;
        this.loginHistoryService = loginHistoryService;
    } 

    @Override
    public List<LoginHistory> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        try {
            Order order = null;
            if(sortField != null){
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            }else{
                order = Order.asc("userName");
            }
            listLoginHistory = loginHistoryService.getAllByFullTextService(parameter, first, pageSize, order);
            total = loginHistoryService.getTotalByFullTextService(parameter);
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
        setPageSize(pageSize);
        setRowCount(total);
        return listLoginHistory;
    }

    @Override
    public Object getRowKey(LoginHistory loginHistory) {
        return loginHistory.getId();
    }

    @Override
    public LoginHistory getRowData(String id) {
        for (LoginHistory loginHistory : listLoginHistory) {
            if (id.equals(String.valueOf(loginHistory.getId()))) {
                return loginHistory;
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
