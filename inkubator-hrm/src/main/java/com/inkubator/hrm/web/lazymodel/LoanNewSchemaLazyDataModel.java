/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.LoanNewSchema;
import com.inkubator.hrm.entity.LoanSchema;
import com.inkubator.hrm.service.LoanNewSchemaService;
import com.inkubator.hrm.service.LoanSchemaService;
import com.inkubator.hrm.web.search.LoanNewSchemaSearchParameter;
import com.inkubator.hrm.web.search.LoanSchemaSearchParameter;
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
public class LoanNewSchemaLazyDataModel extends LazyDataModel<LoanNewSchema> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(LoanNewSchemaLazyDataModel.class);
    private final LoanNewSchemaSearchParameter searchParameter;
    private final LoanNewSchemaService service;
    private List<LoanNewSchema> loanSchemaList = new ArrayList<>();
    private Integer jumlahData;

    public LoanNewSchemaLazyDataModel(LoanNewSchemaSearchParameter searchParameter, LoanNewSchemaService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<LoanNewSchema> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");
            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("loanSchemaCode");
                }
                loanSchemaList = service.getAllDataByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalDataByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
         LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return loanSchemaList;
    }
    
    @Override
    public Object getRowKey(LoanNewSchema loanNewSchema) {
        return loanNewSchema.getId();
    }

    @Override
    public LoanNewSchema getRowData(String id) {
        for (LoanNewSchema loanNewSchema : loanSchemaList) {
            if (id.equals(String.valueOf(loanNewSchema.getId()))) {
                return loanNewSchema;
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