/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.ResourceType;
import com.inkubator.hrm.service.ResourceTypeService;
import com.inkubator.hrm.web.search.ResourceTypeSearchParameter;
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
public class ResourceTypeLazyDataModel extends LazyDataModel<ResourceType> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(ResourceTypeLazyDataModel.class);
    private final ResourceTypeSearchParameter searchParameter;
    private final ResourceTypeService service;
    private List<ResourceType> resourceTypeList = new ArrayList<>();
    private Integer jumlahData;

    public ResourceTypeLazyDataModel(ResourceTypeSearchParameter searchParameter, ResourceTypeService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<ResourceType> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("code");
                }
                resourceTypeList = service.getByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalResourceTypeByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return resourceTypeList;
    }
    
    @Override
    public Object getRowKey(ResourceType resourceType) {
        return resourceType.getId();
    }

    @Override
    public ResourceType getRowData(String id) {
        for (ResourceType resourceType : resourceTypeList) {
            if (id.equals(String.valueOf(resourceType.getId()))) {
                return resourceType;
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
