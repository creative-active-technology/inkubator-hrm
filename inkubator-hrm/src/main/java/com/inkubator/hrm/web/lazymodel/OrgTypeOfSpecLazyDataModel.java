/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.OrgTypeOfSpec;
import com.inkubator.hrm.service.OrgTypeOfSpecService;
import com.inkubator.hrm.web.search.OrgTypeOfSpecSearchParameter;
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
 * @author EKA
 */
public class OrgTypeOfSpecLazyDataModel extends LazyDataModel<OrgTypeOfSpec> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(OrgTypeOfSpecLazyDataModel.class);
    private final OrgTypeOfSpecSearchParameter searchParameter;
    private final OrgTypeOfSpecService service;
    private List<OrgTypeOfSpec> orgTypeOfSpecList = new ArrayList<>();
    private Integer jumlah;
    
    public OrgTypeOfSpecLazyDataModel(OrgTypeOfSpecSearchParameter searchParameter, OrgTypeOfSpecService service){
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<OrgTypeOfSpec> load(int first, int pageSize, String sortField, org.primefaces.model.SortOrder sortOrder, Map<String, Object> filters){
        LOGGER.info("Step Load Lazy Data Model");
        
            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                } else {
                    order = Order.desc("name");
                }
                orgTypeOfSpecList = service.getByParam(searchParameter, first, pageSize, order);
                jumlah = Integer.parseInt(String.valueOf(service.getTotalOrgTypeOfSpecByParam(searchParameter)));
            } catch (Exception ex){
                LOGGER.error("Error", ex);
            }
            
        LOGGER.info("Success Load Lazy Data Model");
        
        setPageSize(pageSize);
        setRowCount(jumlah);
        return orgTypeOfSpecList;
    }
    
    @Override
    public OrgTypeOfSpec getRowData(String id){
        for (OrgTypeOfSpec orgTypeOfSpec : orgTypeOfSpecList){
            if(id.equals(String.valueOf(orgTypeOfSpec.getId()))){
                return orgTypeOfSpec;
            }
        }
        return null;
    }
    
    @Override
    public void setRowIndex(int rowIndex){
        if (rowIndex == -1 || getPageSize() == 0){
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }
    
}
    
