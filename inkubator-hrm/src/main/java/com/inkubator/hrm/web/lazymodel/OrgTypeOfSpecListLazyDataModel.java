/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.service.OrgTypeOfSpecListService;
import com.inkubator.hrm.web.search.OrgTypeOfSpecListSearchParameter;
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
public class OrgTypeOfSpecListLazyDataModel extends LazyDataModel<OrgTypeOfSpecList> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(OrgTypeOfSpecListLazyDataModel.class);
    private final OrgTypeOfSpecListSearchParameter searchParameter;
    private final OrgTypeOfSpecListService service;
    private List<OrgTypeOfSpecList> orgTypeOfSpecList = new ArrayList<>();
    private Integer jumlah;
    
    public OrgTypeOfSpecListLazyDataModel(OrgTypeOfSpecListSearchParameter searchParameter, OrgTypeOfSpecListService service){
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<OrgTypeOfSpecList> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters){
        LOGGER.info("Step Load Lazy Data Model");
        try{
            Order order = null;
            if(sortField != null){
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            } else {
                order = Order.desc("name");
            }
            orgTypeOfSpecList = service.getByParam(searchParameter, first, pageSize, order);
            jumlah = Integer.parseInt(String.valueOf(service.getTotalOrgTypeOfSpecListByParam(searchParameter)));
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
        LOGGER.info("Success Load Lazy Data Model");
        
        setPageSize(pageSize);
        setRowCount(jumlah);
        return orgTypeOfSpecList;
    }
    
    @Override
    public Object getRowKey(OrgTypeOfSpecList orgTypeOfSpecList){
        return orgTypeOfSpecList.getId();
    }
    
    @Override
    public OrgTypeOfSpecList getRowData(String id){
        for(OrgTypeOfSpecList orgSpecList : orgTypeOfSpecList){
            if(id.equals(String.valueOf(orgSpecList.getId()))){
                return orgSpecList;
            }
        }
        return null;
    }
    
    @Override
    public void setRowIndex(int rowIndex){
        if(rowIndex == -1 || getPageSize() == 0){
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }
}
