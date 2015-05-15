/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.LoginHistory;
import com.inkubator.hrm.service.LoginHistoryService;
import com.inkubator.hrm.web.search.LoginHistorySearchParameter;
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
 * @author Deni Husni FR,rizkykojek
 */
public class LoginHistoryLazyDataModel extends LazyDataModel<LoginHistory> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(LoginHistoryLazyDataModel.class);
    private final LoginHistorySearchParameter loginHistorySearchParameter;
    private final LoginHistoryService loginHistoryService;
    private List<LoginHistory> loginHistorys = new ArrayList<>();
    private Integer jumlahData;

    public LoginHistoryLazyDataModel(LoginHistorySearchParameter loginHistorySearchParameter, LoginHistoryService loginHistoryService) {
        this.loginHistorySearchParameter = loginHistorySearchParameter;
        this.loginHistoryService = loginHistoryService;
    }

    @Override
    public List<LoginHistory> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    loginHistorys = loginHistoryService.getByParam(loginHistorySearchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(loginHistoryService.getTotalLoginHistoryByParam(loginHistorySearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    loginHistorys = loginHistoryService.getByParam(loginHistorySearchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(loginHistoryService.getTotalLoginHistoryByParam(loginHistorySearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                loginHistorys = loginHistoryService.getByParam(loginHistorySearchParameter, first, pageSize, Order.desc("loginDate"));
                jumlahData = Integer.parseInt(String.valueOf(loginHistoryService.getTotalLoginHistoryByParam(loginHistorySearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return loginHistorys;
    }

    @Override
    public Object getRowKey(LoginHistory loginHistory) {
        return loginHistory.getId();
    }

    @Override
    public LoginHistory getRowData(String id) {
        for (LoginHistory loginHistory : loginHistorys) {
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
