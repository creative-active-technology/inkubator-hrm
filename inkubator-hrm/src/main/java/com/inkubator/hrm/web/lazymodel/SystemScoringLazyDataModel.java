/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.SavingType;
import com.inkubator.hrm.entity.SystemScoring;
import com.inkubator.hrm.service.SystemScoringService;
import com.inkubator.hrm.web.search.SystemScoringSearchParameter;
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
public class SystemScoringLazyDataModel extends LazyDataModel<SystemScoring> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(SystemScoringLazyDataModel.class);
    private final SystemScoringSearchParameter searchParameter;
    private final SystemScoringService service;
    private List<SystemScoring> listSystemScoring = new ArrayList<>();
    private Integer jumlahData;

    public SystemScoringLazyDataModel(SystemScoringSearchParameter searchParameter, SystemScoringService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<SystemScoring> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("name");
                }
                listSystemScoring = service.getByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return listSystemScoring;
    }
    
    @Override
    public Object getRowKey(SystemScoring systemScoring) {
        return systemScoring.getId();
    }

    @Override
    public SystemScoring getRowData(String id) {
        for (SystemScoring systemScoring : listSystemScoring) {
            if (id.equals(String.valueOf(systemScoring.getId()))) {
                return systemScoring;
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
