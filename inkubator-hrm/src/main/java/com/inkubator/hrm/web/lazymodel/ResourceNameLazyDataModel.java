/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.ResourceName;
import com.inkubator.hrm.service.ResourceNameService;
import com.inkubator.hrm.web.search.ResourceNameSearchParameter;
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
public class ResourceNameLazyDataModel extends LazyDataModel<ResourceName> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(ResourceNameLazyDataModel.class);
    private final ResourceNameSearchParameter searchParameter;
    private final ResourceNameService service;
    private List<ResourceName> resourceNameList = new ArrayList<>();
    private Integer jumlahData;

    public ResourceNameLazyDataModel(ResourceNameSearchParameter searchParameter, ResourceNameService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<ResourceName> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("code");
                }
                resourceNameList = service.getByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalResourceTypeByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return resourceNameList;
    }
    
    @Override
    public Object getRowKey(ResourceName resourceName) {
        return resourceName.getId();
    }

    @Override
    public ResourceName getRowData(String id) {
        for (ResourceName resourceName : resourceNameList) {
            if (id.equals(String.valueOf(resourceName.getId()))) {
                return resourceName;
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
