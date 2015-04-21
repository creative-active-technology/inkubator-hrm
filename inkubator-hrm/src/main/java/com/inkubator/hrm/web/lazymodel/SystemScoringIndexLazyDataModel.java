/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.SystemScoring;
import com.inkubator.hrm.entity.SystemScoringIndex;
import com.inkubator.hrm.service.SystemScoringIndexService;
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
public class SystemScoringIndexLazyDataModel extends LazyDataModel<SystemScoringIndex> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(SystemScoringIndexLazyDataModel.class);
    private final SystemScoringIndexService service;
    private List<SystemScoringIndex> listSystemScoringIndex = new ArrayList<>();
    private Integer jumlahData;

    public SystemScoringIndexLazyDataModel(SystemScoringIndexService service) {
        this.service = service;
    }
    
    @Override
    public List<SystemScoringIndex> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.asc("orderScala");
                }
                listSystemScoringIndex = service.getByParam(first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParam()));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return listSystemScoringIndex;
    }
    
    @Override
    public Object getRowKey(SystemScoringIndex systemScoringIndex) {
        return systemScoringIndex.getId();
    }

    @Override
    public SystemScoringIndex getRowData(String id) {
        for (SystemScoringIndex systemScoringIndex : listSystemScoringIndex) {
            if (id.equals(String.valueOf(systemScoringIndex.getId()))) {
                return systemScoringIndex;
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
