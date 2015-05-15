/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.BankGroup;
import com.inkubator.hrm.service.BankGroupService;
import com.inkubator.hrm.web.search.BankGroupSearchParameter;
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
public class BankGroupLazyDataModel extends LazyDataModel<BankGroup> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(BankGroupLazyDataModel.class);
    private final BankGroupSearchParameter searchParameter;
    private final BankGroupService service;
    private List<BankGroup> bankGroupList = new ArrayList<>();
    private Integer jumlahData;
    
    public BankGroupLazyDataModel(BankGroupSearchParameter searchParameter, BankGroupService service){
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<BankGroup> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
        LOGGER.info("Step Load Lazy Data Model");
        
            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                } else {
                    order = Order.desc("name");
                }
                bankGroupList = service.getByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalBankGroupByParam(searchParameter)));
            } catch (Exception ex){
                LOGGER.error("Error", ex);
            }
            
        LOGGER.info("Success Load Lazy Data Model");
        
        setPageSize(pageSize);
        setRowCount(jumlahData);
        return bankGroupList;
    }
    
}
