/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.SavingType;
import com.inkubator.hrm.service.SavingTypeService;
import com.inkubator.hrm.web.search.SavingTypeSearchParameter;
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
public class SavingTypeLazyDataModel extends LazyDataModel<SavingType> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(SavingTypeLazyDataModel.class);
    private final SavingTypeSearchParameter searchParameter;
    private final SavingTypeService service;
    private List<SavingType> savingTypeList = new ArrayList<>();
    private Integer jumlahData;

    public SavingTypeLazyDataModel(SavingTypeSearchParameter searchParameter, SavingTypeService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<SavingType> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("name");
                }
                savingTypeList = service.getByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalSavingTypeByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return savingTypeList;
    }
    
    @Override
    public Object getRowKey(SavingType savingType) {
        return savingType.getId();
    }

    @Override
    public SavingType getRowData(String id) {
        for (SavingType savingType : savingTypeList) {
            if (id.equals(String.valueOf(savingType.getId()))) {
                return savingType;
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
