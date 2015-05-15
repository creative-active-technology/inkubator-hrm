/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.service.LoanService;
import com.inkubator.hrm.web.search.LoanSearchParameter;
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
public class LoanCanceledProcessLazyDataModel extends LazyDataModel<Loan> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(LoanCanceledProcessLazyDataModel.class);
    private final LoanSearchParameter searchParameter;
    private final LoanService service;
    private List<Loan> loanList = new ArrayList<>();
    private Integer jumlahData;

    public LoanCanceledProcessLazyDataModel(LoanSearchParameter searchParameter, LoanService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<Loan> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("nominalPrincipal");
                }
                loanList = service.getByParamByStatusUndisbursed(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParamByStatusUndisbursed(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return loanList;
    }
    
    @Override
    public Object getRowKey(Loan loan) {
        return loan.getId();
    }

    @Override
    public Loan getRowData(String id) {
        for (Loan loan : loanList) {
            if (id.equals(String.valueOf(loan.getId()))) {
                return loan;
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