/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.web.lazymodel;

import com.inkubator.sms.gateway.entity.User;
import com.inkubator.sms.gateway.service.UserService;
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
public class UserLazy extends LazyDataModel<User> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(UserLazy.class);
    private final String parameter;
    private final UserService loginHistoryService;
    private List<User> listUser = new ArrayList<>();
    private int total;

    public UserLazy(String parameter, UserService loginHistoryService) {
        this.parameter = parameter;
        this.loginHistoryService = loginHistoryService;
    }

    @Override
    public List<User> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        try {
            Order order = null;
            if(sortField != null){
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            }else{
                order = Order.asc("userId");
            }
            listUser = loginHistoryService.getAllByFullTextService(parameter, first, pageSize, order);
            total = loginHistoryService.getTotalByFullTextService(parameter);
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
        setPageSize(pageSize);
        setRowCount(total);
        return listUser;
    }

    @Override
    public Object getRowKey(User user) {
        return user.getId();
    }

    @Override
    public User getRowData(String id) {
        for (User user : listUser) {
            if (id.equals(String.valueOf(user.getId()))) {
                return user;
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
