/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.CostCenterDept;
import com.inkubator.hrm.service.CostCenterDeptService;
import com.inkubator.hrm.web.search.CostCenterDeptSearchParameter;

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
 * @author rizkykojek
 */
public class CostCenterDeptLazyDataModel extends LazyDataModel<CostCenterDept> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(CostCenterDeptLazyDataModel.class);
    private final CostCenterDeptSearchParameter parameter;
    private final CostCenterDeptService costCenterDeptService;
    private List<CostCenterDept> list = new ArrayList<>();
    private Integer total;
    
    public CostCenterDeptLazyDataModel(CostCenterDeptSearchParameter costCenterSearchParameter, CostCenterDeptService costCenterDeptService) {
        this.parameter = costCenterSearchParameter;
        this.costCenterDeptService = costCenterDeptService;
    }
    
    @Override
    public List<CostCenterDept> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    	LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("createdOn");
	        }
	        
	        list = costCenterDeptService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(costCenterDeptService.getTotalByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return list;
    }
    
    @Override
    public Object getRowKey(CostCenterDept costCenterDept) {
        return costCenterDept.getId();
    }

    @Override
    public CostCenterDept getRowData(String id) {
        for (CostCenterDept costCenterDept : list) {
            if (id.equals(String.valueOf(costCenterDept.getId()))) {
                return costCenterDept;
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
