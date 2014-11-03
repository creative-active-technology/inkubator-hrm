/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.BusinessType;
import com.inkubator.hrm.service.BusinessTypeService;
import com.inkubator.hrm.web.search.BusinessTypeSearchParameter;
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
public class BusinessTypeLazyDataModel extends LazyDataModel<BusinessType> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(BusinessTypeLazyDataModel.class);
    private final BusinessTypeSearchParameter searchParameter;
    private final BusinessTypeService service;
    private List<BusinessType> businessTypeList = new ArrayList<>();
    private Integer jumlahData;

    public BusinessTypeLazyDataModel(BusinessTypeSearchParameter searchParameter, BusinessTypeService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<BusinessType> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    businessTypeList = service.getByParam(searchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalBusinessTypeByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    businessTypeList = service.getByParam(searchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalBusinessTypeByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                businessTypeList = service.getByParam(searchParameter, first, pageSize, Order.desc("name"));
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalBusinessTypeByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return businessTypeList;
    }
    
    @Override
    public Object getRowKey(BusinessType businessType) {
        return businessType.getId();
    }

    @Override
    public BusinessType getRowData(String id) {
        for (BusinessType businessType : businessTypeList) {
            if (id.equals(String.valueOf(businessType.getId()))) {
                return businessType;
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
