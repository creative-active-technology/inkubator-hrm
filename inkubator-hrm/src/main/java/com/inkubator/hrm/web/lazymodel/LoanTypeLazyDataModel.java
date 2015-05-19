/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.LoanType;
import com.inkubator.hrm.service.LoanTypeService;
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
 * @author Ahmad Mudzakkir Amal
 */
public class LoanTypeLazyDataModel extends LazyDataModel<LoanType> implements Serializable{
    
    private static final Logger LOGGER = Logger.getLogger(LoanTypeLazyDataModel.class);
    private final LoanTypeSearchParameter searchParameter;
    private final LoanTypeService service;
    private List<LoanType> loanTypeList = new ArrayList<>();
    private Integer jumlahData;

    public LoanTypeLazyDataModel(LoanTypeSearchParameter searchParameter, LoanTypeService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<LoanType> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");
            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("loanTypeCode");
                }
                loanTypeList = service.getByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalLoanTypeByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
         LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return loanTypeList;
    }
    
    @Override
    public Object getRowKey(LoanType loanType) {
        return loanType.getId();
    }

    @Override
    public LoanType getRowData(String id) {
        for (LoanType loanType : loanTypeList) {
            if (id.equals(String.valueOf(loanType.getId()))) {
                return loanType;
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