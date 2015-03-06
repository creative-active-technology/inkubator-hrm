/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.OhsaCategory;
import com.inkubator.hrm.service.OhsaCategoryService;
import com.inkubator.hrm.web.search.OhsaCategorySearchParameter;
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
public class OhsaCategoryLazyDataModel extends LazyDataModel<OhsaCategory> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(OhsaCategoryLazyDataModel.class);
    private final OhsaCategorySearchParameter searchParameter;
    private final OhsaCategoryService service;
    public List<OhsaCategory> ohsaCategoryList = new ArrayList<>();
    public Integer jumlahData;
    
    public OhsaCategoryLazyDataModel(OhsaCategorySearchParameter searchParameter, OhsaCategoryService service){
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<OhsaCategory> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters){
        LOGGER.info("Step Load Lazy Data Model");
        
        try{
            Order order = null;
            if(sortField != null){
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            } else{
                order = Order.desc("name");
            }
            ohsaCategoryList = service.getByParam(searchParameter, first, pageSize, order);
            jumlahData = Integer.parseInt(String.valueOf(service.getTotalOhsaCategoryByParam(searchParameter)));
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
        LOGGER.info("Success Load Lazy Data Model");
        
        setPageSize(pageSize);
        setRowCount(jumlahData);
        return ohsaCategoryList;
    }
}
