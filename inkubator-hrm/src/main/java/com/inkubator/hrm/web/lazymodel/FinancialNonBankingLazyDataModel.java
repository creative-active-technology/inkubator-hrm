/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.FinancialNonBanking;
import com.inkubator.hrm.service.FinancialNonBankingService;
import com.inkubator.hrm.web.search.FinancialNonBankingSearchParameter;
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
public class FinancialNonBankingLazyDataModel extends LazyDataModel<FinancialNonBanking> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(FinancialNonBankingLazyDataModel.class);
    private final FinancialNonBankingSearchParameter searchParameter;
    private final FinancialNonBankingService service;
    private List<FinancialNonBanking> financialNonBankingList = new ArrayList<>();
    private Integer jumlahData;

    public FinancialNonBankingLazyDataModel(FinancialNonBankingSearchParameter searchParameter, FinancialNonBankingService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<FinancialNonBanking> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    financialNonBankingList = service.getByParamWithDetail(searchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalFinancialNonBankingByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    financialNonBankingList = service.getByParamWithDetail(searchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalFinancialNonBankingByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                financialNonBankingList = service.getByParamWithDetail(searchParameter, first, pageSize, Order.desc("name"));
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalFinancialNonBankingByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return financialNonBankingList;
    }
    
    @Override
    public Object getRowKey(FinancialNonBanking financialNonBanking) {
        return financialNonBanking.getId();
    }

    @Override
    public FinancialNonBanking getRowData(String id) {
        for (FinancialNonBanking financialNonBanking : financialNonBankingList) {
            if (id.equals(String.valueOf(financialNonBanking.getId()))) {
                return financialNonBanking;
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
