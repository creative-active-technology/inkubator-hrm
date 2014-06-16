/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.LoginHistory;
import com.inkubator.hrm.entity.PasswordHistory;
import com.inkubator.hrm.service.PasswordHistoryService;
import com.inkubator.hrm.web.search.PasswordHistorySearchParameter;
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
 * @author deniarianto
 */
public class PasswordHistoryLazyDataModel extends LazyDataModel<PasswordHistory> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(PasswordHistoryLazyDataModel.class);
    private final PasswordHistorySearchParameter passwordHistorySearchParameter;
    private final PasswordHistoryService passwordHistoryService;
    private List<PasswordHistory> passwordHistorys = new ArrayList<>();
    private Integer jumlahData;
    
    public PasswordHistoryLazyDataModel(PasswordHistorySearchParameter passwordHistorySearchParameter, PasswordHistoryService passwordHistoryService) {
        this.passwordHistorySearchParameter = passwordHistorySearchParameter;
        this.passwordHistoryService = passwordHistoryService;
    }
    
    @Override
    public List<PasswordHistory> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    passwordHistorys = passwordHistoryService.getByParam(passwordHistorySearchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(passwordHistoryService.getTotalLoginHistoryByParam(passwordHistorySearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    passwordHistorys = passwordHistoryService.getByParam(passwordHistorySearchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(passwordHistoryService.getTotalLoginHistoryByParam(passwordHistorySearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                passwordHistorys = passwordHistoryService.getByParam(passwordHistorySearchParameter, first, pageSize, Order.desc("realName"));
                jumlahData = Integer.parseInt(String.valueOf(passwordHistoryService.getTotalLoginHistoryByParam(passwordHistorySearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return passwordHistorys;
    }
    
    @Override
    public Object getRowKey(PasswordHistory passwordHistory) {
        return passwordHistory.getId();
    }

    @Override
    public PasswordHistory getRowData(String id) {
        for (PasswordHistory passwordHistory : passwordHistorys) {
            if (id.equals(String.valueOf(passwordHistory.getId()))) {
                return passwordHistory;
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
