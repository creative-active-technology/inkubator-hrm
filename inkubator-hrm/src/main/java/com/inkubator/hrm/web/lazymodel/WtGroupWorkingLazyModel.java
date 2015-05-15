/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.service.WtGroupWorkingService;
import com.inkubator.hrm.web.search.WtGroupWorkingSearchParameter;
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
 * @author Deni Husni FR
 */
public class WtGroupWorkingLazyModel extends LazyDataModel<WtGroupWorking> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(WtGroupWorkingLazyModel.class);
    private final WtGroupWorkingSearchParameter wtGroupWorkingSearchParameter;
    private final WtGroupWorkingService wtGroupWorkingService;
    private List<WtGroupWorking> wtGroupWorkings = new ArrayList<>();
    private Integer jumlahData;

    public WtGroupWorkingLazyModel(WtGroupWorkingSearchParameter wtGroupWorkingSearchParameter, WtGroupWorkingService wtGroupWorkingService) {
        this.wtGroupWorkingSearchParameter = wtGroupWorkingSearchParameter;
        this.wtGroupWorkingService = wtGroupWorkingService;
    }

    @Override
    public List<WtGroupWorking> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    wtGroupWorkings = wtGroupWorkingService.getByParam(wtGroupWorkingSearchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(wtGroupWorkingService.getTotalWtGroupWorkingByParam(wtGroupWorkingSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    wtGroupWorkings = wtGroupWorkingService.getByParam(wtGroupWorkingSearchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(wtGroupWorkingService.getTotalWtGroupWorkingByParam(wtGroupWorkingSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                wtGroupWorkings = wtGroupWorkingService.getByParam(wtGroupWorkingSearchParameter, first, pageSize, Order.desc("code"));
                jumlahData = Integer.parseInt(String.valueOf(wtGroupWorkingService.getTotalWtGroupWorkingByParam(wtGroupWorkingSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
//            jumlahData = Integer.parseInt(String.valueOf(loginHistoryService.getTotalLoginHistoryByParam(loginHistorySearchParameter)));
        LOGGER.info("Success Load Lazy data Model");
        setPageSize(pageSize);
        setRowCount(jumlahData);
        return wtGroupWorkings;
    }

    @Override
    public Object getRowKey(WtGroupWorking hrmRole) {
        return hrmRole.getId();
    }

    @Override
    public WtGroupWorking getRowData(String id) {
        for (WtGroupWorking groupWorking : wtGroupWorkings) {
            if (id.equals(String.valueOf(groupWorking.getId()))) {
                return groupWorking;
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
