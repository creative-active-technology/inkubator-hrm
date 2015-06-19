/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.LoanCanceled;
import com.inkubator.hrm.service.LoanCanceledService;
import com.inkubator.hrm.web.search.LoanCanceledSearchParameter;
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
public class LoanCanceledLazyDataModel extends LazyDataModel<LoanCanceled> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(LoanCanceledLazyDataModel.class);
    private final LoanCanceledSearchParameter searchParameter;
    private final LoanCanceledService service;
    private List<LoanCanceled> loanCanceledList = new ArrayList<>();
    private Integer jumlahData;

    public LoanCanceledLazyDataModel(LoanCanceledSearchParameter searchParameter, LoanCanceledService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<LoanCanceled> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("cancelledDate");
                }
                loanCanceledList = service.getByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return loanCanceledList;
    }
    
    @Override
    public Object getRowKey(LoanCanceled loanCanceled) {
        return loanCanceled.getId();
    }

    @Override
    public LoanCanceled getRowData(String id) {
        for (LoanCanceled loanCanceled : loanCanceledList) {
            if (id.equals(String.valueOf(loanCanceled.getId()))) {
                return loanCanceled;
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
