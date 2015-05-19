/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.entity.LoanType;
import com.inkubator.hrm.service.LoanNewTypeService;
import com.inkubator.hrm.service.LoanTypeService;
import com.inkubator.hrm.web.search.LoanNewTypeSearchParameter;
import com.inkubator.hrm.web.search.LoanTypeSearchParameter;
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
public class LoanNewTypeLazyDataModel extends LazyDataModel<LoanNewType> implements Serializable{
    
    private static final Logger LOGGER = Logger.getLogger(LoanNewTypeLazyDataModel.class);
    private final LoanNewTypeSearchParameter searchParameter;
    private final LoanNewTypeService service;
    private List<LoanNewType> loanNewTypeList = new ArrayList<>();
    private Integer jumlahData;

    public LoanNewTypeLazyDataModel(LoanNewTypeSearchParameter searchParameter, LoanNewTypeService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<LoanNewType> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");
            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("loanTypeCode");
                }
                loanNewTypeList = service.getByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalLoanTypeByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
         LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return loanNewTypeList;
    }
    
    @Override
    public Object getRowKey(LoanNewType loanNewType) {
        return loanNewType.getId();
    }

    @Override
    public LoanNewType getRowData(String id) {
        for (LoanNewType loanNewType : loanNewTypeList) {
            if (id.equals(String.valueOf(loanNewType.getId()))) {
                return loanNewType;
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