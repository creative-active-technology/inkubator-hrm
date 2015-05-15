/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.LoanSchema;
import com.inkubator.hrm.service.LoanSchemaService;
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
public class LoanSchemaLazyDataModel extends LazyDataModel<LoanSchema> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(LoanSchemaLazyDataModel.class);
    private final LoanSchemaSearchParameter searchParameter;
    private final LoanSchemaService service;
    private List<LoanSchema> loanSchemaList = new ArrayList<>();
    private Integer jumlahData;

    public LoanSchemaLazyDataModel(LoanSchemaSearchParameter searchParameter, LoanSchemaService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<LoanSchema> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");
            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("code");
                }
                loanSchemaList = service.getAllDataWithAllRelation(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
         LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return loanSchemaList;
    }
    
    @Override
    public Object getRowKey(LoanSchema loanSchema) {
        return loanSchema.getId();
    }

    @Override
    public LoanSchema getRowData(String id) {
        for (LoanSchema loanSchema : loanSchemaList) {
            if (id.equals(String.valueOf(loanSchema.getId()))) {
                return loanSchema;
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