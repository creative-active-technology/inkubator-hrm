/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.service.LoanNewSchemaListOfEmpService;
import com.inkubator.hrm.web.model.LoanNewSchemaListOfEmpViewModel;
import com.inkubator.hrm.web.search.LoanNewSchemaListOfEmpSearchParameter;
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
public class LoanNewSchemaListOfEmpLazyDataModel extends LazyDataModel<LoanNewSchemaListOfEmpViewModel> implements Serializable{
    
    private static final Logger LOGGER = Logger.getLogger(LoanNewSchemaListOfEmpLazyDataModel.class);
    private final LoanNewSchemaListOfEmpSearchParameter searchParameter;
    private final LoanNewSchemaListOfEmpService service;
    private List<LoanNewSchemaListOfEmpViewModel> loanNewSchemaEmpList = new ArrayList<>();
    private Integer jumlahData;

    public LoanNewSchemaListOfEmpLazyDataModel(LoanNewSchemaListOfEmpSearchParameter searchParameter, LoanNewSchemaListOfEmpService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<LoanNewSchemaListOfEmpViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");
            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("firstName");
                }
                loanNewSchemaEmpList = service.getByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
         LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return loanNewSchemaEmpList;
    }
    
    @Override
    public Object getRowKey(LoanNewSchemaListOfEmpViewModel loanNewSchemaListOfEmpViewModel) {
        return loanNewSchemaListOfEmpViewModel.getId();
    }

    @Override
    public LoanNewSchemaListOfEmpViewModel getRowData(String id) {
        for (LoanNewSchemaListOfEmpViewModel loanNewSchemaListOfEmpViewModel : loanNewSchemaEmpList) {
            if (id.equals(String.valueOf(loanNewSchemaListOfEmpViewModel.getId()))) {
                return loanNewSchemaListOfEmpViewModel;
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