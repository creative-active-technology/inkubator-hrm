/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.BioPotensiSwot;
import com.inkubator.hrm.entity.SavingType;
import com.inkubator.hrm.service.BioPotensiSwotService;
import com.inkubator.hrm.web.search.BioPotensiSwotSearchParameter;
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
public class BioPotensiSwotLazyDataModel extends LazyDataModel<BioPotensiSwot> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(BioPotensiSwotLazyDataModel.class);
    private final BioPotensiSwotSearchParameter searchParameter;
    private final BioPotensiSwotService service;
    private List<BioPotensiSwot> bioPotensiSwotList = new ArrayList<>();
    private Integer jumlahData;

    public BioPotensiSwotLazyDataModel(BioPotensiSwotSearchParameter searchParameter, BioPotensiSwotService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<BioPotensiSwot> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("name");
                }
                bioPotensiSwotList = service.getByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return bioPotensiSwotList;
    }
    
    @Override
    public Object getRowKey(BioPotensiSwot bioPotensiSwot) {
        return bioPotensiSwot.getId();
    }

    @Override
    public BioPotensiSwot getRowData(String id) {
        for (BioPotensiSwot bioPotensiSwot : bioPotensiSwotList) {
            if (id.equals(String.valueOf(bioPotensiSwot.getId()))) {
                return bioPotensiSwot;
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
