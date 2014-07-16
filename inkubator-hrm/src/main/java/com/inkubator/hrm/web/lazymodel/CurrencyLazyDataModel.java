/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.service.CurrencyService;
import com.inkubator.hrm.web.search.CurrencySearchParameter;
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
public class CurrencyLazyDataModel  extends LazyDataModel<Currency> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(CostCenterLazyDataModel.class);
    private final CurrencySearchParameter search;
    private final CurrencyService service;
    private List<Currency> currencyList = new ArrayList<>();
    private Integer jumlahData;

    public CurrencyLazyDataModel(CurrencySearchParameter search, CurrencyService service) {
        this.search = search;
        this.service = service;
    }
    
    @Override
    public List<Currency> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    currencyList = service.getByParam(search, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalCurrencyByParam(search)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    currencyList = service.getByParam(search, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalCurrencyByParam(search)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                currencyList = service.getByParam(search, first, pageSize, Order.desc("code"));
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalCurrencyByParam(search)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return currencyList;
    }
    
    @Override
    public Object getRowKey(Currency currency) {
        return currency.getId();
    }

    @Override
    public Currency getRowData(String id) {
        for (Currency currency : currencyList) {
            if (id.equals(String.valueOf(currency.getId()))) {
                return currency;
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