/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.CostCenter;
import com.inkubator.hrm.service.CostCenterService;
import com.inkubator.hrm.web.search.CostCenterSearchParameter;
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
 * @author deniarianto
 */
public class CostCenterLazyDataModel extends LazyDataModel<CostCenter> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(CostCenterLazyDataModel.class);
    private final CostCenterSearchParameter costCenterSearchParameter;
    private final CostCenterService costCenterService;
    private List<CostCenter> costCenterList = new ArrayList<>();
    private Integer jumlahData;
    
    public CostCenterLazyDataModel(CostCenterSearchParameter costCenterSearchParameter, CostCenterService costCenterService) {
        this.costCenterSearchParameter = costCenterSearchParameter;
        this.costCenterService = costCenterService;
    }
    
    @Override
    public List<CostCenter> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");
            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == sortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("name");
                }
                costCenterList = costCenterService.getByParam(costCenterSearchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(costCenterService.getTotalCostCenterByParam(costCenterSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return costCenterList;
    }
    
    @Override
    public Object getRowKey(CostCenter costCenter) {
        return costCenter.getId();
    }

    @Override
    public CostCenter getRowData(String id) {
        for (CostCenter costCenter : costCenterList) {
            if (id.equals(String.valueOf(costCenter.getId()))) {
                return costCenter;
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
